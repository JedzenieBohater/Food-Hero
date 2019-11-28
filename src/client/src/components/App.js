import React from 'react'
import {BrowserRouter as Router, Route, Link} from 'react-router-dom'
import Header from './Header'

export default class App extends React.Component {
    render(){
        return (
            <Router>
                <Header />
                <Route path='/index'>
                    <label>main</label>
                </Route >

                <Route path="/xd">
                    <label>xd</label>
                </Route >
            </Router >
            );

    }
    
}