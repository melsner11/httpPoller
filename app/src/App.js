import React, { Component } from 'react';
import './App.css';
import Home from './components/Home';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import EndPointList from './components/endpoint/EndPointList';
import EndPointEdit from './components/endpoint/EndPointEdit';

class App extends Component {
  render() {
    return (
      <Router>
        <Switch>
          <Route path='/' exact={true} component={Home}/>
          <Route path='/endpoints' exact={true} component={EndPointList}/>
          <Route path='/endpoint/:id' component={EndPointEdit}/>
        </Switch>
      </Router>
    )
  }
}

export default App;