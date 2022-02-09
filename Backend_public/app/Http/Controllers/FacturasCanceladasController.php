<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\FacturaCancelada;

class FacturasCanceladasController extends Controller
{

    public function index($codigoCliente){

        $facturasCanceladas = FacturaCancelada::all()->where('codigoCliente',$codigoCliente);
        return response()->json($facturasCanceladas, 200);
    }  

    public function ver($codigoCliente, $codigo){
        $factura = FacturaCancelada::where([['codigoCliente', $codigoCliente] ,['codigo',$codigo]])->get();
        return response()->json($factura, 200);
    }
}
