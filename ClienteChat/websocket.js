const websocket = new WebSocket('ws://localhost:8080/WebSocketJava/servidor');
const username = prompt('Ingresa tu Nombre: ');
let destinatario = 'TODOS';

let clientList = ['TODOS'];
let chatHistory = []; // create an empty array for History

chatHistory.push({
  client: 'TODOS',
  messages: [],
});

//const datos = chatHistory[0]

/* const { client: final_client } = chatHistory[0];

const {
  messages_user: { order: order_user },
} = chatHistory[0];

const {
  messages_user: { messages: message_user },
} = chatHistory[0];

console.log(final_client);
console.log(order_user);
console.log(message_user);
debugger; */

const username_text = document.getElementById('username');
username_text.innerText = username;

const textochat = document.getElementById('textochat');

const $chatpanel = document.getElementsByClassName(
  'chat-box chatContainerScroll'
);
const $userpanel = document.getElementsByClassName('users');

//Nombre Destinatario
const $nombre_destinatario = document.getElementById('nombre_destinatario');

//Scroll Mensajes

const $scroll_messages = document.getElementById('mensajes');

//Plantilla enviar Mensaje
const $send_message = function (data, user, avatar) {
  return `
    <li class="chat-right">
    <div class="chat-hour"><span class="fa fa-check-circle"></span></div>
    <div class="chat-text">${data}</div>
    <div class="chat-avatar">
        <img src="${avatar}" alt="Avatar">
        <div class="chat-name">${user}</div>
    </div>
    </li>
    `;
};

//Plantilla recibir mensaje
const $recive_message = function (data, user, avatar) {
  return `
  <li class="chat-left">
      <div class="chat-avatar">
          <img src="${avatar}" alt="Avatar">
          <div class="chat-name">${user}</div>
      </div>
      <div class="chat-text">${data}</div>
      <div class="chat-hour"><span class="fa fa-check-circle"></span></div>
  </li>`;
};
//Plantilla Lista de Usuarios
const $user_template = (data, avatar) => {
  return `
  <li class="person" data-user="${data}">
  <div class="user">
      <img src="${avatar}" alt="Avatar">
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

function updateScroll() {
  $scroll_messages.scrollTop = $scroll_messages.scrollHeight;
}

function indexHistorial(user) {
  return chatHistory
    .map((item) => {
      return item.client;
    })
    .indexOf(user);
}

//Retornamos la URL del AVATAT
function getAvatar(name) {
  //const avatar = `https://ui-avatars.com/api/?name=${name}`
  //const avatar = `https://www.gravatar.com/avatar/${hash}?d=identicon`;
  //
  //const hash = md5(name + '@gmail.com');
  const avatar = `https://avatars.dicebear.com/api/gridy/${name}.svg`;
  return avatar;
}

function addEventClick($element) {
  $element.addEventListener('click', () => {
    //Borramos el chat acutal
    $chatpanel.item(0).innerHTML = '';
    $nombre_destinatario.innerText = $element.dataset.user;
    destinatario = $element.dataset.user;

    //Escribimos en el Chat del Historial
    const i = indexHistorial(destinatario);
    if (i > -1) {
      chatHistory[i].messages.forEach((item) => {
        escribirHistorial(item);
      });
    }
  });
}

//Reciibir Mensajees
websocket.onopen = function (event) {
  if (event.data === undefined) return;
  debugger;
  escribirRespuesta(event.data);
};

// Al recibir un mensaje
websocket.onmessage = function (event) {
  let array = event.data.split('#');
  let operacion = array[0];
  array.shift();
  array.pop();
  switch (operacion) {
    case 'Conexion Creada':
      //Enviamos el nombre de usuario
      websocket.send(username);
      //Ejecutamos la llamada a actualizar los usuarios;
      setInterval(() => {
        //console.log("Entramos");
        websocket.send('pls');
      }, 1000);
      //Definimos la funcion para actualizar los usuarios;
      setInterval(() => {
        $userpanel.item(0).innerHTML = '';

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

    case 'ListaUsuarios':
      chatHistory.forEach;
      clientList = ['TODOS'];
      // este pedacito lo repeti porque YOLO, con el diccionario
      // Ya no deberiamos reiniciar el array y asignar todos
      array.forEach((element) => {
        if (element != username) clientList.push(element);
      });

      //Agreamos Usuario Historial
      clientList.forEach((element) => {
        found = false;
        chatHistory.forEach((item) => {
          if (element === item.client) {
            found = true;
          }
        });
        //Mira si no fue encontrado dentro del Historial
        // Y lo agrega
        if (!found) {
          chatHistory.push({
            client: element,
            messages: [],
          });
        }
      });
      break;

    default:
      guardarHistorial(event.data);
      escribirRespuesta(event.data);
      break;
  }
};

function closeSocket() {
  websocket.close();
}

//Al Cerrar Conexion
websocket.onclose = function () {
  escribirRespuesta('Conexion Cerrada');
};

function onKeyUp(event) {
  let keycode = event.keyCode;
  if (keycode == '13') {
    sendMessage();
    document.getElementById('txtmensaje').value = '';
  }
}

function sendMessage() {
  //Tomamos el mensjaes
  let texto = document.getElementById('txtmensaje').value;

  //Enviamos el socket
  websocket.send(texto + '#' + destinatario + '#' + username);

  document.getElementById('txtmensaje').value = '';
}

function escribirRespuesta(texto) {
  const array = texto.split('#');
  const mensaje = array[0];
  const destinatario_local = array[1];
  const usuario = array[2];

  if (destinatario_local != username || usuario == destinatario) {
    if (usuario == username) {
      $chatpanel
        .item(0)
        .append(
          createTemplate($send_message(mensaje, usuario, getAvatar(usuario)))
        );
    } else {
      if (destinatario_local == destinatario || usuario == destinatario) {
        $chatpanel
          .item(0)
          .append(
            createTemplate(
              $recive_message(mensaje, usuario, getAvatar(usuario))
            )
          );
      }
    }
  }

  updateScroll();
}

function escribirHistorial(texto) {
  const array = texto.split('#');
  const mensaje = array[0];
  const usuario = array[1];

  if (usuario == username) {
    $chatpanel
      .item(0)
      .append(
        createTemplate($send_message(mensaje, usuario, getAvatar(usuario)))
      );
  } else {
    $chatpanel
      .item(0)
      .append(
        createTemplate($recive_message(mensaje, usuario, getAvatar(usuario)))
      );
  }
  updateScroll();
}

function guardarHistorial(texto) {
  //Guardamos en el historial
  const array = texto.split('#');
  const mensaje = array[0];
  const destinatario_local = array[1];
  const usuario = array[2];

  if (destinatario_local == destinatario) {
    //Usuario Que envia
    const index = indexHistorial(destinatario_local);
    if (index > -1) {
      chatHistory[index].messages.push(`${mensaje}#${usuario}`);
    }
  } else {
    //Usuario Que Recibe
    const index = indexHistorial(usuario);
    if (index > -1) {
      chatHistory[index].messages.push(`${mensaje}#${usuario}`);
    }
  }
}
