<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Reserva extends Model
{
    use HasFactory;

    protected $table = 'RESERVAS';

    protected $primaryKey = 'codigo';
    protected $fillable = ['localizador', 'codigoCliente', 'hora', 'codigoEspacio', 'estado', 'dia', 'precio'];
    public $timestamps = false;
    
    
}
