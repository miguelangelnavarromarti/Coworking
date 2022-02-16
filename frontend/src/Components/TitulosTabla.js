import React, { Component } from "react";
import FilaTabla from "./FilaTabla";

class TitulosTabla extends Component {
    constructor(props) {
        super(props);
    
        this.state = {
            titulos = [],
            valores = [],
            
        };
      }
    render(){
        return(
            <table>
                <thead>
                    <tr>
                        Mapa titol amb TH
                    </tr>
                </thead>
                <tbody>            
                    Fer mapa  de TR amb FilaTabla dins
                    <FilaTabla />
                </tbody>
            </table>
        );
    }
}

export default TitulosTabla;