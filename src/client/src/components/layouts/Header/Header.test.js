import React from 'react'
import { shallow } from 'enzyme'
import Header from './index'
import { findByTestAttr } from '../../../utils/test'

describe('<Header />', () => {
    let component;
    beforeEach(() => {
        component = shallow(<Header />)
    })

    it('Should render without errors', () => {
        expect(findByTestAttr(component, 'top-bar').length).toBe(1)
    })
})