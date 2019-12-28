import React from 'react'
import { Link } from 'react-router-dom'
import { connect } from 'react-redux'
import { changeLanguage } from '../../../actions/language'
//import logo from "../static/logo.svg"

const mapStateToProps = ({ languageReducer }) => ({
  lang: languageReducer.header
})

const mapDispatchToProps = dispatch => ({
  changeLang: short => dispatch(changeLanguage(short))
})

export const Header = (props) => {
  return (
    <header className="top-bar" test-data="top-bar">
      <Link to="/">
        <img className="logo" alt="Logo" />
      </Link>
      <nav className="top-bar-links">
        <select value={props.lang.login === "Sign in" ? "en" : "pl"} onChange={(e) => props.changeLang(e.target.value)}>
          <option value="en">EN</option>
          <option value="pl">PL</option>
        </select>
        <Link to="/login">
          <button className="btn-no-background-login">{props.lang.login}</button>
        </Link>
        <Link to="/register">
          <button className="btn-no-background-register">{props.lang.register}</button>
        </Link>
      </nav>
    </header>
  )
}

export default connect(mapStateToProps, mapDispatchToProps)(Header)