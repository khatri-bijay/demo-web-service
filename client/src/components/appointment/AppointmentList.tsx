import * as React from 'react';
import { IAppointment } from '../common/contract/contract';
import { Grid, Button } from '@material-ui/core';

import './Appointment.scss';

export interface IAppointmentListItemProps {
	Appointment: IAppointment;
}

export interface IAppointmentListItemState {
}

export default class AppointmentListItem extends React.Component<IAppointmentListItemProps, IAppointmentListItemState> {
	constructor(props: IAppointmentListItemProps) {
		super(props);
	}

	public render() {
		return (
			<Grid container direction="row" justify="space-between" alignItems="center" className="appointment-list-item">
				<Grid item>{this.props.Appointment.id}</Grid>
				<Grid item>{this.props.Appointment.pet.name}</Grid>
				<Grid item>{this.props.Appointment.start}</Grid>
				<Grid item>{this.props.Appointment.end}</Grid>
				<Grid item>
					{this.props.children}&nbsp;&nbsp;
					<Button variant="outlined">Reschedule</Button>&nbsp;&nbsp;
      				<Button variant="outlined" color="secondary">Cancel</Button>
				</Grid>
			</Grid>);
	}
}
