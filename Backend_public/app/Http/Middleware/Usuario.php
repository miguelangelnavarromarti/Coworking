<?php

namespace App\Http\Middleware;

use Closure;
use Illuminate\Http\Request;
use App\Models\Cliente;

class Usuario
{
    /**
     * Handle an incoming request.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \Closure(\Illuminate\Http\Request): (\Illuminate\Http\Response|\Illuminate\Http\RedirectResponse)  $next
     * @return \Illuminate\Http\Response|\Illuminate\Http\RedirectResponse
     */
    public function handle(Request $request, Closure $next)
    {   
        $jsonX = '{
            "nombreUsuario": "JC1",
            "password":"123456"
            }';
        $json = json_decode($jsonX);

        $cliente = Cliente::where('nombreUsuario',$json->nombreUsuario);
        $passwordInsertadaEncripatada = bcrypt ($json->password);

        if($passwordInsertadaEncripatada == $cliente->password){
            return $next($request);
        }
        else{
            return redirect('no-autorizado');
        }
        //---------------------------
 /*       $cliente = Cliente::where('nombreUsuario',$request->nombreUsuario);
        $passwordInsertadaEncripatada = bcrypt ($request->password);

        if($passwordInsertadaEncripatada == $cliente->password){
            return $next($request);
        }
        else{
            return redirect('no-autorizado');
        }
*/

        //return $next($request);
        /*
        if(auth()->user()->email == 'correu que vull'){
            return $next($request);
        }
        else{
            return redirect('no-autorizado');
        }
        */
    }
}
