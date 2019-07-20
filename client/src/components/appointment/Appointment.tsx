import * as React from 'react';

export interface IAppointmentProps {
}

export interface IAppointmentState {
}

export default class Appointment extends React.Component<IAppointmentProps, IAppointmentState> {
  constructor(props: IAppointmentProps) {
    super(props);

    this.state = {
    }
  }

  public render() {
    return (
      <div>
        Hello from Appointment
      </div>
    );
  }
}
