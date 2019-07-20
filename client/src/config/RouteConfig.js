import * as React from 'react';
import { Switch, Route } from "react-router-dom";
import Appointment from '../components/appointment/Appointment';
import Pet from '../components/pet/Pet';
import Vet from '../components/vet/Vet';
import Home from '../components/home/Home';

const RouteConfig =  () =>
            <Switch>
                <Route exact path="/" component={Home} />
                <Route path="/pet" component={Pet} />
                <Route path="/vet" component={Vet} />
                <Route path="/appointment" component={Appointment} />
                <Route component={Home} />
            </Switch>;

export default RouteConfig;