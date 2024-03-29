import './App.css';
import logo from './logo1.png';
import React from 'react';
import { BrowserRouter as Router, Link, Route, Routes } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Container } from 'reactstrap';

import Home from "./Components/Home";
import Buscador from './Components/Buscador';

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

import Resumen from './Components/Resumen';

class App extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      token: localStorage.getItem("token")
    };
  }

  render() {
    return (
      <Router>
        <Container className='bg-white'>

          <div className='d-flex justify-content-between align-items-center py-3 w-100'>
            <img src={logo} alt="logo" height="55" width="95"></img>

            <Link className='text-decoration-none text-dark fs-5' to="/">Home</Link>

            <IconoLogin token={this.state.token} />
          </div>

          <Routes>
            <Route path="/" element={<Home />} />

            <Route path="/buscador" element={<Buscador token={this.state.token} />} />
            <Route path="/cliente" element={<Cliente token={this.state.token} />} />

            <Route path="/datosCliente" element={<Cliente token={this.state.token} />} />
            <Route path="/formCliente" element={<FormCliente token={this.state.token} />} />
            <Route path="/reservas" element={<Reservas token={this.state.token} />} />
            <Route path="/facturas" element={<Facturas token={this.state.token} />} />
            <Route path="/facturaCompleta/:id" element={<FacturaCompleta token={this.state.token} />} />
            <Route path="/facturasCanceladas" element={<FacturasCanceladas token={this.state.token} />} />
            <Route path="/opiniones" element={<Opiniones token={this.state.token} />} />
            <Route path='/formCrearOpinion/:codigoReserva' element={<FormCrearOpinion token={this.state.token} />} />
            <Route path='/formModificarOpinion/:codigoReserva' element={<FormModificarOpinion token={this.state.token} />} />
            <Route path="/resumen/:localizador" element={<Resumen token={this.state.token} />} />

            <Route path='/login' element={<Login />} />
            <Route path='/registro' element={<Registro />} />

          </Routes>
        </Container>

        <Footer />
      </Router>
    );
  }
}


export default App;
