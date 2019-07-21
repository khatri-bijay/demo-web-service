import * as React from 'react';
import { IPet } from '../common/contract/contract';
import { Grid, Button } from '@material-ui/core';

import './pet.scss';

export interface IPetListItemProps {
	pet: IPet;
}

export interface IPetListItemState {
}

export default class PetListItem extends React.Component<IPetListItemProps, IPetListItemState> {
	constructor(props: IPetListItemProps) {
		super(props);
	}

	public render() {
		return (
			<Grid container direction="row" justify="space-between" alignItems="center" className="pet-list-item">
				<Grid item>{this.props.pet.id}</Grid>
				<Grid item>{this.props.pet.name}</Grid>
				<Grid item>{this.props.pet.type.name}</Grid>
				<Grid item>
					{this.props.children}&nbsp;&nbsp;
					<Button variant="outlined">Edit</Button>&nbsp;&nbsp;
      				<Button variant="outlined" color="secondary">Delete</Button>
				</Grid>
			</Grid>);
	}
}
