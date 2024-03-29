import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import { Button } from 'reactstrap';
import ClienteHeader from './AreaCliente/ClienteHeader';


function Resumen(props) {
  let { localizador } = useParams();

  const API = 'http://localhost:8000';
  const REQUEST = '/localizador/'

  const [localizadorReserva, setLocalizadorReserva] = useState();
  const [nombreEspacio, setNombreEspacio] = useState();
  const [descuento, setDescuento] = useState();
  const [horas, setHoras] = useState();
  const [precioTotal, setPrecioTotal] = useState();
  const [precioFinal, setPrecioFinal] = useState()

  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    setIsLoading(true);
    axios.get(API + REQUEST + localizador,{
      headers: {
          'authorization':'Bearer ' + props.token,
          'Accept' : 'application/json',
          'Content-Type': 'application/json'
      }
  })
      .then(result => {
        const localizadorReserva = result.data.localizador;
        const nombreEspacio = result.data.nombreEspacio.nombre;
        const descuento = result.data.descuento;
        const horas = result.data.horas;
        const precioTotal = result.data.precio;

        setLocalizadorReserva(localizadorReserva);
        setNombreEspacio(nombreEspacio);
        setDescuento(descuento);
        setHoras(horas);
        setPrecioTotal(precioTotal);
        setPrecioFinal(Math.round(((precioTotal - (precioTotal * (descuento / 100))) + Number.EPSILON) * 100) / 100);
        setIsLoading(false);
      })
      .catch((error) => {
        setError(error);
        setIsLoading(false);
      });
  }, [localizador])


  if (error) {
    return <p>{error.message}</p>;
  }

  if (isLoading) {
    return <p>Loading ...</p>;
  }

  return (
    <div className='d-flex flex-column gap-3 align-items-center mt-1 pb-5'>
      <ClienteHeader/>
      <h2 className='text-center my-4'>Ver los detalles del pedido</h2>
      <div className='shadow bg-blauFort border-groc border-2 rounded-3 text-white mt-2 p-4 d-flex flex-column gap-3 w-50'>
        <div className='d-flex'>
          <span className='fw-light w-50'>Localitzador nº</span>
          <span className='w-50'>{localizadorReserva}</span>
        </div>
        <div className='d-flex'>
          <span className='fw-light w-50'>Espacio reservado</span>
          <span className='w-50'>{nombreEspacio}</span>
        </div>
        <div className='d-flex'>
          <span className='fw-light w-50'>Horas reservadas</span>
          <span className='w-50 d-flex flex-wrap'>
            {horas && horas.map((hora, key) => (
              <span key={key}>
                {hora.hora}:00,
              </span>
            ))}
          </span>
        </div>
        <div className='d-flex'>
          <span className='fw-light w-50'>Precio total</span>
          <span className='w-50'>{precioTotal} €</span>
        </div>
        <div className='d-flex'>
          <span className='fw-light w-50'>Descuento aplicado</span>
          <span className='w-50'>{descuento} %</span>
        </div>
        <div className='d-flex'>
          <span className='fw-light w-50'>Precio final</span>
          <span className='w-50'>{precioFinal} €</span>
        </div>
      </div>

      <a className='my-1 mx-auto w-50' href={"http://localhost:8000/pagar/" + localizador}><Button className='w-100 shadow bg-blauFort border-groc border-2 rounded-3'>PAGAR</Button></a>

    </div>
  );
}


export default Resumen;
