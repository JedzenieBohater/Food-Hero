import React from 'react'
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import { Header, Login, Register, ForgottenPassword, Home } from './layouts'
import { AuthRoute } from '../utils/route'
import counterpart from 'counterpart'
import {en, pl} from '../language/index'



counterpart.registerTranslations('pl', pl)
counterpart.registerTranslations('en', en)
counterpart.setLocale('en')

export default class App extends React.Component {
  constructor(props){
    super(props)

    this.state = {
      lang : counterpart.getLocale(),
      translation: counterpart.getLocale() === 'en' ? en : pl
    }

  }
  
  onLangChange = e => {
    this.setState({
      lang: e.target.value,
      translation: e.target.value === 'en'? en : pl
    })
    counterpart.setLocale(e.target.value)
  }

  render() {
    return (
      <Router>
        <Header translation={this.state.translation.header} lang={counterpart.getLocale()} onLangChange={this.onLangChange}/>
        <Switch>
          <Route exact path="/" component={Home} />
          <AuthRoute path='/login' component={Login} />
          <Route path="/register" component={Register} />
          <Route path="/forgottenpassword" component={ForgottenPassword} />
        </Switch>
      </Router>
    )
  }
}
