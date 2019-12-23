import React from 'react'
import { Link } from 'react-router-dom'

export default (props) => {
  return (
    <div className="content-box-middle">
      <form className="content-box">
        <div className="middle">
          {props.translation.message}
        </div>
        <table>
          <tbody>
            <tr>
              <td>
                <label htmlFor="email">{props.translation.email}:</label>
              </td>
              <td>
                <input type="email" id="email" placeholder={props.translation.email.toLowerCase()} />
              </td>
            </tr>
            <tr>
              <td colSpan="2">
                <button className="btn-blue">{props.translation.remember}</button>
              </td>
            </tr>
          </tbody>
        </table>
        <div className="middle">
          <Link to="/login">{props.translation.back}</Link>
        </div>
      </form>
    </div>
  )
}
