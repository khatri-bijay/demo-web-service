import * as React from 'react';
import { InputLabel, Select, FormControl, MenuItem, FormHelperText } from '@material-ui/core';

export interface IDropDownProps {
    name: string;
    options: Array<any>;
    handlePetChange: any;
    selectedDefault: any;
}

export interface IDropdownState {

}

export default class Dropdown extends React.Component<IDropDownProps, IDropdownState> {
    constructor(props: IDropDownProps) {
        super(props);
    }

    public render() {
        return (
            <FormControl fullWidth ={true}>
                <InputLabel>{this.props.name}</InputLabel>
                <Select autoWidth={true} value={this.props.selectedDefault} onChange={this.props.handlePetChange}>
                    {
                        this.props.options.map(
                            (option, index) => <MenuItem key={index} value={option}>{option.name}</MenuItem>)
                    }
                </Select> 
                <FormHelperText>Required</FormHelperText>
            </FormControl>
        );
    }
}
