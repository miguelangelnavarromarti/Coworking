<?php

use Illuminate\Support\Facades\Route;

use App\Http\Controllers\FacturasController;
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

Route::get('/facturas', [FacturasController::class, 'index']);
Route::post('/facturas', [FacturasController::class, 'crear']);
Route::get('/facturas/{codigo}', [FacturasController::class, 'ver']);

/*
Route::post('/facturas/canceladas/', [FacturasController::class, 'crear'], [GestionCancelacionController::class, 'ver'])->middleware(usuari);
Route::get('/facturas/canceladas/{codigo}', [FacturasCanceladasController::class, 'ver'])->middleware(usuari);

Route::get('/datosClientes', [DatosClientesController::class, 'ver'])->middleware(usuari);
Route::get('/datosClientes/password', [DatosClientesController::class, 'verPassword'])->middleware(usuari);

Route::put('/datosClientes', [DatosClientesController::class, 'modificar'])->middleware(usuari);
Route::put('/datosClientes/modificarPassword', [DatosClientesController::class, 'modificarPassword'])->middleware(usuari);

Route::get('/opiniones', [OpinionesController::class, 'ver'])->middleware(usuari);
Route::get('/opiniones/{codigo}', [OpinionesController::class, 'ver'])->middleware(usuari);
Route::post('/opiniones', [OpinionesController::class, 'crear'])->middleware(usuari);
Route::put('/opiniones/{codigo}', [OpinionesController::class, 'modificar'])->middleware(usuari);
Route::delete('/opiniones/{codigo}', [OpinionesController::class, 'eliminar'])->middleware(usuari);
*/