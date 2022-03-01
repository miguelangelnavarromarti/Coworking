import React, { Component } from "react";
import axios from "axios";
import { Table, Button, Card, CardBody, CardTitle, Col, CardText, Input, Row, CardGroup, FormGroup, Form, Label, Modal, ModalHeader, ModalBody, ModalFooter} from 'reactstrap';

import ClienteHeader from "./ClienteHeader";
import Login from "../Login";

const API = 'http://localhost:8000';
 
class Opiniones extends Component {
    constructor(props) {
        super(props);
    
        this.state = {
          opiniones: [],
          isLoading: false,
          error: null,         
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

    redirectModificar=(codigoOpinion)=>{
        window.location.href= "http://localhost:3000/formModificarOpinion/" + codigoOpinion;
    }

    peticionDelete=(codigoOpinion)=>{        // SI POS UNA RUTA HO BORRA AUTOMATICAMENT SENSE FER CLICK
        axios.delete(API + "/opiniones/1/" + codigoOpinion)
        .then(response=>{
            this.peticionGet();
        })
    }



    componentDidMount() {

    this.setState({ isLoading: true });
    this.peticionGet();

    console.log(this.state.opiniones);

    
    }

    render() {
        const { opiniones, isLoading, error } = this.state;

        console.log(this.state.opiniones);

        if(this.props.login){
        if (error) {
        return <p>{error.message}</p>;
        }

        if (isLoading) {
        return <p>Loading ...</p>;
        }

    return (
     
        <div className="container my-1 py-1 px-4 pb-5">
            <ClienteHeader/>    
            <h1 className="text-center my-4">Todas las opiniones</h1>                    
            <CardGroup>
            {opiniones.map((opinion)=>
                <Col key={opinion.codigo} sm="5" className="m-auto">
                    <Card className="my-5 py-4 px-5  shadow bg-body rounded-3 bg-degradatInvertit text-white border-2 border-warning"
                        body
                        >                                     
                            <CardBody>
                                <CardTitle  tag="h3" className='text-center mb-4'>
                                {opinion.titulo}
                                </CardTitle>
                                <CardText>
                                    <Row>
                                        <p className='fs-5'>Reserva: {opinion.codigoReserva} </p>
                                        <p className='fs-5'>Opinión: </p>
                                        <p className='fs-6'>{opinion.opinion}</p>
                                        <p className='fs-5'>Puntuación: {opinion.puntuacion}</p>                                    
                                    </Row>
                                </CardText>
                            <div className="row">
                            <Col>
                                <Button
                                    className="bg-groc"
                                    color="black"
                                    size=""
                                    onClick={()=>this.redirectModificar(opinion.codigo)}>                                                
                                    Modificar Opinión
                                </Button>
                            </Col>
                            <Col>
                                <Button
                                    color="danger"
                                    size=""                                    
                                    onClick={()=>{this.peticionDelete(opinion.codigo)}}
                                    >
                                    Eliminar Opinión
                                </Button>
                            </Col>
                            </div>
                            </CardBody>                                            
                    </Card>
                </Col>
                )}
            </CardGroup>                               
        </div>
        );
    } else{
        return (
            <Login/>
        );
    }
  }
  
  
}
 
export default Opiniones;