import * as React from 'react';
import { TextField } from '@material-ui/core';

export interface IAppointmentDateProps {
  handleDateChange: any;
}

export interface IAppointmentDateState {
}

export default class AppointmentDate extends React.Component<IAppointmentDateProps, IAppointmentDateState> {
  constructor(props: IAppointmentDateProps) {
    super(props);
  }

  public render() {
    return (
      <TextField
        id="date"
        label="AppointmentDate"
        type="date"
        defaultValue="2016-05-29"
        onChange = {this.props.handleDateChange}
      />
    );
  }
}
