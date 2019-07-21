

export interface IApiResponse<T> {
    data: T
    error: any;
}

export interface INameId {
    id: number,
    name: String;
}
export interface ISpecialty extends INameId{}
export interface IPet extends INameId {
    type: INameId;
 }
export interface IVet extends INameId { 
    specialty: ISpecialty
}
export interface ISlot {
    id: number;
    slots: ISlotItem[];
}

export interface ISlotItem {
    start: String;
    end: String
}

export interface IAppointment {
    id: number;
    date: Date;
    petId: number;
    vet: IVet;
}