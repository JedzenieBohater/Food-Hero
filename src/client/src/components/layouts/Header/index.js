import React from 'react'
import { BrowserRouter as Router, Route, Link, Switch } from 'react-router-dom'
//import logo from "../static/logo.svg"

export default (props) => {
  return (
    <header className="top-bar" test-data="top-bar">
      <Link to="/">
        <img className="logo" alt="Logo" />
      </Link>
      <nav className="top-bar-links">
      <select value={props.lang} onChange={(e) => props.onLangChange(e)}>
        <option value="en">EN</option>
        <option value="pl">PL</option>
      </select>
        <Link to="/login">
          <button className="btn-no-background-login">{props.translation.login}</button>
        </Link>
        <Link to="/register">
  <button className="btn-no-background-register">{props.translation.register}</button>
        </Link>
      </nav>
    </header>
  )
}