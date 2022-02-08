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
    public function crear(){
        
    }
    public function ver($codigo,$codigoCliente){
        $factura = Factura::where([['codigoCliente', $codigoCliente] ,['codigo',$codigo]])->get();
        return response()->json($factura, 200);
    }
}
