import React, { Component } from "react";
import axios from "axios";
import { Table, Button, Card, CardBody, CardTitle, Col, Container, Input, Row } from 'reactstrap';

const API = 'http://localhost:8000';
 
class Facturas extends Component {
    constructor(props) {
        super(props);
    
        this.state = {
          facturas: [],
          isLoading: false,
          error: null,
        };
      }
    componentDidMount() {


    this.setState({ isLoading: true });

    axios.get(API + "/facturas/1")  // MODIFICAR I AGAFAR ID CLIENT
        .then(result => this.setState({
            facturas: result.data,
            isLoading: false
        }))
        .catch(error => this.setState({
        error,
        isLoading: false
        }));
    }

    render() {
        const { facturas, isLoading, error } = this.state;

        if (error) {
        return <p>{error.message}</p>;
        }

        if (isLoading) {
        return <p>Loading ...</p>;
        }

    return (
     
        <div className="container my-5 py-4 px-5  shadow bg-body rounded-3">
          <h1 className="text-center my-4">Todas las facturas</h1>
          <Table
            hover
            responsive
            striped
          >
            <thead>
              <tr className="text-center">
                <th>Codigo</th>                
                <th>Dia Factura</th>
                <th>Precio Total</th>
                <th>Min. Horas Oferta</th>
                <th>Desc. Oferta</th>
              </tr>
            </thead>
            <tbody>
              {facturas.map(function(factura,key){
                return (
                  <tr key={key} className="text-center">
                    <td>{factura.codigo}</td>                    
                    <td>{factura.diaFactura}</td>                                        
                    <td>{factura.precioTotal} €</td>
                    <td>{factura.minimoHoraOferta}h</td>                    
                    <td>{factura.descuentoOferta}h</td>                    

                  </tr>
                )
              })}
            </tbody>
          </Table>      
        </div>
    );
  }
}
 
export default Facturas;