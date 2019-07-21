import * as React from 'react';
import { Input, FormControl, InputLabel, Button, Grid, TextField } from '@material-ui/core';

export interface ISearchProps {
}

export interface ISearchState {
	keyword: String;
}

export default class Search extends React.Component<ISearchProps, ISearchState> {
  constructor(props: ISearchState) {
	super(props);
	this.state = {
		keyword: ''
	};
	this.handleChange = this.handleChange.bind(this);
  }

  public handleChange(event: any) {
	this.setState({
		keyword : event.target.value
	});
  }

  public render() {

    return (
      <FormControl>
        <Grid container direction="row" justify="space-between" alignItems="center">
          <Grid item>
		  <TextField
			id="search-keyname"
			label="Name"
			name= "keyword"
			value={ this.state.keyword}
			onChange={ this.handleChange}
			margin="normal"/>
          </Grid>
		  <Grid item><Button variant="outlined">Search</Button></Grid>
        </Grid>
      </FormControl>
    );
  }
}
