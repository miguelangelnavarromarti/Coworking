<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Horario extends Model
{
    use HasFactory;

    protected $table = 'HORARIOS_DISPONIBLES';

    protected $primaryKey = 'hora';

    public $incrementing = false;

    public $timestamps = false;
}
