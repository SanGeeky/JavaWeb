<?php

include 'base.php';
include 'controller.php';

$option = 'ConsultarPromedioPorCurso';
$parameters = NULL;

$json = SOAP($option, $parameters);
//print_r($json);
// ConsultarEstudiantePorCodigo($_GET["id_estudiante"]);
?>

<br>
<div class = "d-flex justify-content-center" id="prom_cursos">
    <h2>
        Promedio Cursos
    </h2>
</div>
<div id="query" class="container" >
  <style>
    h4{
      display:inline-block;
      color: rgb(253,189,18);
    }
    span{
      margin-left: 15px;
      font-size: 2em;
    }
    thead{
      font-size: 1.3em;
      color: white;
    }
    tbody{
      font-size: 1em;
    }
  </style>
  <br>
  <div class="row justify-content-md-center">
    <div class="col-md-auto">
      <table class="table">
        <thead class="bg-warning ">
          <tr>
            <th scope="col">Año</th>
            <th></th><th></th><th></th>
            <th scope="col">ID</th>
            <th></th><th></th>
            <th scope="col">Grado</th>
            <th scope="col">Grupo</th>
            <th></th><th></th>
            <th scope="col">Nota</th>

          </tr>
        </thead>
        <tbody>
            <?php
              foreach($json->{'promedios'} as $promedio_curso){
                echo '<tr>';
                echo '<th scope="row">'.$promedio_curso->{'Anio'}.'</th>';
                echo '<td></td><td></td><td></td>';
                echo '<td>'.$promedio_curso->{'Id_Curso'}.'</td>';
                echo '<td></td><td></td>';
                echo '<td>'.$promedio_curso->{'Grado'}.'</td>';
                echo '<td>'.$promedio_curso->{'Grupo'}.'</td>';
                echo '<td></td><td></td>';
                echo '<th scope="row">'.round($promedio_curso->{'Nota'},1).'</th>';
                echo '</tr>';
              }
            ?>
          </tr>
        </tbody>
      </table>

    </div>
  </div>
</div>
<br>


