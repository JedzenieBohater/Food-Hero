import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import profile from 'static/earth.bmp'
import { logout } from 'actions/session'
import { connect } from 'react-redux'

class Dropdown extends Component {
  constructor(props) {
    super(props)

    this.state = {
      showMenu: false,
    }
  }

  showMenu = event => {
    event.preventDefault()

    this.setState({ showMenu: true }, () => {
      document.addEventListener('click', this.closeMenu)
    })
  }

  closeMenu = () => {
    this.setState({ showMenu: false }, () => {
      document.removeEventListener('click', this.closeMenu)
    })
  }

  render() {
    return (
      <>
        <label onClick={this.showMenu} className="user-header">
          <img src={profile} alt="User" />
        </label>

        {this.state.showMenu ? (
          <ul className="menu">
            <li>
              <Link to="/profile" className="no-link-color">
                {this.props.lang.profile}
              </Link>
            </li>
            <li>
              {' '}
              <Link to="/search" className="no-link-color">
                {this.props.lang.search}
              </Link>{' '}
            </li>
            <li>
              {' '}
              <Link
                to="/"
                className="no-link-color"
                onClick={() => this.props.logout()}
              >
                {this.props.lang.logout}
              </Link>
            </li>
          </ul>
        ) : null}
      </>
    )
  }
}

const mapStateToProps = ({ languageReducer }) => ({
  lang: languageReducer.header.dropdown,
})

const mapDispatchToProps = dispatch => ({
  logout: () => dispatch(logout()),
})

export default connect(mapStateToProps, mapDispatchToProps)(Dropdown)
