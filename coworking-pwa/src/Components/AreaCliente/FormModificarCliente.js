import React, { Fragment } from 'react';
import axios from "axios";
import { FormGroup, Button, Form, Label, Input, Col, Row } from 'reactstrap';

import ClienteHeader from './ClienteHeader';
import Login from '../Login';

const API = 'https://api.coworkingmiki.me';

class FormModificarCliente extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            datosCliente: [],
            isLoading: false,
            error: null,
            form: {
                codigo: '',
                nombreUsuario: '',
                nombre: '',
                apellido1: '',
                apellido2: '',
                codigoPostal: '',
                telefono: '',
                email: '',
                password: '',
            },
        };
    }

    peticionGet = () => {
        axios.get(API + "/datosClientes", {
            headers: {
                'authorization': 'Bearer ' + this.props.token,
                'Accept': 'application/json',
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



    peticionPut = () => {
        axios.put(API + "/datosClientes", this.state.form, {
            headers: {
                'authorization': 'Bearer ' + this.props.token,
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
            .then(response => {
                console.log("Enviat!");
                window.location.href = "http://localhost:3000/datosCliente";       //Modifica sa url i me redirigeix aixi    
            })
    }

    redirectCancelar = () => {
        window.location.href = "http://localhost:3000/cliente";
    }

    handleChange = async e => {
        e.persist();
        await this.setState({
            form: {
                ...this.state.form,
                [e.target.name]: e.target.value,
                codigo: document.querySelector('#codigo').value,
                nombreUsuario: document.querySelector('#nombreUsuario').value,
            }
        });
        console.log(this.state.form);
    }

    componentDidMount() {

        this.setState({ isLoading: true });
        this.peticionGet();


    }

    render() {
        const { datosCliente, isLoading, error } = this.state;

        if (this.props.token != null) {

            if (error) {
                return <p>{error.message}</p>;
            }

            if (isLoading) {
                return <p>Loading ...</p>;
            }



            return (

                <div className="container my-1 py-1 px-4 pb-5">
                    <ClienteHeader />
                    <h1 className="text-center my-4">Modificar Datos Personales</h1>

                    <Col sm="8" className='m-auto'>
                        <div className="my-5 py-4 px-5  shadow bg-body rounded-3"
                            body
                            color='primary'
                        >
                            {datosCliente.map((datos) =>
                                <Form key={datos.codigo} className="row">
                                    <input type="hidden" id="codigo" name="codigo" value={datos.codigo} />
                                    <input type="hidden" id="nombreUsuario" name="nombreUsuario" value={datos.nombreUsuario} />

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
                                                onChange={this.handleChange}
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
                                                onChange={this.handleChange}
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
                                                onChange={this.handleChange}
                                            />
                                        </FormGroup>
                                    </div>
                                    <div className='col-6'>
                                        <FormGroup>
                                            <Label for="codigoPostal">
                                                Código Postal
                                            </Label>
                                            <Input
                                                id="codigoPostal"
                                                name="codigoPostal"
                                                placeholder={datos.codigoPostal}
                                                type='number'
                                                onChange={this.handleChange}
                                            />
                                        </FormGroup>
                                    </div>
                                    <div className='col-6'>
                                        <FormGroup>
                                            <Label for="telefono">
                                                Teléfono
                                            </Label>
                                            <Input
                                                id="telefono"
                                                name="telefono"
                                                placeholder={datos.telefono}
                                                type="number"
                                                onChange={this.handleChange}
                                            />
                                        </FormGroup>
                                    </div>
                                    <div className='col-12'>
                                        <FormGroup>
                                            <Label for="email">
                                                Correo Electrónico
                                            </Label>
                                            <Input
                                                id="email"
                                                name="email"
                                                placeholder={datos.email}
                                                type="email"
                                                onChange={this.handleChange}
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
                                                onChange={this.handleChange}
                                            />
                                        </FormGroup>
                                    </div>
                                    <Col sm={2}>
                                        <Button
                                            color='success'
                                            onClick={() => this.peticionPut()}>
                                            Guardar
                                        </Button>
                                    </Col>
                                    <Col sm={2}>
                                        <Button
                                            color='danger'
                                            onClick={() => this.redirectCancelar()}>
                                            Cancelar
                                        </Button>
                                    </Col>
                                </Form>
                            )}
                        </div>
                    </Col>
                </div>
            );
        }
        else {
            return (
                <Login />
            );
        }
    }
}


export default FormModificarCliente;