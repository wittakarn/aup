import * as React from 'react';
import { SettingService } from 'services/SettingService';
import { SheetSetting } from 'interfaces/Setting';
import { NumberUtil } from 'utils/NumberUtil';
import { ControllerService } from 'services/ControllerService';
import { SuperAgent } from 'superagent';

interface Props {
}

interface State {
    sheetCount: string;
    field: SheetSetting[];
}

export class SheetSettingPage extends React.Component<Props, State> {

    private sheetCountRef: React.RefObject<HTMLInputElement>;

    constructor(props: Props) {
        super(props);
        this.state = {
            sheetCount: '',
            field: [],
        }
        this.sheetCountRef = React.createRef();
    }

    componentDidMount() {
        this.getSetting();
    }



    private updateSheetCount = () => {
        const sheetCount = this.sheetCountRef.current.value;
        if (sheetCount) {
            const fields = [];
            for (let i = 0; i < NumberUtil.tryParseInt(sheetCount); i += 1) {
                fields.push({
                    sheetSettingPK: {
                        seq: i + 1,
                    },
                    sheetId: this.state.field[i] ? this.state.field[i].sheetId : '',
                });
            }
            this.setState({ field: fields });
        }
    }

    private handleSheetCountChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { target: { value } } = event;
        const sheetCount = NumberUtil.tryParseInt((value))
        this.setState({ sheetCount: sheetCount ? value : '' });
    }

    private handleTextChange = (event: React.ChangeEvent<HTMLInputElement>, seq: number) => {
        const { target: { value } } = event
        const sheets = [...this.state.field];
        sheets[seq - 1].sheetId = value;
        this.setState({ field: sheets });
    }

    private generateSheetSettingInput = () => {
        return this.state.field ? this.state.field.map(this.generateInputText) : null;
    }

    private generateSheetCountInputText = () => {
        return (
            <div className="row">
                <span className="col-2">จำนวนชีท</span>
                <span className="col-2">
                    <input type="text" ref={this.sheetCountRef} onChange={this.handleSheetCountChange} value={this.state.sheetCount} />
                </span>
                <span className="col-1">
                    <button type="button" onClick={this.updateSheetCount}>ยืนยัน</button>
                </span>
                <span className="col-7"></span>
            </div>
        );
    }

    private generateInputText = (sheet: SheetSetting) => {
        return (
            <div className="row">
                <span className="col-2">Sheet number {sheet.sheetSettingPK.seq}</span>
                <span className="col-3">
                    <input type="text"
                        data-index={sheet.sheetSettingPK.seq}
                        value={sheet.sheetId}
                        onChange={e => this.handleTextChange(e, sheet.sheetSettingPK.seq)}
                    />
                </span>
                <span className="col-1">
                    <button type="button" onClick={() => this.start(sheet.sheetSettingPK.seq - 1)}>ปรับราคา</button>
                </span>
                <span className="col-6"></span>
            </div>
        );
    }

    private generateCreateButton = () => {
        return (
            <div className="row">
                <button type="button" onClick={this.createNewSetting}>Submit</button>
            </div>
        );
    }

    private getSetting = async () => {
        const response = await SettingService.get('sheetsetting/get');
        const sheetSetting = response.body ? response.body : [];
        this.setState({
            sheetCount: sheetSetting.length,
            field: sheetSetting,
        });
    }

    private createNewSetting = async () => {
        const response = await SettingService.create(this.state.field, 'sheetsetting/create');
        alert(response.body.message);
    }

    private start = async (index: number) => {
        const response = await ControllerService.start(index);
        alert(response.body.message);
    }

    render() {
        return <React.Fragment>
            {this.generateSheetCountInputText()}
            {this.generateSheetSettingInput()}
            {this.generateCreateButton()}
        </React.Fragment>
    }
}