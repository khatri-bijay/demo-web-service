import HttpBaseClient from "./http-base-service";
import { IPet } from "../components/common/contract/contract";

const petHttpClient = new HttpBaseClient();
export const PetService = {
    getPets: () => petHttpClient.get('pets'),
    getTypes: () => petHttpClient.get('pets/types'),
    addPet: (pet:IPet) => petHttpClient.post('pets', pet)
}