<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Factura;

class FacturasController extends Controller
{
    public function index($codigoCliente){
        
        $facturas = Factura::all()->where('codigoCliente',$codigoCliente);
        return response()->json($facturas, 200);
    }
    
    public function crear(Request $request){
        $requestObject = json_decode($request); //Tenc un objecte ara

        $novaFactura = new Factura;
        $novaFactura->codigoCliente = $requestObject->codigoCliente;
        $novaFactura->diaFactura = $requestObject->diaFactura;
        $novaFactura->minimoHoraOferta = $requestObject->minimoHoraOferta;
        $novaFactura->descuentoOferta = $requestObject->descuentoOferta;
        $novaFactura->precioTotal = $requestObject->precioTotal;

        $novaFactura->save();
        
        
        return $novaFactura;
        //return response()->json($json, 200);
    }


    public function ver($codigo,$codigoCliente){
        $factura = Factura::where([['codigoCliente', $codigoCliente] ,['codigo',$codigo]])->get();
        return response()->json($factura, 200);
    }
}
