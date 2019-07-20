import * as React from 'react';

export interface IPetProps {
}

export interface IPetState {
}

export default class Pet extends React.Component<IPetProps, IPetState> {
  constructor(props: IPetProps) {
    super(props);

    this.state = {
    }
  }

  public render() {
    return (
      <div>
        Hello from Pet
      </div>
    );
  }
}
