import React, { Component } from "react";
import axios from "axios";
import { Table, Button, Card, CardBody, CardTitle, Col, Container, Input, Row } from 'reactstrap';
import {Accordion,AccordionItem, AccordionHeader,} from 'reactstrap';

import ClienteHeader from "./ClienteHeader";
import Login from "../Login";
const API = 'http://localhost:8000';
 
class FacturaCompleta extends Component {
    constructor(props) {
        super(props);
    
        this.state = {
          facturas: [],
          reservas:[],
          isLoading: false,
          error: null,
          desplegar: false,
          codigoFactura:'',
        };
      }

   

    

    componentDidMount() {
   
    this.setState({ isLoading: true });

    let url = window.location.href;
    const codigoFactura = url.split("/").splice(-1)[0];
    
    axios.get(API + "/facturas/" + codigoFactura,{
      headers: {
          'authorization':'Bearer ' + this.props.token,
          'Accept' : 'application/json',
          'Content-Type': 'application/json'
      }
    })
        .then(result => this.setState({
            facturas: result.data,
            isLoading: false
        }))
        .catch(error => this.setState({
        error,
        isLoading: false
        }));

        axios.get(API + "/reservasFactura/" + codigoFactura,{
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
        const { reservas, facturas, isLoading, error } = this.state;
        
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
          <h1 className="text-center my-4">Factura Detallada</h1>          
          {facturas.map(function(factura){
                return (
                <Col key={factura.codigo} className="text-center row mb-5">
                    <div className="col-2 fw-bold mx-auto">Codigo factura: {factura.codigo}</div>
                    <div className="col-3 fw-bold mx-auto">Dia de Factura: {factura.diaFactura}</div>
                    <div className="col-2 fw-bold mx-auto">Precio Total: {factura.precioTotal} €</div>                    
                    <div className="col-2 fw-bold mx-auto">Desc. Oferta: {factura.descuentoOferta}%</div>
                </Col>
            )})}

          <Table
            hover
            responsive
            striped
          >
            <thead>
              <tr className="text-center">
                <th>Codigo Reserva</th>                
                <th>Dia Reserva</th>
                <th>Hora</th>
                <th>Creación Reserva</th>
                <th>Estado Reserva</th>                                
              </tr>
            </thead>
            <tbody>
              {reservas.map(function(reserva){
                return (
                    <tr key={reserva.codigo} className="text-center">
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
    else {
      return (
          <Login/>
      );
  }
  }
}
 
export default FacturaCompleta;