import React from 'react';
import axios from 'axios';

import DayPicker from 'react-day-picker';
import 'react-day-picker/lib/style.css';

import dateFnsFormat from 'date-fns/format';
import { Input } from 'reactstrap';
const API = 'http://localhost:8000';
const REQUEST = '/disponibilidad/';
const ESPACIOS = '/espacios';

class Selectable extends React.Component {


    constructor(props) {
        super(props);

        this.handleDayClick = this.handleDayClick.bind(this);
        this.handleInputChange = this.handleInputChange.bind(this);
        this.state = {
            disponibilidad: [],
            espacios: [],
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
        const { disponibilidad, espacios, isLoading, error, requestDay, selectedDay } = this.state;

        if (error) {
            return <p>{error.message}</p>;
        }

        if (isLoading) {
            return <p>Loading ...</p>;
        }

        return (

            <div>
                <h1>Disponibilidad</h1>

                <DayPicker
                    month={new Date(requestDay.split('-')[0], requestDay.split('-')[1] - 1)}
                    selectedDays={selectedDay}
                    onDayClick={this.handleDayClick}
                />
                <p>
                    {requestDay}
                </p>

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

                <table>
                    <thead>
                        <tr>
                            <th>Hora</th>
                        </tr>
                    </thead>
                    <tbody>
                        {disponibilidad.map((dispo) => (
                            <tr key={dispo.hora}>
                                <td>{dispo.hora}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>

            </div>

        );
    }

}

export default Selectable;