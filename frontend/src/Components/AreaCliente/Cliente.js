import React from 'react';
import axios from "axios";
import { Table, Button, Card, CardBody, CardTitle, CardSubtitle, CardText, Col, Row } from 'reactstrap';

import ClienteHeader from './ClienteHeader';
import Login from '../Login';

const API = 'http://localhost:8000';

class Cliente extends React.Component {

    constructor(props) {
        super(props);
    
        this.state = {
          datosCliente: [],
          isLoading: false,
          error: null,
        };
      }
    componentDidMount() {


    this.setState({ isLoading: true });
    //const token = localStorage.getItem("token");
    axios.get(API + "/datosClientes",{
        headers: {
            'authorization':'Bearer ' + this.props.token,
            'Accept' : 'application/json',
            'Content-Type': 'application/json'
        }
    })
        .then(result => this.setState({
            datosCliente: result.data,
            isLoading: false
        }))
        .catch(error => this.setState({
        error,
        isLoading: false
        }));
    }

    render() {

        if(this.props.token != null){
            const { datosCliente, isLoading, error } = this.state;

            if (error) {
            return <p>{error.message}</p>;
            }

            if (isLoading) {
            return <p>Loading ...</p>;
            }


            return (
            
                <div className="container my-1 py-1 px-4 pb-5">
                <ClienteHeader/>
                <h1 className="text-center my-4">Datos Personales</h1>
                
                    <Col sm="8" className='m-auto'>
                        <Card className="my-5 py-4 px-5  shadow bg-body rounded-3 bg-degradatInvertit text-white border-2 border-warning"
                            body                                                    
                            >                
                            {datosCliente.map((datos)=>
                                <CardBody>
                                    <CardTitle key={datos.codigo} tag="h2" className='text-center mb-4 fs-4'>
                                    Usuario {datos.nombreUsuario}
                                    </CardTitle>
                                    <CardText>
                                        <Row>
                                            <p className='fs-5'>Nombre: {datos.nombre} {datos.apellido1} {datos.apellido2}</p>
                                            <p className='fs-5'>Teléfono: {datos.telefono}</p>
                                            <p className='fs-5'>Email: {datos.email}</p>
                                            <p className='fs-5'>Código Postal: {datos.codigoPostal}</p>                                        
                                        </Row>
                                    </CardText>
                                    <Button
                                        color="warning"
                                        size=""                                    
                                    >
                                        <a href='/formCliente' className='fw-normal text-decoration-none text-dark'>Modificar Datos</a>
                                    </Button>
                                </CardBody>
                            )}                        
                        </Card>
                    </Col>      
                </div>
            );
        }
        else {
            return (
                <Login/>
            );
        }
    }
}


export default Cliente;