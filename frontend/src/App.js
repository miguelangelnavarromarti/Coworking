import './App.css';
import React from 'react';
import axios from 'axios';

const API = 'http://localhost:8000';

class App extends React.Component {

  constructor(props) {
    super(props);

    this.state = {
      facturas: [],
      isLoading: false,
      error: null,
    };
  }

  componentDidMount() {


    this.setState({ isLoading: true });

    axios.get(API + "/facturas/1")
      .then(result => this.setState({
          facturas: result.data,
          isLoading: false
        }))
      .catch(error => this.setState({
        error,
        isLoading: false
      }));
  }

  render() {
    const { facturas, isLoading, error } = this.state;

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
              <tr>
                <th>codigoCliente</th>
                <th>codigo</th>
                <th>Precio Total</th>
              </tr>
            </thead>
            <tbody>
              {facturas.map(function(factura,key){
                return (
                  <tr key={key}>
                    <td>{factura.codigoCliente}</td>
                    <td>{factura.codigo}</td>
                    <td>{factura.precioTotal}</td>
                  </tr>
                )
              })}
            </tbody>
          </table>      
        </div>
      </div>
    );
  }

}

export default App;