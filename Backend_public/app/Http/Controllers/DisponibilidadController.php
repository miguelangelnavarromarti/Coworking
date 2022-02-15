<?php

namespace App\Http\Controllers;

use App\Models\Bloqueo;
use Illuminate\Http\Request;
use App\Models\Horario;
use App\Models\Reserva;
use App\Models\Espacio;

class DisponibilidadController extends Controller
{
    public function getHorario() {
        return response()->json(Horario::All());
    }

    public function getReservas() {
        return response()->json(Reserva::All());
    }

    public function getBloqueos() {
        return response()->json(Bloqueo::All());
    }

    public function getDisponibilidadPorDia($dia) {
        return response()->json(Reserva::where('dia', $dia)->get());
    }

    public function getEspacios() {
        return response()->json(Espacio::orderBy('codigo', 'asc')->get(['codigo', 'nombre']));
    }

    public function getDisponibilidad($dia, $codigoEspacio) {
        return Horario::whereNotIn('hora', function($query) use($dia, $codigoEspacio) {
            $query->select('hora')->from('RESERVAS')->where([['dia', '=', $dia], ['codigoEspacio', '=', $codigoEspacio]]);
        })->whereNotIn('hora', function($query) use($dia, $codigoEspacio) {
            $query->select('hora')->from('BLOQUEOS_ESPACIOS_HORAS')->where([['diaBloqueo', '=', $dia,], ['codigoEspacio', '=', $codigoEspacio]]);
        })->get();
    }
}
