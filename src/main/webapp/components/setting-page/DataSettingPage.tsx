import * as React from 'react';
import * as superagent from 'superagent';
import { DataSetting } from 'interfaces/DataSettingPage';
import { SettingService } from 'services/SettingService';
import { ControllerService } from 'services/controllerService';

interface Props {
}

interface State {
    field: DataSetting;
}

export class DataSettingPage extends React.Component<Props, State> {

    private defaultSetting = {
        link: '',
        asin: '',
        oldData: '',
        newData: '',
        asinCrawling: '',
        reviewScore: '',
        review: '',
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
        updateStatus: '',
        sku: '',
        asinApi: '',
        priceApi: '',
        minAllowPrice: '',
        maxAllowPrice: '',
        quantity: '',
        leadTime: '',
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

    private generateDataSettingInput = () => {
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

    private generateControlButton: any = () => {
        return (
            <div className="row">
                <button type="button" onClick={this.start}>Start</button>
                <button type="button" onClick={this.stop}>Stop</button>
            </div>
        );
    }

    private getSetting = async () => {
        const response = await SettingService.get();
        this.setState({ field: { ...response.body } });
    }

    private createNewSetting = async () => {
        const response = await SettingService.create(this.state.field);
        alert(response.body.message);
    }

    private start = () => {
        const promise: Promise<superagent.Response> = ControllerService.start();
        promise.then(response => alert(response.body.message));
    }

    private stop = () => {
        const promise: Promise<superagent.Response> = ControllerService.stop();
        promise.then(response => alert(response.body.message));
    }

    render() {
        return <React.Fragment>
            {this.generateDataSettingInput()}
            {this.generateCreateButton()}
            <hr />
            {this.generateControlButton()}
        </React.Fragment>
    }
}