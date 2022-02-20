import React, { Component } from "react";
import { FormGroup, Button, Form, Label, Input, Col, } from 'reactstrap';

class Registro extends Component {
    render() {
        return (
            <div className="container my-5 py-4 px-5  shadow bg-body rounded-3">            
            <h1 className="text-center my-4">Registro</h1>
            
            <Col sm="8" className='m-auto'>
                    <div className="my-5 py-4 px-5  shadow bg-body rounded-3"
                        body   
                        color='primary'                     
                        >                                        
                        <Form className="row">
                            <div className='col-6'>                                        
                                <FormGroup>
                                    <Label for="nombreUsuario">
                                    Nombre de Usuario
                                    </Label>
                                    <Input
                                    id="nombreUsuario"
                                    name="nombreUsuario"
                                    type="text  "
                                    />
                                </FormGroup>                                        
                            </div>
                            <div className='col-6'>                                        
                                <FormGroup>
                                    <Label for="nombre">
                                    Nombre
                                    </Label>
                                    <Input
                                    id="nombre"
                                    name="nombre"
                                    type="text"
                                    />
                                </FormGroup>                                        
                            </div>
                            <div className='col-6'>                                        
                                <FormGroup>
                                    <Label for="apellido1">
                                    Primer Apellido
                                    </Label>
                                    <Input
                                    id="apellido1"
                                    name="apellido1"
                                    type="text"
                                    />
                                </FormGroup>                                        
                            </div>
                            <div className='col-6'>
                                <FormGroup>
                                        <Label for="apellido2">
                                        Segundo Apellido
                                        </Label>
                                        <Input
                                        id="apellido2"
                                        name="apellido2"                                                
                                        type="text"
                                        />
                                </FormGroup>
                            </div>
                            <div className='col-6'>                                         
                                <FormGroup>
                                    <Label for="codigoPostal">
                                    Codigo Postal
                                    </Label>
                                    <Input
                                    id="codigoPostal"
                                    name="codigoPostal"
                                    placeholder='07500'
                                    type='number'
                                    />
                                </FormGroup>
                            </div>
                            <div className='col-6'>
                                <FormGroup>
                                    <Label for="telefono">
                                    Telefono
                                    </Label>
                                    <Input
                                    id="telefono"
                                    name="telefono"
                                    placeholder='666777888'
                                    type="number"
                                    />
                                </FormGroup>
                            </div>
                            <div className='col-12'>
                                <FormGroup>
                                    <Label for="email">
                                    Correo Electronico
                                    </Label>
                                    <Input
                                    id="email"
                                    name="email"
                                    placeholder='email@email.com'
                                    type="email"
                                    />
                                </FormGroup>
                            </div>                                    
                            <div className='col-12'>
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
                            </div> 
                                <Col sm={2}>
                                <Button
                                    color='success'>
                                    Guardar
                                </Button>                                       
                            </Col>    
                            <Col sm={2}>
                                <Button
                                        color='danger'>
                                        <a href='/' className='text-decoration-none text-light'>Cancelar</a>
                                </Button>
                            </Col>                         
                        </Form>                       
                    </div>
                </Col>
            </div>
        );
    }
}

export default Registro;