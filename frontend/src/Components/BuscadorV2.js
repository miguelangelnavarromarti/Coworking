import React from 'react';
import axios from 'axios';

import CardEspacio from './CardEspacio';
import DayPickerInput from 'react-day-picker/DayPickerInput';
import dateFnsFormat from 'date-fns/format';
import styles from '../styles.css';
import ConfirmationButton from './ConfirmationButton';


import { Button, Col, Container, Input, InputGroup, InputGroupText, Row } from 'reactstrap';

const API = 'http://localhost:8000';
const REQUEST = '/disponibilidad/';
const ESPACIO = '/espacio/';
const ESPACIOS = '/espacios/';
const TIPOESPACIOS = '/tipoEspacios';

function formatDate(date, format, locale) {
    return dateFnsFormat(date, format, { locale });
}


class BuscadorV2 extends React.Component {

    constructor(props) {
        super(props);

        this.handleDayChange = this.handleDayChange.bind(this);
        this.handleInputChangeEspacio = this.handleInputChangeEspacio.bind(this);
        this.handleInputChangeTipoEspacio = this.handleInputChangeTipoEspacio.bind(this);
        this.handleClickButton = this.handleClickButton.bind(this);
        this.handleClickButtonReverse = this.handleClickButtonReverse.bind(this);
        this.state = {
            disponibilidad: [],
            selectedTipoEspacio: null,
            tipoEspacios: [],
            selectedEspacio: null,
            espacios: [],
            espacio: [],
            tipoEspacio: [],
            reservas: [],
            reserva: {
                codigoCliente: 1,
                codigoEspacio: null,
                estado: 'confirmado',
                dia: `${dateFnsFormat(new Date(), 'yyyy-MM-dd')}`,
            },
            isLoading: false,
            error: null,
            requestDay: `${dateFnsFormat(new Date(), 'yyyy-MM-dd')}`,
            selectedDay: new Date(),
            format: 'yyyy-MM-dd',
        };
    }


    handleDayChange(day) {
        this.setState(prevState => ({
            requestDay: `${dateFnsFormat(day, this.state.format)}`,
            selectedDay: day,
            reservas: [],
            reserva: {
                ...prevState.reserva, dia: `${dateFnsFormat(day, this.state.format)}`
            },
        }));
    }

    handleInputChangeEspacio(event) {
        this.setState(prevState => ({
            selectedEspacio: event.target.value,
            reserva: {
                ...prevState.reserva, codigoEspacio: event.target.value
            },
        }));
    }

    handleInputChangeTipoEspacio(event) {
        this.setState({
            selectedTipoEspacio: event.target.value,
        });
    }

    async handleClickButton(i) {
        const reservas = this.state.reservas.slice();
        const disponibilidad = this.state.disponibilidad.slice();
        const horaBoton = i.target.text.split(":")[0];

        let indexHora;
        for (let i = 0; i < disponibilidad.length; i++) {
            if (disponibilidad[i].hora == horaBoton) {
                indexHora = i;
            }
        }

        reservas.push(horaBoton);
        reservas.sort((a, b) => parseInt(a) - parseInt(b));
        disponibilidad.splice(indexHora, 1);

        await this.setState({
            reservas: reservas,
            disponibilidad: disponibilidad,
        });
    }

