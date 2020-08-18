const websocket = new WebSocket("ws://localhost:8080/WebSocketJava/servidor");
const username = prompt("Ingresa tu Nombre: ");
let destinatario = "TODOS";

let clientList = ["TODOS"];

console.log(username);

/* const username_text = document.getElementById("username");
username_text.innerText = username; */

const $chatpanel = document.getElementsByClassName("chat-history");
const $userpanel = document.getElementsByClassName("list-group");

const $nombre_destinatario = document.getElementById("destino");
$nombre_destinatario.innerText = destinatario;

//Plantilla enviar Mensaje
const $send_message = function (data, user, avatar) {
  return `
  <div class="media" >
  <div class="media-left">
      <img src="${avatar}" width="60" alt="avatar" class="media-object">
  </div>
  <div class="media-body message">
    <div class="panel panel-default">
      <div class="panel-heading panel-heading-white">
        <div class="pull-right">
          <small class="text-muted">2 min ago</small>
        </div>
        <strong>
        ${user}
        </strong>
      </div>
      <div class="panel-body">
        ${data}
      </div>
    </div>
  </div>
</div>
    `;
};

//Plantilla recibir mensaje
const $recive_message = function (data, user, avatar) {
  return `
  <div class="media" >
  <div class="media-body message">
    <div class="panel panel-default">
      <div class="panel-heading panel-heading-white">
        <div class="pull-right">
          <small class="text-muted">10 min ago</small>
        </div>
        <strong>
        ${user}
        </strong>
      </div>
      <div class="panel-body">
        ${data}
      </div>
    </div>
  </div>
  <div class="media-right">
    <img src="${avatar}" width="60" alt="avatar" class="media-object">
  </div>
</div>
`;
};
//Plantilla Lista de Usuarios
const $user_template = (data, avatar) => {
  return `
  <li class="list-group-item" data-user="${data}">
    <div class="media">
      <div class="media-left">
        <img src="${avatar}" height="50" alt="avatar" class="media-object">
      </div>
      <div class="media-body">
        <span class="date">Active</span>
        <span class="user">${data}</span>
      </div>
    </div>
</li>
`;
};

//Creamos un nuevo elemetno que se va a agregar al HTML
function createTemplate(HTMLstring) {
  const html = document.implementation.createHTMLDocument();
  html.body.innerHTML = HTMLstring;
  return html.body.children[0];
}

//Retornamos la URL del AVATAT
function getAvatar(name) {
  const avatar = `https://avatars.dicebear.com/api/initials/${name}.svg`;
  return avatar;
}

function addEventClick($element) {
  $element.addEventListener("click", () => {
    $nombre_destinatario.innerText = "Para: " + $element.dataset.user;
    destinatario = $element.dataset.user;
  });
}

//Reciibir Mensajees
websocket.onopen = function (event) {
  if (event.data === undefined) return;
  escribirRespuesta(event.data);
};

// Al recibir un mensaje
websocket.onmessage = function (event) {
  let array = event.data.split("#");
  let operacion = array[0];
  array.shift();
  array.pop();
  switch (operacion) {
    case "Conexion Creada":
      websocket.send(username);
      setInterval(() => {
        //console.log("Entramos");
        websocket.send("pls");
      }, 1000);
      setInterval(() => {
        $userpanel.item(0).innerHTML = "";

        clientList.forEach((element) => {
          const userElement = createTemplate(
            $user_template(element, getAvatar(element))
          );
          $userpanel.item(0).append(userElement);
          // Click
          addEventClick(userElement);
        });
      }, 1000);
      break;

    case "ListaUsuarios":
      clientList = ["TODOS"];
      array.forEach((element) => {
        clientList.push(element);
      });
      break;

    default:
      escribirRespuesta(event.data);
      break;
  }
};

function closeSocket() {
  websocket.close();
}

//Al Cerrar Conexion
websocket.onclose = function () {
  escribirRespuesta("Conexion Cerrada");
};

function onKeyUp(event) {
  let keycode = event.keyCode;
  if (keycode == "13") {
    sendMessage();
    document.getElementById("txtmensaje").value = "";
  }
}

function sendMessage() {
  let texto = document.getElementById("txtmensaje").value;
  websocket.send(texto + "#" + destinatario + "#" + username);
  document.getElementById("txtmensaje").value = "";
}

function escribirRespuesta(texto) {
  let array = event.data.split("#");
  let mensaje = array[0];
  let usuario = array[1];

  if (usuario == username) {
    $chatpanel
      .item(0)
      .insertBefore(
        createTemplate($send_message(mensaje, usuario, getAvatar(usuario))),
        $chatpanel.item(0).firstChild
      );
  } else {
    $chatpanel
      .item(0)
      .insertBefore(
        createTemplate($recive_message(mensaje, usuario, getAvatar(usuario))),
        $chatpanel.item(0).firstChild
      );
  }
}
