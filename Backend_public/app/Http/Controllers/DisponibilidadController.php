<?php

namespace App\Http\Controllers;

use App\Models\Bloqueo;
use Illuminate\Http\Request;
use App\Models\Horario;
use App\Models\Reserva;

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

    public function getDisponibilidad($dia, $codigoEspacio) {
        $horario = Horario::All();
        $reservas = Reserva::where([
            ['dia', $dia],
            ['codigoEspacio', $codigoEspacio]
        ])->get();
        $bloqueos = Bloqueo::where([
            ['diaBloqueo', $dia],
            ['codigoEspacio', $codigoEspacio]
        ])->get();

        $arrayHorario = $horario->toArray();
        $disponibilidad = array();

        foreach ($arrayHorario as $dispo) {
            foreach ($dispo as $hora) {
                $disponibilidad[$hora] = "VACIO";
            }
        }

        
        
        dump($disponibilidad);

        foreach($reservas as $reserva) {
            $disponibilidad[$reserva->hora] = $reserva->codigoCliente;
        }

        echo "<br>";

        dump($disponibilidad);

        return $arrayHorario;
    }
}
