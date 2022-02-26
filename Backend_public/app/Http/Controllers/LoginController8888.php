<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Cliente;

class LoginController8888 extends Controller
{
    public function login(Request $request){
        $email = "admin@admin.com";
        $password = "admin";
        
        

        //$cliente = Cliente ::where('email', $request)->get();
        //$cliente = Cliente::select('codigo')->where('email',$email)->get();
        $codigo = Cliente::where('email',$email)->value('codigo');
        //$codigo = $cliente->only('codigo');
        $credentials = [$codigo, $password];
        //$credentials = [$codigo, $request->password];
        //$credentials = $request->only('codigo', 'password');
        return $credentials;



     /*
        $cliente = Cliente::where('nombreUsuario',$request->nombreUsuario);

        //Guardar variables de sessio
        session(['nombreUsuario' => $request->nombreUsuario , 'password'=> $request->password]);

        session()->regenerate();    //Per regenerar sa sessio(al fer login de nou)
    */
    }
}
