import React from 'react';
import axios from 'axios';

import CardEspacio from './CardEspacio';
import DayPickerInput from 'react-day-picker/DayPickerInput';
import dateFnsFormat from 'date-fns/format';
import styles from '../styles.css';


import { Col, Container, Input, InputGroup, InputGroupText, Row } from 'reactstrap';

const API = 'http://localhost:8000';
const REQUEST = '/disponibilidad/';
const ESPACIOS = '/espacios/';
const TIPOESPACIOS = '/tipoEspacios';

function formatDate(date, format, locale) {
    return dateFnsFormat(date, format, { locale });
}


class Buscador extends React.Component {

    constructor(props) {
        super(props);

        this.handleDayChange = this.handleDayChange.bind(this);
        this.handleInputChangeEspacio = this.handleInputChangeEspacio.bind(this);
        this.handleInputChangeTipoEspacio = this.handleInputChangeTipoEspacio.bind(this);
        this.handleClickButton = this.handleClickButton.bind(this);
        this.state = {
            disponibilidad: [],
            selectedTipoEspacio: null,
            tipoEspacios: [],
            selectedEspacio: null,
            espacios: [],
            reservas: [],
            isLoading: false,
            error: null,
            requestDay: `${dateFnsFormat(new Date(), 'yyyy-MM-dd')}`,
            selectedDay: new Date(),
            format: 'yyyy-MM-dd',
        };
    }

    handleDayChange(day) {
        this.setState({
            requestDay: `${dateFnsFormat(day, this.state.format)}`,
            selectedDay: day
        });
        console.log(this.state.requestDay);
        console.log(this.state.selectedDay);
    }

    handleInputChangeEspacio(event) {
        this.setState({
            selectedEspacio: event.target.value,
        });
    }

    handleInputChangeTipoEspacio(event) {
        this.setState({
            selectedTipoEspacio: event.target.value,
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

        if (this.state.selectedTipoEspacio !== null) {
            axios.get(API + ESPACIOS + this.state.selectedTipoEspacio)
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

        axios.get(API + TIPOESPACIOS)
            .then(result => {
                const tipoEspacios = result.data;
                this.setState({
                    tipoEspacios: tipoEspacios,
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

        if (this.state.selectedTipoEspacio !== prevState.selectedTipoEspacio || this.state.requestDay !== prevState.requestDay) {
            this.setState({ isLoading: true });

            axios.get(API + ESPACIOS + this.state.selectedTipoEspacio)
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
    }

    render() {
        const { disponibilidad, espacios, tipoEspacios, reservas, isLoading, error, requestDay, selectedDay } = this.state;
        const FORMAT = 'yyyy-MM-dd';

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
                <div className='my-3 d-flex gap-1 align-self-center align-items-center searcher'>
                    <Col>
                        <InputGroup>
                            <InputGroupText>
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" className="bi bi-door-open" viewBox="0 0 16 16">
                                    <path d="M8.5 10c-.276 0-.5-.448-.5-1s.224-1 .5-1 .5.448.5 1-.224 1-.5 1z" />
                                    <path d="M10.828.122A.5.5 0 0 1 11 .5V1h.5A1.5 1.5 0 0 1 13 2.5V15h1.5a.5.5 0 0 1 0 1h-13a.5.5 0 0 1 0-1H3V1.5a.5.5 0 0 1 .43-.495l7-1a.5.5 0 0 1 .398.117zM11.5 2H11v13h1V2.5a.5.5 0 0 0-.5-.5zM4 1.934V15h6V1.077l-6 .857z" />
                                </svg>
                            </InputGroupText>
                            <Input
                                id='selectTipoEspacio'
                                name='selectTipoEspacio'
                                type='select'
                                onChange={this.handleInputChangeTipoEspacio}
                                value={this.state.selectedTipoEspacio}
                            >
                                <option selected='selected' disabled='disabled'>¿Qué tipo de espacio quieres reservar?</option>
                                {tipoEspacios.map((tipoEspacio) => (
                                    <option key={tipoEspacio.codigo} value={tipoEspacio.codigo}>
                                        {tipoEspacio.nombre}
                                    </option>
                                ))}

                            </Input>
                        </InputGroup>
                    </Col>
                    <Col xs="4">
                        <DayPickerInput
                            formatDate={formatDate}
                            format={FORMAT}
                            placeholder={requestDay}
                            month={new Date(requestDay.split('-')[0], requestDay.split('-')[1] - 1)}
                            selectedDays={selectedDay}
                            onDayChange={this.handleDayChange}
                            className={styles}
                        /*dayPickerProps={
                            <DayPicker disabledDays={[
                                `${dateFnsFormat(new Date(2022, 1, 14), 'yyyy-MM-dd')}`,
                                new Date(2022, 1, 2),
                                {
                                  after: new Date(2022, 1, 20),
                                  before: new Date(2022, 1, 25),
                                },
                              ]} />
                        }
                        */
                        />
                    </Col>
                </div>
                {/*
                <Row>
                    <Col xs="3">
                        <Input
                            id='selectEspacio'
                            name='selectEspacio'
                            type='select'
                            onChange={this.handleInputChangeEspacio}
                            value={this.state.selectedEspacio}
                            className="form-select"
                        >
                            <option selected='selected' disabled='disabled'>Selecciona un espacio</option>
                            {espacios.map((espacio) => (
                                <option key={espacio.codigo} value={espacio.codigo}>
                                    {espacio.nombre}
                                </option>
                            ))}

                        </Input>
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
                */}
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
                <Row className='my-3'>
                    {espacios.map((espacio) => (
                        <Col xs='3'>
                            <CardEspacio key={espacio.codigo} fullRequest={API + REQUEST + requestDay + '/'} espacio={espacio.codigo} />
                        </Col>
                    ))}
                </Row>

            </Container>

        );
    }

}

export default Buscador;