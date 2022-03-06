<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Cliente extends Model
{
    use HasFactory;

    protected $table = 'CLIENTES';
    protected $primaryKey = 'codigo';
    protected $fillable =['codigo','nombreUsuario','nombre','apellido1','apellido2','telefono','email','codigoPostal','password'];
    public $timestamps = false;


}
