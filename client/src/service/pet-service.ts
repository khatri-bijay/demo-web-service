import { loggerService } from './logger-service';
import apiConfig from '../config/app.config';

const baseApi = apiConfig.apiBase;

export const petService = {
    getPets: () => fetch(`${baseApi}/pets`)
                 .then(res => res.json())
                 .then(res => res.data)
                 .catch(error => loggerService.log(error))
}