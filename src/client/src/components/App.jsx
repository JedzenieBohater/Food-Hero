import React from 'react'
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import {
  Header,
  Login,
  Register,
  ForgottenPassword,
  Home,
  Search,
  Profile,
  AddOffer,
  Offer,
} from './layouts'
import { AuthRoute, ProtectedRoute } from 'utils/route'

export default () => (
  <Router>
    <Header />
    <Switch>
      <Route exact path="/" component={Home} />
      <AuthRoute path="/login" component={Login} />
      <Route path="/register" component={Register} />
      <Route path="/forgottenpassword" component={ForgottenPassword} />
      <Route path="/search" component={Search} />
      <ProtectedRoute path="/profile" component={Profile} />
      <Route path="/offer/:id" component={Offer} />
    </Switch>
  </Router>
)
