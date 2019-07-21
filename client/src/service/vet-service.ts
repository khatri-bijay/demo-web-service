import { IVet } from '../components/common/contract/contract';
import HttpBaseClient from './http-base-service';

const vetHttpClient = new HttpBaseClient();
export const vetService = {
    getVets: () => vetHttpClient.get('vets'),
    getSpecialties: () => vetHttpClient.get('vets/specialties'),
    addVet: (vet: IVet) => vetHttpClient.post('vets', vet)                   
}