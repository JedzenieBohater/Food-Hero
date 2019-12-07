import React from "react";
import { Link } from "react-router-dom";

export default () => {
  return (
    <div className="content-box-middle">
      <form className="content-box">
        <div className="middle">
          Wpisz adres email, na który wyślemy wiadomość jak odzyskać hasło
        </div>
        <table>
          <tbody>
            <tr>
              <td>
                <label htmlFor="email">Email:</label>
              </td>
              <td>
                <input type="text" id="email" placeholder="email" />
              </td>
            </tr>
            <tr>
              <td colSpan="2">
                <button className="btn-blue">Przypomnij</button>
              </td>
            </tr>
          </tbody>
        </table>
        <div className="middle">
          <Link to="/login">Powrót</Link>
        </div>
      </form>
    </div>
  );
};
