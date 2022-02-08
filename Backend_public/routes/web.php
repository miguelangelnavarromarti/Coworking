<?php

use Illuminate\Support\Facades\Route;

use App\Http\Controllers\FacturasController;
use App\Http\Controllers\DatosClientesController;
use App\Http\Controllers\OpinionesController;
/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    return view('welcome');
});

Route::get('/conexion', function () {
    return view('conexion');
});

Route::get('/facturas/{codigoCliente}', [FacturasController::class, 'index']);
Route::post('/facturas', [FacturasController::class, 'crear']);
Route::get('/facturas/{codigo}/{codigoCliente}', [FacturasController::class, 'ver']);


//Route::post('/facturas/canceladas/', [FacturasController::class, 'crear'], [GestionCancelacionController::class, 'ver'])->middleware(usuari);
//Route::get('/facturas/canceladas/{codigo}', [FacturasCanceladasController::class, 'ver'])->middleware(usuari);

Route::get('/datosClientes/{codigo}', [DatosClientesController::class, 'ver']);
Route::get('/datosClientes/{codigo}/password', [DatosClientesController::class, 'verPassword']);

Route::put('/datosClientes/{codigo}', [DatosClientesController::class, 'modificar']);
Route::put('/datosClientes/{codigo}/modificarPassword', [DatosClientesController::class, 'modificarPassword']);

Route::get('/opiniones/{codigoCliente}', [OpinionesController::class, 'index']);
Route::get('/opiniones/{codigoCliente}/{codigo}', [OpinionesController::class, 'ver']);
Route::post('/opiniones/{codigoCliente}', [OpinionesController::class, 'crear']);
Route::put('/opiniones/{codigoCliente}/{codigo}', [OpinionesController::class, 'modificar']);
Route::delete('/opiniones/{codigoCliente}/{codigo}', [OpinionesController::class, 'eliminar']);

