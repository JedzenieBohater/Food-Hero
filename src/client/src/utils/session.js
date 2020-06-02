export const register = user =>
  fetch('/api/login/register', {
    method: 'POST',
    body: JSON.stringify(user),
    headers: {
      'Content-Type': 'application/json',
    },
  })

export const login = user =>
  fetch('/api/login', {
    method: 'POST',
    body: JSON.stringify(user),
    headers: {
      'Content-Type': 'application/json',
    },
  })

export const logout = () =>
  fetch('/api/logout', {
    method: 'POST',
  })

export const checkLoggedIn = async preloadedState => {
  const response = await fetch('/api/login/status')
  const user = await response.json()
  preloadedState = {}
  if (user) {
    preloadedState = {
      sessionReducer: user,
    }
  }
  return preloadedState
}
