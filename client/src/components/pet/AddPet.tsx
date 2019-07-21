import * as React from 'react';
import TextField from '@material-ui/core/TextField/index';
import Button from '@material-ui/core/Button/index';
import Radio from '@material-ui/core/Radio';
import RadioGroup from '@material-ui/core/RadioGroup';
import FormControlLabel from '@material-ui/core/FormControlLabel/index';
import { IType } from '../common/contract/contract';
import { PetService }  from '../../service/Pet-service';

import './pet.scss';

export interface IAddPetProps {
	onCancel: () => void;
	onAdd: (Pet: any) => void;
}

export interface IAddPetState {
	name: string,
	type: string,
	types: IType[]
}

export default class AddPet extends React.Component<IAddPetProps, IAddPetState> {
	constructor(props: IAddPetProps) {
		super(props);
		this.state = {
			name: '',
			type: '',
			types: []
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
			type: event.target.value
		})
	}

	public cancelHandler() {
		this.props.onCancel();
	}

	public addHandler() {
		if (this.isFormValid()) {
			this.props.onAdd({
				name: this.state.name,
				type: {
					name: this.state.type
				}
			});
		}
	}

	private isFormValid() {
		if (this.state.name.trim().length === 0) {
			return false;
		}

        if(this.state.type.trim().length === 0) {
            return false;
        }
		return true;
	}

	public render() {
		return <form className="add-pet-form" onSubmit={event => event.preventDefault()}>
			<div className="add-pet-form__title"> Add Pet </div>
			<div className="add-pet-form__input">
				<div className="add-pet-form__name">
					<TextField required
						id="name"
						label="Name"
						value={this.state.name}
						onChange={this.handleChange}
						margin="normal"
						name="name"
					/>
				</div>
					
				<div className="add-pet-form__type-select">
					<label>Type</label>&nbsp;&nbsp;
					<RadioGroup
						aria-label="Type"
						name="type"
						value={this.state.type}
						onChange={this.handleSpecialtySelection}
					>
						{this.state.types.map(s =>  <FormControlLabel  key ={s.id} value={s.name} control={<Radio />} label={s.name} />)}	
					</RadioGroup>
				</div>
			</div>
			<div className="add-pet-form__footer">
				<Button variant="contained" onClick={this.cancelHandler}>Cancel</Button>
				<Button variant="contained" onClick={this.addHandler}>Add</Button>
			</div>
		</form>;
	}

	public componentDidMount() {
		PetService.getTypes()
		.then(types => this.setState({types}));
	}
}

