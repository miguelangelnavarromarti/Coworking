import React, {Component, Fragment} from 'react';
import { BrowserRouter as Router, Link, Route, Routes } from 'react-router-dom';
import { Navbar,NavItem, Collapse, Nav, NavbarToggler, NavLink} from 'reactstrap';

class ClienteHeader extends Component {
    constructor(props) {
        super(props);
        this.toggle = this.toggle.bind(this);
        this.state = {
        isOpen: false
        };
    }
    toggle() {
        this.setState({
          isOpen: !this.state.isOpen
        });
      }

    render(){
        return (
            <React.Fragment>
                <Navbar                    
                    expand="sm"
                    light
                    className='shadow rounded-3 bg-blauFort'                                   
                >
                    <NavbarToggler onClick={this.toggle} />
                    <Collapse isOpen={this.state.isOpen} navbar>
                        <Nav
                            className="mx-auto"
                            navbar
                        >
                        <NavItem>
                            <NavLink href="/datosCliente" className='text-light mx-5'>Datos Personales</NavLink>                            
                        </NavItem>
                        <NavItem>                
                            <NavLink href="/reservas" className='text-light mx-5'>Reservas</NavLink>
                        </NavItem>                    
                        <NavItem>
                            <NavLink href="/opiniones" className='text-light mx-5'>Opiniones</NavLink>
                        </NavItem>
                        <NavItem>
                            <NavLink href="/facturas" className='text-light mx-5'>Facturas</NavLink>
                        </NavItem>
                        <NavItem>
                            <NavLink href="/facturasCanceladas" className='text-light mx-5'>Facturas Canceladas</NavLink>
                        </NavItem>
                        </Nav>
                    </Collapse>           
                </Navbar>
            </React.Fragment>
        );
    }
}

export default ClienteHeader;