import React, { Component } from "react";
import axios from "axios";
import { Table, Button, Card, CardBody, CardTitle, Col, Container, Input, Row } from 'reactstrap';
import {Accordion,AccordionItem, AccordionHeader,} from 'reactstrap';

import ClienteHeader from "./ClienteHeader";
import Login from "../Login";
const API = 'http://localhost:8000';
 
class Facturas extends Component {
    constructor(props) {
        super(props);
    
        this.state = {
          facturas: [],
          isLoading: false,
          error: null,
          desplegar: false,
        };
        
      }

    desplegarAccordion=()=>{ //Me passa de true a false i de false a true
      this.setState({desplegar: !this.state.desplegar});
    }

    

    componentDidMount() {
    this.desplegarAccordion();

    this.setState({ isLoading: true });
    const token = localStorage.getItem("token");
    //console.log(token);
    axios.get(API + "/facturas/"+this.props.id, {
      headers: {
          'authorization':'Bearer ' + token,
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
    }
  

    render() {
        const { facturas, isLoading, error } = this.state;
        console.log(facturas); //Borrar
        if(this.props.login){

        if (error) {
        return <p>{error.message}</p>;
        }

        if (isLoading) {
        return <p>Loading ...</p>;
        }

    return (
     
        <div className="container my-1 py-1 px-4 pb-5">
          <ClienteHeader/>
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
                <th>Detallado</th>
              </tr>
            </thead>
            <tbody>
              {facturas.map(function(factura){
                return (
                  <tr key={factura.codigo} className="text-center">              
                    <td>{factura.codigo}</td>                    
                    <td>{factura.diaFactura}</td>                                        
                    <td>{factura.precioTotal} €</td>
                    <td>{factura.minimoHoraOferta}h</td>                    
                    <td>{factura.descuentoOferta}</td>
                    <td>
                      <a href={'/facturaCompleta/'+factura.codigo} className="btn bg-blauFort text-white border-warning border-2 text-decoration-none p-1 rounded">
                      <span>VER -{'>'} </span>
                      <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" className="bi bi-list-columns-reverse" viewBox="0 0 16 16" aria-label="Factura Completa">
                        <path fill-rule="evenodd" d="M0 .5A.5.5 0 0 1 .5 0h2a.5.5 0 0 1 0 1h-2A.5.5 0 0 1 0 .5Zm4 0a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1h-10A.5.5 0 0 1 4 .5Zm-4 2A.5.5 0 0 1 .5 2h2a.5.5 0 0 1 0 1h-2a.5.5 0 0 1-.5-.5Zm4 0a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5Zm-4 2A.5.5 0 0 1 .5 4h2a.5.5 0 0 1 0 1h-2a.5.5 0 0 1-.5-.5Zm4 0a.5.5 0 0 1 .5-.5h11a.5.5 0 0 1 0 1h-11a.5.5 0 0 1-.5-.5Zm-4 2A.5.5 0 0 1 .5 6h2a.5.5 0 0 1 0 1h-2a.5.5 0 0 1-.5-.5Zm4 0a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 0 1h-8a.5.5 0 0 1-.5-.5Zm-4 2A.5.5 0 0 1 .5 8h2a.5.5 0 0 1 0 1h-2a.5.5 0 0 1-.5-.5Zm4 0a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 0 1h-8a.5.5 0 0 1-.5-.5Zm-4 2a.5.5 0 0 1 .5-.5h2a.5.5 0 0 1 0 1h-2a.5.5 0 0 1-.5-.5Zm4 0a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1h-10a.5.5 0 0 1-.5-.5Zm-4 2a.5.5 0 0 1 .5-.5h2a.5.5 0 0 1 0 1h-2a.5.5 0 0 1-.5-.5Zm4 0a.5.5 0 0 1 .5-.5h6a.5.5 0 0 1 0 1h-6a.5.5 0 0 1-.5-.5Zm-4 2a.5.5 0 0 1 .5-.5h2a.5.5 0 0 1 0 1h-2a.5.5 0 0 1-.5-.5Zm4 0a.5.5 0 0 1 .5-.5h11a.5.5 0 0 1 0 1h-11a.5.5 0 0 1-.5-.5Z"/>
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
  else {
      return (
          <Login/>
      );
  }
  }
}
 
export default Facturas;