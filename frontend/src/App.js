import './App.css';
import React from 'react';
import axios from 'axios';

const API = 'http://localhost:8000';

class App extends React.Component {

  constructor(props) {
    super(props);

    this.state = {
      datos: [],
      reservas: [],
      bloqueos: [],
      isLoading: false,
      error: null,
    };
  }

  componentDidMount() {

    this.setState({ isLoading: true });

    axios.get(API + "/horario")
      .then(result => {
        const datos = result.data;
        this.setState({
          datos: datos,
          isLoading: false
        })
      })
      .catch(error => this.setState({
        error,
        isLoading: false
      }));

    axios.get(API + "/reservas")
      .then(result => {
        const reservas = result.data;
        this.setState({
          reservas: reservas,
          isLoading: false
        })
      })
      .catch(error => this.setState({
        error,
        isLoading: false
      }));

      axios.get(API + "/bloqueos")
      .then(result => {
        const bloqueos = result.data;
        this.setState({
          bloqueos: bloqueos,
          isLoading: false
        })
      })
      .catch(error => this.setState({
        error,
        isLoading: false
      }));
  }

  render() {
    const { datos, reservas, bloqueos, isLoading, error } = this.state;

    if (error) {
      return <p>{error.message}</p>;
    }

    if (isLoading) {
      return <p>Loading ...</p>;
    }

    return (
      <div className="App">
        <div className="container">
          <h1>Table JSON React + Axios </h1>
          <table className="table table-striped table-bordered">
            <thead>
              <tr><th>Horarios</th></tr>
            </thead>
            <tbody>
              {datos.map((horaDispo) => (
                <tr key="{horaDispo.hora}">
                  <td>{horaDispo.hora}</td>
                </tr>
              ))}
            </tbody>
          </table>
          <table className="table table-striped table-bordered">
            <thead>
              <tr>
                <th>Código</th>
                <th>Código Cliente</th>
                <th>Hora</th>
                <th>Código Espacio</th>
                <th>Estado</th>
                <th>Día</th>
                <th>Precio</th>
                <th>Día Hora Creación</th>
              </tr>
            </thead>
            <tbody>
              {reservas.map((reserva) => (
                <tr key="{reserva.codigo}">
                  <td>{reserva.codigo}</td>
                  <td>{reserva.codigoCliente}</td>
                  <td>{reserva.hora}</td>
                  <td>{reserva.codigoEspacio}</td>
                  <td>{reserva.estado}</td>
                  <td>{reserva.dia}</td>
                  <td>{reserva.precio}</td>
                  <td>{reserva.diaHoraCreacion}</td>
                </tr>
              ))}
            </tbody>
          </table>
          <table className="table table-striped table-bordered">
            <thead>
              <tr>
                <th>Código</th>
                <th>Código Espacio</th>
                <th>Hora</th>
                <th>Día Bloqueo</th>
                <th>Día Hora Creación</th>
              </tr>
            </thead>
            <tbody>
              {bloqueos.map((bloqueo) => (
                <tr key="{bloqueo.codigo}">
                  <td>{bloqueo.codigo}</td>
                  <td>{bloqueo.codigoEspacio}</td>
                  <td>{bloqueo.hora}</td>
                  <td>{bloqueo.diaBloqueo}</td>
                  <td>{bloqueo.diaHoraCreacion}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    );
  }

}

export default App;
