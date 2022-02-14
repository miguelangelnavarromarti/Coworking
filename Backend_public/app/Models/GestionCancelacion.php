<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class GestionCancelacion extends Model
{
    use HasFactory;

    protected $fillable = [
        'codigo',
        'diasAntelacion',
        'devolucion',
    ];

    protected $table = 'GESTION_CANCELACIONES';
    protected $primaryKey = 'codigo';
}
