<?php

include 'base.php';
include 'controller.php';

$option = 'ConsultarPromedioPorAnio';
$parameters = NULL;

$json = SOAP($option, $parameters);
//print_r($json);
// ConsultarEstudiantePorCodigo($_GET["id_estudiante"]);
?>
<br>
        <div class = "d-flex justify-content-center" id="prom_anios">
          <h1>
             Promedio Años
          </h1>
        </div>
<br>
<div id="query" class="container" >
  <style>
    h2{
      color: rgb(253,189,18);
    }
    span{
      margin-left: 15px;
      font-size: 2.5em;
    }
  </style>
  <br><br>
  <div class="row">
    <div class="col">
      <h2>Año:</h3>
      <span class="display-2 text-center"><?php echo $json->{'Anio'};?></span>
    </div>
    <div class="col">
      <h2>Promedio:</h3>
      <span class="display-2 text-center"><strong><?php echo round($json->{'Promedio'}, 1);?></strong></span>
    </div>
  </div>
</div>