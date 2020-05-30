
<?php
error_reporting(E_ERROR);
include_once 'lib/nusoap.php';
$client = new nusoap_client('http://localhost:8080/ColegioSOAP/WebServiceColegio?WSDL','wsl');

$err = $client->getError();
if ($err) {	echo 'Error en Constructor' . $err ; }
$param = array('codig' => '20');
$result = $client->call('ConsultarEstudiantePorCodigo', $param);
if ($client->fault) {
	echo 'Fallo';
	print_r($result);
} else {	// Chequea errores
	$err = $client->getError();
	if ($err) {		// Muestra el error
		echo 'Error ddd' . $err ;
	} else {		// Muestra el resultado
		echo 'Resultado';
		print_r ($result);
	}
}

?>