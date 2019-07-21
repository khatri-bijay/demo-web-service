import * as React from 'react';
import { Grid, Button } from '@material-ui/core';
import AppointmentDate from './AppointmentDate';
import { IPet, IVet, IAppointment, ISlot } from '../common/contract/contract';
import { vetService } from '../../service/vet-service';
import appointmentService from '../../service/appointment-service';
import Dropdown from '../common/Dropdown';
import Slot from './Slot';

export interface IBookAppointmentProps {
    pet: IPet,
    onSchedule: (appointment: IAppointment) => void;
}

export interface IBookAppointmentState {
    vets: any,
    vet: IVet,
    availableSlot: any;
    appointment: IAppointment;
    isLoading: boolean;
    date: Date;
}

export default class BookAppointment extends React.Component<IBookAppointmentProps, IBookAppointmentState> {
    constructor(props: IBookAppointmentProps) {
        super(props);

        this.state = {
            vets: [],
            vet: { id: 0, name: 'SELECT ONE', specialty: null },
            availableSlot: [],
            appointment: null,
            isLoading: true,
            date: null
        }

        this.handleDateChange = this.handleDateChange.bind(this);
        this.handleSelectChange = this.handleSelectChange.bind(this);
        this.scheduleHandler = this.scheduleHandler.bind(this);
    }


    private handleDateChange(e) {
        const selectedDate = e.target.value;
        this.setState({
            appointment: {
                ...this.state.appointment,
                date: selectedDate
            }
        });
    }
    private handleSelectChange(e) {
        const selectedVet = e.target.value;
        this.setState({
            appointment: {
                ...this.state.appointment
            },
            vet: selectedVet
        });
        this.loadSlots(selectedVet.id, new Date('2012-09-04'));
    }

    public render() {
        let scheduleNow = <Button variant="contained" onClick={this.scheduleHandler}>Schedule Now</Button>;
        if (this.state.vet.id === 0) {
            <Button variant="contained" onClick={this.scheduleHandler} disabled={true}>Schedule Now</Button>
        }
        return (<form method="Post" onSubmit={event => event.preventDefault()} className="book-appointment-form">
            <Grid container direction="column">
                <Grid item>
                    <div className="book-appointment-title">Book an Appoinement for {this.props.pet.name} </div>
                </Grid>

                <Grid item>
                    <div className="appointment-date-picker">
                        Choose a Date: <AppointmentDate handleDateChange={this.handleDateChange}></AppointmentDate>
                    </div>
                </Grid>

                <Grid item>
                    <Dropdown selectedDefault={this.state.vet} name={'Vets'} options={this.state.vets} handlePetChange={this.handleSelectChange}></Dropdown>
                </Grid>

                <Grid item>
                    <Slot slots={this.state.availableSlot}></Slot>
                </Grid>
                <Grid item>
                    <Grid container direction="row" justify="space-between" className="schedule-appointment-form__footer">
                        <Button variant="contained" onClick={this.cancelHandler}>Cancel</Button>
                        {scheduleNow}
                    </Grid>
                </Grid>
            </Grid>
        </form>);
    }

    public componentDidMount() {
        vetService.getVets()
            .then(vets => this.setState({ 
                    vets: vets, 
                    isLoading: false }
            ));
    }

    private loadSlots(vetId: number, date: Date) {
        appointmentService.getSlots(vetId, date)
            .then((slot: ISlot) => {
                console.log("Slots", slot);
                this.setState({
                    availableSlot: slot.slots,
                    isLoading: false
                });
            });
    }

    public cancelHandler() {

    }

    public scheduleHandler() {
        let appointment: IAppointment = {
            date: new Date(),
            pet: this.props.pet,
            vet: this.state.vet,
            start: '10AM',
            end: '11AM',
            id: 0
        }
        this.props.onSchedule(appointment);
    }


}
