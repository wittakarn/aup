import * as React from 'react';
import { SettingService } from 'services/SettingService';
import { ApiSetting } from 'interfaces/Setting';

interface Props {
}

interface State {
    field: ApiSetting;
}

export class ApiSettingPage extends React.Component<Props, State> {

    private defaultSetting = {
        sheetName: '',
        startRow: 3,
        updateStatus: '',
        sku: '',
        asinApi: '',
        priceApi: '',
        minAllowPrice: '',
        maxAllowPrice: '',
        quantity: '',
        leadTime: '',
        timestamp: '',
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
                <span className="col-2">{key}</span>
                <span className="col-3">
                    <input type="text"
                        name={key}
                        value={this.state.field && this.state.field[key]}
                        onChange={this.handleChange}
                    />
                </span>
                <span className="col-7"></span>
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
        const response = await SettingService.get('apisetting/get');
        this.setState({ field: { ...response.body } });
    }

    private createNewSetting = async () => {
        const response = await SettingService.create(this.state.field, 'apisetting/create');
        alert(response.body.message);
    }

    render() {
        return <React.Fragment>
            {this.generateSettingInput()}
            {this.generateCreateButton()}
        </React.Fragment>
    }
}