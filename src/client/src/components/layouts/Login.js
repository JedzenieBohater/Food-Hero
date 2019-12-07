import React from "react";
import { Link } from "react-router-dom";

export default () => {
  return (
    <div className="content-box-middle">
      <form className="content-box">
        <table>
          <tbody>
            <tr>
              <td>
                <label htmlFor="username">Adres email:</label>
              </td>
              <td>
                <input type="email" id="username" placeholder="email" />
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
                <button className="btn-blue">Zaloguj</button>
              </td>
            </tr>
          </tbody>
        </table>
        <div className="middle">
          <Link to="/register">Nie masz konta, zarejestruj się!</Link>
        </div>
        <div className="middle">
          <Link to="/forgottenpassword">Zapomniałem hasła </Link>
        </div>
      </form>
    </div>
  );
};
