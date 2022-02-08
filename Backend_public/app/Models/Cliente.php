<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Cliente extends Model
{
    use HasFactory;

    protected $fillable = [
        'codigo',
        'nombreUsuario',
        'password',
        'nombre',
        'apellido1',
        'apellido2',
        'telefono',
        'email',
        'codigoPostal',
        'rol',
        'alta'
    ];

    protected $table = 'clientes';
    protected $primaryKey = 'codigo';


}
