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

    public function crear(Request $request){

        $requestObject = json_decode($request);

        $newFacturaCancelada = new FacturaCancelada;
        $newFacturaCancelada->codigoFactura = $requestObject->codigoFactura;
        $newFacturaCancelada->codigoCliente = $requestObject->codigoCliente;
        $newFacturaCancelada->devolucion = $requestObject->devolucion;
        $newFacturaCancelada->diasAntelacionCancelacion = $requestObject->diasAntelacionCancelacion;
        $newFacturaCancelada->descuentoCancelacion = $requestObject->descuentoCancelacion;
        $newFacturaCancelada->diaHoraCancelacion = $requestObject->diaHoraCancelacion;

        $newFacturaCancelada->save();
        
    }
}
