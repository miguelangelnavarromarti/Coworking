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
    
    public function ver($codigoCliente,$codigo){
        $opinion = Opinion::where([['codigoCliente', $codigoCliente] ,['codigo',$codigo]])->get();
        return response()->json($opinion, 200);
    }

    public function crear(Request $request){
        //$opinionObject = json_decode($request);

        $nuevaOpinion= Opinion::create([
            'codigoCliente' => $request['codigoCliente'],
            'codigoReserva' => $request['codigoReserva'],
            'titulo' => $request['titulo'],
            'opinion' => $request['opinion'],
            'puntuacion' => $request['puntuacion'],
            
        ]);
        return "S'ha pujat";
/*
        $newOpinion = new Opinion;
        $newOpinion->codigoCliente = $opinionObject->codigoCliente;
        $newOpinion->codigoReserva = $opinionObject->codigoReserva;
        $newOpinion->titulo = $opinionObject->titulo;
        $newOpinion->opinion = $opinionObject->opinion;
        $newOpinion->puntuacion = $opinionObject->puntuacion;
        $nuevaOpinion->save();
*/        
        
    }

    public function modificar(Request $request, $codigoCliente, $codigo){
        //$opinion = Opinion::where([['codigoCliente', $codigoCliente] ,['codigo',$codigo]])->get();
        $opinion = Opinion::findOrFail($codigo);
        $opinion->titulo = $request->titulo;
        $opinion->opinion = $request->opinion;
        $opinion->puntuacion = $request->puntuacion;

        $opinion->save();
        return "MODIFICAT";
    }

    public function eliminar($codigoCliente, $codigo){

        //$opinion = Opinion::where([['codigoCliente', $codigoCliente] ,['codigo',$codigo]])->get();
        $opinion = Opinion::findOrFail($codigo);
        $opinion->delete();
        return "ELIMINAT";
    }
}



//https://www.fundaofwebit.com/laravel-8/how-to-insert-data-in-laravel-8