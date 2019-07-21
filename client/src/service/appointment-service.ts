import { IAppointment } from '../components/common/contract/contract';
import HttpBaseClient from './http-base-service';

const appointmentHttpClient = new HttpBaseClient();
const createCommand = function(appointment) {
    return {
        "date": appointment.date,
        "vet": {
          "id": appointment.vet.id
        },
        "start": appointment.start,
        "end": appointment.start
      }
}

const appointmentService = {
    getSlots: (vetId: number, date: Date) => appointmentHttpClient.get(`vets/${vetId}/appointments/slots?date=${date}`),
    addAppointment: (appoinment:IAppointment) =>  appointmentHttpClient.post(`pets/${appoinment.pet.id}/appointments`, createCommand(appoinment)),
    getVetAppointments: (vetId: number) => appointmentHttpClient.get(`vets/${vetId}/appointments`)
}

export default appointmentService;