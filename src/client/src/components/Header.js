import React from "react"
import {BrowserRouter as Router, Route, Link, Switch} from 'react-router-dom'
import "../static/header.css"
import logo from "../static/logo.svg"



export default class Header extends React.Component {
    render() {
        return (
            <header className="top-bar">
                <Router>
                <Link to="/"><img className="logo" src={logo} alt="Logo"/></Link>
                    <nav className="top-bar-links">
                        <Switch>
                            <Route path="/xd">
                                <label>Powiadomienia</label>{ "  " }
                                <label>Profil</label>
                            </Route>
                            <Route path="/">
                                <label>Zaloguj</label>
                            </Route>
                        </Switch>
                    </nav>
                </Router>
            </header>
        );
    }
}