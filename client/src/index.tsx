import * as React from 'react';
import * as ReactDOM from 'react-dom';
import { BrowserRouter as Router } from 'react-router-dom';
import { Container, Grid } from '@material-ui/core';
import RouteConfig from './config/RouteConfig';
import Nav from './components/common/nav/Nav';

import './app.scss';

class App extends React.Component {
    render() {
        return (
            <Router>
                <Container maxWidth="lg">
                <h4> Appointment Scheduler - Demo App for Pet and Vet </h4>
                    <Grid container spacing = {2}>
                        <Grid item lg={2} xs= {4}>
                            <Nav />
                        </Grid>
                        <Grid item lg={10} xs= {8}>
                            <div className="container">
                                <RouteConfig />
                            </div>
                        </Grid>
                    </Grid>
                </Container>
            </Router>)
    }
}
ReactDOM.render(<App />, document.querySelector('#react-root'));