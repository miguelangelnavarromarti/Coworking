import React, { Component } from "react";
import axios from "axios";
import { Table, Button, Card, CardBody, CardTitle, Col, CardText, Input, Row, CardGroup, FormGroup, Form, Label, Modal, ModalHeader, ModalBody, ModalFooter} from 'reactstrap';

import ClienteHeader from "./ClienteHeader";
import Login from "../Login";

const API = 'http://localhost:8000';
 
class FormCrearOpinion extends Component {
    constructor(props) {
        super(props);
    
        this.state = {
          opiniones: [],
          isLoading: false,
          error: null,
          url : window.location.href,
          form:{
              codigoCliente:1,  //Aquest 1 ha de ser s'id des client
              codigoReserva: '',
              titulo:'',
              opinion:'',
              puntuacion:'',
          }, 

        }; 
      }

    peticionGet=()=>{
        axios.get(API + "/opiniones/"+this.props.id)
        .then(result => this.setState({
            opiniones: result.data,
            isLoading: false
        }))
        .catch(error => this.setState({
        error,
        isLoading: false
        }));    
    }

    peticionPost=()=>{
        axios.post(API+"/opiniones/"+this.props.id , this.state.form)
        .then(response=>{
            this.peticionGet();  
            console.log("Enviat!");
            window.location.href = "http://localhost:3000/reservas";       //Modifica sa url i me redirigeix aqui                      
        })
        .catch(error => this.setState({
            error,
            isLoading: false
        }));
    }

    redirectCancelar=()=>{
        window.location.href= "http://localhost:3000/reservas";
    }

    handleChange=async e=>{
        e.persist();
        await this.setState({
            form:{
                ...this.state.form,
                [e.target.name]: e.target.value,
                codigoReserva: document.querySelector('#codigoReserva').value
            }
        });
        console.log(this.state.form);
    };
    
    

    componentDidMount() {

    this.setState({ isLoading: true });
    this.peticionGet();
    

    
    }

    render() {
        const { form, isLoading, error, url } = this.state;
        const urlBona = url.split("/").splice(-1)[0];        
        
        if(this.props.login){

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
                            <input type="hidden" id="codigoReserva" name="codigoReserva" value={urlBona} />
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
                                <Label for="opinion">
                                    Opinión
                                </Label>
                                <Input
                                    id="opinion"
                                    name="opinion"                                
                                    type="textarea"
                                    onChange={this.handleChange}
                                    value={form.opinion}
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
                                color='danger'
                                onClick={()=>this.redirectCancelar()}>                                                
                                Cancelar
                            </Button>
                        </Col>    
                    </Form>    
             
                    
                </div>
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
 
export default FormCrearOpinion;