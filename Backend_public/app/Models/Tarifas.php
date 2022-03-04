<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Tarifas extends Model
{
    use HasFactory;

    protected $table = 'TARIFAS';

    protected $primaryKey = 'codigo';

    public $timestamps = false;
    
    protected $fillable = ['codigoTipoEspacio', 'precio', 'dataInicio', 'dataFin', 'porDefecto'];
}
