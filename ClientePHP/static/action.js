var divs_dom = [
  "estudiantes",
  "materias",
  "cursos",
  "prom_cursos",
  "prom_anios",
];

//"prom_cursos", "prom_anios"

function hide_elements() {
  for (let i = 0; i < divs_dom.length; i++) {
    const element = divs_dom[i];
    console.log(element);
    document.getElementById(element).style.display = "none";
  }
}

hide_elements();

function change_status(div_id) {
  hide_elements();

  if (div_id === "home") {
    var status = document.getElementById(div_id).style.display;
    if (status == "" || status == "none") {
      document.getElementById(div_id).style.display = "block";
    } else document.getElementById(div_id).style.display = "none";
  } else {
    document.getElementById("home").style.display = "none";
    document.getElementById(div_id).style.display = "block";
  }
}

document.getElementById("home_click").onclick = function () {
  change_status("home");
};

document.getElementById("estudiantes_click").onclick = function () {
  change_status("estudiantes");
};

document.getElementById("materias_click").onclick = function () {
  change_status("materias");
};

document.getElementById("cursos_click").onclick = function () {
  change_status("cursos");
};

document.getElementById("prom_cursos_click").onclick = function () {
  change_status("prom_cursos");
};

document.getElementById("prom_anios_click").onclick = function () {
  change_status("prom_anios");
};
