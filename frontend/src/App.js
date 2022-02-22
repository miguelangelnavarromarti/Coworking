import './App.css';
import React from 'react';
import { BrowserRouter as Router, Link, Route, Routes } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';

import Home from "./Components/Home";
import Table from "./Components/Table";
import Selectable from './Components/Selectable';
import Buscador from './Components/Buscador';
import BuscadorV2 from './Components/BuscadorV2';
import { Row, Col, Container } from 'reactstrap';

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
              <Link to="/buscador">Buscador</Link>
            </Col>
            <Col className='text-center'>
              <Link to="/buscadorV2">Buscador v2</Link>
            </Col>
          </Row>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/table" element={<Table />} />
            <Route path="/selectable" element={<Selectable />} />
            <Route path="/buscador" element={<Buscador />} />
            <Route path="/buscadorV2" element={<BuscadorV2 />} />
          </Routes>
        </Container>
      </Router>
    );
  }
}


export default App;
