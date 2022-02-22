<?php

namespace App\Http\Controllers;

use App\Models\Bloqueo;
use Illuminate\Http\Request;
use App\Models\Horario;
use App\Models\Reserva;
use App\Models\Espacio;
use App\Models\TipoEspacio;

class DisponibilidadController extends Controller
{
    public function getHorario()
    {
        return response()->json(Horario::All());
    }

    public function getReservas()
    {
        return response()->json(Reserva::All());
    }

    public function getBloqueos()
    {
        return response()->json(Bloqueo::All());
    }

    public function getDisponibilidadPorDia($dia)
    {
        return response()->json(Reserva::where('dia', $dia)->get());
    }

    public function getEspacios($codigo)
    {
        return response()->json(Espacio::where('codigo', $codigo)->get(['nombre', 'descripcion']));
    }

    public function getEspaciosPorTipoEspacio($tipoEspacios)
    {
        return response()->json(Espacio::where('codigoTipoEspacio', $tipoEspacios)->orderBy('codigo', 'asc')->get(['codigo', 'nombre']));
    }

    public function getTipoEspacios()
    {
        return response()->json(TipoEspacio::orderBy('codigo', 'asc')->get(['codigo', 'nombre']));
    }

    public function getDisponibilidad($dia, $codigoEspacio)
    {
        return Horario::whereNotIn('hora', function ($query) use ($dia, $codigoEspacio) {
            $query->select('hora')->from('RESERVAS')->where([['dia', '=', $dia], ['codigoEspacio', '=', $codigoEspacio]]);
        })->whereNotIn('hora', function ($query) use ($dia, $codigoEspacio) {
            $query->select('hora')->from('BLOQUEOS_ESPACIOS_HORAS')->where([['diaBloqueo', '=', $dia,], ['codigoEspacio', '=', $codigoEspacio]]);
        })->get();
    }

    /*public function store(Request $request)
    {
        $nuevaReserva = Reserva::create([
            'codigoCliente' => $request['codigoCliente'],
            'hora' => $request['hora'][0],
            'codigoEspacio' => $request['codigoEspacio'],
            'estado' => $request['estado'],
            'dia' => $request['dia'],
            'precio' => 2102
        ]);

        return response()->json($nuevaReserva, 201);
    }*/

    public function store(Request $request)
    {
        $objeto = json_decode($request->getContent());
        for ($i = 0; $i < count((array)$objeto->hora); $i++) {
            $nuevaReserva = Reserva::create([
                'codigoCliente' => $objeto->codigoCliente,
                'hora' => $objeto->hora[$i],
                'codigoEspacio' => $objeto->codigoEspacio,
                'estado' => $objeto->estado,
                'dia' => $objeto->dia,
                'precio' => 2102
            ]);
        }

        return response()->json("Success", 201);
    }
}
