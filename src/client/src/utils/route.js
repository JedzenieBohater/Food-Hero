import React from 'react'
import { connect } from 'react-redux'
import { Redirect, Route } from 'react-router-dom'

const Protected = ({ loggedIn, path, component: Component, exact }) => (
  <Route
    path={path}
    exact={Boolean(exact)}
    render={props =>
      loggedIn ? <Component {...props} /> : <Redirect to="/login" />
    }
  />
)

const Auth = ({ loggedIn, path, component: Component, exact }) => (
  <Route
    path={path}
    exact={Boolean(exact)}
    render={props =>
      loggedIn ? <Redirect to="/" /> : <Component {...props} />
    }
  />
)

const mapStateToProps = ({ sessionReducer: { userID } }) => ({
  loggedIn: Boolean(userID),
})

export const ProtectedRoute = connect(mapStateToProps)(Protected)

export const AuthRoute = connect(mapStateToProps)(Auth)
