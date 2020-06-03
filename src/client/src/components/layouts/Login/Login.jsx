import React, { useState } from 'react'
import { Link } from 'react-router-dom'
import { connect } from 'react-redux'
import { login } from 'actions/session'
import { clearErrors } from 'actions/errors'
import PropTypes from 'prop-types'

export const Login = ({ login, errors, lang }) => {
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [errorMessage, setErrorMessage] = useState(null)

  const handleSubmit = async event => {
    event.preventDefault()
    clearErrors()
    if (email.length < 5 || email.length > 50) {
      setErrorMessage(lang.emailincorrect)
    } else {
      setErrorMessage(null)
      login({ email, password })
    }
  }
  let error = null
  if (errorMessage != null) {
    error = <label className="profileerror">{errorMessage}</label>
  }
  if (errors !== '') {
    error = <label className="profileerror">{lang.passwordincorrect}</label>
  }

  return (
    <div className="content-box-middle" test-data="wrapper">
      <form className="content-box" test-data="form">
        <table className="tableform">
          <tbody>
            <tr>
              <td>
                <label htmlFor="email">{lang.email}:</label>
              </td>
              <td>
                <input
                  onChange={e => setEmail(e.target.value)}
                  type="email"
                  id="email"
                  placeholder={lang.email.toLowerCase()}
                />
              </td>
            </tr>
            <tr>
              <td>
                <label htmlFor="password">{lang.password}:</label>
              </td>
              <td>
                <input
                  onChange={e => setPassword(e.target.value)}
                  type="password"
                  id="password"
                  placeholder={lang.password.toLowerCase()}
                  required
                />
              </td>
            </tr>
            <tr>
              <td colSpan="2">
                <button
                  onClick={handleSubmit}
                  className="btn-blue"
                  test-data="submit"
                >
                  {lang.login}
                </button>
              </td>
            </tr>
          </tbody>
        </table>
        <div className="middle">
          {lang.noAccount}{' '}
          <Link to="/register" test-data="register">
            {lang.register}
          </Link>
        </div>
        <div className="middle">
          <Link to="/forgottenpassword">{lang.forget}</Link>
        </div>
        {error}
      </form>
    </div>
  )
}

Login.propTypes = {
  errors: PropTypes.string,
  login: PropTypes.func.isRequired,
  lang: PropTypes.shape({
    email: PropTypes.string.isRequired,
    password: PropTypes.string.isRequired,
    login: PropTypes.string.isRequired,
    noAccount: PropTypes.string.isRequired,
    forget: PropTypes.string.isRequired,
  }),
}

const mapStateToProps = ({ errorReducer, languageReducer }) => ({
  errors: errorReducer,
  lang: languageReducer.login,
})

const mapDispatchToProps = dispatch => ({
  login: user => dispatch(login(user)),
  clearErrors: () => dispatch(clearErrors()),
})

export default connect(mapStateToProps, mapDispatchToProps)(Login)
