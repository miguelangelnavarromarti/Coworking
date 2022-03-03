<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Factura;
use App\Http\Controllers\UserController;
use JWTAuth;


class FacturasController extends Controller
{
    public function index(Request $request, $codigoCliente){
        $header = $request->header('authorization');    // T O K E N

        
       
//-------------------------------------------------------
        $facturas = Factura::where('codigoCliente',$codigoCliente)->get();
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


    public function ver($codigo,$codigoCliente){
        $factura = Factura::where([['codigoCliente', $codigoCliente] ,['codigo',$codigo]])->get();
        return response()->json($factura, 200);
    }
}
