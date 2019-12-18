import React, { useState } from 'react'
import { Link } from 'react-router-dom'
import { connect } from 'react-redux'
import { login } from '../../actions/session'
import bcrypt from 'bcryptjs'

const mapStateToProps = ({ errorReducer }) => ({
  errors: errorReducer
})

const mapDispatchToProps = dispatch => ({
  login: user => dispatch(login(user))
})

const Login = ({ login, errors }) => {
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')

  const handleSubmit = async event => {
    event.preventDefault()
    const hashPassword = await bcrypt.hash(password, 10);
    login({ email, password })
  }

  return (
    <div className="content-box-middle">
      <form className="content-box">
        <table>
          <tbody>
            <tr>
              <td>
                <label htmlFor="email">Adres email:</label>
              </td>
              <td>
                <input onChange={e => setEmail(e.target.value)} type="email" id="email" placeholder="email" />
              </td>
            </tr>
            <tr>
              <td>
                <label htmlFor="password">Hasło:</label>
              </td>
              <td>
                <input onChange={e => setPassword(e.target.value)} type="password" id="password" placeholder="hasło" />
              </td>
            </tr>
            <tr>
              <td colSpan="2">
                <button onClick={handleSubmit} className="btn-blue">Zaloguj</button>
              </td>
            </tr>
          </tbody>
        </table>
        <div className="middle">
          Nie masz konta?<Link to="/register"> Zarejestruj się!</Link>
        </div>
        <div className="middle">
          <Link to="/forgottenpassword">Zapomniałem hasła </Link>
        </div>
      </form>
    </div>
  )
}

export default connect(mapStateToProps, mapDispatchToProps)(Login)
