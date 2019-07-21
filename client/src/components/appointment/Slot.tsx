import * as React from 'react';
import './appointment.scss';
import { ISlotItem } from '../common/contract/contract';
import { Checkbox, Grid } from '@material-ui/core';

interface ISlot extends ISlotItem {
	checked: boolean;
}

export interface ISlotProps {
	slots: ISlot[]
}

export interface ISlotState {
	slots: ISlot[]
}

export default class Slot extends React.Component<ISlotProps, ISlotState> {
	constructor(props: ISlotProps) {
		super(props);
		this.state = {
			slots: this.props.slots
		}
	}

	public handleChange(slot: ISlot) {
		// const slots = this.state.slots;
		// const index = this.state.slots.findIndex(s => s.start === slot.start && s.end === slot.end);
		// slots.forEach(s => {
		// 	if(s.checked){
		// 		s.checked = false;
		// 	}
		// });
		// slots[index].checked = true;

		// this.setState({slots});
	}

	public render() {
		if (!this.props.slots) {
			return '';
		}

		const slots = this.props.slots.map((slot, index) => {
			return <div key={index} className="appointment-slot">
				<div>{slot.start}-{slot.end}</div>
				<Checkbox checked={slot.checked} onChange={() => this.handleChange(slot)} />
			</div>
		});

		return slots;
	}
}
