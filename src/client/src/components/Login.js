import React from "react"
import "../static/style.css"



export default class Login extends React.Component {
    render() {
        return (
            <div class="content-box-middle">
                <form class="content-box">
                    <table>
                        <tr>
                            <td><label for="username">Nazwa użytkownika:</label></td>
                            <td><input type="text" id="username" placeholder="nazwa" /></td>
                        </tr>
                        <tr>
                            <td><label for="password">Hasło:</label></td>
                            <td><input type="password" id="password" placeholder="hasło" /></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <button class="btn-blue">Powrót</button>
                                <button class="btn-blue">Zaloguj</button>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        );
    }
}