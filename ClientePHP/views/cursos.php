<?php

include 'base.php';
include 'controller.php';

$option = 'EstudianteCurso';
$parameters = array('grado' => $_GET["Grado"], 'grupo' => $_GET["Curso"] );

$json = SOAP($option, $parameters);
//print_r($json);
// ConsultarEstudiantePorCodigo($_GET["id_estudiante"]);
?>



<div class="row" style="margin-top:20px" id="materias">
    <div class="offset-md-4 col-md-4">
        <div class="card">
            <div class="card-header text-center">
                Listado de Estudiantes
            </div>
            <div class="card-body" style="padding:10px">
                <form action="cursos.php" method="GET">
                    <div class="form-row">
                        <div class="form-group col-md-6">

                            <label| for="Grado" class="mr-sm-2" for="inlineFormCustomSelect">Grado</label>
                            <select name="Grado" class="custom-select mr-sm-2" id="inlineFormCustomSelect" required>
                                <option selected>Seleccione...</option>
                                <?php
                                for ($i = 1; $i <= 10; $i++) {
                                    echo '<option value="'. $i .'">' . $i . '</option>';
                                }
                                ?>
                            </select>

                        </div>
                        <div class="form-group col-md-6">
                            <label| for="Curso" class="mr-sm-2" for="inlineFormCustomSelect">Curso</label>
                            <select name="Curso" class="custom-select mr-sm-2" id="inlineFormCustomSelect">
                                <option selected>Seleccione...</option>
                                <?php
                                for ($i = 1; $i <= 5; $i++) {
                                    echo '<option value="'. $i .'">' . $i . '</option>';
                                }
                                ?>
                            </select>

                        </div>
                    </div>

                    <div class="form-group col-md- text-center">
                        <button type="submit" class="btn btn-primary">
                            Consulta
                        </button>
                    </div>

                </form>
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
      <h4>ID Curso:</h4> <span><?php echo $json->{'Id_Curso'};?></span>
      <h4 style="margin-left: 10%">Grado:</h4> <span><?php echo $json->{'Grado'};?></span>
      <h4 style="margin-left: 15%">Grupo:</h4><span><?php echo $json->{'Grupo'};?></span>
  </div>
  <br>
  <div class="row justify-content-md-center">
    <div class="col-md-auto">
      <table class="table">
        <thead class="bg-warning ">
          <tr>
            <th scope="col">ID</th>
            </th><th></th><th></th>
            <th scope="col">Nombre</th>
            <th></th><th></th><th></th><th></th>
            <th scope="col">Apellido</th>
          </tr>
        </thead>
        <tbody>
            <?php
              foreach($json->{'Estudiantes'} as $estudiante){
                echo '<tr>';
                echo '<th scope="row">'.$estudiante->{'Id_Estudiante'}.'</th>';
                echo '<td></td><td></td>';
                echo '<td>'.$estudiante->{'Nombres'}.'</td>';
                echo '<td></td><td></td><td></td><td></td>';
                echo '<td>'.$estudiante->{'Apellidos'}.'</td>';
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