<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Opinion;

class OpinionesController extends Controller
{
    public function index($codigoCliente){
        
        $opiniones = Opinion::where('codigoCliente',$codigoCliente)->get();
        return response()->json($opiniones, 200);
    }
    
    public function ver($codigo,$codigoCliente){
        $opinion = Opinion::where([['codigoCliente', $codigoCliente] ,['codigo',$codigo]])->get();
        return response()->json($opinion, 200);
    }

    public function crear(Request $request){
        $opinionObject = json_decode($request);

        $newOpinion = new Opinion;
        $newOpinion->codigoCliente = $opinionObject->codigoCliente;
        $newOpinion->codigoReserva = $opinionObject->codigoReserva;
        $newOpinion->titulo = $opinionObject->titulo;
        $newOpinion->opinion = $opinionObject->opinion;
        $newOpinion->puntuacion = $opinionObject->puntuacion;

        $newOpinion->save();
    }

    public function modificar(Request $request, $codigoCliente, $codigo){
        $opinion = Opinion::where([['codigoCliente', $codigoCliente] ,['codigo',$codigo]])->get();

        $opinion->titulo = $request->titulo;
        $opinion->opinion = $request->opinion;
        $opinion->puntuacion = $request->puntuacion;

        $opinion->update();
    }

    public function eliminar($codigoCliente, $codigo){

        $opinion = Opinion::where([['codigoCliente', $codigoCliente] ,['codigo',$codigo]])->get();

        $opinion->delete();
    }
}



//https://www.fundaofwebit.com/laravel-8/how-to-insert-data-in-laravel-8