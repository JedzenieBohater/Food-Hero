const url = process.env.REACT_APP_BACKEND_URL

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
    },
    credentials: 'include'
  })

export const logout = () => 
  fetch(`${url}/logout`, { 
    method: 'DELETE', 
    credentials: 'include' 
  })

export const checkLoggedIn = async preloadedState => {
  const response = await fetch(`${url}/login/status`, { credentials: 'include' })
  const { user } = await response.json()
  preloadedState = {}
  if (user) {
    preloadedState = {
      sessionReducer: user
    }
  }
  return preloadedState
}
