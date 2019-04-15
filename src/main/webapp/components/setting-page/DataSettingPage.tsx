import * as React from 'react';
import { DataSetting } from 'interfaces/Setting';
import { SettingService } from 'services/SettingService';

interface Props {
}

interface State {
    field: DataSetting;
}

export class DataSettingPage extends React.Component<Props, State> {

    private defaultSetting = {
        sheetName: '',
        startRow: 3,
        link: '',
        asin: '',
        oldData: '',
        newData: '',
        asinCrawling: '',
        sellerName1: '',
        price1: '',
        shipping1: '',
        addOn1: '',
        type1: '',
        wid1: '',
        sellerName2: '',
        price2: '',
        shipping2: '',
        addOn2: '',
        type2: '',
        wid2: '',
        sellerName3: '',
        price3: '',
        shipping3: '',
        addOn3: '',
        type3: '',
        wid3: '',
    };

    constructor(props: Props) {
        super(props);
        this.state = {
            field: this.defaultSetting,
        }
    }

    componentDidMount() {
        this.getSetting();
    }

    private handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { target: { name, value } } = event
        this.setState({ field: { ...this.state.field, [name]: value } });
    }

    private generateSettingInput = () => {
        return Object.keys(this.defaultSetting).map(p => this.generateInputText(p));
    }

    private generateInputText: any = (key: string) => {
        return (
            <div className="row">
                <span className="col col-2">{key}</span>
                <span className="col col-3">
                    <input type="text"
                        name={key}
                        value={this.state.field && this.state.field[key]}
                        onChange={this.handleChange}
                    />
                </span>
                <span className="col col-7"></span>
            </div>
        );
    }

    private generateCreateButton: any = () => {
        return (
            <div className="row">
                <button type="button" onClick={this.createNewSetting}>Submit</button>
            </div>
        );
    }

    private getSetting = async () => {
        const response = await SettingService.get('datasetting/get');
        this.setState({ field: { ...response.body } });
    }

    private createNewSetting = async () => {
        const response = await SettingService.create(this.state.field, 'datasetting/create');
        alert(response.body.message);
    }

    render() {
        return <React.Fragment>
            {this.generateSettingInput()}
            {this.generateCreateButton()}
        </React.Fragment>
    }
}