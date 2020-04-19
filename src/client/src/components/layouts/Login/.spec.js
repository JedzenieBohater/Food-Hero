import React from 'react'
import { shallow } from 'enzyme'
import { findByTestAttr, checkProps } from 'utils/test'
import { Login } from './Login'

describe('<Login />', () => {
  describe('Checking PropTypes', () => {
    it('Should NOT throw a warning', () => {
      const expectedProps = {
        login: user => {},
        lang: {
          email: '',
          password: '',
          login: '',
          noAccount: '',
          forget: '',
        },
        errors: '',
      }

      expect(checkProps(Login, expectedProps)).toBeUndefined()
    })
  })

  describe('Renders', () => {
    let wrapper
    beforeEach(() => {
      const props = {
        login: user => {},
        lang: {
          email: '',
          password: '',
          login: '',
          noAccount: '',
          forget: '',
        },
        errors: '',
      }
      wrapper = shallow(<Login {...props} />)
    })

    it('Should render without errors', () => {
      expect(findByTestAttr(wrapper, 'wrapper').length).toBe(1)
    })

    it('Should render a form', () => {
      expect(findByTestAttr(wrapper, 'form').length).toBe(1)
    })

    it('Should render a submit button', () => {
      expect(findByTestAttr(wrapper, 'submit').length).toBe(1)
    })

    it('Should render a register buttn', () => {
      expect(findByTestAttr(wrapper, 'register').length).toBe(1)
    })
  })
})
