<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class FacturaCancelada extends Model
{
    use HasFactory;

    protected $table = 'FACTURAS_CANCELACIONES';
    protected $primaryKey = 'codigo';
    protected $fillable =['codigoFactura','codigoCliente','devolucion','diasAntelacionCancelacion','descuentoCancelacion','diaHoraCancelacion'];
    public $timestamps = false;
}
