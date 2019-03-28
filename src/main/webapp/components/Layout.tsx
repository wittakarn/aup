import * as React from 'react';
import { SettingPage } from './setting-page/SettingPage';

import './grid.scss';
import { UserSettingPage } from './setting-page/UserSettingPage';

interface Props {
}

export class Layout extends React.Component<Props, {}> {

    constructor(props: Props) {
        super(props);
    }

    render() {
        return (
            <div className="container container--center" >
                <UserSettingPage />
                <SettingPage />
            </div>
        );
    }

}