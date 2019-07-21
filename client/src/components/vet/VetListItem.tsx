import * as React from 'react';
import { IVet } from '../common/contract/contract';
import { Grid, Button } from '@material-ui/core';
import './vet.scss';

export interface IVetListItemProps {
    vet: IVet;
}

export interface IVetListItemState {
}

export default class VetListItem extends React.Component<IVetListItemProps, IVetListItemState> {
  constructor(props: IVetListItemProps) {
    super(props);

    this.state = {

    }
  }

  public render() {    
    return (
      <Grid container direction="row" justify="space-between" alignItems="center" className="vet-list-item">
        <Grid item>{this.props.vet.id}</Grid>
        <Grid item>{this.props.vet.name}</Grid>
        <Grid item>{this.props.vet.specialty.name}</Grid>
        <Grid item>
          {this.props.children}&nbsp;&nbsp;
          <Button variant="outlined">Edit</Button>&nbsp;&nbsp;
          <Button variant="outlined" color="secondary">Delete</Button>
        </Grid>
        
      </Grid>
    );
  }
}
