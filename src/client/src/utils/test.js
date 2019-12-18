import checkPropTypes from 'check-prop-types'
import { createStore, applyMiddleware } from 'redux'
import thunk from 'redux-thunk'
import reducers from '../reducers'

export const testStore = preloadedState => (
    createStore(
        reducers,
        preloadedState,
        applyMiddleware(thunk)
    )
)

export const findByTestAttr = (component, attr) => component.find(`[test-data='${attr}']`)

export const checkProps = (component, expectedProps) => checkPropTypes(component.propTypes, expectedProps, 'props', component.name)
