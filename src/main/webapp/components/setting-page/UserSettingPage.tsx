import * as React from 'react';
import * as superagent from 'superagent';
import { UserSetting } from 'interfaces/SettingPage';
import { UserSettingService } from 'services/UserSettingService';

interface Props {
}

interface State {
    field: UserSetting;
}

export class UserSettingPage extends React.Component<Props, State> {

    constructor(props: Props) {
        super(props);
        this.getSetting();
        this.state = { field: null };
    }

    private handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { target: { name, value } } = event
        this.setState({ field: { ...this.state.field, [name]: value } });
    }

    private generateAmazonUserInput: any = () => {
        return (
            <div className="row">
                <span className="col-2">Amazon user</span>
                <span className="col-5">
                    <input type="text"
                        name="amazonUser"
                        value={this.state.field && this.state.field.amazonUser}
                        onChange={this.handleChange}
                    />
                </span>
                <span className="col-5"></span>
            </div>
        );
    }

    private generateAmazonPasswordInput: any = () => {
        return (
            <div className="row">
                <span className="col-2">Amazon password</span>
                <span className="col-5">
                    <input type="password"
                        name="amazonPassword"
                        value={this.state.field && this.state.field.amazonPassword}
                        onChange={this.handleChange}
                    />
                </span>
                <span className="col-5"></span>
            </div>
        );
    }

    private generateBefrugalUserInput: any = () => {
        return (
            <div className="row">
                <span className="col-2">Befrugal user</span>
                <span className="col-5">
                    <input type="text"
                        name="befrugalUser"
                        value={this.state.field && this.state.field.befrugalUser}
                        onChange={this.handleChange}
                    />
                </span>
                <span className="col-5"></span>
            </div>
        );
    }

    private generateBefrugalPasswordInput: any = () => {
        return (
            <div className="row">
                <span className="col-2">Befrugal password</span>
                <span className="col-5">
                    <input type="password"
                        name="befrugalPassword"
                        value={this.state.field && this.state.field.befrugalPassword}
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

    private getSetting = () => {
        const promise: Promise<superagent.Response> = UserSettingService.get();
        promise.then(response => {
            if (response.body) {
                this.setState({ field: { ...response.body } })
            }
        });
    }

    private createNewSetting = () => {
        const promise: Promise<superagent.Response> = UserSettingService.create(this.state.field);
        promise.then(response => alert(response.body.message));
    }

    render() {
        return this.state.field ? <React.Fragment>
            {this.generateAmazonUserInput()}
            {this.generateAmazonPasswordInput()}
            {this.generateBefrugalUserInput()}
            {this.generateBefrugalPasswordInput()}
            {this.generateCreateButton()}
            <hr/>
        </React.Fragment> : null;
    }
}