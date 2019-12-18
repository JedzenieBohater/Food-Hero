import { combineReducers } from 'redux'
import { sessionReducer, errorReducer } from './reducers'

export default combineReducers({ sessionReducer, errorReducer })
