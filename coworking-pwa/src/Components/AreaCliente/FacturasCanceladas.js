import React, { Component } from "react";
import axios from "axios";
import { Table, Button, Card, CardBody, CardTitle, Col, Container, Input, Row } from 'reactstrap';

import ClienteHeader from "./ClienteHeader";
import Login from "../Login";
const API = 'https://api.coworkingmiki.me';
 
class FacturasCanceladas extends Component {
    constructor(props) {
        super(props);
    
        this.state = {
          facturasCanceladas: [],
          isLoading: false,
          error: null,
        };
      }

      sortMovies(facturas1, facturas2){
        return parseFloat(facturas2.codigo) - parseFloat(facturas1.codigo);
      }

    componentDidMount() {


    this.setState({ isLoading: true });

    axios.get(API + "/facturasCanceladas",{
      headers: {
          'authorization':'Bearer ' + this.props.token,
          'Accept' : 'application/json',
          'Content-Type': 'application/json'
      }
    })
        .then(result => this.setState({
            facturasCanceladas: result.data,
            isLoading: false
        }))
        .catch(error => this.setState({
        error,
        isLoading: false
        }));
    }

    render() {
        const { facturasCanceladas, isLoading, error } = this.state;

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
          <h1 className="text-center my-4">Todas las Facturas Canceladas</h1>
          <Table
            hover
            responsive
            striped
          >
            <thead>
              <tr className="text-center">
                <th>Código</th>                
                <th>Código Factura</th>
                <th>Devolución</th>
                <th>Días Antelación</th>
                <th>Descuento</th>
                <th>Día Cancelación</th>
              </tr>
            </thead>
            <tbody>
              {facturasCanceladas
              .sort( (facturas1, facturas2) => this.sortMovies(facturas1, facturas2))
              .map(function(factura){
                return (
                  <tr key={factura.codigo} className="text-center">
                    <td>{factura.codigo}</td>                    
                    <td>{factura.codigoFactura}</td>                                        
                    <td>{factura.devolucion} €</td>
                    <td>{factura.diasAntelacionCancelacion} días</td>                    
                    <td>{factura.descuentoCancelacion}%</td>
                    <td>{factura.diaHoraCancelacion}</td>
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
 
export default FacturasCanceladas;