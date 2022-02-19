import React, { Component } from "react";
import axios from "axios";
import { Table, Button, Card, CardBody, CardTitle, Col, CardText, Input, Row, CardGroup, CardSubtitle } from 'reactstrap';

import ClienteHeader from "./ClienteHeader";

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
    componentDidMount() {


    this.setState({ isLoading: true });

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
          <h1 className="text-center my-4">Todas las opiniones</h1>        

          <CardGroup>
          {opiniones.map((opinion)=>
            <Col sm="5" className="m-auto">
                <Card className="my-5 py-4 px-5  shadow bg-body rounded-3"
                    body
                    >                                     
                        <CardBody>
                            <CardTitle key={opinion.codigo} tag="h3" className='text-center mb-4'>
                            {opinion.titulo}
                            </CardTitle>
                            <CardText>
                                <Row>
                                    <p className='fs-5'>Opinión: </p>
                                    <p className='fs-6'>{opinion.opinion}</p>
                                    <p className='fs-5'>Puntuación: {opinion.puntuacion}</p>                                    
                                </Row>
                            </CardText>
                            <Button
                                color="warning"
                                size=""
                            >
                                Modificar Opinión
                            </Button>
                        </CardBody>                                            
                </Card>
              </Col>
              )}
          </CardGroup>    

        </div>
    );
  }
}
 
export default Opiniones;