import * as React from 'react';
//import { DatePicker } from "@material-ui/pickers";
import DateFnsUtils from "@date-io/date-fns"; // choose your lib
import {
	DatePicker,
	TimePicker,
	DateTimePicker,
	MuiPickersUtilsProvider,
} from "@material-ui/pickers";
import { TextField } from '@material-ui/core';

export interface IAppointmentDateProps {
	handleDateChange: any;
}

export interface IAppointmentDateState {
	date: Date
}

export default class AppointmentDate extends React.Component<IAppointmentDateProps, IAppointmentDateState> {
	constructor(props: IAppointmentDateProps) {
		super(props);
		this.state = {
			date: new Date('2016-05-29')
		}

		this.handleDateChange = this.handleDateChange.bind(this);
	}

	public handleDateChange(date) {
		this.setState({ date});
		this.props.handleDateChange(date);
	}

	public render() {
		return (
			// <TextField
			//   id="date"
			//   label="AppointmentDate"
			//   type="date"
			//   defaultValue="2016-05-29"
			//   onChange = {this.props.handleDateChange}
			// />
			<MuiPickersUtilsProvider utils={DateFnsUtils}>
				<DatePicker
					label="Appointment Date"
					value={this.state.date}
					onChange={this.handleDateChange}
					animateYearScrolling
				/>
			</MuiPickersUtilsProvider>
		);
	}
}
