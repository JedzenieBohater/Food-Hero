import React from 'react'
import { BrowserRouter as Router, Route, Link, Switch } from 'react-router-dom'
import Header from './Header'
import Login from './Login'

export default class App extends React.Component {
    render(){
        return (
            <Router>
                <Header />
                <Switch>
                    <Route path="/login">
                        <Login />
                    </Route>
                    <Route path="/xd">
                        <label>xd</label>
                    </Route >
                    <Route path='/'>
                        <label>main</label>
                    </Route >
                </Switch>
            </Router >
            );
    }
    
}