import React, { useState, useEffect } from 'react'
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import { Header, Login, Register, ForgottenPassword, Home } from './layouts'

export default () => {
  const [loggedIn, setLoggedIn] = useState(false)

  useEffect(() => {
    console.log(`Użytkownik ${loggedIn ? '' : 'nie'} zalogowany`)
  }, [loggedIn])

  return (
    <Router>
      <Header />
      <Switch>
        <Route exact path="/" component={Home} />
        <Route path="/login" component={Login} />
        <Route path="/register" component={Register} />
        <Route path="/forgottenpassword" component={ForgottenPassword} />
      </Switch>
    </Router>
  )
}
