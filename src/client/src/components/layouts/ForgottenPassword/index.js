import React from 'react'
import { Link } from 'react-router-dom'
import { connect } from 'react-redux'

const mapStateToProps = ({ languageReducer }) => ({
  lang: languageReducer.forgottenpassword
})

export const ForgottenPassword = (props) => {
  return (
    <div className="content-box-middle">
      <form className="content-box">
        <div className="middle">
          {props.lang.message}
        </div>
        <table>
          <tbody>
            <tr>
              <td>
                <label htmlFor="email">{props.lang.email}:</label>
              </td>
              <td>
                <input type="email" id="email" placeholder={props.lang.email.toLowerCase()} />
              </td>
            </tr>
            <tr>
              <td colSpan="2">
                <button className="btn-blue">{props.lang.remember}</button>
              </td>
            </tr>
          </tbody>
        </table>
        <div className="middle">
          <Link to="/login">{props.lang.back}</Link>
        </div>
      </form>
    </div>
  )
}


export default connect(mapStateToProps)(ForgottenPassword)