<?php

include 'base.php';
include 'controller.php';

$option = 'ConsultarMateriasMatriculadasPorEstudiante';
$parameters = array('codigo' => $_GET["id_estudiante"] );

$json = SOAP($option, $parameters);
?>

<div class="container">
  <div class="row" style="margin-top:20px" id="materias">
    <div class="offset-md-4 col-md-4">
      <div class="card">
        <div class="card-header text-center">
          Consultar Materias de Estudiante
        </div>
        <div class="card-body" style="padding:10px">
          <form action="materias.php" method="GET">
            <div class="form-group">
              <label| for="id_estudiante">ID Estudiante</label>
                <input type="text" name="id_estudiante" id="id_estudiante" class="form-control" placeholder="Ingrese ID" required>
            </div>

            <div class="form-group text-center">
              <button type="submit" class="btn btn-primary">
                Consulta
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>

  </div>
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
      <h4>Codigo:</h4> <span><?php echo $json->{'Codigo'};?></span>
      <h4 style="margin-left: 10%">Nombres:</h4> <span><?php echo $json->{'Nombres'};?></span>
      <h4 style="margin-left: 15%">Apellidos:</h4><span><?php echo $json->{'Apellidos'};?></span>
  </div>
  <br>
  <div class="row justify-content-md-center">
    <div class="col-md-auto">
      <table class="table">
        <thead class="bg-warning ">
          <tr>
            <th scope="col">ID</th>
            <th scope="col">Materia</th>
            <th></th><th></th><th></th><th></th><th></th><th></th><th></th>
            <th scope="col">Año</th>
          </tr>
        </thead>
        <tbody>
            <?php
              foreach($json->{'Materias'} as $materia){
                echo '<tr>';
                echo '<th scope="row">'.$materia->{'Id_Materia'}.'</th>';
                echo '<td>'.$materia->{'Materia'}.'</td>';
                echo '<td></td><td></td><td></td><td></td><td></td><td></td><td></td>';
                echo '<td>'.$materia->{'Anio'}.'</td>';
                echo '</tr>';
              }
            ?>
          </tr>
        </tbody>
      </table>

    </div>
  </div>
</div>

<?php
if($json == NULL)
  echo '<script>document.getElementById("query").style.display = "none"</script>';
else
  echo '<script>document.getElementById("query").style.display = "block"</script>';
?>

