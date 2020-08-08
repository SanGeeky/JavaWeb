<?php 
include 'base.php';

$data = file_get_contents('http://localhost:8080/JavaRestful/webresources/web.estudiantes');

$xml = simplexml_load_string($data, "SimpleXMLElement", LIBXML_NOCDATA);
$json = json_encode($xml);
$array = json_decode($json,TRUE);
//print_r($array['estudiantes']);
?>

<div id="query" class="container" >
  <style>
    h3{
      display:inline-block;
      color: rgb(253,189,18);
    }
    span{
      margin-left: 15px;
      font-size: 2.5em;
    }
  </style>
  <br>
  <h2>Listado Estudiantes Rest</h2>
  <table class="table">
        <thead class="bg-warning ">
          <tr>
            <th scope="col">ID</th>
            <th scope="col">Nombre</th>
            <th scope="col">Apellido</th>
          </tr>
        </thead>
        <tbody>
            <?php
              foreach($array['estudiantes'] as $estudiante){
                echo '<tr>';
                echo '<th scope="row">'.$estudiante['idEstudiante'].'</th>';
                echo '<td>'.$estudiante['nombres'].'</td>';
                echo '<td>'.$estudiante['apellidos'].'</td>';
                echo '</tr>';
              }
            ?>
          </tr>
        </tbody>
  </table>

</div>

<?php
  

?>
