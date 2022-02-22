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
            "password":"123456"
            }';
        $Object = json_decode($jsonX);

        //$Object = json_decode($request);

        $cliente = Cliente::where('nombreUsuario', $Object->nombreUsuario)->get();
        $clienteObjectoCodifi = json_encode($cliente);
        $clienteObjectoDec = json_decode($clienteObjectoCodifi);
        
        if($clienteObjectoDec != null){   
            //Aquest Hash encripta un i mira si es sa mateixa                        
            if(Hash::check($Object->password, $clienteObjectoDec[0]->password)){
                return $next($request);
            }
            else{
                return redirect('login');
            }
        } else{
            return redirect('login');
        }

    }
}
