<?php
error_reporting(E_ERROR);
include_once '../lib/nusoap.php';

$client = new nusoap_client('http://localhost:8080/ColegioSOAP/WebServiceColegio?WSDL', 'wsdl');
$err = $client->getError();
if ($err) {
    echo '<script>console.log("Error en Construct" . "$err"); </script>';
}

function CheckErrors($client)
{
    if ($client->fault) {
        echo '<script>console.log("Fallo en el Request"); </script>';
        #print_r($result);
    } else {  // Chequea errores
        $err = $client->getError();
        if ($err) {    // Muestra el error
            return FALSE;
        } else {    // Muestra el resultado
            return TRUE;
        }
    }
}

function WebService(&$webservice, $parameters)
{
    $client = $GLOBALS['client'];

    //$parameters = array('codigo' => $id);
    if ($parameters != NULL)
    {
        $result = $client->call($webservice, $parameters);
    }
    else
    {
        $result = $client->call($webservice);
    }

    if(CheckErrors($client, $result))
    {

        $json = json_decode(utf8_encode($result["return"]));
        return $json;
        //echo "<h1>" . $obj->{'Nota'} . "</h1>";
    }
    else
    {
        return 'Error en el Servidor - Lo Sentimos';
    }
}

function SOAP(&$option, $parameters)
{
    switch ($option) {
        case 'ConsultarEstudiantePorCodigo':
            return WebService($option, $parameters);
            break;
        case 'ConsultarMateriasMatriculadasPorEstudiante':
            return WebService($option, $parameters);
            break;
        case 'EstudianteCurso':
            return WebService($option, $parameters);
            break;
        case 'ConsultarPromedioPorCurso':
            return WebService($option, $parameters);
            break;
        case 'ConsultarPromedioPorAnio':
            return WebService($option, $parameters);
            break;
        default:
            echo '<script>console.log("WebService No Valido"); </script>';
            break;
    }
}
