import React, { Component } from "react";
import { Link } from "react-router-dom";


const API = 'https://api.coworkingmiki.me';

class IconoLogin extends Component {
  constructor(props) {
    super(props);

  }

  logOut = () => {
    localStorage.removeItem("token");
    window.location.reload(true);
  }
  render() {

    const { token } = this.props;

    if (token != null) {
      return (
        <div className='text-center fs-5'>
          <svg xmlns="http://www.w3.org/2000/svg" width="26" height="26" fill="currentColor" className="bi bi-person-circle mx-2" viewBox="0 0 16 16" aria-label="Area Cliente">
            <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z" />
            <path fillRule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z" />
          </svg>
          <Link className='text-decoration-none text-dark' to="/cliente">Área Cliente</Link>
          <Link className='text-decoration-none text-dark ms-4 me-4' to="/" onClick={() => this.logOut()}>LogOut</Link>

        </div>
      );
    } else {
      return (
        <div className='text-center fs-5'>
          <Link className='text-decoration-none text-dark' to="/login">LogIn</Link>
          <svg xmlns="http://www.w3.org/2000/svg" width="26" height="26" fill="black" className="bi bi-door-open mx-2" viewBox="0 0 16 16" aria-label="Login">
            <path d="M8.5 10c-.276 0-.5-.448-.5-1s.224-1 .5-1 .5.448.5 1-.224 1-.5 1z" />
            <path d="M10.828.122A.5.5 0 0 1 11 .5V1h.5A1.5 1.5 0 0 1 13 2.5V15h1.5a.5.5 0 0 1 0 1h-13a.5.5 0 0 1 0-1H3V1.5a.5.5 0 0 1 .43-.495l7-1a.5.5 0 0 1 .398.117zM11.5 2H11v13h1V2.5a.5.5 0 0 0-.5-.5zM4 1.934V15h6V1.077l-6 .857z" />
          </svg>
        </div>
      );
    }




  }
}

export default IconoLogin;