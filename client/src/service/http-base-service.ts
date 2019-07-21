import { loggerService } from './logger-service';
import apiConfig from '../config/app.config';

const baseApi = apiConfig.apiBase;
export default class HttpBaseClient {
    public get<T>(uri: String): Promise<T> {
        return fetch(`${baseApi}/${uri}`)
            .then(res => res.json())
            .then(res => res.data)
            .catch(error => loggerService.log(error))
    }

    public post<T>(uri: String, data: T): Promise<T> {
        return fetch(`${baseApi}/${uri}`, {
            method: 'post',
            body: JSON.stringify(data),
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then(res => res.json())
            .then(res => {
                if (!!res.error) {
                    throw new Error(res.error);
                }
                return res.data;
            })
            .catch(error => {
                loggerService.log(error);
                throw error;
            })
    }

}