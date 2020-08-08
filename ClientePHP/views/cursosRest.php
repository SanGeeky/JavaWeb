<?php

include 'base.php';

$data = file_get_contents('http://localhost:8080/JavaRestful/webresources/web.cursos');

$xml = simplexml_load_string($data, "SimpleXMLElement", LIBXML_NOCDATA);
$json = json_encode($xml);
$array = json_decode($json,TRUE);
//print_r($array);

?>

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
  <h2>Listado Estudiantes Rest</h2>
  <br>
  <div class="row justify-content-md-center">
    <div class="col-md-auto">
      <table class="table">
        <thead class="bg-warning ">
          <tr>
            <th scope="col">ID</th>
            <th scope="col">Grado</th>
            <th scope="col">Grupo</th>
            <th scope="col">Descripcion</th>
          </tr>
        </thead>
        <tbody>
            <?php
              foreach($array['cursos'] as $curso){
                echo '<tr>';
                echo '<th scope="row">'.$curso['idCurso'].'</th>';
                echo '<td>'.$curso['grado'].'</td>';
                echo '<td>'.$curso['grupo'].'</td>';
                echo '<td>'.$curso['descripcion'].'</td>';
                echo '</tr>';
              }
            ?>
          </tr>
        </tbody>
      </table>

    </div>
  </div>
</div>

