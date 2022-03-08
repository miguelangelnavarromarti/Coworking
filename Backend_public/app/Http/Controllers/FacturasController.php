<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Factura;
use App\Http\Controllers\UserController;
use App\Models\Espacio;
use App\Models\Reserva;
use App\Models\ReservaFactura;
use JWTAuth;


class FacturasController extends Controller
{
    public function index(Request $request){
        $header = $request->header('authorization');    // T O K E N
        
        $user = auth()->user();

        $facturas = Factura::where('codigoCliente', $user->codigo)->get();
        return response()->json($facturas, 200);
    }
    
    public function crear(Request $request){

        $newFactura = Factura::create([
            'codigoCliente' => $request['codigoCliente'],
            'diaFactura' => $request['diaFactura'],
            'minimoHoraOferta' => $request['minimoHoraOferta'],
            'descuentoOferta' => $request['descuentoOferta'],
            'precioTotal' => $request['precioTotal'],
        ]);
        return "S'ha pujat";        
    }


    public function ver(Request $request, $codigo){
        $user = auth()->user();
        $factura = Factura::where([['codigoCliente', $user->codigo] ,['codigo',$codigo]])->get();
        $codigoFactura = Factura::where('codigo', $codigo)->get('codigo')[0]->codigo;
        $codigoReserva = ReservaFactura::where('codigoFactura', $codigoFactura)->get('codigoReserva')[0]->codigoReserva;
        $codigoEspacio = Reserva::where('codigo', $codigoReserva)->get('codigoEspacio')[0]->codigoEspacio;
        $nombreEspacio = Espacio::where('codigo', $codigoEspacio)->get('nombre')[0]->nombre;

        return response()->json(['factura' => $factura, 'nombreEspacio' => $nombreEspacio], 200);
    }
}
