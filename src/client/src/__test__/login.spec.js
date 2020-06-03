import { testStore } from 'utils/test'
import { login } from 'actions/session'
import fetchMock from 'fetch-mock'

describe('Login action', () => {
  afterEach(() => {
    fetchMock.restore()
  })

  test('User login success', async () => {
    const store = testStore({})
    const expectedState = {
      userID: 'fjdskfj124298fu32hufu',
    }
    fetchMock.post('/api/login', {
      body: expectedState,
      status: 200,
      headers: {
        'Content-Type': 'application/json',
      },
    })
    await store.dispatch(login({}))
    expect(store.getState().sessionReducer.userID).toBe(expectedState.userID)
  })

  test('User login failure', async () => {
    const store = testStore({})
    const expectedState = {
      message: 'Password incorrect',
    }
    fetchMock.post('/api/login', {
      body: expectedState,
      status: 404,
      headers: {
        'Content-Type': 'application/json',
      },
    })
    await store.dispatch(login({}))
    expect(store.getState().errorReducer).toBe(expectedState.message)
  })
})
