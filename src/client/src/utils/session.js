const url = 'http://localhost:18080'

export const register = user =>
  fetch(`${url}/login/register`, {
    method: 'POST',
    body: JSON.stringify(user),
    headers: {
      'Content-Type': 'application/json'
    }
  })

export const login = user =>
  fetch(`${url}/login`, {
    method: 'POST',
    body: JSON.stringify(user),
    headers: {
      'Content-Type': 'application/json'
    }
  })

export const logout = () => fetch(`${url}/logout`, { method: 'DELETE' })

export const checkLoggedIn = async preloadedState => {
  const response = await fetch(`${url}/login/status`)
  const { user } = await response.json()
  preloadedState = {}
  if (user) {
    preloadedState = {
      session: user
    }
  }
  return preloadedState
}
