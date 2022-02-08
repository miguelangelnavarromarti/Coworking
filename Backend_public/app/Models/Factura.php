<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Factura extends Model
{
    use HasFactory;

    protected $fillable = [
        'codigo',
        'codigoCliente',
        'diaFactura',
        'precioTotal',
        'minimoHoraOferta',
        'descuentoOferta'
    ];

    protected $table = 'facturas';
    protected $primaryKey = 'codigo';

    public $timestamps = false;


}
