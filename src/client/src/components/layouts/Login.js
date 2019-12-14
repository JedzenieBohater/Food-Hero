import React, { useState } from 'react'
import { Link } from 'react-router-dom'
import { connect } from 'react-redux'
import { login } from '../../actions/session'
import bcrypt from 'bcryptjs'

const mapStateToProps = ({ errors }) => ({
  errors
})

const mapDispatchToProps = dispatch => ({
  login: user => dispatch(login(user))
})

const Login = ({ login, errors }) => {
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')

  const handleSubmit = async event => {
    event.preventDefault()
    const hashPassword = await bcrypt.hash(req.body.password, 10);
    await login({ email, password: hashPassword })
  }

  const handleFun = async () => {
    const response = await fetch('http://some_backend:8080/account/1')
    const data = await response.json()
    console.log(data)
  }

  return (
    <div className="content-box-middle">
      <button onClick={handleFun}>Fajny baton</button>
      <form className="content-box" onSubmit={handleSubmit}>
        <table>
          <tbody>
            <tr>
              <td>
                <label htmlFor="email">Adres email:</label>
              </td>
              <td>
                <input type="email" id="email" placeholder="email" />
              </td>
            </tr>
            <tr>
              <td>
                <label htmlFor="password">Hasło:</label>
              </td>
              <td>
                <input type="password" id="password" placeholder="hasło" />
              </td>
            </tr>
            <tr>
              <td colSpan="2">
                <input type="submit" className="btn-blue">Zaloguj</input>
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
