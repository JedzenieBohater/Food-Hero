import React, { useState } from 'react'
import { Link } from 'react-router-dom'
import { connect } from 'react-redux'
import { login } from '../../../actions/session'
import bcrypt from 'bcryptjs'
import PropTypes from 'prop-types'

const mapStateToProps = ({ errorReducer }) => ({
  errors: errorReducer
})

const mapDispatchToProps = dispatch => ({
  login: user => dispatch(login(user))
})

export const Login = ({ login, errors, translation }) => {
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')

  const handleSubmit = async event => {
    event.preventDefault()
    const hashPassword = await bcrypt.hash(password, 10);
    login({ email, password })
  }

  return (
    <div className="content-box-middle" test-data="wrapper">
      <form className="content-box" test-data="form">
        <table className="tableform">
          <tbody>
            <tr>
              <td>
                <label htmlFor="email">{translation.email}:</label>
              </td>
              <td>
                <input onChange={e => setEmail(e.target.value)} type="email" id="email" placeholder={translation.email.toLowerCase()} />
              </td>
            </tr>
            <tr>
              <td>
                <label htmlFor="password">{translation.password}:</label>
              </td>
              <td>
                <input onChange={e => setPassword(e.target.value)} type="password" id="password" placeholder={translation.password.toLowerCase()} />
              </td>
            </tr>
            <tr>
              <td colSpan="2">
                <button onClick={handleSubmit} className="btn-blue" test-data="submit">{translation.login}</button>
              </td>
            </tr>
          </tbody>
        </table>
        <div className="middle">
          {translation.noAccount} <Link to="/register" test-data="register">{translation.register}</Link>
        </div>
        <div className="middle">
          <Link to="/forgottenpassword">{translation.forget}</Link>
        </div>
      </form>
    </div>
  )
}

Login.propTypes = {
  errors: PropTypes.string,
  login: PropTypes.func.isRequired
}

export default connect(mapStateToProps, mapDispatchToProps)(Login)