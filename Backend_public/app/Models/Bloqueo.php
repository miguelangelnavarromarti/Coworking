<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Bloqueo extends Model
{
    use HasFactory;

    protected $table = 'BLOQUEOS_ESPACIOS_HORAS';

    protected $primaryKey = 'codigo';

    public $timestamps = false;
}
