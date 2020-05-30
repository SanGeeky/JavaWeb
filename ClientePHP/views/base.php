<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/ClienteColegioSoap/static/styles.css">
    <!-- CSS only -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <!-- JS, Popper.js, and jQuery -->
    
    <title>Colegio | SOAP</title>
</head>
<html>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light" style="font-size:larger;">
            <?php
              echo '<a class="navbar-brand" href="/ClienteColegioSoap/index.php" style="font-size:xx-large;  color: orange;" id="home_click">Colegio Pikachu</a>            ';
            ?>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <!-- <span class="navbar-nav mr-auto mt-2 mt-lg-0"></span>   -->
            <ul class="navbar-nav">
                <li class="nav-item active">
                  <?php
                    echo '<a class="nav-link" href="/ClienteColegioSoap/views/estudiantes.php" target="_self" id="estudiantes_click">Estudiantes <span class="sr-only">(current)</span></a>                  ';
                  ?>
                </li>
                <li class="nav-item active">
                    <?php 
                    echo '<a class="nav-link" href="/ClienteColegioSoap/views/materias.php" target="_self" id="materias_click">Materias <span class="sr-only" id="materias_click">(current)</span></a>';
                    ?>
                </li>
                <li class="nav-item active">
                    <?php
                      echo '<a class="nav-link" href="/ClienteColegioSoap/views/cursos.php" id="cursos_click">Cursos <span class="sr-only" >(current)</span></a>                    ';
                    ?>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                      Promedios
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink" style="font-size:large;">
                      <?php
                        echo '<a class="dropdown-item" href="/ClienteColegioSoap/views/prom_cursos.php" id="prom_cursos_click">Cursos</a>                      ';
                      ?>
                      <?php
                        echo '<a class="dropdown-item" href="/ClienteColegioSoap/views/prom_anios.php" id="prom_anios_click">Año Escolar</a>                      ';
                      ?>
                    </div>
                </li>
              </ul>
            </div>
        </nav>
    </body>
   <!-- <script src="action.js"></script> -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</html>   
   