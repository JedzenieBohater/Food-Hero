import React from 'react'
import { shallow } from 'enzyme'
import Header from './index'
import { findByTestAttr } from '../../../utils/test'

describe('<Header />', () => {
    let wrapper
    beforeEach(() => {
        wrapper = shallow(<Header />)
    })

    it('Should render without errors', () => {
        expect(findByTestAttr(wrapper, 'top-bar').length).toBe(1)
    })
})