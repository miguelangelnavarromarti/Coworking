import './App.css';
import React from 'react';
import { BrowserRouter as Router, Link, Route, Routes } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';

import Home from "./Components/Home";
import Table from "./Components/Table";
import Selectable from './Components/Selectable';
import { Row, Col, Container } from 'reactstrap';
import Cliente from './Components/AreaCliente/Cliente';
import FormCliente from './Components/AreaCliente/FormCliente';
import Reservas from './Components/AreaCliente/Reservas';
import Facturas from './Components/AreaCliente/Facturas';
import Opiniones from './Components/AreaCliente/Opiniones';
import FormCrearOpinion from './Components/AreaCliente/FormCrearOpinion';


class App extends React.Component {
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
              <Link to="/cliente">Area Cliente</Link>
            </Col>
          </Row>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/table" element={<Table />} />
            <Route path="/selectable" element={<Selectable />} />
            <Route path="/cliente" element={<Cliente />} />

            <Route path="/datosCliente" element={<Cliente />} />
            <Route path="/formCliente" element={<FormCliente />} />
            <Route path="/reservas" element={<Reservas />} />
            <Route path="/facturas" element={<Facturas />} />
            <Route path="/opiniones" element={<Opiniones />} />
            <Route path='/formCrearOpinion' element={<FormCrearOpinion />}/>
            

          </Routes>
        </Container>
      </Router>

    );
  }
}


export default App;
