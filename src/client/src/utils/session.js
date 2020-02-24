export const register = user =>
  fetch('/login/register', {
    method: 'POST',
    body: JSON.stringify(user),
    headers: {
      'Content-Type': 'application/json'
    }
  })

export const login = user => 
  fetch('/login', {
    method: 'POST',
    body: JSON.stringify(user),
    headers: {
      'Content-Type': 'application/json'
    },
    credentials: 'include'
  })

export const logout = () => 
  fetch('/logout', { 
    method: 'DELETE',
    credentials: 'include'
  })

export const checkLoggedIn = async preloadedState => {
  const response = await fetch('/login/status', { credentials: 'include' })
  const { user } = await response.json()
  preloadedState = {}
  if (user) {
    preloadedState = {
      sessionReducer: user
    }
  }
  return preloadedState
}
