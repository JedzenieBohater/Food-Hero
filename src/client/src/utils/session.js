export const register = user =>
  fetch('/api/user/register', {
    method: 'POST',
    body: JSON.stringify(user),
    headers: {
      'Content-Type': 'application/json'
    }
  })

export const login = user =>
  fetch('/api/user/login', {
    method: 'POST',
    body: JSON.stringify(user),
    headers: {
      'Content-Type': 'application/json'
    }
  })

export const logout = () => fetch('/api/user/logout', { method: 'DELETE' })

export const checkLoggedIn = async preloadedState => {
  const response = await fetch('/api/user')
  const { user } = await response.json()
  preloadedState = {}
  if (user) {
    preloadedState = {
      session: user
    }
  }
  return preloadedState
}
