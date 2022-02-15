<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Factura extends Model
{
    use HasFactory;

   

    protected $table = 'FACTURAS';
    protected $primaryKey = 'codigo';

    public $timestamps = false;


}
