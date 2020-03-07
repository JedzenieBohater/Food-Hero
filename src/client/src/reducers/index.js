import { combineReducers } from 'redux'
import { sessionReducer, errorReducer, languageReducer } from './reducers'

export default combineReducers({
  sessionReducer,
  errorReducer,
  languageReducer,
})
