<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Reserva extends Model
{
    use HasFactory;

    protected $fillable = [
        'codigo',
        'codigoCliente',
        'hora',
        'codigoEspacio',
        'estado',
        'dia',
        'precio',
        'diaHoraCreacion'
    ];

    protected $table = 'RESERVAS';
    protected $primaryKey = 'codigo';
}
