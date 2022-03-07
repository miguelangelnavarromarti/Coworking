<?php
header('Access-Control-Allow-Origin: *');
header("Access-Control-Allow-Headers: X-API-KEY, Origin, X-Requested-With, Content-Type, Accept, Access-Control-Request-Method, authorization");
header("Access-Control-Allow-Methods: GET, POST, OPTIONS, PUT, DELETE");
header("Allow: GET, POST, OPTIONS, PUT, DELETE");



use Illuminate\Support\Facades\Route;
use App\Http\Controllers\DisponibilidadController;

use App\Http\Controllers\FacturasController;
use App\Http\Controllers\DatosClientesController;
use App\Http\Controllers\OpinionesController;
use App\Http\Controllers\ReservasController;
use App\Http\Controllers\FacturasCanceladasController;
use App\Http\Controllers\GestionCancelacionesController;
use App\Http\Controllers\RedsysController;



use App\Http\Controllers\LoginController8888;


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

//BORRAR DSP
Route::post('prueba',function(){
    return "has entrat be a sa ruta";
})->middleware('jwt.verify');
Route::get('noAutorizado', function(){
    return 'No estas autorizado';
});

//Route::get('/login', [LoginController8888::class, 'login']);




Route::get('/facturas', [FacturasController::class, 'index'])->middleware('jwt.verify');
Route::get('/facturas/{codigo}', [FacturasController::class, 'ver'])->middleware('jwt.verify');
Route::post('/facturas', [FacturasController::class, 'crear'])->middleware('jwt.verify');

Route::get('/facturasCanceladas', [FacturasCanceladasController::class, 'index'])->middleware('jwt.verify');
Route::get('/facturasCanceladas/{codigoCliente}/{codigo}', [FacturasCanceladasController::class, 'ver'])->middleware('jwt.verify');

//Route::post('/facturasCanceladas/{codigoCliente}/', [FacturasCanceladasController::class, 'crear'], [GestionCancelacionesController::class, 'ver']);

Route::get('/datosClientes', [DatosClientesController::class, 'ver'])->middleware('jwt.verify');
Route::put('/datosClientes', [DatosClientesController::class, 'modificar'])->middleware('jwt.verify');


Route::get('/opiniones', [OpinionesController::class, 'index'])->middleware('jwt.verify');
Route::get('/opiniones/{codigo}', [OpinionesController::class, 'ver'])->middleware('jwt.verify');     //NO FA FALTA JA, BORRAR
Route::post('/opiniones', [OpinionesController::class, 'crear'])->middleware('jwt.verify');
Route::put('/opiniones/{codigo}', [OpinionesController::class, 'modificar'])->middleware('jwt.verify');
Route::delete('/opiniones/{codigo}', [OpinionesController::class, 'eliminar'])->middleware('jwt.verify');

Route::get('/reservas', [ReservasController::class, 'index'])->middleware('jwt.verify');
Route::get('/reservas/{codigo}', [ReservasController::class, 'ver'])->middleware('jwt.verify');
Route::get('/reservasFactura/{codigoFactura}', [ReservasController::class, 'reservasFactura'])->middleware('jwt.verify');

Route::post('/reservas', [ReservasController::class, 'crear']);

Route::get('/config', function () {
    return view('connection');
});

Route::get('/horario', [DisponibilidadController::class, 'getHorario']);

Route::get('/reservas', [DisponibilidadController::class, 'getReservas']);

Route::get('/bloqueos', [DisponibilidadController::class, 'getBloqueos']);

Route::get('/reservas/{dia}', [DisponibilidadController::class, 'getDisponibilidadPorDia']);

Route::get('/tipoEspacios', [DisponibilidadController::class, 'getTipoEspacios']);

Route::get('/codigoEspacio/{codigo}', [DisponibilidadController::class, 'getEspacios']);
Route::get('/espacio/{codigo}', [DisponibilidadController::class, 'getDatosEspacio']);

Route::get('/espacios/{tipoEspacio}', [DisponibilidadController::class, 'getEspaciosPorTipoEspacio']);

Route::get('/tipoEspacios/{codigo}', [DisponibilidadController::class, 'getTipoEspacioPorCodigo']);

Route::get('/espacio/{codigo}', [DisponibilidadController::class, 'getEspacioPorCodigo']);

Route::get('/disponibilidad/{dia}/{codigoEspacio}', [DisponibilidadController::class, 'getDisponibilidad']);

Route::post('/postReserva', [DisponibilidadController::class, 'store']);

Route::get('/localizador/{localizador}', [DisponibilidadController::class, 'verReservasPorLocalizador'])->middleware('jwt.verify');

Route::get('/pagar/{localizador}' , [RedsysController::class, 'index']);

Route::get('/respuestaOK', [RedsysController::class, 'respuestaOK']);

Route::get('/respuestaKO', [RedsysController::class, 'respuestaKO']);

Route::post('cancelarFactura/{codigo}', [FacturasCanceladasController::class, 'create']);