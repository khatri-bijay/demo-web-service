import * as React from 'react';

import Dropdown from '../common/Dropdown';
import { Grid, Button } from '@material-ui/core';
import Slot from './Slot';

import appointmentService from '../../service/appointment-service';
import { ISlot, IPet, IVet, IAppointment } from '../common/contract/contract';
import AppointmentDate from './AppointmentDate';
import { vetService } from '../../service/vet-service';

export interface IAppointmentProps {
	location: {
		appointmentProps: IPet
	}
}

export interface IAppointmentState {
	availableSlot: any;
	vets: IVet[];
	isLoading: boolean;
	isError: boolean;
	appointment: IAppointment;
	selectedVet: any;
	selectedPet: any;
	date: Date,
}

export default class Appointment extends React.Component<IAppointmentProps, IAppointmentState> {
	constructor(props: IAppointmentProps) {
		super(props);
		console.log('Appointment Props', props.location.appointmentProps);
		this.state = {
			date: null,
			vets: [],
			isLoading: true,
			isError: false,
			appointment: null,
			availableSlot: [],
			selectedVet: null,
			selectedPet: props.location.appointmentProps
		};

		this.handleSubmit = this.handleSubmit.bind(this);
		this.handleSelectChange = this.handleSelectChange.bind(this);
		this.handleDateChange = this.handleDateChange.bind(this);
		this.scheduleHandler = this.scheduleHandler.bind(this);
	}

	public componentDidMount() {
		vetService.getVets()
			.then(vets => this.setState({ vets, isLoading: false, selectedVet: vets[0] }));
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
		let appointment : IAppointment = {
			date: new Date(),
			petId: this.state.selectedPet.id,
			vet: this.state.selectedVet,
			id: 0
		}
		console.log('Appointment', appointment);
		appointmentService.addAppointment(appointment)
		.then(() => console.log('Appointment added successfully'));
	}

	public render() {
		if (this.state.isLoading) {
			return <p>Loading ...</p>;
		}

		return (
			<form method="Post" onSubmit={event => event.preventDefault()}>
				<Grid container direction="column">
					<Grid item>
						<h3>Book an Appoinement for {this.state.selectedPet.name} </h3>
					</Grid>
					<Grid item container direction="row" justify="space-between" alignItems="center">
						<Grid item>
							Choose a Date: <AppointmentDate handleDateChange={this.handleDateChange}></AppointmentDate>
						</Grid>

						<Grid item>
							<Dropdown selectedDefault={this.state.selectedVet} name={'Vets'} options={this.state.vets} handlePetChange={this.handleSelectChange}></Dropdown>
						</Grid>
					</Grid>
					<Grid item>
						<Slot slots={this.state.availableSlot}></Slot>
					</Grid>
					<Grid item>
						<Grid container direction="row" justify="space-between" className="schedule-appointment-form__footer">
							<Button variant="contained" onClick={this.cancelHandler}>Cancel</Button>
							<Button variant="contained" onClick={this.scheduleHandler}>Schedule Now</Button>
						</Grid>
					</Grid>
				</Grid>
			</form>
		);
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

	private handleSubmit(e: Event) {
		e.preventDefault();
		console.log('submit', this.state.appointment)
	}

	private handleSelectChange(e) {
		const selectedVet = e.target.value;
		this.setState({
			appointment: {
				...this.state.appointment
			},
			selectedVet: selectedVet
		});
		this.loadSlots(selectedVet.id, new Date('2012-09-04'));
	}
}
