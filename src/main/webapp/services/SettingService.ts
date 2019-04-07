import * as superagent from 'superagent';

export namespace SettingService {
    export async function get(endpoint: string) {
        const agent = superagent['get'](`/aup/rest/${endpoint}`);
        return await agent.type('application/json')
    };

    export async function create(setting: any, endpoint: string): Promise<superagent.Response> {
        const agent = superagent['post'](`/aup/rest/${endpoint}`);
        return await agent.type('application/json').send(setting);
    };
}