import './App.css';
import React from 'react';
import Facturas from './Components/Facturas';
import Header from './Components/Header';



class App extends React.Component {

  constructor(props) {
    super(props);

    this.state = {
     
    };
  }

 

  render() {
   
    return (
      <div className="App">
        <Header></Header>
        <Facturas></Facturas>      
      </div>
    );
  }

}

export default App;