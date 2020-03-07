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
} from './layouts'
import { AuthRoute } from 'utils/route'

export default () => (
  <Router>
    <Header />
    <Switch>
      <Route exact path="/" component={Home} />
      <AuthRoute path="/login" component={Login} />
      <Route path="/register" component={Register} />
      <Route path="/forgottenpassword" component={ForgottenPassword} />
      <Route path="/search" component={Search} />
      <Route path="/profile" component={Profile} />
      <Route path="/add" component={AddOffer} />
    </Switch>
  </Router>
)
