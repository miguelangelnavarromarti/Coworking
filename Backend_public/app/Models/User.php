<?php

namespace App\Models;

use Illuminate\Contracts\Auth\MustVerifyEmail;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Foundation\Auth\User as Authenticatable;
use Illuminate\Notifications\Notifiable;
use Laravel\Sanctum\HasApiTokens;
use Tymon\JWTAuth\Contracts\JWTSubject;

class User extends Authenticatable implements JWTSubject 
{
    use HasApiTokens, HasFactory, Notifiable;

    /**
     * The attributes that are mass assignable.
     *
     * @var array<int, string>
     */
    protected $table = 'CLIENTES';
    protected $primaryKey = 'codigo';
    protected $fillable =['codigo','nombreUsuario','nombre','apellido1','apellido2','telefono','email','codigoPostal','password'];
    
    

    public function getJWTIdentifier()
    {
        return $this->getKey();
        //return $this->$fillable['email'];

    }

    public function getJWTCustomClaims()
    {
        return [];
    }
}
