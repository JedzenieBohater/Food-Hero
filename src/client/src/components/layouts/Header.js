import React from "react";
import { BrowserRouter as Router, Route, Link, Switch } from "react-router-dom";
//import logo from "../static/logo.svg"

export default () => {
  return (
    <header className="top-bar">
      <Link to="/">
        <img className="logo" alt="Logo" />
      </Link>
      <nav className="top-bar-links">
        <Link to="/login">
          <button className="btn-no-background-login">Zaloguj</button>
        </Link>
        <Link to="/register">
          <button className="btn-no-background-register">Zarejestruj</button>
        </Link>
      </nav>
    </header>
  );
};
