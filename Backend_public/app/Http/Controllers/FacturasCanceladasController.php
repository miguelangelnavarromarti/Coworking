<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\FacturaCancelada;

class FacturasCanceladasController extends Controller
{

    public function index(Request $request){
        $user = auth()->user();
        $facturasCanceladas = FacturaCancelada::where('codigoCliente',$user->codigo)->get();
        return response()->json($facturasCanceladas, 200);
    }  

    public function ver($codigoCliente, $codigo){       //Borrar
        $factura = FacturaCancelada::where([['codigoCliente', $codigoCliente] ,['codigo',$codigo]])->get();
        return response()->json($factura, 200);
    }

    //NO ESTA COMPLET
    public function crear(Request $request){
/*
        $requestObject = json_decode($request);
        $newFacturaCancelada = new FacturaCancelada;
        $newFacturaCancelada->codigoFactura = $requestObject->codigoFactura;
        $newFacturaCancelada->codigoCliente = $requestObject->codigoCliente;
        $newFacturaCancelada->devolucion = $requestObject->devolucion;
        $newFacturaCancelada->diasAntelacionCancelacion = $requestObject->diasAntelacionCancelacion;
        $newFacturaCancelada->descuentoCancelacion = $requestObject->descuentoCancelacion;
        $newFacturaCancelada->diaHoraCancelacion = $requestObject->diaHoraCancelacion;
        $newFacturaCancelada->save();
*/
        $newFacturaCancelada = FacturaCancelada::create([
            'codigoFactura' => $request['codigoFactura'],
            'codigoCliente' => $request['codigoCliente'],
            'devolucion' => $request['devolucion'],
            'diasAntelacionCancelacion' => $request['diasAntelacionCancelacion'],
            'descuentoCancelacion' => $request['descuentoCancelacion'],
            'diaHoraCancelacion' => $request['diaHoraCancelacion'],
        ]);        
        return "S'ha pujat";
    }
}
