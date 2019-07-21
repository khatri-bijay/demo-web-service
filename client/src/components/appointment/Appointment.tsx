import * as React from 'react';

import { IPet, IVet, IAppointment } from '../common/contract/contract';
import BookAppointment from './BookAppointment';
import appointmentService from '../../service/appointment-service';
import AppointmentListItem from './AppointmentList';
import Search from '../common/Search';

export interface IAppointmentProps {
	location: {
		appointmentProps: {
			vet: IVet,
			pet: IPet
		}
	}
}

export interface IAppointmentState {
	pet: IPet;
	vet: IVet;
	appointments:any;
	isError: boolean;
}

export default class Appointment extends React.Component<IAppointmentProps, IAppointmentState> {
	constructor(props: IAppointmentProps) {
		super(props);
		console.log('Appointment Props', props.location.appointmentProps);
		this.state = {
			vet: props.location.appointmentProps && props.location.appointmentProps.vet,
			pet: props.location.appointmentProps && props.location.appointmentProps.pet,
			appointments : [],
			isError: false
		};
		this.handleSchedule = this.handleSchedule.bind(this);
	}

	public render() {
		let elem, title;
		if (!!this.state.vet) {
			title = <h4> Available Appointments </h4>;
			elem = this.state.appointments.map((appointment, index) => <AppointmentListItem key= {index} Appointment = {appointment} />);
		} else if(!!this.state.pet) {
			elem = <BookAppointment pet = {this.state.pet} onSchedule= { this.handleSchedule } />
		}
		else elem = <h2>Nothing here</h2>;
		if(this.state.isError) {
			title = <div className='appointment-conflict-error'> Appointment can't be scheduled.</div>;
		}
		return (
			<div className="appointment-container">
				{ title }
				<Search />
				{ elem }
			</div>)
	}

	public handleSchedule(appointment: IAppointment) {
		appointmentService.addAppointment(appointment)
    		.then(() => {
				this.setState({ isError: false});
			}).catch(() => {
				this.setState({ isError: true});
			});
	}

	public componentDidMount() {
		if(!!this.state.vet) {
			this.getAppointments(this.state.vet.id);
		}
	}

	private getAppointments(vetId: number) {
		appointmentService.getVetAppointments(vetId)
		    .then(appointments => {
				console.log("Appointments", appointments);
				this.setState({ appointments });
			});
		}
	}
