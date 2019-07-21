import * as React from 'react';
import TextField from '@material-ui/core/TextField/index';
import Button from '@material-ui/core/Button/index';
import Radio from '@material-ui/core/Radio';
import RadioGroup from '@material-ui/core/RadioGroup';
import FormControlLabel from '@material-ui/core/FormControlLabel/index';
import { vetService } from '../../service/vet-service';

import './vet.scss';

export interface IAddVetProps {
	onCancel: () => void;
	onAdd: (vet: any) => void;
}

export interface IAddVetState {
	name: string,
	specialty: string,
	specialties: any[]
}

export default class AddVet extends React.Component<IAddVetProps, IAddVetState> {
	constructor(props: IAddVetProps) {
		super(props);
		this.state = {
			name: '',
			specialty: '',
			specialties: []
		};

		this.handleChange = this.handleChange.bind(this);
		this.handleSpecialtySelection = this.handleSpecialtySelection.bind(this);
		this.addHandler = this.addHandler.bind(this);
		this.cancelHandler = this.cancelHandler.bind(this);
	}

	public handleChange(event) {
		this.setState({
			name: event.target.value
		});
	}

	public handleSpecialtySelection(event) {
		this.setState({
			specialty: event.target.value
		})
	}

	public cancelHandler() {
		this.props.onCancel();
	}

	public addHandler() {
		if (this.isFormValid()) {
			this.props.onAdd({
				name: this.state.name,
				specialty: {
					name: this.state.specialty
				}
			});
		}
	}

	private isFormValid() {
		if (this.state.name.trim().length === 0) {
			return false;
		}

		if (this.state.specialty.length === 0) { 
			return false;
		}
		return true;
	}

	public render() {
		return <form className="add-vet-form" onSubmit={event => event.preventDefault()}>
			<div className="add-vet-form__title"> Add Vet </div>
			<div className="add-vet-form__input">
				<div className="add-vet-form__name">
					<TextField required
						id="name"
						label="Name"
						value={this.state.name}
						onChange={this.handleChange}
						margin="normal"
						name="name"
					/>
				</div>
					
				<div className="add-vet-form__speciality-select">
					<label>Specialty</label>&nbsp;&nbsp;
					<RadioGroup
						aria-label="Specialty"
						name="specialty"
						value={this.state.specialty}
						onChange={this.handleSpecialtySelection}
					>
						{this.state.specialties.map(s =>  <FormControlLabel  key ={s.id} value={s.name} control={<Radio />} label={s.name} />)}	
					</RadioGroup>
				</div>
			</div>
			<div className="add-vet-form__footer">
				<Button variant="contained" onClick={this.cancelHandler}>Cancel</Button>
				<Button variant="contained" onClick={this.addHandler}>Add</Button>
			</div>
		</form>;
	}

	public componentDidMount() {
		vetService.getSpecialties()
		.then((specialties: any[]) => this.setState({specialties}));
	}
}

