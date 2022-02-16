<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Cliente;

class LoginController extends Controller
{
    public function login(Request $request){
        
     
        $cliente = Cliente::where('nombreUsuario',$request->nombreUsuario);

        //Guardar variables de sessio
        session(['nombreUsuario' => $request->nombreUsuario , 'password'=> $request->password]);

        session()->regenerate();    //Per regenerar sa sessio(al fer login de nou)

        
    }
}
