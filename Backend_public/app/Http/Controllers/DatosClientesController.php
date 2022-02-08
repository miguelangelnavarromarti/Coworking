<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Cliente;

class DatosClientesController extends Controller
{
    public function ver($codigo){
        
        $cliente = Cliente::all()->where('codigo',$codigo);
        return response()->json($cliente, 200);
    }
    

    public function verPassword($codigo){
        //Fa falta aquest??
    }

    public function modificar($codigo){}

    public function modificarPassword($codigo){}

    
}
