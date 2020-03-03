import React from 'react'
import { Link } from 'react-router-dom'
import { connect } from 'react-redux'
import { changeLanguage } from '../../../actions/language'
import Dropdown from "./Dropdownmenu"
import PropTypes from 'prop-types'

export const Header = ({ lang, changeLang, loggedIn }) => {
  return (
    <header test-data="top-bar">
      <Link to="/">
        <img 
          className="logo" 
          alt="Logo" 
          src={`${process.env.PUBLIC_URL}/static/images/Logo.png`}
        />
      </Link>

      <nav className="top-bar-links">
        <select 
          value={lang.login === "Sign in" ? "en" : "pl"} 
          onChange={e => changeLang(e.target.value)}
        >
          <option value="en">EN</option>
          <option value="pl">PL</option>
        </select>

        {loggedIn ? (
          <Dropdown />
        ) : (
          <>
            <Link to="/login">
              <button className="btn-no-background-login">
                {lang.login}
              </button>
            </Link>
            <Link to="/register">
              <button className="btn-no-background-register">
                {lang.register}
              </button>
            </Link>
          </>
        )}
      </nav>
    </header>
  )
}

Header.propTypes = {
  changeLang: PropTypes.func.isRequired,
  lang: PropTypes.shape({
    login: PropTypes.string.isRequired,
    register: PropTypes.string.isRequired
  })
}

const mapStateToProps = ({ languageReducer, sessionReducer }) => ({
  lang: languageReducer.header,
  loggedIn: sessionReducer.userID
})

const mapDispatchToProps = dispatch => ({
  changeLang: short => dispatch(changeLanguage(short))
})

export default connect(
  mapStateToProps, 
  mapDispatchToProps
)(Header)