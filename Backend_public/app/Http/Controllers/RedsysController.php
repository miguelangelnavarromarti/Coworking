<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
// Se incluye la librería
use App\Http\Controllers\RedsysAPI;
use App\Models\Reserva;
use App\Models\Espacio;
use App\Models\Factura;
use App\Models\GestionOfertas;
use App\Models\ReservaFactura;

class RedsysController extends Controller
{
    public function index($localizador)
    {
        // Se crea Objeto
        $miObj = new RedsysAPI;

        $reservas = Reserva::where('localizador', $localizador)->get();
        $numeroReservas = count($reservas);
        $ofertas = GestionOfertas::orderBy('minimoHora', 'desc')->get();
        $descuento = 0;
        $precios = Reserva::where('localizador', $localizador)->get('precio');

        $precioTotal = 0;
        for ($j = 0; $j < count($precios); $j++) {
            $precioTotal = $precioTotal + $precios[$j]->precio;
        }


        for ($i = 0; $i < count($ofertas); $i++) {
            if ($numeroReservas >= $ofertas[$i]->minimoHora) {
                $descuento = $ofertas[$i]->descuento;
                break;
            }
        }
        $precioTotal = $precioTotal - ($precioTotal * ($descuento / 100));

        // Valores de entrada que no hemos cmbiado para ningun ejemplo
        $fuc = "999008881";
        $terminal = "1";
        $moneda = "978";
        $trans = "0";
        $url = "http://localhost:3000";
        $urlOK = "http://localhost:8000/respuestaOK";
        $urlKO = "http://localhost:8000/respuestaKO";
        $id = $localizador;
        $amount = $precioTotal * 100;

        // Se Rellenan los campos
        $miObj->setParameter("DS_MERCHANT_AMOUNT", $amount);
        $miObj->setParameter("DS_MERCHANT_ORDER", $id);
        $miObj->setParameter("DS_MERCHANT_MERCHANTCODE", $fuc);
        $miObj->setParameter("DS_MERCHANT_CURRENCY", $moneda);
        $miObj->setParameter("DS_MERCHANT_TRANSACTIONTYPE", $trans);
        $miObj->setParameter("DS_MERCHANT_TERMINAL", $terminal);
        $miObj->setParameter("DS_MERCHANT_MERCHANTURL", $url);
        $miObj->setParameter("DS_MERCHANT_URLOK", $urlOK);
        $miObj->setParameter("DS_MERCHANT_URLKO", $urlKO);

        //Datos de configuración
        $version = "HMAC_SHA256_V1";
        $kc = 'sq7HjrUOBfKmC576ILgskD5srU870gJ7'; //Clave recuperada de CANALES
        // Se generan los parámetros de la petición
        $request = "";
        $params = $miObj->createMerchantParameters();
        $signature = $miObj->createMerchantSignature($kc);

?>
        <html lang="es">

        <head>
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        </head>

        <body>
            <form id="form" name="frm" action="https://sis-t.redsys.es:25443/sis/realizarPago" method="POST">
                <input type="hidden" name="Ds_SignatureVersion" value="<?php echo $version; ?>" /></br>
                <input type="hidden" name="Ds_MerchantParameters" value="<?php echo $params; ?>" /></br>
                <input type="hidden" name="Ds_Signature" value="<?php echo $signature; ?>" /></br>
                <input id="button" type="submit" value="Enviar">
            </form>

        </body>
        <script>
            $(document).ready(function() {
                $("form#form").submit();
            });
        </script>

        </html>


<?php
    }

    public function respuestaOK()
    {
        $miObj = new RedsysAPI;

        if (!empty($_GET)) { //URL DE RESP. ONLINE

            $version = $_GET["Ds_SignatureVersion"];
            $datos = $_GET["Ds_MerchantParameters"];
            $signatureRecibida = $_GET["Ds_Signature"];


            $decodec = $miObj->decodeMerchantParameters($datos);
            $kc = 'sq7HjrUOBfKmC576ILgskD5srU870gJ7'; //Clave recuperada de CANALES
            $firma = $miObj->createMerchantSignatureNotif($kc, $datos);

            if ($firma === $signatureRecibida) {
                $order = $miObj->getParameter("Ds_Order");

                $reservas = Reserva::where('localizador', $order)->get();
                $numeroReservas = count($reservas);
                $ofertas = GestionOfertas::orderBy('minimoHora', 'desc')->get();
                $minimoOferta = 0;
                $descuento = 0;
                $precios = Reserva::where('localizador', $order)->get('precio');

                $precioTotal = 0;
                for ($j = 0; $j < count($precios); $j++) {
                    $precioTotal = $precioTotal + $precios[$j]->precio;
                }


                for ($i = 0; $i < count($ofertas); $i++) {
                    if ($numeroReservas >= $ofertas[$i]->minimoHora) {
                        $minimoOferta = $ofertas[$i]->minimoHora;
                        $descuento = $ofertas[$i]->descuento;
                        break;
                    }
                }
                $precioTotal = $precioTotal - ($precioTotal * ($descuento / 100));

                $factura = new Factura;

                $factura->codigoCliente = $reservas[0]->codigoCliente;
                $factura->diaFactura = date("Y-m-d");
                $factura->minimoHoraOferta = $minimoOferta;
                $factura->descuentoOferta = $descuento;
                $factura->precioTotal = $precioTotal;

                $factura->save();

                for ($r = 0; $r < count($reservas); $r++) {
                    $reservaFactura = new ReservaFactura;
                    $reservaFactura->codigoFactura = $factura->codigo;
                    $reservaFactura->codigoReserva = $reservas[$r]->codigo;
                    $reservaFactura->save();
                }

                return redirect()->route('facturas', ['codigo' => $factura->codigo]);
            } else {
                echo "FIRMA KO";
            }
        }
    }

    public function respuestaKO()
    {
        $miObj = new RedsysAPI;

        if (!empty($_GET)) { //URL DE RESP. ONLINE

            $version = $_GET["Ds_SignatureVersion"];
            $datos = $_GET["Ds_MerchantParameters"];
            $signatureRecibida = $_GET["Ds_Signature"];


            $decodec = $miObj->decodeMerchantParameters($datos);
            $kc = 'sq7HjrUOBfKmC576ILgskD5srU870gJ7'; //Clave recuperada de CANALES
            $firma = $miObj->createMerchantSignatureNotif($kc, $datos);

            if ($firma === $signatureRecibida) {
                $order = $miObj->getParameter("Ds_Order");

                $reservas = Reserva::where('localizador', $order)->get();

                foreach ($reservas as $reserva) {
                    Reserva::where('codigo', $reserva->codigo)->update(
                        ['estado' => 'cancelado']
                    );
                }

                //return redirect()->route('facturas', ['codigo' => $factura->codigo]);

                return response()->json([
                    'reservas' => $reservas,
                ]);
            } else {
                echo "FIRMA KO";
            }
        }
    }
}
