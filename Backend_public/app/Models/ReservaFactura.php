<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class ReservaFactura extends Model
{
    use HasFactory;

    protected $table = 'FACTURAS_RESERVAS';

    protected $primaryKey = 'codigoReserva';

    public $timestamps = false;
}
