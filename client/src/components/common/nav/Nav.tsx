import * as React from 'react';
import { NavLink } from 'react-router-dom';
import Icon from '@material-ui/core/Icon';

import './nav.scss';

const Nav = () => (<div className="site-menu">
    <div className="menu-collapse">
        <Icon>menu</Icon>
    </div>
    <div className="menu-container">
        <div className="menu-item">
            <NavLink to='/' activeClassName="menu-item-active">Home</NavLink>
        </div>
        <div className="menu-item">
            <NavLink to='/vet' activeClassName="menu-item-active">Vet</NavLink>
        </div>
        <div className="menu-item">
            <NavLink to='/pet' activeClassName="menu-item-active">Pet</NavLink>
        </div>
        <div className="menu-item">
            <NavLink to='/appointment' activeClassName="menu-item-active">Appointment</NavLink>
        </div>
    </div>
</div>);

export default Nav;
