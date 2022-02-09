<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class FacturaCancelada extends Model
{
    use HasFactory;

    protected $fillable = [
        'codigo',
        'codigoFactura',
        'codigoCliente',
        'devolucion',
        'diasAntelacionCancelacion',
        'descuentoCancelacion',
        'diaHoraCancelacion'
    ];

    protected $table = 'FACTURAS_CANCELACIONES';
    protected $primaryKey = 'codigo';
}
