import { useState } from 'react';
import { Button } from 'reactstrap';


function ConfirmationButton(props) {
    
    const [codigoCliente, setCodigoCliente] = useState(props.objeto.codigoCliente);
    const [hora, setHora] = useState(props.objeto.hora);
    const [codigoEspacio, setCodigoEspacio] = useState(props.objeto.codigoEspacio);
    const [estado, setEstado] = useState(props.objeto.estado);
    const [dia, setDia] = useState(props.objeto.dia);
    const [message, setMessage] = useState("");

    let handleSubmit = async (e) => {
        e.preventDefault();
        try {
            let res = await fetch("http://localhost:8000/postReserva", {
                method: "POST",
                body: JSON.stringify({
                    codigoCliente: codigoCliente,
                    hora: hora,
                    codigoEspacio: codigoEspacio,
                    estado: estado,
                    dia: dia,
                }),
            });
            /*let resJson = await res.json();
            if (res.status === 200) {
                setCodigoCliente("");
                setHora("");
                setCodigoEspacio("");
                setEstado("");
                setDia("");
                setMessage("Reserva generada correctamente");
            } else {
                setMessage("Ha ocurrido algún error");
            }*/
        } catch (err) {
            console.log(err);
        }
    };

    return (
        <Button color='primary' className='w-100' onClick={handleSubmit}>{props.texto}</Button>
    );
}
export default ConfirmationButton;