import * as React from 'react';
import { NavLink } from 'react-router-dom';
import Icon from '@material-ui/core/Icon';

import './nav.scss';
import { Grid } from '@material-ui/core';

const Nav = () => (
    <Grid container direction="row" justify="space-between" alignItems="flex-start" className="menu-container">
            <Grid item className="menu-item">
                <NavLink to='/' activeClassName="menu-item-active">Home</NavLink>
            </Grid>
            <Grid className="menu-item">
                <NavLink to='/vet' activeClassName="menu-item-active">Vet</NavLink>
            </Grid>
            <Grid className="menu-item">
                <NavLink to='/pet' activeClassName="menu-item-active">Pet</NavLink>
            </Grid>
            <Grid className="menu-item">
                <NavLink to='/appointment' activeClassName="menu-item-active">Appointment</NavLink>
            </Grid>
    </Grid>);

export default Nav;
