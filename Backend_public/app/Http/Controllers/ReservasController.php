<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Reserva;

class ReservasController extends Controller
{
  
    public function index($codigoCliente){
        
        $reservas = Reserva::all()->where('codigoCliente',$codigoCliente);
        return response()->json($reservas, 200);
    }

    public function ver($codigoCliente,$codigo){
        
        $reservas = Reserva::where([['codigoCliente', $codigoCliente] ,['codigo',$codigo]])->get();
        return response()->json($reservas, 200);
    }

    public function crear (Request $request){
        $reservaObject = json_decode($request);
        $newReserva = new Reserva;
        $newReserva->codigoCliente = $reservaObject->codigoCliente;
        $newReserva->hora = $reservaObject->hora;
        $newReserva->codigoEspacio = $reservaObject->codigoEspacio;
        $newReserva->estado = $reservaObject->estado;
        $newReserva->dia = $reservaObject->dia;
        $newReserva->precio = $reservaObject->precio;
        $newReserva->diaHoraCreacion = $reservaObject->diaHoraCreacion;

        $newReserva->save();
    }
}
