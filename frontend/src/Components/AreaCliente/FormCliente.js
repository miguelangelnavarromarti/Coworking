import React, { Fragment } from 'react';
import axios from "axios";
import { FormGroup, Button, Form, Label, Input, Col, Row } from 'reactstrap';

import ClienteHeader from './ClienteHeader';

const API = 'http://localhost:8000';

class FormCliente extends React.Component {

    constructor(props) {
        super(props);
    
        this.state = {
          datosCliente: [],
          isLoading: false,
          error: null,
        };
      }

    peticionGet=()=>{
      axios.get(API + "/datosClientes/1")  // MODIFICAR I AGAFAR ID CLIENT
        .then(result => this.setState({
            datosCliente: result.data,
            isLoading: false
        }))
        .catch(error => this.setState({
        error,
        isLoading: false
        }));
    }

    componentDidMount() {

    this.setState({ isLoading: true });
    this.peticionGet();
        
    
    }

    render() {
        const { datosCliente, isLoading, error } = this.state;

        if (error) {
        return <p>{error.message}</p>;
        }

        if (isLoading) {
        return <p>Loading ...</p>;
        }


        return (
        
            <div className="container my-5 py-4 px-5  shadow bg-body rounded-3">
            <ClienteHeader/>
            <h1 className="text-center my-4">Modificar Datos Personales</h1>
            
                <Col sm="8" className='m-auto'>
                    <div className="my-5 py-4 px-5  shadow bg-body rounded-3"
                        body   
                        color='primary'                     
                        >                
                        {datosCliente.map((datos)=>
                            <Form key={datos.codigo} className="row">
                                
                                    <div className='col-12'>                                        
                                        <FormGroup>
                                            <Label for="nombre">
                                            Nombre
                                            </Label>
                                            <Input
                                            id="nombre"
                                            name="nombre"
                                            placeholder={datos.nombreUsuario}
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
                                            placeholder={datos.apellido1}
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
                                                placeholder={datos.apellido2}
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
                                            placeholder={datos.codigoPostal}
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
                                            placeholder={datos.telefono}
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
                                            placeholder={datos.email}
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
                                                <a href='/cliente' className='text-decoration-none text-light'>Cancelar</a>
                                        </Button>
                                    </Col>                         
                            </Form>
                        )}                        
                    </div>
                </Col>      
            </div>
        );
    }
}


export default FormCliente;