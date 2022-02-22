import React, { Component } from "react";
import axios from "axios";
import { Table, Button, Card, CardBody, CardTitle, Col, CardText, Input, Row, CardGroup, FormGroup, Form, Label, Modal, ModalHeader, ModalBody, ModalFooter} from 'reactstrap';

import ClienteHeader from "./ClienteHeader";

const API = 'http://localhost:8000';
 
class FormCrearOpinion extends Component {
    constructor(props) {
        super(props);
    
        this.state = {
          opiniones: [],
          isLoading: false,
          error: null,
          form:{
              titulo:'',
              descripcion:'',
              puntuacion:'',
          }
        }; 
      }

    peticionGet=()=>{
        axios.get(API + "/opiniones/1")  // MODIFICAR I AGAFAR ID CLIENT
        .then(result => this.setState({
            opiniones: result.data,
            isLoading: false
        }))
        .catch(error => this.setState({
        error,
        isLoading: false
        }));    
    }

    peticionPost=async()=>{
        await axios.post(API+"/opiniones/1",this.state.form)
        .then(response=>{
            this.peticionGet();
        })
        .catch(error => this.setState({
            error,
            isLoading: false
        }));
    }

    handleChange=async e=>{
        e.persist();
        await this.setState({
            form:{
                ...this.state.form,
                [e.target.name]: e.target.value
            }
        });
        console.log(this.state.form);
    };
    
    

    componentDidMount() {

    this.setState({ isLoading: true });
    this.peticionGet();
    

    
    }

    render() {
        const { form, isLoading, error } = this.state;

        if (error) {
        return <p>{error.message}</p>;
        }

        if (isLoading) {
        return <p>Loading ...</p>;
        }

    return (
     
        <div className="container my-5 py-4 px-5  shadow bg-body rounded-3">
            <ClienteHeader/>    
            <h1 className="text-center my-4">Nueva opinión</h1>        
            <Col sm="8" className='m-auto'>
                <div className="my-5 py-4 px-5  shadow bg-body rounded-3"
                    body   
                    color='primary'                     
                >
                    <Form className="row">                        
                        <div className='col-12'>
                            <FormGroup>
                                <Label for="titulo">
                                    Titulo
                                </Label>
                                <Input
                                    id="titulo"
                                    name="titulo"                                
                                    type="text"
                                    onChange={this.handleChange}
                                    value={form.titulo}
                                />  
                            </FormGroup>
                        </div>
                        <div className='col-12'>
                            <FormGroup>
                                <Label for="descripcion">
                                    Descripción
                                </Label>
                                <Input
                                    id="descripcion"
                                    name="descripcion"                                
                                    type="textarea"
                                    onChange={this.handleChange}
                                    value={form.descripcion}
                                />  
                            </FormGroup>
                        </div> 
                        <div className='col-12'>
                            <FormGroup>
                                <Label for="puntuacion">
                                    Puntuación
                                </Label>
                                <Input
                                    id="puntuacion"
                                    name="puntuacion"                                
                                    type="number"
                                    onChange={this.handleChange}
                                    value={form.puntuacion}
                                />  
                            </FormGroup>
                        </div>                      

                        <Col sm={2}>
                            <Button
                                color='success' 
                                onClick={()=>this.peticionPost()}>
                                Guardar
                            </Button>                                       
                        </Col>    
                        <Col sm={2}>
                            <Button
                                color='danger'>
                                <a href='/opiniones' className='text-decoration-none text-light'>Cancelar</a>
                            </Button>
                        </Col>    
                    </Form>    
             
                    
                </div>
            </Col>           
            
        </div>
    );
  }
}
 
export default FormCrearOpinion;