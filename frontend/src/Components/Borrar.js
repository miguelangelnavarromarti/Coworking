import React, { Component } from "react";

import Home from "./Components/FilaTabla";
import TitulosTabla from "./TitulosTabla";

class Borrar extends Component {

    render() {
        return (
            <div>
                <h2>BORRAR</h2>
                <h1>Taula</h1>
                <TitulosTabla  titulos={this.state.titulos}></TitulosTabla>/>

                
            </div>
        );
    }
}

export default Borrar;