<?php
header("Access-Control-Allow-Origin: *");

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\DisponibilidadController;

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

Route::get('/config', function () {
    return view('connection');
});

Route::get('/horario', [DisponibilidadController::class, 'getHorario']);

Route::get('/reservas', [DisponibilidadController::class, 'getReservas']);

Route::get('/bloqueos', [DisponibilidadController::class, 'getBloqueos']);

Route::get('/reservas/{dia}', [DisponibilidadController::class, 'getDisponibilidadPorDia']);

Route::get('/disponibilidad/{dia}/{codigoEspacio}', [DisponibilidadController::class, 'getDisponibilidad']);