import * as React from 'react';
import { vetService } from '../../service/vet-service';
import { IVet } from '../common/contract/contract';
import VetListItem from './VetListItem';
import AddVet from './AddVet';
import Search from '../common/Search';
import { Fab, Button, Icon } from '@material-ui/core';
import AddIcon from '@material-ui/icons/Add';
import { NavLink } from 'react-router-dom';

import './vet.scss';

export interface IVetProps {
}

export interface IVetState {
	vets : any[];
	isFormVisible: boolean;
}

export default class Vet extends React.Component<IVetProps, IVetState> {
	constructor(props: IVetProps) {
		super(props);

		this.state = {
			vets: [],
			isFormVisible: false
		}

		this.handleAdd = this.handleAdd.bind(this);
		this.handleCancel = this.handleCancel.bind(this);
		this.showForm = this.showForm.bind(this);
	}

	public handleAdd(vet: IVet) {
		vetService.addVet(vet)
		.then(vet => {
			this.setState({
				isFormVisible: false,
			});
			this.getVets();
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
		const vetList = this.state.vets.map((vet, index) => <VetListItem key= {index} vet = {vet}>
			<NavLink to={{ pathname: '/appointment', appointmentProps: {vet}}}>
					<Button variant="outlined" className="book-appointment-button">
						<Icon>list</Icon>view Appointment
					</Button>
			</NavLink>
		</VetListItem>)
		let addVet
		if(this.state.isFormVisible) {
			addVet = <AddVet onAdd = { this.handleAdd } onCancel = {this.handleCancel} />;
		}else {
			addVet =  <Fab color="primary" aria-label="Add" size="small" className="add-vet">
						<AddIcon onClick = { this.showForm }/> 
		  			</Fab>;
		}

		return (
			<div className="container-vet">
				<h4>Available Vets</h4>
				<Search />
				{ vetList }
				{ addVet }
			</div>
		)
	}

	public componentDidMount() {
		this.getVets();
	}

	private getVets() {
		vetService.getVets()
			.then((vets: any[]) => this.setState({ vets: vets }));
	}
}
