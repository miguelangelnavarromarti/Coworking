<?php
namespace App\Http\Controllers;

use App\Models\Factura;
use Illuminate\Http\Request;
use App\Models\FacturaCancelada;
use App\Models\GestionCancelacion;
use DateTime;
use Illuminate\Support\Facades\Redirect;

class FacturasCanceladasController extends Controller
{

    public function index(Request $request)
    {
        $user = auth()->user();
        $facturasCanceladas = FacturaCancelada::where('codigoCliente', $user->codigo)->get();
        return response()->json($facturasCanceladas, 200);
    }

    public function ver($codigoCliente, $codigo)
    {       //Borrar
        $factura = FacturaCancelada::where([['codigoCliente', $codigoCliente], ['codigo', $codigo]])->get();
        return response()->json($factura, 200);
    }

    //NO ESTA COMPLET
    public function crear(Request $request)
    {
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

    public function create(Request $request)
    {
        $codigoCliente = auth()->user()->codigo;
        $objeto = json_decode($request->getContent());
        $codigoFactura = $objeto->codigoFactura;

        $diaFactura = Factura::where('codigo', $codigoFactura)->get()[0]->diaFactura;
        $precioFactura = Factura::where('codigo', $codigoFactura)->get()[0]->precioTotal;
        $hoy = date("Y-m-d");
        $dateTimeDiaFactura = new DateTime($diaFactura);
        $dateTimeHoy = new DateTime($hoy);
        $intervalo = $dateTimeDiaFactura->diff($dateTimeHoy);
        $diasAntelacion = $intervalo->format('%a');

        $cancelaciones = GestionCancelacion::orderBy('diasAntelacion', 'asc')->get();
        
        $descuentoCancelacion = 0;

        for ($i = 0; $i < count($cancelaciones); $i++) {
            if ($diasAntelacion >= $cancelaciones[$i]->diasAntelacion) {
                $descuentoCancelacion = $cancelaciones[$i]->devolucion;
            }
        }

        $devolucion = 0;
        $devolucion = $precioFactura * ($descuentoCancelacion / 100);

        $facturaCancelada = FacturaCancelada::create([
            'codigoFactura' => $codigoFactura,
            'codigoCliente' => $codigoCliente,
            'devolucion' => $devolucion,
            'diasAntelacionCancelacion' => $diasAntelacion,
            'descuentoCancelacion' => $descuentoCancelacion
        ]);
    }
}