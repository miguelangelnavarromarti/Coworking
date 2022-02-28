import './App.css';
import React from 'react';
import { BrowserRouter as Router, Link, Route, Routes } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Row, Col, Container } from 'reactstrap';

import Home from "./Components/Home";
import Table from "./Components/Table";
import Selectable from './Components/Selectable';
import Buscador from './Components/Buscador';
import BuscadorV2 from './Components/BuscadorV2';
import Login from './Components/Login';
import Registro from './Components/Registro';
import Cliente from './Components/AreaCliente/Cliente';
import FormCliente from './Components/AreaCliente/FormModificarCliente';
import Reservas from './Components/AreaCliente/Reservas';
import Facturas from './Components/AreaCliente/Facturas';
import Opiniones from './Components/AreaCliente/Opiniones';
import FormCrearOpinion from './Components/AreaCliente/FormCrearOpinion';
import FormModificarOpinion from './Components/AreaCliente/FormModificarOpinion';
import FacturasCanceladas from './Components/AreaCliente/FacturasCanceladas';
import FacturaCompleta from './Components/AreaCliente/FacturaCompleta';
import IconoLogin from './Components/IconoLogin';
import Footer from './Components/Footer';


class App extends React.Component {
  constructor(props){
    super(props);

    this.state = {
      login : true,
      id: 1,
    };
  }
  render() {
    return (
      <Router>
        <Container>
          <Row className='my-4'>
            <Col className='text-center'>
              <Link to="/">Home</Link>
            </Col>
            <Col className='text-center'>
              <Link to="/table">Table</Link>
            </Col>
            <Col className='text-center'>
              <Link to="/selectable">Selectable</Link>
            </Col>
            <Col className='text-center'>
              <Link to="/buscador">Buscador</Link>
            </Col>
            <Col className='text-center'>
              <Link to="/buscadorV2">Buscador v2</Link>              
            </Col>
           
            <Col>
              <IconoLogin login={this.state.login}/>
            </Col>
          </Row>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/table" element={<Table />} />
            <Route path="/selectable" element={<Selectable />} />
            <Route path="/buscador" element={<Buscador />} />
            <Route path="/buscadorV2" element={<BuscadorV2 />} />
            <Route path="/cliente" element={<Cliente id={this.state.id} login={this.state.login}/>} />

            <Route path="/datosCliente" element={<Cliente id ={this.state.id} login={this.state.login}/>} />
            <Route path="/formCliente" element={<FormCliente id={this.state.id} login={this.state.login}/>} />
            <Route path="/reservas" element={<Reservas id={this.state.id} login={this.state.login}/>} />
            <Route path="/facturas" element={<Facturas id={this.state.id} login={this.state.login}/>} />
            <Route path="/facturaCompleta/:id" element={<FacturaCompleta id={this.state.id} login={this.state.login}/>} />
            <Route path="/facturasCanceladas" element={<FacturasCanceladas id={this.state.id} login={this.state.login}/>} />
            <Route path="/opiniones" element={<Opiniones id={this.state.id} login={this.state.login}/>} />
            <Route path='/formCrearOpinion/:codigoReserva' element={<FormCrearOpinion id={this.state.id} login={this.state.login}/>}/>
            <Route path='/formModificarOpinion/:codigoReserva' element={<FormModificarOpinion id={this.state.id} login={this.state.login}/>}/>

            <Route path='/login' element={<Login />}/>
            <Route path='/registro' element={<Registro />}/>
            
            

          </Routes>
        </Container>
        
        <Footer/>
      </Router>
    );
  }
}


export default App;
