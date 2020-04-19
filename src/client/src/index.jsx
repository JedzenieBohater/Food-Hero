import React from 'react'
import ReactDOM from 'react-dom'
import App from './components/App'
import thunk from 'redux-thunk'
import reducers from './reducers'
import { createStore, applyMiddleware } from 'redux'
import { Provider } from 'react-redux'
import { checkLoggedIn } from './utils/session'

const renderApp = preloadedState => {
  const store = createStore(reducers, preloadedState, applyMiddleware(thunk))
  ReactDOM.render(
    <Provider store={store}>
      <App />
    </Provider>,
    document.getElementById('root')
  )
}

;(async () => renderApp(await checkLoggedIn()))()
//;(async () => renderApp(await {}))()
