import { loggerService } from './logger-service';
import apiConfig from '../config/app.config';
import { IApiResponse as ApiResponse, ISlot as Slot, IAppointment } from '../components/common/contract/contract';

const baseApi = apiConfig.apiBase;

const appointmentService = {
    getSlots: (vetId: number, date: Date) => fetch(`${baseApi}/vets/${vetId}/appointments/slots?date=${date}`)
                        .then(res => res.json())
                        .then((res: ApiResponse<Slot>) => res.data)
                        .catch(error => loggerService.log(error)),
    addAppointment: (appoinment:IAppointment) =>  fetch(`${baseApi}/pets/${appoinment.petId}/appointments`, {
        method: 'post',
        body: JSON.stringify(appoinment),
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }
    })
        .then(res => res.json())
        .then(res => res.data)
        .catch(error => loggerService.log(error))
}

export default appointmentService;