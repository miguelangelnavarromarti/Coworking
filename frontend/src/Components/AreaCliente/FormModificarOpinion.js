import React, { Component } from "react";
import axios from "axios";
import { Button, Col, Input, FormGroup, Form, Label,} from 'reactstrap';

import ClienteHeader from "./ClienteHeader";
import Login from "../Login";

const API = 'http://localhost:8000';
 
class FormModificarOpinion extends Component {
    constructor(props) {
        super(props);
    
        this.state = {
          opiniones: [],
          isLoading: false,
          error: null,
          form:{
            codigo:'',
            codigoCliente:'',
            codigoReserva:'',
            titulo:'',
            opinion:'',
            puntuacion:''
            },
        };
      }

    peticionPut=()=>{
        axios.put(API+"/opiniones/" + this.state.form.codigoCliente + "/" + this.state.form.codigo, this.state.form)
        .then(response=>{            
            console.log("Enviat!");
            window.location.href = "http://localhost:3000/opiniones";       //Modifica sa url i me redirigeix aixi    
        })
    }

    redirectCancelar=()=>{
        window.location.href= "http://localhost:3000/cliente";
    }

    handleChange=async e=>{
        e.persist();
        await this.setState({
            form:{
                ...this.state.form,
                [e.target.name]: e.target.value,
                codigoReserva: document.querySelector('#codigoReserva').value,
                codigoCliente: document.querySelector('#codigoCliente').value,
                codigo: document.querySelector('#codigo').value,                
            }
        });
        console.log(this.state.form);
    }

    peticionGet=(codigoOpinion)=>{
        axios.get(API + "/opiniones/"+this.props.id + "/" + codigoOpinion)  // MODIFICAR I AGAFAR ID OPINION
        .then(result => this.setState({
            opiniones: result.data,
            isLoading: false
        }))
        .catch(error => this.setState({
        error,
        isLoading: false
        }));
    }
    

    componentDidMount() {

    this.setState({ isLoading: true });

    let url = window.location.href;
    const codigoOpinion = url.split("/").splice(-1)[0];

    
    this.peticionGet(codigoOpinion);

    
    }

    render() {
        const { opiniones, isLoading, error } = this.state;

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
            <h1 className="text-center my-4">Modificar opinión</h1>        
            <Col sm="8" className='m-auto'>
                <div className="my-5 py-4 px-5  shadow bg-body rounded-3"
                    body   
                    color='primary'                     
                >
                    {opiniones.map((datos)=>
                    <Form className="row">
                        <div className='col-12'>
                            <input type="hidden" id="codigo" name="codigo" value={datos.codigo}/>
                            <input type="hidden" id="codigoReserva" name="codigoReserva" value={datos.codigoReserva}/>
                            <input type="hidden" id="codigoCliente" name="codigoCliente" value={datos.codigoCliente}/>
                            <FormGroup>
                                <Label for="titulo">
                                    Titulo
                                </Label>
                                <Input
                                    id="titulo"
                                    name="titulo"
                                    placeholder={datos.titulo}
                                    onChange={this.handleChange}                                    
                                    type="text"
                                />  
                            </FormGroup>
                        </div>
                        <div className='col-12'>
                            <FormGroup>
                                <Label for="opinion">
                                    Descripción
                                </Label>
                                <Input
                                    id="opinion"
                                    name="opinion"
                                    placeholder={datos.opinion}                                                                
                                    type="textarea"
                                    onChange={this.handleChange}
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
                                    placeholder={datos.puntuacion}                                
                                    type="number"
                                    onChange={this.handleChange}
                                />  
                            </FormGroup>
                        </div>                      

                        <Col sm={2}>
                            <Button
                                color='success'
                                onClick={()=>this.peticionPut()}>
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
                )}
                    
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
 
export default FormModificarOpinion;