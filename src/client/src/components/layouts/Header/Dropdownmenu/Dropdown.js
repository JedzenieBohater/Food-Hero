import React, { Component } from 'react';
import { Link } from 'react-router-dom'
import  profile  from '../../../../static/earth.bmp'

class Dropdown extends Component {
  constructor(props) {
    super(props);

    this.state = {
      showMenu: false
    };
  }

  showMenu = (event) => {
    event.preventDefault();

    this.setState({ showMenu: true }, () => {
      document.addEventListener('click', this.closeMenu);
    });
  }

  closeMenu = () => {
    this.setState({ showMenu: false }, () => {
      document.removeEventListener('click', this.closeMenu);
    });
  }

  render() {
    return (
      <>

          <label onClick={this.showMenu} className="user-header">
            <img  src={profile} alt="User"/>
            Username
          </label> 


        {
          this.state.showMenu
            ? (
              <ul className="menu">
                <li ><Link to="profile" className="no-link-color"> Profile</Link></li>
                <li> <Link to="search" className="no-link-color">Search</Link> </li>
                <li> <Link to="search" className="no-link-color">Menu item 3</Link></li>
              </ul>

            )
            : (
              null
            )
        }
      </>
    );
  }
}

export default Dropdown