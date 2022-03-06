<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Opinion extends Model
{
    use HasFactory;

    protected $table = 'OPINIONES';
    protected $primaryKey = 'codigo';
    protected $fillable =['codigoCliente','codigoReserva','titulo','opinion','puntuacion'];
    public $timestamps = false;

}
