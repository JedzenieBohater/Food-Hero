import React from "react"
import {BrowserRouter as Router, Route, Link} from 'react-router-dom'
import "../static/header.css"
import logo from "../static/logo.svg"



export default class Header extends React.Component {
    render() {
        return (
            <header className="top-bar">
                <a href="/index"><img className="logo" src={logo} alt="Logo"/></a>
                <nav className="top-bar-links">
                    <label>Powiadomienia</label>{ "  " }
                    <label>Profil</label>
                </nav>
            </header>
        );
    }
}