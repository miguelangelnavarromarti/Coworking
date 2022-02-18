import React, {Component} from 'react';
import { BrowserRouter as Router, Link, Route, Routes } from 'react-router-dom';
import { Navbar,NavItem, Collapse, Nav, NavbarToggler, NavLink} from 'reactstrap';

class ClienteHeader extends Component {
    render(){
        return (
            <div>
                <Navbar
                    color="light"
                    expand="sm"
                    light                                   
                >
                    <NavbarToggler onClick={function noRefCheck(){}} />
                    <Collapse navbar>
                        <Nav
                            className="mx-auto"
                            navbar
                        >
                        <NavItem>
                            <NavLink href="/datosCliente" className='text-dark mx-5'>Datos Personales</NavLink>                            
                        </NavItem>
                        <NavItem>                
                            <NavLink href="/reservas" className='text-dark mx-5'>Reservas</NavLink>
                        </NavItem>                    
                        <NavItem>
                            <NavLink href="/opiniones" className='text-dark mx-5'>Opiniones</NavLink>
                        </NavItem>
                        <NavItem>
                            <NavLink href="/facturas" className='text-dark mx-5'>Facturas</NavLink>
                        </NavItem>
                        </Nav>
                    </Collapse>           
                </Navbar>
            </div>
        );
    }
}

export default ClienteHeader;