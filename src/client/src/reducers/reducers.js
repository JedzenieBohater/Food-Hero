import { RECEIVE_CURRENT_USER, LOGOUT_CURRENT_USER } from '../actions/session'
import { RECEIVE_ERRORS, CLEAR_ERRORS } from '../actions/errors'
import {CHANGE_LANGUAGE} from '../actions/language'
import { en } from '../languages'

const nullSession = { userId: null, accessToken: null, refreshToken: null }

export function sessionReducer(state = nullSession, { type, user }) {
  Object.freeze(state)
  switch (type) {
    case LOGOUT_CURRENT_USER:
      return {
        ...state,
        ...nullSession
      }
    case RECEIVE_CURRENT_USER:
      return {
        ...state,
        ...user
      }
    default:
      return state
  }
}

export function errorReducer(state = '', { message, type }) {
  Object.freeze(state)
  switch (type) {
    case CLEAR_ERRORS:
      return ''
    case RECEIVE_ERRORS:
      return message
    default:
      return state
  }
}

export function languageReducer(state = en, {lang, type}) {
  Object.freeze(state)
  switch(type){
    case CHANGE_LANGUAGE:
      return lang
    default:
      return state
  }
}