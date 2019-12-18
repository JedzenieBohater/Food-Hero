import React, { useState } from 'react'
import { Link, Redirect } from 'react-router-dom'
import { register } from '../../../utils/session'

export default () => {
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [password2, setPassword2] = useState('')
  const [isPassword2Valid, setisPassword2Valid] = useState(true)
  const [redirect, setRedirect] = useState(false)

  const handleSubmit = async event => {
    event.preventDefault()
    try {
      await register({ email, password })
      setRedirect(true)
    } catch(err) {
      console.log(err)
    }
  }

  return (
    <>
      {redirect ? <Redirect to='/login' /> : null}
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
                  <input
                    type="password"
                    id="password"
                    placeholder="hasło"
                    className="valid"
                    onChange={e => {
                      setPassword(e.target.value)
                    }}
                  />
                </td>
              </tr>
              <tr>
                <td>
                  <label htmlFor="password2">Powtórz hasło:</label>
                </td>
                <td>
                  <input
                    type="password"
                    id="password2"
                    placeholder="powtórz hasło"
                    className={isPassword2Valid ? '' : 'invalid'}
                    onChange={e => {
                      setPassword2(e.target.value)
                      if (password !== e.target.value) {
                        setisPassword2Valid(false)
                      } else {
                        setisPassword2Valid(true)
                      }
                    }}
                  />
                </td>
              </tr>
              <tr>
                <td colSpan="2">
                  <button
                    className="btn-blue"
                    onClick={handleSubmit}
                    disabled={isPassword2Valid ? '' : 'disabled'}
                  >
                    Zarejestruj
                  </button>
                </td>
              </tr>
            </tbody>
          </table>
          <div className="middle">
            <Link to="/login">Zaloguj się</Link>
          </div>
        </form>
      </div>
    </>
  )
}
