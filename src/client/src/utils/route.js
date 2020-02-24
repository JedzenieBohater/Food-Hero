import React from 'react'
import { connect } from 'react-redux'
import { Redirect, Route } from 'react-router-dom'

const mapStateToProps = ({ sessionReducer: { userID } }) => ({
  loggedIn: Boolean(userID)
})

const Auth = ({ loggedIn, path, component: Component }) => (
  <Route
    path={path}
    render={props => (loggedIn ? <Redirect to='/' /> : <Component {...props} />)} 
  />
)

export const AuthRoute = connect(mapStateToProps)(Auth)