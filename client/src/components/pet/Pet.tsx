import * as React from 'react';

import { NavLink } from 'react-router-dom';
import { PetService } from '../../service/pet-service';
import PetListItem from './PetListItem';
import { Button, Fab } from '@material-ui/core';
import Search from '../common/Search';
import AddPet from './AddPet';
import { IPet } from '../common/contract/contract';
import AddIcon from '@material-ui/icons/Add';


export interface IPetProps {
	pets: any;
}

export interface IPetState {
	pets: [];
	isLoading: boolean;
	isFormVisible: boolean;
}

export default class Pet extends React.Component<IPetProps, IPetState> {

	constructor(props: IPetProps) {
		super(props);

		this.state = {
			pets: [],
			isLoading: true,
			isFormVisible: false
		};
		this.handleAdd = this.handleAdd.bind(this);
		this.handleCancel = this.handleCancel.bind(this);
		this.showForm = this.showForm.bind(this);
	}

	public componentDidMount() {
		this.getPets();
	}

	public handleAdd(pet: IPet) {
		PetService.addPet(pet)
			.then(pet => {
				this.setState({
					isFormVisible: false,
				});
				this.getPets();
			})
	}

	public handleCancel() {
		this.setState({
			isFormVisible: false
		})
	}

	private showForm() {
		this.setState({
			isFormVisible: true
		})
	}

	public render() {
		if (this.state.isLoading) {
			return <p>Loading ...</p>;
		}
		let addPet
		if (this.state.isFormVisible) {
			addPet = <AddPet onAdd={this.handleAdd} onCancel={this.handleCancel} />;
		} else {
			addPet = <Fab color="primary" aria-label="Add" size="small" className="add-pet">
				<AddIcon onClick={this.showForm} />
			</Fab>;
		}
		const petList = this.state.pets.map((pet, index) => {
			return <PetListItem key={index} pet={pet}>
				<NavLink to={{ pathname: '/appointment', appointmentProps: { pet } }}>
					<Button variant="outlined" className="book-appointment-button">
						Book an Appointment
					</Button>
				</NavLink>
			</PetListItem>
		});

		return (
			<div className="container-pet">
				<h4>Available Pets</h4>
				<Search />
				{petList}
				{addPet}
			</div>
		);
	}

	private getPets() {
		PetService.getPets()
			.then((respone: any) => {
				this.setState({
					pets: respone,
					isLoading: false
				});
			});
	}
}
