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

        if("Hola" == "confirmado"){
          var confirmado = <td>Confirmat</td>;
        } else{
          var noConfirmado = <td>No Confirmat</td>;
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
                <th>Opinar</th>
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
                    <td>                    
                      FER CONDICIONAL
                      <a href="/formCrearOpinion">
                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-pencil-square btn-warning" viewBox="0 0 16 16">
                          <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                          <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
                        </svg>
                      </a>
                    </td>
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