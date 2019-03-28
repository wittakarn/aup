import * as superagent from 'superagent';
export namespace ControllerService {
    export async function start(): Promise<superagent.Response> {
        const agent = superagent['post']('/aup/rest/controller/processrequest');
        return await agent.type('application/json').send('start');
    };

    export async function stop(): Promise<superagent.Response> {
        const agent = superagent['post']('/aup/rest/controller/processrequest');
        return await agent.type('application/json').send('stop');
    };
}