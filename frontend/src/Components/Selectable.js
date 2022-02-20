import React from 'react';
import axios from 'axios';

import DayPicker from 'react-day-picker';
import 'react-day-picker/lib/style.css';

import dateFnsFormat from 'date-fns/format';
import { Button, Card, CardBody, CardTitle, Col, Container, Input, Row } from 'reactstrap';
const API = 'http://localhost:8000';
const REQUEST = '/disponibilidad/';
const ESPACIOS = '/espacios';

class Selectable extends React.Component {


    constructor(props) {
        super(props);

        this.handleDayClick = this.handleDayClick.bind(this);
        this.handleInputChange = this.handleInputChange.bind(this);
        this.handleClickButton = this.handleClickButton.bind(this);
        this.state = {
            disponibilidad: [],
            espacios: [],
            reservas: [],
            isLoading: false,
            error: null,
            requestDay: `${dateFnsFormat(new Date(), 'yyyy-MM-dd')}`,
            selectedDay: new Date(),
            format: 'yyyy-MM-dd',
            selectedEspacio: null
        };
    }

    handleDayClick(day) {
        this.setState({
            requestDay: `${dateFnsFormat(day, this.state.format)}`,
            selectedDay: day
        });
    }

    handleInputChange(event) {
        this.setState({
            selectedEspacio: event.target.value,
        });
    }

    handleClickButton(i) {
        const reservas = this.state.reservas.slice();
        const disponibilidad = this.state.disponibilidad.slice();
        const horaBoton = i.target.text.split(":")[0];
        var indexHora;

        for (let i = 0; i < disponibilidad.length; i++) {
            if (disponibilidad[i].hora === horaBoton) {
                indexHora = i;
            }
        }

        reservas.push(i.target.text);
        disponibilidad.splice(indexHora, 1);
        this.setState({
            reservas: reservas,
            disponibilidad: disponibilidad,
        });
    }

    componentDidMount() {

        this.setState({ isLoading: true });

        if (this.state.selectedEspacio !== null) {
            axios.get(API + REQUEST + this.state.requestDay + "/" + this.state.selectedEspacio)
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
        }

        axios.get(API + ESPACIOS)
            .then(result => {
                const espacios = result.data;
                this.setState({
                    espacios: espacios,
                    isLoading: false
                })
            })
            .catch(error => this.setState({
                error,
                isLoading: false
            }));
    }

    componentDidUpdate(prevProps, prevState) {
        if (this.state.selectedEspacio !== null) {
            if (this.state.requestDay !== prevState.requestDay || this.state.selectedEspacio !== prevState.selectedEspacio) {
                this.setState({ isLoading: true });

                axios.get(API + REQUEST + this.state.requestDay + "/" + this.state.selectedEspacio)
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
            }
        }
    }

    render() {
        const { disponibilidad, espacios, reservas, isLoading, error, requestDay, selectedDay } = this.state;

        if (error) {
            return <p>{error.message}</p>;
        }

        if (isLoading) {
            return <p>Loading ...</p>;
        }

        return (

            <Container>
                <Row>
                    <Col className='text-center my-5'>
                        <h1>Disponibilidad</h1>
                    </Col>
                </Row>
                <Row>
                    <Col xs="3">
                        <Input
                            id='select'
                            name='select'
                            type='select'
                            onChange={this.handleInputChange}
                            value={this.state.selectedEspacio}
                        >
                            <option selected='selected' disabled='disabled'>Selecciona un espacio</option>
                            {espacios.map((espacio) => (
                                <option key={espacio.codigo} value={espacio.codigo}>
                                    {espacio.nombre}
                                </option>
                            ))}

                        </Input>
                    </Col>
                    <Col xs="3">
                        <DayPicker
                            month={new Date(requestDay.split('-')[0], requestDay.split('-')[1] - 1)}
                            selectedDays={selectedDay}
                            onDayClick={this.handleDayClick}
                        />
                    </Col>
                    <Col>
                        <Card>
                            <CardBody>
                                <CardTitle tag='h4'>
                                    Horas disponibles del {requestDay} de {this.state.selectedEspacio}
                                </CardTitle>
                                <Row xs='4'>
                                    {disponibilidad.map((dispo) => (

                                        <Col>
                                            <Button
                                                key={dispo.hora}
                                                color='primary'
                                                href='#'
                                                className='m-2'
                                                onClick={this.handleClickButton}
                                            >
                                                {dispo.hora}:00
                                            </Button>
                                        </Col>

                                    ))}
                                </Row>
                            </CardBody>
                        </Card>
                    </Col>
                </Row>
                <Row>
                    <Col>
                        {reservas.map((reserva) => (
                                <p
                                    key={reserva}
                                >
                                    {reserva}
                                </p>
                        ))}
                    </Col>
                </Row>
            </Container>

        );
    }

}

export default Selectable;