import React, { Component } from "react";
import { FormGroup, Button, Form, Label, Input, Col, } from 'reactstrap';
import axios from "axios";

const API = 'http://localhost:8000';


class Login extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isLoading: false,
            error: null,
            form: {
                email: '',
                password: '',
            },
        };

    }


    peticionPost = () => {
        axios.post(API + "/api/login", this.state.form)
            .then(response => {
                localStorage.removeItem("token");
                localStorage.setItem("token", response.data.token);
                window.location.href = "http://localhost:3000/reservas";       //Modifica sa url i me redirigeix aixi                      
            })
            .catch(error => this.setState({
                error,
                isLoading: false
            }));

    }


    redirectRegistros = () => {
        window.location.href = "http://localhost:3000/registro";
    }

    handleChange = async e => {
        e.persist();
        await this.setState({
            form: {
                ...this.state.form,
                [e.target.name]: e.target.value,
            }
        });
        //console.log(this.state.form);
    };



    render() {

        const { isLoading, error } = this.state;

        if (error) {
            return <p>{error.message}</p>;
        }

        if (isLoading) {
            return <p>Loading ...</p>;
        }
        return (
            <div className="container my-5">
                <h1 className="text-center">Login</h1>

                <Col sm="5" className='mx-auto pb-5'>
                    <div className="my-5 py-4 px-5  shadow bg-body rounded-3"
                        body
                        color='primary'
                    >
                        <Form className="row">
                            <FormGroup>
                                <Label for="email">
                                    Email
                                </Label>
                                <Input
                                    id="email"
                                    name="email"
                                    type="text"
                                    onChange={this.handleChange}
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
                                    onChange={this.handleChange}
                                />
                            </FormGroup>
                            <Col xxl={2}>
                                <Button
                                    className="bg-blauFort border-groc border-2"
                                    onClick={() => this.peticionPost()}>
                                    Login
                                </Button>
                            </Col>
                            <Col xxl={1} />
                            <Col xxl={2}>
                                <Button
                                    className="bg-blauFort border-groc border-2"
                                    onClick={() => this.redirectRegistros()}>
                                    Registrarse
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