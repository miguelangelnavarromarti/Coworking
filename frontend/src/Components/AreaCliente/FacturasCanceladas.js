import React, { Component } from "react";
import axios from "axios";
import { Table, Button, Card, CardBody, CardTitle, Col, Container, Input, Row } from 'reactstrap';

import ClienteHeader from "./ClienteHeader";
const API = 'http://localhost:8000';
 
class FacturasCanceladas extends Component {
    constructor(props) {
        super(props);
    
        this.state = {
          facturasCanceladas: [],
          isLoading: false,
          error: null,
        };
      }
    componentDidMount() {


    this.setState({ isLoading: true });

    axios.get(API + "/facturasCanceladas/1")  // MODIFICAR I AGAFAR ID CLIENT
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

        if (error) {
        return <p>{error.message}</p>;
        }

        if (isLoading) {
        return <p>Loading ...</p>;
        }

    return (
     
        <div className="container my-5 py-4 px-5  shadow bg-body rounded-3">
          <ClienteHeader/>
          <h1 className="text-center my-4">Todas las Facturas Canceladas</h1>
          <Table
            hover
            responsive
            striped
          >
            <thead>
              <tr className="text-center">
                <th>Codigo</th>                
                <th>Codigo Factura</th>
                <th>Devolución</th>
                <th>Dias Antelación</th>
                <th>Descuento</th>
                <th>Dia Cancelación</th>
              </tr>
            </thead>
            <tbody>
              {facturasCanceladas.map(function(factura){
                return (
                  <tr key={factura.codigo} className="text-center">
                    <td>{factura.codigo}</td>                    
                    <td>{factura.codigoFactura}</td>                                        
                    <td>{factura.devolucion} €</td>
                    <td>{factura.diasAntelacionCancelacion} dias</td>                    
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
}
 
export default FacturasCanceladas;