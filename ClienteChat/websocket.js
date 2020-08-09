const websocket = new WebSocket("ws://localhost:8080/WebSocketJava/servidor");
const username = prompt("Ingresa tu Nombre: ");
let destinatario = "TODOS"

let clientList = [];

console.log(username);
//websocket.send(username);

let textochat = document.getElementById("textochat");

const $chatpanel = document.getElementsByClassName(
  "chat-box chatContainerScroll"
);
const $userpanel = document.getElementsByClassName("users");

//Plantilla enviar Mensaje
const $send_message = function (data, user) {
  return `
    <li class="chat-right">
    <div class="chat-hour"><span class="fa fa-check-circle"></span></div>
    <div class="chat-text">${data}</div>
    <div class="chat-avatar">
        <img src="https://www.bootdey.com/img/Content/avatar/avatar5.png" alt="Retail Admin">
        <div class="chat-name">${user}</div>
    </div>
    </li>
    `;
};

//Plantilla recibir mensaje
const $recive_message = function (data, user) {
  return `
    <li class="chat-left">
      <div class="chat-avatar">
          <img src="https://www.bootdey.com/img/Content/avatar/avatar3.png" alt="Retail Admin">
          <div class="chat-name">${user}</div>
      </div>
      <div class="chat-text">${data}</div>
      <div class="chat-hour"><span class="fa fa-check-circle"></span></div>
  </li>`;
};
//Plantilla Lista de Usuarios
const $user_template = (data) => {
  return `
<li class="person" data-chat="person1">
<div class="user">
    <img src="https://www.bootdey.com/img/Content/avatar/avatar3.png" alt="Retail Admin">
    <span class="status busy"></span>
</div>
<p class="name-time">
    <span class="name">${data}</span>
</p>
</li>
`;
};

//Creamos un nuevo elemetno que se va a agregar al HTML
function createTemplate(HTMLstring) {
  const html = document.implementation.createHTMLDocument();
  html.body.innerHTML = HTMLstring;
  return html.body.children[0];
}

function addEventClick($element) {
  $element.addEventListener("click", () => {
    alert('click!')
    //showModal($element);
  });
}

//Reciibir Mensajees
websocket.onopen = function (event) {
  if (event.data === undefined ) return;
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
      }, 1000)
      setInterval(() => {
        $userpanel.item(0).innerHTML=''
        clientList.forEach((element) => {
          const userElement = createTemplate($user_template(element))
          $userpanel.item(0).append(userElement)
          // Click
          addEventClick(userElement);
        })
      }, 1000)
      break;
    
    case "ListaUsuarios":
      clientList = [];
      array.forEach(element => {
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
  if(keycode == '13'){
    sendMessage();
    document.getElementById("txtmensaje").value = "";
  }
}

function sendMessage() {
  
  let texto = document.getElementById("txtmensaje").value;
  websocket.send(texto+"#"+destinatario+"#"+username);
  document.getElementById("txtmensaje").value = "";
}

function escribirRespuesta(texto) {
  let array = event.data.split("#");
  let mensaje = array[0];
  let usuario = array[1];

  if(usuario == username)
  {
    $chatpanel.item(0).append(createTemplate($send_message(mensaje, usuario)));
  }
  else{
    $chatpanel.item(0).append(createTemplate($recive_message(mensaje, usuario)));
  }

}


