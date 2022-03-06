import React from 'react';
import { useState } from 'react';
import { Button } from 'reactstrap';
import { BrowserRouter as Router, Link, Navigate, Route, Routes, useNavigate } from 'react-router-dom';


function ConfirmationButton(props) {

    const [codigoCliente, setCodigoCliente] = useState(props.objeto.codigoCliente);
    const [hora, setHora] = useState(props.horas);
    const [codigoEspacio, setCodigoEspacio] = useState(props.objeto.codigoEspacio);
    const [estado, setEstado] = useState(props.objeto.estado);
    const [dia, setDia] = useState(props.objeto.dia);

    const history = useNavigate();

    const routeChange = (res) => {
        const { localizador } = res
        let path = '/resumen/' + localizador;
        history(path, {
            state: {
                cliente: codigoCliente,
                horas: props.horas,
                espacio: codigoEspacio,
                dia: dia
            }
        });
    }

    let handleSubmit = async (e) => {
        e.preventDefault();
        try {
            let res = await fetch("http://localhost:8000/postReserva", {
                method: "POST",
                body: JSON.stringify({
                    codigoCliente: codigoCliente,
                    hora: props.horas,
                    codigoEspacio: codigoEspacio,
                    estado: estado,
                    dia: dia,
                }),
            });
            let jsonres = await res.json()
            routeChange(jsonres);
        } catch (err) {
            console.log(err);
        }

    };

    return (
        <Button  className='w-100 bg-blauFort border-groc border-2 rounded-3 shadow' onClick={handleSubmit}>{props.texto}</Button>
    );
}
export default ConfirmationButton;