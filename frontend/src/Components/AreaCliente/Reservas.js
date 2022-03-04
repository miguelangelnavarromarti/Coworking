import React, { Component } from "react";
import axios from "axios";
import { Table, Button, Card, CardBody, CardTitle, Col, Container, Input, Row } from 'reactstrap';

import ClienteHeader from "./ClienteHeader";
import IconoCondicionalOpinion from "./IconoCondicionalOpinion";
import Login from "../Login";

const API = 'http://localhost:8000';
 
class Reservas extends Component {
    constructor(props) {
        super(props);
    
        this.state = {
          reservas: [],
          isLoading: false,
          error: null,  
        };
      }

      sortMovies(reserva1, reserva2){
        return parseFloat(reserva2.codigo) - parseFloat(reserva1.codigo);
      }


    componentDidMount() {


    this.setState({ isLoading: true });

    axios.get(API + "/reservas",{
      headers: {
          'authorization':'Bearer ' + this.props.token,
          'Accept' : 'application/json',
          'Content-Type': 'application/json'
      }
    })
        .then(result => this.setState({
            reservas: result.data,
            isLoading: false
        }))
        .catch(error => this.setState({
        error,
        isLoading: false
        }));

        
    }

    render() {
        const { reservas, isLoading, error } = this.state;
        console.log(reservas);    //Borrar
        if(this.props.token != null){

        if (error) {
        return <p>{error.message}</p>;
        }

        if (isLoading) {
        return <p>Loading ...</p>;
        }

       

    return (
     
        <div className="container my-1 py-1 px-4 pb-5">
            <ClienteHeader/>
          <h1 className="text-center my-4">Todas las reservas</h1>
          <Button
            color="primary">
              <a href="#" className='text-decoration-none text-light'>Hacer Nueva Reserva</a>
            </Button>
          <Table
            hover
            responsive
            striped
          >
            <thead>
              <tr className="text-center">
                <th>Codigo Reserva</th>                
                <th>Dia Factura</th>
                <th>Hora</th>
                <th>Creación Reserva</th>
                <th>Estado Reserva</th>
                <th>Opinar</th>
              </tr>
            </thead>
            <tbody>
              {reservas
              .sort( (reserva1, reserva2) => this.sortMovies(reserva1, reserva2))
              .map(function(reserva,key){
                return (
                  <tr key={key} className="text-center">
                    <td>{reserva.codigo}</td>                                                                                      
                    <td>{reserva.dia}</td>                    
                    <td>{reserva.hora}h</td>
                    <td>{reserva.diaHoraCreacion}</td>
                    <td>{reserva.estado}</td>
                    <td>                    
                      <IconoCondicionalOpinion reservaEstado={reserva.estado} codigoReserva ={reserva.codigo}/>                      
                    </td>
                  </tr>
                )
              })}
            </tbody>
          </Table>      
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
 
export default Reservas;