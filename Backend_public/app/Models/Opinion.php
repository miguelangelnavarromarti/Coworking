<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Opinion extends Model
{
    use HasFactory;

    protected $fillable = [
        'codigo',
        'codigoCliente',
        'codigoReserva',
        'titulo',
        'opinion',
        'puntuacion'
    ];

    protected $table = 'opiniones';
    protected $primaryKey = 'codigo';
}
