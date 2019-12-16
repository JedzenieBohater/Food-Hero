export const register = user =>
  fetch('http://some_backend:8080/login/register', {
    method: 'POST',
    body: JSON.stringify(user),
    headers: {
      'Content-Type': 'application/json'
    }
  })

export const login = user =>
  fetch('http://some_backend:8080/login', {
    method: 'POST',
    body: JSON.stringify(user),
    headers: {
      'Content-Type': 'application/json'
    }
  })

export const logout = () => fetch('http://some_backend:8080/logout', { method: 'DELETE' })

export const checkLoggedIn = async preloadedState => {
  const response = await fetch('http://localhost:18080/login/status')
  const { user } = await response.json()
  preloadedState = {}
  if (user) {
    preloadedState = {
      session: user
    }
  }
  return preloadedState
}
