import * as superagent from 'superagent';
import { Setting } from 'interfaces/SettingPage';

export namespace SettingService {
    export async function get() {
        const agent = superagent['get']('/aup/rest/setting/get');
        return await agent.type('application/json')
    };

    export async function create(setting: Setting): Promise<superagent.Response> {
        const agent = superagent['post']('/aup/rest/setting/create');
        return await agent.type('application/json').send(setting);
    };
}