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
}
