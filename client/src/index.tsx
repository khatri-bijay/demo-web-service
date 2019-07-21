import * as React from 'react';
import * as ReactDOM from 'react-dom';
import { BrowserRouter as Router } from 'react-router-dom';
import { Container, Grid, AppBar, Toolbar, IconButton, Typography, Button, Icon } from '@material-ui/core';
import MenuIcon from '@material-ui/icons/Menu';
import RouteConfig from './config/RouteConfig';
import appConfig from './config/app.config';
import Nav from './components/common/nav/Nav';

import './app.scss';

class App extends React.Component {
    render() {
        return (
            <Router>
                <Container maxWidth="lg">
                <AppBar position="static">
                    <Toolbar>
                        <IconButton edge="start" color="inherit" aria-label="Menu">
                            <MenuIcon />
                        </IconButton>
                        <Typography variant="h6">
                            { appConfig.appName }
                        </Typography>
                        <IconButton edge="end" color="inherit" aria-label="Menu" style= {{ position: 'absolute', right: '1rem'}}>
                            <Icon>person</Icon>
                        </IconButton>
                    </Toolbar>
                </AppBar>
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