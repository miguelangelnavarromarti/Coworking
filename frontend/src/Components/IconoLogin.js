import React, { Component, Fragment } from "react";
import { Link } from "react-router-dom";
import { Col } from "reactstrap";


const API = 'http://localhost:8000';
 
class IconoLogin extends Component {
    constructor(props) {
        super(props);   
        
      }

      logOut=()=>{
        localStorage.removeItem("token");  
        //reload                             
        window.location.reload(true);
      }
    render() {

    const { token } = this.props;

    if(token != null){
    return  (<React.Fragment>              
                    <Col className='text-center mt-3'>
                      <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" fill="currentColor" class="bi bi-person-circle mx-2" viewBox="0 0 16 16" aria-label="Area Cliente">
                            <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
                            <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
                      </svg>
                      <Link className='text-decoration-none text-dark' to="/cliente">Area Cliente</Link>
                      <Link className='text-decoration-none text-dark ms-4' to="/" onClick={()=>this.logOut()}>LogOut</Link>                      
                      <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" fill="currentColor" class="bi bi-person-x-fill mx-2" viewBox="0 0 16 16">
                        <path fill-rule="evenodd" d="M1 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm6.146-2.854a.5.5 0 0 1 .708 0L14 6.293l1.146-1.147a.5.5 0 0 1 .708.708L14.707 7l1.147 1.146a.5.5 0 0 1-.708.708L14 7.707l-1.146 1.147a.5.5 0 0 1-.708-.708L13.293 7l-1.147-1.146a.5.5 0 0 1 0-.708z"/>
                      </svg>
                      
                    </Col>                
            </React.Fragment>);
    } else{
        return  (<React.Fragment> 
                    <Col className='text-center mt-3'>
                    <Link className='text-decoration-none text-dark' to="/login">Login</Link>
                    <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" fill="black" class="bi bi-door-open mx-2" viewBox="0 0 16 16" aria-label="Login">
                        <path d="M8.5 10c-.276 0-.5-.448-.5-1s.224-1 .5-1 .5.448.5 1-.224 1-.5 1z"/>
                        <path d="M10.828.122A.5.5 0 0 1 11 .5V1h.5A1.5 1.5 0 0 1 13 2.5V15h1.5a.5.5 0 0 1 0 1h-13a.5.5 0 0 1 0-1H3V1.5a.5.5 0 0 1 .43-.495l7-1a.5.5 0 0 1 .398.117zM11.5 2H11v13h1V2.5a.5.5 0 0 0-.5-.5zM4 1.934V15h6V1.077l-6 .857z"/>
                    </svg>                                    
                    </Col>               
                </React.Fragment>);
    }

      

    
  }
}
 
export default IconoLogin;