import React, { useState } from "react";
import { Link } from "react-router-dom";

export default () => {
    const [password, setPassword] = useState("");
    const [password2, setPassword2] = useState("");
    const [isPassword2Valid, setisPassword2Valid] = useState(true);

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
                                <input
                                    type="email"
                                    id="email"
                                    placeholder="email"
                                />
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
                                    onChange={e => {
                                        setPassword(e.target.value);
                                    }}
                                />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label htmlFor="password">Powtórz hasło:</label>
                            </td>
                            <td>
                                <input
                                    type="password"
                                    id="password"
                                    placeholder="powtórz hasło"
                                    value={password2}
                                    onChange={e => {
                                        setPassword2(e.target.value);
                                    }}
                                />
                            </td>
                        </tr>
                        <tr>
                            <td colSpan="2">
                                <button className="btn-blue">
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
    );
};
