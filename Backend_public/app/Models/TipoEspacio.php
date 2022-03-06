<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class TipoEspacio extends Model
{
    use HasFactory;

    protected $table = 'TIPOS_ESPACIOS';

    protected $primaryKey = 'codigo';

    public $incrementing = false;

    public $timestamps = false;
}
