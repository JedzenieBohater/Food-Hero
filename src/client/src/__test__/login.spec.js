import { testStore } from '../utils/test'
import { login } from '../actions/session'
import fetchMock from 'fetch-mock'

describe('Login action', () => {
    afterEach(() => {
        fetchMock.restore()
    })

    test('User login success', async () => {
        const store = testStore({})
        const expectedState = {
            userId: 'fjdskfj124298fu32hufu'
        }
        fetchMock.post('http://localhost:18080/login', {
            body: expectedState,
            status: 200,
            headers: {
                'Content-Type': 'application/json'
            }
        })
        await store.dispatch(login({}))
        expect(store.getState().sessionReducer.userId).toBe(expectedState.userId)
    })

    test('User login failure', async () => {
        const store = testStore({})
        const expectedState = {
            message: 'Password incorrect'
        }
        fetchMock.post('http://localhost:18080/login', {
            body: expectedState,
            status: 404,
            headers: {
                'Content-Type': 'application/json'
            }
        })
        await store.dispatch(login({}))
        expect(store.getState().errorReducer).toBe(expectedState.message)
    })
})