    async handleClickButtonReverse(i) {
        const reservas = this.state.reservas.slice();
        const disponibilidad = this.state.disponibilidad.slice();
        const hora = i.target.text.split(":")[0];
        const objectHora = { hora: hora.toString() };

        var indexReserva;
        for (let i = 0; i < reservas.length; i++) {
            if (reservas[i] == hora) {
                indexReserva = i;
                break;
            }
        }

        reservas.splice(indexReserva, 1);
        disponibilidad.push(objectHora);
        disponibilidad.sort((a, b) => parseInt(a.hora) - parseInt(b.hora));

        await this.setState({
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

        if (this.state.selectedTipoEspacio !== null) {
            axios.get(API + TIPOESPACIOS + "/" + this.state.selectedTipoEspacio)
                .then(result => {
                    const tipoEspacio = result.data;
                    this.setState({
                        tipoEspacio: tipoEspacio,
                        isLoading: false
                    })
                })
                .catch(error => this.setState({
                    error,
                    isLoading: false
                }));
        }

        if (this.state.selectedEspacio !== null) {
            axios.get(API + ESPACIO + this.state.selectedEspacio)
                .then(result => {
                    const espacio = result.data;
                    this.setState({
                        espacio: espacio,
                        isLoading: false
                    })
                })
                .catch(error => this.setState({
                    error,
                    isLoading: false
                }));
        }
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

        if (this.state.selectedTipoEspacio !== prevState.selectedTipoEspacio) {
            axios.get(API + TIPOESPACIOS + "/" + this.state.selectedTipoEspacio)
                .then(result => {
                    const tipoEspacio = result.data;
                    this.setState({
                        tipoEspacio: tipoEspacio,
                        isLoading: false
                    })
                })
                .catch(error => this.setState({
                    error,
                    isLoading: false
                }));
        }

        if (this.state.selectedEspacio !== prevState.selectedEspacio) {
            axios.get(API + ESPACIO + this.state.selectedEspacio)
                .then(result => {
                    const espacio = result.data;
                    this.setState({
                        espacio: espacio,
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
        const { disponibilidad, espacio, espacios, tipoEspacio, tipoEspacios, reservas, reserva, isLoading, error, requestDay, selectedDay } = this.state;
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
                <div className='my-3 d-flex gap-1 align-self-center align-items-center searcherV2'>
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
                                defaultValue={'DEFAULT'}
                                onChange={this.handleInputChangeTipoEspacio}
                                value={this.state.selectedTipoEspacio}
                            >
                                <option value='DEFAULT' disabled='disabled'>¿Qué tipo de espacio quieres reservar?</option>
                                {tipoEspacios && tipoEspacios.map((tipoEspacio) => (
                                    <option key={tipoEspacio.codigo} value={tipoEspacio.codigo}>
                                        {tipoEspacio.nombre}
                                    </option>
                                ))}

                            </Input>
                        </InputGroup>
                    </Col>
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
                                defaultValue={'DEFAULT'}
                                onChange={this.handleInputChangeEspacio}
                                value={this.state.selectedEspacio}
                            >
                                <option value='DEFAULT' disabled='disabled'>¿Qué espacio quieres reservar?</option>
                                {espacios && espacios.map((espacio) => (
                                    <option key={espacio.codigo} value={espacio.codigo}>
                                        {espacio.nombre}
                                    </option>
                                ))}

                            </Input>
                        </InputGroup>
                    </Col>
                    <Col xs="2">
                        <DayPickerInput
                            formatDate={formatDate}
                            format={FORMAT}
                            placeholder={requestDay}
                            month={new Date(requestDay.split('-')[0], requestDay.split('-')[1] - 1)}
                            selectedDays={selectedDay}
                            onDayChange={this.handleDayChange}
                            className={styles}
                            dayPickerProps={{
                                disabledDays: [
                                    {
                                        before: new Date()
                                    }
                                ]
                            }}
                        />
                    </Col>
                </div>
                <Row>
                    <Col xs='6'>
                        {tipoEspacio.tipoEspacio && tipoEspacio.tipoEspacio.map((tipoEspacio, key) => (
                            <div key={key}>
                                <h5 className="card-title">{tipoEspacio.nombre}</h5>
                            </div>
                        ))}
                        <p className="card-text">{tipoEspacio.precio}</p>
                    </Col>
                    <Col xs='6'>
                        {espacio.espacio && espacio.espacio.map((espacio, key) => (
                            <div key={key}>
                                <h5 className="card-title">{espacio.nombre}</h5>
                                <p className="card-text">{espacio.descripcion}</p>
                            </div>
                        ))}
                    </Col>
                </Row>
                <Row className='my-3'>
                    <Col className='d-flex align-items-center'>
                        <div className='w-45 d-flex flex-wrap border-dispo justify-content-between'>
                            <div className='border-bottom border-dark w-100 text-center fw-light py-2'>
                                Horas Disponibles
                            </div>
                            <div className='d-flex flex-wrap'>
                                {disponibilidad.map((dispo) => (
                                    <Button
                                        key={dispo.hora}
                                        color='light'
                                        href='#'
                                        className='m-2'
                                        onClick={this.handleClickButton}
                                    >
                                        {dispo.hora}:00
                                    </Button>
                                ))}
                            </div>
                        </div>
                        <p className='w-10 text-center'>
                            <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" fill="currentColor" className="bi bi-arrow-left-right" viewBox="0 0 16 16">
                                <path fill-rule="evenodd" d="M1 11.5a.5.5 0 0 0 .5.5h11.793l-3.147 3.146a.5.5 0 0 0 .708.708l4-4a.5.5 0 0 0 0-.708l-4-4a.5.5 0 0 0-.708.708L13.293 11H1.5a.5.5 0 0 0-.5.5zm14-7a.5.5 0 0 1-.5.5H2.707l3.147 3.146a.5.5 0 1 1-.708.708l-4-4a.5.5 0 0 1 0-.708l4-4a.5.5 0 1 1 .708.708L2.707 4H14.5a.5.5 0 0 1 .5.5z" />
                            </svg>
                        </p>
                        <div className='w-45'>
                            <div className='w-100 d-flex flex-wrap border-dispo'>
                                <div className='border-bottom border-dark w-100 text-center fw-light py-2'>
                                    Horas Reservades
                                </div>
                                <div className='d-flex flex-wrap'>
                                    {reservas.map((reserva) => (
                                        <Button
                                            key={reserva}
                                            color='light'
                                            href='#'
                                            className='m-2'
                                            onClick={this.handleClickButtonReverse}
                                        >
                                            {reserva}:00
                                        </Button>
                                    ))}
                                </div>

                            </div>
                            <ConfirmationButton texto='Reservar' objeto={reserva} horas={reservas} />
                        </div>
                    </Col>
                </Row>

            </Container>
        );
    }

}

export default BuscadorV2;