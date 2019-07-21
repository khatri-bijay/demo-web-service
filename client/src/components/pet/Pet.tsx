import * as React from 'react';

import { NavLink } from 'react-router-dom';
import { petService } from '../../service/pet-service';
import PetListItem from './PetListItem';
import { Button } from '@material-ui/core';
import Search from '../common/Search';

export interface IPetProps {
	pets: any;
}

export interface IPetState {
	pets: [],
	isLoading: boolean
}

export default class Pet extends React.Component<IPetProps, IPetState> {

	constructor(props: IPetProps) {
		super(props);

		this.state = {
			pets: [],
			isLoading: true
		};
	}

	public componentDidMount() {
		petService.getPets()
			.then(respone => {
				this.setState({
					pets: respone,
					isLoading: false
				});
			});
	}

	public render() {
		if (this.state.isLoading) {
			return <p>Loading ...</p>;
		}

		const pets = this.state.pets.map((pet, index) => {
			return <PetListItem key={index} pet={pet}>
				<NavLink to={{ pathname: '/appointment',appointmentProps: pet }}>
					<Button variant="contained" className="book-appointment-button">
						Book an Appointment
					</Button>
				</NavLink>
			</PetListItem>
		});

		return (
			<React.Fragment>
				<h4>Available Pets</h4>
				<Search />
				<div className="pet-list">
					{pets}
				</div>
			</React.Fragment>
		);
	}
}
