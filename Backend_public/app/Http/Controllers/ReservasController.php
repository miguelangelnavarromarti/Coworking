<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Reserva;
use App\Models\ReservaFactura;
use Symfony\Component\Console\Output\ConsoleOutput;

class ReservasController extends Controller
{
  
    public function index(Request $request){
        $user = auth()->user();
        
        $reservas = Reserva::where('codigoCliente',$user->codigo)->get();
        
        return response()->json($reservas, 200);
    }

    public function ver(Request $request, $codigo){
        $user = auth()->user();
        $reservas = Reserva::where([['codigoCliente', $user->codigo] ,['codigo',$codigo]])->get();
        return response()->json($reservas, 200);
    }

    public function reservasFactura($codigoFactura){                
// SELECT * from RESERVAS where codigo in (SELECT codigoReserva from FACTURAS_RESERVAS where codigoFactura = $codigoFactura);
        return Reserva::whereIn('codigo', function($query) use ($codigoFactura) {
        $query->select('codigoReserva')->from('FACTURAS_RESERVAS')->where('codigoFactura', '=', $codigoFactura);
        })->get();
    }

    public function crear (Request $request){
/*
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
*/

        $newReserva = Reserva::create([
            'codigoCliente' => $request['codigoCliente'],
            'hora' => $request['hora'],
            'codigoEspacio' => $request['codigoEspacio'],
            'estado' => $request['estado'],
            'dia' => $request['dia'],
            'precio' => $request['precio'],
            'diaHoraCreacion' => ['diaHoraCreacion'],
        ]);
        return "S'ha pujat";
    }
}
