<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Cliente;

class DatosClientesController extends Controller
{
    public function ver($codigo){
        
        $cliente = Cliente::where('codigo',$codigo)->get();
        return response()->json($cliente, 200);
    }
    

    public function modificar(Request $request, $codigo){
        $cliente = Cliente::all()->where('codigo',$codigo);

        $cliente->password = $request->password;
        $cliente->nombre = $request->nombre;
        $cliente->apellido1 = $request->apellido1;
        $cliente->apellido2 = $request->apellido2;
        $cliente->telefono = $request->telefono;
        $cliente->email = $request->email;
        $cliente->update();
    }


    public function crear(Request $request){
        $clienteObject = json_decode($request);
        $newCliente = new Cliente;
        $newCliente->nombreUsuario = $clienteObject->nombreUsuario;
        $newCliente->password = $clienteObject->password;
        $newCliente->nombre = $clienteObject->nombre;
        $newCliente->apellido1 = $clienteObject->apellido1;
        $newCliente->apellido2 = $clienteObject->apellido2;
        $newCliente->telefono = $clienteObject->telefono;
        $newCliente->email = $clienteObject->email;
        $newCliente->rol = $clienteObject->rol;
        $newCliente->alta = $clienteObject->alta;

        $newCliente->save();

    }
}
