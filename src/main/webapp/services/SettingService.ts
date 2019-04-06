import * as superagent from 'superagent';
import { DataSetting } from 'interfaces/DataSettingPage';

export namespace SettingService {
    export async function get() {
        const agent = superagent['get']('/aup/rest/datasetting/get');
        return await agent.type('application/json')
    };

    export async function create(setting: DataSetting): Promise<superagent.Response> {
        const agent = superagent['post']('/aup/rest/datasetting/create');
        return await agent.type('application/json').send(setting);
    };
}