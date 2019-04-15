import * as superagent from 'superagent';
export namespace ControllerService {
    export async function start(index: number): Promise<superagent.Response> {
        const agent = superagent['post']('/aup/rest/controller/start');
        return await agent.type('application/json').send({
            index,
        });
    };

    export async function stop(): Promise<superagent.Response> {
        const agent = superagent['post']('/aup/rest/controller/stop');
        return await agent.type('application/json').send();
    };
}