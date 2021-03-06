import * as ApiUtils from '../utils/session'
import { receiveErrors } from './errors'

export const LOGOUT_CURRENT_USER = 'LOGOUT_CURRENT_USER'

export const RECEIVE_CURRENT_USER = 'RECEIVE_CURRENT_USER'

const receiveCurrentUser = user => ({
  type: RECEIVE_CURRENT_USER,
  user,
})

const logoutCurrentUser = () => ({
  type: LOGOUT_CURRENT_USER,
})

export const login = user => async dispatch => {
  const response = await ApiUtils.login(user)
  const data = await response.json()

  if (response.ok) return dispatch(receiveCurrentUser(data))

  return dispatch(receiveErrors(data))
}

export const register = user => async dispatch => {
  const response = await ApiUtils.register(user)
  const data = await response.json()

  if (!response.ok) return dispatch(receiveErrors(data))
}

export const logout = () => async dispatch => {
  const response = await ApiUtils.logout()

  if (response.ok) return dispatch(logoutCurrentUser())

  return dispatch(receiveErrors('Problem while logging out'))
}
