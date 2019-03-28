import * as React from 'react';
import * as superagent from 'superagent';
import { Setting } from 'interfaces/SettingPage';
import { SettingService } from 'services/SettingService';
import { ControllerService } from 'services/controllerService';

interface Props {
}

interface State {
    field: Setting;
}

export class SettingPage extends React.Component<Props, State> {

    constructor(props: Props) {
        super(props);
        this.getSetting();
        this.state = { field: null };
    }

    private handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { target: { name, value } } = event
        this.setState({ field: { ...this.state.field, [name]: value } });
    }

    private generateSheetIdInput: any = () => {
        return (
            <div className="row">
                <span className="col-2">Sheet id</span>
                <span className="col-5">
                    <input type="text"
                        name="sheetId"
                        value={this.state.field && this.state.field.sheetId}
                        onChange={this.handleChange}
                    />
                </span>
                <span className="col-5"></span>
            </div>
        );
    }

    private generateSpreadTabInput: any = () => {
        return (
            <div className="row">
                <span className="col-2">Sheet tab</span>
                <span className="col-5">
                    <input type="text"
                        name="sheetTab"
                        value={this.state.field && this.state.field.sheetTab}
                        onChange={this.handleChange}
                    />
                </span>
                <span className="col-5"></span>
            </div>
        );
    }

    private generateStartRowInput: any = () => {
        return (
            <div className="row">
                <span className="col-2">Start row</span>
                <span className="col-5">
                    <input
                        type="text"
                        name="startRow"
                        value={this.state.field && this.state.field.startRow}
                        onChange={this.handleChange}
                    />
                </span>
                <span className="col-5"></span>
            </div>
        );
    }

    private generateStaffNameColumnInput: any = () => {
        return (
            <div className="row">
                <span className="col-2">Staff name column</span>
                <span className="col-5">
                    <input
                        type="text"
                        name="staffName"
                        value={this.state.field && this.state.field.staffName}
                        onChange={this.handleChange}
                    />
                </span>
                <span className="col-5"></span>
            </div>
        );
    }

    private generateVendorColumnInput: any = () => {
        return (
            <div className="row">
                <span className="col-2">Vendor column</span>
                <span className="col-5">
                    <input type="text"
                        name="vendor"
                        value={this.state.field && this.state.field.vendor}
                        onChange={this.handleChange}
                    />
                </span>
                <span className="col-5"></span>
            </div>
        );
    }

    private generateShippingNameColumnInput: any = () => {
        return (
            <div className="row">
                <span className="col-2">Shipping name column</span>
                <span className="col-5">
                    <input
                        type="text"
                        name="shippingName"
                        value={this.state.field && this.state.field.shippingName}
                        onChange={this.handleChange}
                    />
                </span>
                <span className="col-5"></span>
            </div>
        );
    }

    private generateshippingLastnameColumnInput: any = () => {
        return (
            <div className="row">
                <span className="col-2">Shipping last name column</span>
                <span className="col-5">
                    <input
                        type="text"
                        name="shippingLastname"
                        value={this.state.field && this.state.field.shippingLastname}
                        onChange={this.handleChange}
                    />
                </span>
                <span className="col-5"></span>
            </div>
        );
    }

    private generateAddress1ColumnInput: any = () => {
        return (
            <div className="row">
                <span className="col-2">Address 1 column</span>
                <span className="col-5">
                    <input
                        type="text"
                        name="address1"
                        value={this.state.field && this.state.field.address1}
                        onChange={this.handleChange}
                    />
                </span>
                <span className="col-5"></span>
            </div>
        );
    }

    private generateAddress2ColumnInput: any = () => {
        return (
            <div className="row">
                <span className="col-2">Address 2 column</span>
                <span className="col-5">
                    <input
                        type="text"
                        name="address2"
                        value={this.state.field && this.state.field.address2}
                        onChange={this.handleChange}
                    />
                </span>
                <span className="col-5"></span>
            </div>
        );
    }

    private generateCityColumnInput: any = () => {
        return (
            <div className="row">
                <span className="col-2">City column</span>
                <span className="col-5">
                    <input
                        type="text"
                        name="city"
                        value={this.state.field && this.state.field.city}
                        onChange={this.handleChange}
                    />
                </span>
                <span className="col-5"></span>
            </div>
        );
    }

    private generateStateColumnInput: any = () => {
        return (
            <div className="row">
                <span className="col-2">State column</span>
                <span className="col-5">
                    <input
                        type="text"
                        name="state"
                        value={this.state.field && this.state.field.state}
                        onChange={this.handleChange}
                    />
                </span>
                <span className="col-5"></span>
            </div>
        );
    }

    private generateZipColumnInput: any = () => {
        return (
            <div className="row">
                <span className="col-2">Zip column</span>
                <span className="col-5">
                    <input
                        type="text"
                        name="zip"
                        value={this.state.field && this.state.field.zip}
                        onChange={this.handleChange}
                    />
                </span>
                <span className="col-5"></span>
            </div>
        );
    }

    private generatePhoneColumnInput: any = () => {
        return (
            <div className="row">
                <span className="col-2">Phone column</span>
                <span className="col-5">
                    <input
                        type="text"
                        name="phone"
                        value={this.state.field && this.state.field.phone}
                        onChange={this.handleChange}
                    />
                </span>
                <span className="col-5"></span>
            </div>
        );
    }

    private generateToBuyColumnInput: any = () => {
        return (
            <div className="row">
                <span className="col-2">To buy column</span>
                <span className="col-5">
                    <input
                        type="text"
                        name="toBuy"
                        value={this.state.field && this.state.field.toBuy}
                        onChange={this.handleChange}
                    />
                </span>
                <span className="col-5"></span>
            </div>
        );
    }

    private generatePayToVendorColumnInput: any = () => {
        return (
            <div className="row">
                <span className="col-2">Pay to vendor column</span>
                <span className="col-5">
                    <input
                        type="text"
                        name="payToVendor"
                        value={this.state.field && this.state.field.payToVendor}
                        onChange={this.handleChange}
                    />
                </span>
                <span className="col-5"></span>
            </div>
        );
    }

    private generateOrderProcessColumnInput: any = () => {
        return (
            <div className="row">
                <span className="col-2">Order process column</span>
                <span className="col-5">
                    <input
                        type="text"
                        name="orderProcess"
                        value={this.state.field && this.state.field.orderProcess}
                        onChange={this.handleChange}
                    />
                </span>
                <span className="col-5"></span>
            </div>
        );
    }

    private generateVendorIdColumnInput: any = () => {
        return (
            <div className="row">
                <span className="col-2">Vendor id column</span>
                <span className="col-5">
                    <input
                        type="text"
                        name="vendorId"
                        value={this.state.field && this.state.field.vendorId}
                        onChange={this.handleChange}
                    />
                </span>
                <span className="col-5"></span>
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

    private getSetting = () => {
        const promise: Promise<superagent.Response> = SettingService.get();
        promise.then(response => this.setState({ field: { ...response.body } }));
    }

    private createNewSetting = () => {
        const promise: Promise<superagent.Response> = SettingService.create(this.state.field);
        promise.then(response => alert(response.body.message));
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
            {this.generateSheetIdInput()}
            {this.generateSpreadTabInput()}
            {this.generateStartRowInput()}
            {this.generateStaffNameColumnInput()}
            {this.generateVendorColumnInput()}
            {this.generateShippingNameColumnInput()}
            {this.generateshippingLastnameColumnInput()}
            {this.generateAddress1ColumnInput()}
            {this.generateAddress2ColumnInput()}
            {this.generateCityColumnInput()}
            {this.generateStateColumnInput()}
            {this.generateZipColumnInput()}
            {this.generatePhoneColumnInput()}
            {this.generateToBuyColumnInput()}
            {this.generatePayToVendorColumnInput()}
            {this.generateOrderProcessColumnInput()}
            {this.generateVendorIdColumnInput()}
            {this.generateCreateButton()}
            <hr/>
            {this.generateControlButton()}
        </React.Fragment>
    }
}