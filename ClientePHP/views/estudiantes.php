<?php 
include 'base.php';
include 'controller.php';

$option = 'ConsultarEstudiantePorCodigo';
$parameters = array('codigo' => $_GET["id_estudiante"] );

$json = SOAP($option, $parameters);

?>

<div class="container">
<div class="row" style="margin-top:20px" id="estudiantes">
  <div class="offset-md-4 col-md-4">
    <div class="card">
      <div class="card-header text-center">
        Consultar Estudiante
      </div>
      <div class="card-body" style="padding:10px">
        <form action="estudiantes.php" method="GET">
          <div class="form-group">
            <label for="id_estudiante">ID Estudiante</label>
            <input type="text" name="id_estudiante" id="id_estudiante" class="form-control" placeholder="Ingrese ID" required>
          </div>

          <div class="form-group text-center">
            <button type="submit" class="btn btn-primary" id="btn-query">
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
  <div class="row">
    <div class="col">
      <h3>Codigo:</h3>
      <span><?php echo $json->{'Codigo'};?></span>
      <br><br>
      <h3>Nombres:</h3>
      <span><?php echo $json->{'Nombres'};?></span>
      <br><br>
      <h3>Apellidos:</h3>
      <span><?php echo $json->{'Apellidos'};?></span>
    </div>
    <div class="col">
      <h3>Curso:</h3>
      <span><?php echo $json->{'Curso'};?></span>
      <br><br>
      <h3>Nota:</h3>
      <p class="display-2 text-center"><strong><?php echo round($json->{'Nota'}, 1);?></strong></p>
    </div>
  </div>
</div>

<?php
if($json == NULL)
  echo '<script>document.getElementById("query").style.display = "none"</script>';
else
  echo '<script>document.getElementById("query").style.display = "block"</script>';
?>
