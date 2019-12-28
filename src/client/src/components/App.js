import React from 'react'
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import { Header, Login, Register, ForgottenPassword, Home } from './layouts'
import { AuthRoute } from '../utils/route'
import {en, pl} from '../languages/index'



export default class App extends React.Component {
  constructor(props){
    super(props)

    this.state = {
      lang : en,
      translation: en
    }

  }
  
  onLangChange = e => {
    var language = en
    switch(e.target.value){
      case 'en':
        language = en
        break
      case 'pl':
        language = pl
        break
      default:
        language = en
        break
    }
    this.setState({
      lang: e.target.value,
      translation: language
    })
  }

  render() {
    return (
      <Router>
        <Header translation={this.state.translation.header} lang={this.state.lang} onLangChange={this.onLangChange}/>
        <Switch>
          <Route exact path="/" component={() => <Home />} />
          <AuthRoute path='/login' component={() => <Login translation={this.state.translation.login}/>} />
          <Route path="/register" component={() => <Register translation={this.state.translation.register}/>} />
          <Route path="/forgottenpassword" component={() => <ForgottenPassword translation={this.state.translation.forgottenpassword}/>} />
        </Switch>
      </Router>
    )
  }
}
