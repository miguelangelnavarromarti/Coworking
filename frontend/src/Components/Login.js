import React, { Component } from "react";
import { FormGroup, Button, Form, Label, Input, Col, } from 'reactstrap';

class Login extends Component {
    constructor(props) {
        super(props);   
        
    }
    
    render() {
        return (
            <div className="container my-5 py-4 px-5  shadow bg-body rounded-3">            
            <h1 className="text-center my-4">Login</h1>
            
                <Col sm="5" className='m-auto'>
                    <div className="my-5 py-4 px-5  shadow bg-body rounded-3"
                        body   
                        color='primary'                     
                        >                                        
                        <Form className="row">                                                                                                          
                            <FormGroup>
                                <Label for="nombreUsuario">
                                Usuario
                                </Label>
                                <Input
                                id="nombreUsuario"
                                name="nombreUsuario"                                 
                                type="text"
                                />
                            </FormGroup>                                                                                                                                                      
                            <FormGroup>
                                <Label for="password">
                                Contraseña
                                </Label>
                                <Input
                                id="password"
                                name="password"
                                type="password"
                                />
                            </FormGroup>                                                                                                                
                            <Col sm={2}>
                                <Button
                                    color='primary'>
                                    Login
                                </Button>                                       
                            </Col>    
                            <Col sm={2}>
                                <Button
                                    color='danger'>
                                    <a href='/registro' className='text-decoration-none text-light'>Registrarse</a>
                                </Button>
                            </Col>                         
                        </Form>                                                
                    </div>
                </Col>      
            </div>
        );
    }
}

export default Login;