import { loggerService } from './logger-service';
import apiConfig from '../config/app.config';
import { IVet } from '../components/common/contract/contract';

const baseApi = apiConfig.apiBase;

export const vetService = {
    getVets: () => fetch(`${baseApi}/vets`)
                 .then(res => res.json())
                 .then(res => res.data)
                 .catch(error => loggerService.log(error)),
    getSpecialties: () => fetch(`${baseApi}/vets/specialties`)
                        .then(res => res.json())
                        .then(res => res.data)
                        .catch(error => loggerService.log(error)),
    addVet: (vet: IVet) => fetch(`${baseApi}/vets`, {
        method: 'post',
        body: JSON.stringify(vet),
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }
    })
        .then(res => res.json())
        .then(res => res.data)
        .catch(error => loggerService.log(error))
                        
}