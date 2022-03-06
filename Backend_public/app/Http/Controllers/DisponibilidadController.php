<?php

namespace App\Http\Controllers;

use App\Models\Bloqueo;
use Illuminate\Http\Request;
use App\Models\Horario;
use App\Models\Reserva;
use App\Models\Espacio;
use App\Models\GestionOfertas;
use App\Models\Tarifas;
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
        return response()->json(Espacio::where('codigo', $codigo)->get('codigo')[0]->codigo);
        //return response()->json(Espacio::where('codigo', $codigo)->get(['nombre', 'descripcion']));
    }
    public function getDatosEspacio($codigo)
    {
        return response()->json(['reserva' => Espacio::where('codigo', $codigo)->get()[0]]);
    }

    public function getEspaciosPorTipoEspacio($tipoEspacios)
    {
        return response()->json(Espacio::where('codigoTipoEspacio', $tipoEspacios)->orderBy('codigo', 'asc')->get());
    }

    public function getTipoEspacioPorCodigo($codigo)
    {
        $tipoEspacio = TipoEspacio::where('codigo', $codigo)->get();
        $precio = Tarifas::where([['codigoTipoEspacio', '=', $codigo],['porDefecto', '=', 'true']])->get('precio')[0]->precio;
        return response()->json([
            'tipoEspacio' => $tipoEspacio,
            'precio' => $precio.' € per hora'
        ]);
    }

    public function getEspacioPorCodigo($codigo)
    {
        $espacio = Espacio::where('codigo', $codigo)->get(['nombre', 'descripcion']);
        return response()->json([
            'espacio' => $espacio
        ]);
    }

    public function getTipoEspacios()
    {
        return response()->json(TipoEspacio::orderBy('codigo', 'asc')->get(['codigo', 'nombre']));
    }

    public function getDisponibilidad($dia, $codigoEspacio)
    {
        return Horario::whereNotIn('hora', function ($query) use ($dia, $codigoEspacio) {
            $query->select('hora')->from('RESERVAS')->where([['dia', '=', $dia], ['codigoEspacio', '=', $codigoEspacio], ['estado', '=', 'confirmado']]);
        })->whereNotIn('hora', function ($query) use ($dia, $codigoEspacio) {
            $query->select('hora')->from('BLOQUEOS_ESPACIOS_HORAS')->where([['diaBloqueo', '=', $dia,], ['codigoEspacio', '=', $codigoEspacio]]);
        })->get();
    }

    public function store(Request $request)
    {
        $objeto = json_decode($request->getContent());
        $precio = Espacio::where('codigo', $objeto->codigoEspacio)->get('codigo')[0]->codigo;

        $localizador = strval(rand(10000, 99999));

        for ($i = 0; $i < count((array)$objeto->hora); $i++) {
            $nuevaReserva = Reserva::create([
                'codigoCliente' => $objeto->codigoCliente,
                'localizador' => $localizador,
                'hora' => $objeto->hora[$i],
                'codigoEspacio' => $objeto->codigoEspacio,
                'estado' => $objeto->estado,
                'dia' => $objeto->dia,
                'precio' => $precio
            ]);
        }

        return response()->json(['localizador' => $localizador], 201);
    }

    public function verReservasPorLocalizador($localizador)
    {
        $codigoEspacio = Reserva::where('localizador', $localizador)->get('codigoEspacio')[0]->codigoEspacio;
        $reservas = Reserva::where('localizador', $localizador)->get();
        $nombreEspacio = Espacio::where('codigo', $codigoEspacio)->get('nombre')[0];
        $numeroReservas = count($reservas);
        $ofertas = GestionOfertas::orderBy('minimoHora', 'desc')->get();
        $descuento = 0;
        $horas = Reserva::where('localizador', $localizador)->get('hora');
        $precios = Reserva::where('localizador', $localizador)->get('precio');

        $precioTotal = 0;
        for ($j = 0; $j < count($precios); $j++) {
            $precioTotal = $precioTotal + $precios[$j]->precio;
        }


        for ($i = 0; $i < count($ofertas); $i++) {
            if ($numeroReservas >= $ofertas[$i]->minimoHora) {
                $descuento = $ofertas[$i]->descuento;
                break;
            }
        }
        return response()->json([
            'localizador' => $localizador,
            'nombreEspacio' => $nombreEspacio,
            'descuento' => $descuento,
            'horas' => $horas,
            'precio' => $precioTotal,
            'ofertas' => $ofertas
        ]);
    }
}
