import React, { useState } from 'react'
import { Link } from 'react-router-dom'

export default () => {
  const [password, setPassword] = useState('')
  const [password2, setPassword2] = useState('')
  const [isPassword2Valid, setisPassword2Valid] = useState(true)

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
                <input type="email" id="email" placeholder="email" />
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
                  value={password}
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
                  value={password2}
                  className={isPassword2Valid ? '' : 'invalid'}
                  onChange={e => {
                    setPassword2(e.target.value)
                    console.log(password)
                    console.log(password2)
                    if (password !== e.target.value) {
                      setisPassword2Valid(false)
                    } else {
                      setisPassword2Valid(true)
                    }
                    console.log(isPassword2Valid)
                  }}
                />
              </td>
            </tr>
            <tr>
              <td colSpan="2">
                <button
                  className="btn-blue"
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
  )
}
