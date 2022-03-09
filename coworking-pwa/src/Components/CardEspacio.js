import React from 'react';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Button, Card, CardBody, CardFooter, CardImg, CardSubtitle, CardTitle, Col, Row } from 'reactstrap';

import logo from '../logo.svg';

const API = 'http://localhost:8000';
const ESPACIO = '/espacio/';

class CardEspacio extends React.Component {

  constructor(props) {
    super(props);

    this.state = {
      disponibilidad: [],
      infoEspacio: [],
      isLoading: false,
      error: null,
    }
  }

  componentDidMount() {
    axios.get(this.props.fullRequest + this.props.espacio)
      .then(result => {
        const disponibilidad = result.data;
        this.setState({
          disponibilidad: disponibilidad,
          isLoading: false
        })
      })
      .catch(error => this.setState({
        error,
        isLoading: false
      }));

    axios.get(API + ESPACIO + this.props.espacio)
      .then(result => {
        const infoEspacio = result.data;
        this.setState({
          infoEspacio: infoEspacio,
          isLoading: false
        })
      })
      .catch(error => this.setState({
        error,
        isLoading: false
      }));
  }

  render() {
    const { disponibilidad, infoEspacio, error, isLoading } = this.state;

    if (error) {
      return <p>{error.message}</p>;
    }

    if (isLoading) {
      return <p>Loading ...</p>;
    }

    return (
      <Card>
        <CardImg src={logo} className="App-logo" alt="logo" />
        {infoEspacio.map((espacio, key) => (
          <CardBody key={key}>
            <CardTitle tag='h5'>
              {espacio.nombre}
            </CardTitle>
            <CardSubtitle
              className='mb-2 text-muted'
              tag='h6'
            >
              {espacio.descripcion}
            </CardSubtitle>
          </CardBody>
        ))}
        <CardFooter>
          <Row>
            {disponibilidad.map((dispo, key) => (
              <Col xs='3' className='mt-2'>
                <Button
                  key={key}
                  color='primary'
                  className='w-100'
                >
                  {dispo.hora}
                </Button>
              </Col>
            ))}
          </Row>
        </CardFooter>
      </Card>
    );
  }
}


export default CardEspacio;