<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Cliente;

class DatosClientesController extends Controller
{
    public function ver(Request $request){
        //$header = $request->header('authorization');    // T O K E N
        
        $user = auth()->user();

        $cliente = Cliente::where('codigo',$user->codigo)->get();
        return response()->json($cliente, 200);
    }
    

    public function modificar(Request $request){
        $user = auth()->user();
        
        $cliente = Cliente::findOrFail($user->codigo);

        $password = bcrypt($request->password); //Encripta sa password
        $cliente->password = $password; //La guarda encriptada

        $cliente->nombre = $request->nombre;
        $cliente->apellido1 = $request->apellido1;
        $cliente->apellido2 = $request->apellido2;
        $cliente->telefono = $request->telefono;
        $cliente->codigoPostal = $request->codigoPostal;
        $cliente->email = $request->email;
        $cliente->save();
        return "MODIFICAT";
    }


    public function crear(Request $request){

        $newCliente = Cliente::create([
            'nombreUsuario' => $request['nombreUsuario'],
            'password' => $request['password'],
            'nombre' => $request['nombre'],
            'apellido1' => $request['apellido1'],
            'apellido2' => $request['apellido2'],
            'telefono' => $request['telefono'],
            'email' => $request['email'],
            'rol' => $request['rol'],
            'alta' => $request['alta'],
        ]);        

    }
}
