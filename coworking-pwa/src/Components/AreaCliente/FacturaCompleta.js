import React, { Component } from "react";
import axios from "axios";
import { Table, Button, Card, CardBody, CardTitle, Col, Container, Input, Row } from 'reactstrap';
import { Accordion, AccordionItem, AccordionHeader, } from 'reactstrap';
import { useNavigate } from 'react-router-dom';

import ClienteHeader from "./ClienteHeader";
import Login from "../Login";
const API = 'https://api.coworkingmiki.me';

class FacturaCompleta extends Component {
  constructor(props) {
    super(props);

    this.handleClickButton = this.handleClickButton.bind(this);
    this.routeChange = this.routeChange.bind(this);

    this.state = {
      facturas: [],
      nombreEspacio: null,
      reservas: [],
      isLoading: false,
      error: null,
      desplegar: false,
      codigoFactura: '',
    };
    
  }

  routeChange = () => {
    window.location.href= "http://localhost:3000/facturasCanceladas";
}

  handleClickButton = async (e) => {
    e.preventDefault();
    try {
        let res = await fetch("http://localhost:8000/cancelarFactura/" + this.state.codigoFactura, {
            method: "POST",
            headers: {
                'authorization': 'Bearer ' + this.props.token,
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                codigoFactura: this.state.codigoFactura
            }),
        });
        this.routeChange();
    } catch (err) {
        console.log(err);
    }

};



  componentDidMount() {

    this.setState({ isLoading: true });

    let url = window.location.href;
    const codigoFactura = url.split("/").splice(-1)[0];

    axios.get(API + "/facturas/" + codigoFactura, {
      headers: {
        'authorization': 'Bearer ' + this.props.token,
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    })
      .then(result => this.setState({
        facturas: result.data.factura,
        nombreEspacio: result.data.nombreEspacio,
        isLoading: false,
        codigoFactura: codigoFactura
      }))
      .catch(error => this.setState({
        error,
        isLoading: false
      }));

    axios.get(API + "/reservasFactura/" + codigoFactura, {
      headers: {
        'authorization': 'Bearer ' + this.props.token,
        'Accept': 'application/json',
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
    const { reservas, facturas, nombreEspacio, isLoading, error } = this.state;

    if (this.props.token != null) {
      if (error) {
        return <p>{error.message}</p>;
      }

      if (isLoading) {
        return <p>Loading ...</p>;
      }

      return (

        <div className="container my-1 py-1 px-4 pb-5">
          <ClienteHeader />
          <h1 className="text-center my-4">Factura Detallada</h1>
          <Row>
            <Col xs='3' className="fw-bold mx-auto text-center">Espacio reservado: {nombreEspacio}</Col>
            <Col>
            {facturas.map(function (factura) {
            return (
              <Row key={factura.codigo} className="text-center row mb-5">
                <Col xs='2' className="fw-bold mx-auto">Código factura: {factura.codigo}</Col>
                <Col xs='3' className="fw-bold mx-auto">Día de Factura: {factura.diaFactura}</Col>
                <Col xs='2' className="fw-bold mx-auto">Precio Total: {factura.precioTotal} €</Col>
                <Col xs='2' className="fw-bold mx-auto">Desc. Oferta: {factura.descuentoOferta}%</Col>
              </Row>
            )
          })}
            </Col>
          </Row>
          

          <Button className='w-25 bg-blauFort border-2 border-groc' onClick={this.handleClickButton}>Cancelar Factura</Button>

          <Table
            hover
            responsive
            striped
          >
            <thead>
              <tr className="text-center">
                <th>Código Reserva</th>
                <th>Día Reserva</th>
                <th>Hora</th>
                <th>Creación Reserva</th>
                <th>Estado Reserva</th>
              </tr>
            </thead>
            <tbody>
              {reservas.map(function (reserva) {
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
        <Login />
      );
    }
  }
}

export default FacturaCompleta;