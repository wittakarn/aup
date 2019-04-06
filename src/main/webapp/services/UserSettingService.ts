import * as superagent from 'superagent';
import { DataSetting } from 'interfaces/DataSettingPage';

export namespace UserSettingService {
    export async function get() {
        const agent = superagent['get']('/aup/rest/usersetting/get');
        return await agent.type('application/json')
    };

    export async function create(setting: DataSetting): Promise<superagent.Response> {
        const agent = superagent['post']('/aup/rest/usersetting/create');
        return await agent.type('application/json').send(setting);
    };
}