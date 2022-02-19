import React, { Component } from "react";
import axios from "axios";
import { Table, Button, Card, CardBody, CardTitle, Col, Container, Input, Row } from 'reactstrap';

import ClienteHeader from "./ClienteHeader";

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
    componentDidMount() {


    this.setState({ isLoading: true });

    axios.get(API + "/reservas/1")  // MODIFICAR I AGAFAR ID CLIENT
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

        if (error) {
        return <p>{error.message}</p>;
        }

        if (isLoading) {
        return <p>Loading ...</p>;
        }

    return (
     
        <div className="container my-5 py-4 px-5  shadow bg-body rounded-3">
            <ClienteHeader/>
          <h1 className="text-center my-4">Todas las reservas</h1>
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
              </tr>
            </thead>
            <tbody>
              {reservas.map(function(reserva,key){
                return (
                  <tr key={key} className="text-center">
                    <td>{reserva.codigo}</td>                                                                                      
                    <td>{reserva.dia}</td>                    
                    <td>{reserva.hora}h</td>
                    <td>{reserva.diaHoraCreacion}</td>
                    <td>{reserva.estado}</td>
                  </tr>
                )
              })}
            </tbody>
          </Table>      
        </div>
    );
  }
}
 
export default Reservas;