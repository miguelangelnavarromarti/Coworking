import React, { Component } from "react";

class Header extends Component {
    constructor(props) {
        super(props);
    
        this.state = {
        };
      }
      render(){
          return(
              <div>
                <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                    <div class="container-fluid">
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <a class="navbar-brand text-warning" href="#">Coworking</a>
                        <div class="collapse navbar-collapse justify-content-center" id="navbarSupportedContent">
                            <ul class="navbar-nav mb-2 mb-lg-0">
                                <li class="nav-item">
                                    <a class="nav-link active" aria-current="page" href="#">Inicio </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link text-warning" href="#">Galeria</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link text-warning" href="#">Precios</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link text-warning" href="#">Contacto</a>
                                </li>
                            </ul>
                        </div>
                        <a class="text-warning" href="#">Login
                            <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
                                <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
                                <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
                            </svg>
                        </a>
                    </div>
                </nav>
              </div>
          );
      }
    }

export default Header;