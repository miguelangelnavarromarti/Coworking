import React, { Component } from "react";
import axios from "axios";
import { Table, Button, Card, CardBody, CardTitle, Col, Container, Input, Row } from 'reactstrap';
import {Accordion,AccordionItem, AccordionHeader,} from 'reactstrap';

import ClienteHeader from "./ClienteHeader";
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
        };
      }

   

    

    componentDidMount() {
   

    this.setState({ isLoading: true });

    axios.get(API + "/facturas/1/1")  // MODIFICAR I AGAFAR ID CLIENT
        .then(result => this.setState({
            facturas: result.data,
            isLoading: false
        }))
        .catch(error => this.setState({
        error,
        isLoading: false
        }));

        axios.get(API + "/reservasFactura/1")  // MODIFICAR I AGAFAR ID CLIENT
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
        

        if (error) {
        return <p>{error.message}</p>;
        }

        if (isLoading) {
        return <p>Loading ...</p>;
        }

    return (
     
        <div className="container my-5 py-4 px-5  shadow bg-body rounded-3">
             <ClienteHeader/>
          <h1 className="text-center my-4">Factura Detallada</h1>          
          {facturas.map(function(factura){
                return (
                <Col key={factura.codigo} className="text-center row mb-5">
                    <div className="col-2 fw-bold mx-auto">Codigo factura: {factura.codigo}</div>
                    <div className="col-3 fw-bold mx-auto">Dia de Factura: {factura.diaFactura}</div>
                    <div className="col-2 fw-bold mx-auto">Precio Total: {factura.precioTotal} €</div>
                    <div className="col-2 fw-bold mx-auto">Min. Horas Oferta: {factura.minimoHoraOferta}h</div>
                    <div className="col-2 fw-bold mx-auto">Desc. Oferta: {factura.descuentoOferta}</div>
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
}
 
export default FacturaCompleta;