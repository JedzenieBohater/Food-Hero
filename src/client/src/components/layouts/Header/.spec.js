import React from 'react'
import { shallow } from 'enzyme'
import { Header } from './Header'
import { findByTestAttr } from 'utils/test'

describe('<Header />', () => {
  let wrapper
  beforeEach(() => {
    const props = {
      changeLang: () => {},
      lang: {
        login: '',
        register: '',
      },
    }
    wrapper = shallow(<Header {...props} />)
  })

  it('Should render without errors', () => {
    expect(findByTestAttr(wrapper, 'top-bar').length).toBe(1)
  })
})
