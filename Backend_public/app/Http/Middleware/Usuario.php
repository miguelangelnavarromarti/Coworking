<?php

namespace App\Http\Middleware;

use Closure;
use Illuminate\Http\Request;

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
        if($request->usuario == 'admin'){
            return $next($request);
        }
        else{
            return redirect('no-autorizado');
        }
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
