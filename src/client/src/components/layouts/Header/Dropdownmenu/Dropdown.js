import React, { Component } from 'react';
import { Link } from 'react-router-dom'

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
      <span>
        <button onClick={this.showMenu}>
          User
        </button>

        {
          this.state.showMenu
            ? (
              <ul className="menu">
                <li><Link to="profile"> Profile </Link></li>
                <li> Menu item 2 </li>
                <li> Menu item 3 </li>
              </ul>

            )
            : (
              null
            )
        }
      </span>
    );
  }
}

export default Dropdown