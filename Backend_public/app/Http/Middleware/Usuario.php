<?php

namespace App\Http\Middleware;

use Closure;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Hash;
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
            "nombreUsuario": "jc777",
            "password":"123456K"
            }';
        $Object = json_decode($jsonX);

        //$Object = json_decode($request);

        $cliente = Cliente::where('nombreUsuario', $Object->nombreUsuario)->get();
        $clienteObjecto = json_encode($cliente);
        $clienteObjecto2 = json_decode($clienteObjecto);
        
        if(Hash::check($Object->password, $clienteObjecto2[0]->password)){
            return $next($request);
        }
        else{
            return redirect('no-autorizado');
        }

    }
}
