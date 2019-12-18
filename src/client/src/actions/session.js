import * as ApiUtils from '../utils/session'
import { receiveErrors } from './errors'

export const LOGOUT_CURRENT_USER = 'LOGOUT_CURRENT_USER'

export const RECEIVE_CURRENT_USER = 'RECEIVE_CURRENT_USER'

const receiveCurrentUser = user => ({
  type: RECEIVE_CURRENT_USER,
  user
})

const logoutCurrentUser = () => ({
  type: LOGOUT_CURRENT_USER
})

export const login = user => async dispatch => {
  const response = await ApiUtils.login(user)
  const data = await response.json()
  
  if (response.ok) return dispatch(receiveCurrentUser(data))

<<<<<<< HEAD
  return dispatch(receiveErrors(data))
=======
  if (response.ok) dispatch(receiveCurrentUser(data))
  else dispatch(receiveErrors(data))
>>>>>>> frontend
}

export const register = user => async dispatch => {
  const response = await ApiUtils.register(user)
  const data = await response.json()

  if (response.ok) return dispatch(receiveCurrentUser(data))

  return dispatch(receiveErrors(data))
}

export const logout = () => async dispatch => {
  const response = await ApiUtils.logout()
  const data = await response.json()

  if (response.ok) return dispatch(logoutCurrentUser())

  return dispatch(receiveErrors(data))
}
