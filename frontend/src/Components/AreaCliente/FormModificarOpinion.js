import React, { Component } from "react";
import axios from "axios";
import { Button, Col, Input, FormGroup, Form, Label,} from 'reactstrap';

import ClienteHeader from "./ClienteHeader";

const API = 'http://localhost:8000';
 
class FormModificarOpinion extends Component {
    constructor(props) {
        super(props);
    
        this.state = {
          opiniones: [],
          isLoading: false,
          error: null,
          modalInsertar: false,
        };
      }

    peticionGet=()=>{
        axios.get(API + "/opiniones/1/1")  // MODIFICAR I AGAFAR ID CLIENT i ID OPINION
        .then(result => this.setState({
            opiniones: result.data,
            isLoading: false
        }))
        .catch(error => this.setState({
        error,
        isLoading: false
        }));
    }

    modalInsertar=()=>{ //Me passa de true a false i de false a true
        this.setState({modalInsertar: !this.state.modalInsertar});
    }

    componentDidMount() {

    this.setState({ isLoading: true });
    this.peticionGet();

    
    }

    render() {
        const { opiniones, isLoading, error } = this.state;

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
                            <FormGroup>
                                <Label for="titulo">
                                    Titulo
                                </Label>
                                <Input
                                    id="titulo"
                                    name="titulo"
                                    placeholder={datos.titulo}                                
                                    type="text"
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
                                    placeholder={datos.opinion}                                                                
                                    type="textarea"
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
}
 
export default FormModificarOpinion;