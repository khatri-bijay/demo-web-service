import * as React from 'react';
import * as ReactDOM from 'react-dom';
import { BrowserRouter as Router} from 'react-router-dom';
import RouteConfig from './config/RouteConfig';
import Nav from './components/common/nav/Nav';

import './app.scss';

class App extends React.Component {
    render() {
        return (
        <Router>
            <React.Fragment>
                <Nav />
                <div className="container">
                    <RouteConfig />
                </div>
            </React.Fragment>
        </Router>)
    }
}
ReactDOM.render(<App />, document.querySelector('#react-root'));