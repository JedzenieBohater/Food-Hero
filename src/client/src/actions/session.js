import * as ApiUtils from '../utils/session'

export const REGISTER_USER = 'REGISTER_USER'

export const LOGIN_USER = 'LOGIN_USER'

export const LOGOUT = 'LOGOUT'

export const RECEIVE_CURRENT_USER = 'RECEIVE_CURRENT_USER'

const receiveCurrentUser = user => ({
  type: RECEIVE_CURRENT_USER,
  user
})

export const login = user => async dispatch => {
  const response = await ApiUtils.login(user)
  const data = await response.json()

  if (response.ok) dispatch(receiveCurrentUser(user))
}

export const register = user => async dispatch => {
  const response = await ApiUtils.register(user)
  const data = await response.json()
}

export const logout = () => async dispatch => {
  const response = await ApiUtils.logout()
  const data = await response.json()
}
