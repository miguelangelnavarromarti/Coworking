<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\GestionCancelacion;

class GestionCancelacionController extends Controller
{
    public function ver($codigo){
        $gestionCancelacion = GestionCancelacion::where(['codigo',$codigo])->get();
        return response()->json($gestionCancelacion, 200);
    }
}
