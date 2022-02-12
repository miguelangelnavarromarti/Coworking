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
