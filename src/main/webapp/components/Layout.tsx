import * as React from 'react';
import { DataSettingPage } from './setting-page/DataSettingPage';

import './grid.scss';

interface Props {
}

export class Layout extends React.Component<Props, {}> {

    constructor(props: Props) {
        super(props);
    }

    render() {
        return (
            <div className="container container--center" >
                <DataSettingPage />
            </div>
        );
    }

}