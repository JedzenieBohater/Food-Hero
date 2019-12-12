import React from 'react'
import { connect } from 'react-redux'
import { Redirect, Route, withRouter } from 'react-router-dom'

const mapStateToProps = ({ session: { userId } }) => ({
  loggedIn: Boolean(userId)
})

const Protected = ({ loggedIn, path, component: Component }) => (
  <Route
    path={path}
    render={props =>
      loggedIn ? <Component {...props} /> : <Redirect to="/login" />
    }
  />
)

export const ProtectedRoute = withRouter(connect(mapStateToProps)(Protected))