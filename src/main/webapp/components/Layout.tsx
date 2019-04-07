import * as React from 'react';
import { DataSettingPage } from './setting-page/DataSettingPage';
import { ApiSettingPage } from './setting-page/ApiSettingPage';

import './grid.scss';
import { SheetSettingPage } from './setting-page/SheetSetting';

interface Props {
}

export class Layout extends React.Component<Props, {}> {

    constructor(props: Props) {
        super(props);
    }

    render() {
        return (
            <div className="container container--center" >
                <SheetSettingPage />
                <DataSettingPage />
                <ApiSettingPage />
            </div>
        );
    }

}