<<<<<<< HEAD
export const register = user =>
  fetch('http://localhost:18080/login/register', {
    method: 'POST',
    body: JSON.stringify(user),
    headers: {
      'Content-Type': 'application/json'
    }
  })

export const login = user =>
  fetch('http://localhost:18080/login', {
    method: 'POST',
    body: JSON.stringify(user),
    headers: {
      'Content-Type': 'application/json'
    }
  })

export const logout = () => fetch('http://localhost:18080/logout', { method: 'DELETE' })

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
=======
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
>>>>>>> frontend
