import * as React from 'react';

export interface IVetProps {
}

export interface IVetState {
}

export default class Vet extends React.Component<IVetProps, IVetState> {
  constructor(props: IVetProps) {
    super(props);

    this.state = {
    }
  }

  public render() {
    return (
      <div>
        Vet Component
      </div>
    );
  }
}
