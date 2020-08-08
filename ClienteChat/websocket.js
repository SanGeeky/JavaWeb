const websocket = new WebSocket("ws://localhost:8080/WebSocketJava/servidor");
let textochat=document.getElementById("textochat");

const $chatpanel = document.getElementsByClassName("chat-box chatContainerScroll");
const $userpanel = document.getElementsByClassName("users-container");

//Plantilla enviar Mensaje
const $send_message = function (data) {
    return`
    <li class="chat-right">
    <div class="chat-hour">08:59 <span class="fa fa-check-circle"></span></div>
    <div class="chat-text">${data}</div>
    <div class="chat-avatar">
        <img src="https://www.bootdey.com/img/Content/avatar/avatar5.png" alt="Retail Admin">
        <div class="chat-name">${data}</div>
    </div>
    </li>
    `;   
};

//Plantilla recibir mensaje
const $recive_message = function (data) {
    return`
    <li class="chat-left">
      <div class="chat-avatar">
          <img src="https://www.bootdey.com/img/Content/avatar/avatar3.png" alt="Retail Admin">
          <div class="chat-name">${data}</div>
      </div>
      <div class="chat-text">${data}</div>
      <div class="chat-hour">08:57 <span class="fa fa-check-circle"></span></div>
  </li>`;
};

const $user_list = `
<li class="person" data-chat="person1">
<div class="user">
    <img src="https://www.bootdey.com/img/Content/avatar/avatar3.png" alt="Retail Admin">
    <span class="status busy"></span>
</div>
<p class="name-time">
    <span class="name">${"username"}</span>
</p>
</li>
`



//Creamos un nuevo elemetno que se va a agregar al HTML
function createTemplate(HTMLstring) {
    const html = document.implementation.createHTMLDocument();
    html.body.innerHTML = HTMLstring;
    return html.body.children[0];
}




  websocket.onopen = function (event) {
    if (event.data === undefined) return;
    escribirRespuesta(event.data);
  };
  websocket.onmessage = function (event) {
    escribirRespuesta(event.data);
  };
  websocket.onclose = function () {
    escribirRespuesta("Conexion Cerrada");
  };

function sendMessage() {
  let texto = document.getElementById("txtmensaje").value;
  websocket.send(texto);
}
function closeSocket() {
  websocket.close();
}
function escribirRespuesta(texto) {
    
    $chatpanel.item(0).append(createTemplate($recive_message(texto)))
    //$chatpanel.item(0).append(createTemplate($send_message(texto)))

    //textochat.innerHTML+="</br>"+texto;
}

