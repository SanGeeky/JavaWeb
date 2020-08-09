/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sangeeky;

import java.io.IOException;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.OnOpen;
import javax.websocket.OnMessage;
import javax.websocket.OnError;
import javax.websocket.OnClose;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author sangeeky
 */
@ServerEndpoint("/servidor")
public class ServerWebSocket {

    static Vector<Client> clientList = new Vector<>();

    Timer timer = new Timer();

    public int getIndexClient(Session session) {
        int indexClient = 0;
        for (Client c : clientList) {
            if (c.clientSession.equals(session)) {
                break;
            }
            indexClient++;
        }
        return indexClient;
    }

    @OnOpen
    public void onOpen(Session session) {
        //Nuevo Ciente se Conecta
        System.out.println("Solicitud conexion " + session.getId());
        try {
            Client cliente = new Client(session, ""); // Creamos el objeto Cliente
            clientList.add(cliente); //Agregamos a nuestro array de CLientes
            session.getBasicRemote().sendText("Conexion Creada#"); // Respuesta al Cliente que se conecto
        } catch (IOException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        Client cliente;
        try {
            int indexClient = getIndexClient(session);
            cliente = clientList.get(indexClient);

            //Recibimos el Nombre de usuario
            if (cliente.username.equals("")) {
                clientList.get(indexClient).username = message;
                cliente = clientList.get(indexClient);
                System.out.println("Solicitud conexion " + cliente.username);
            } else if (message.equals("pls")) //Devolvemos los Usuarios Conectados
            {
                if (!clientList.isEmpty()) {
                    String listaUsuarios = "ListaUsuarios#";
                    //clientList.forEach((c) -> listaUsuarios.concat(c.username + "#"));
                    for (Client c : clientList) {
                        listaUsuarios += c.username + "#";
                    }
                    for (Session s : session.getOpenSessions()) {
                        try {
                            s.getBasicRemote().sendText(listaUsuarios);
                        } catch (IOException ex) {
                            Logger.getLogger(ServerWebSocket.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            } else {   //Broadcast a todos los usuarios
                StringTokenizer st = new StringTokenizer(message, "#");
                String mensaje = st.nextToken();
                String destinatario = st.nextToken();
                String usuario = st.nextToken();
                
                for (Session s : session.getOpenSessions()) {
                    s.getBasicRemote().sendText(mensaje.concat("#"+usuario));
                    System.out.println("OK" + message); /// Aqui mandamos a todos los usuarios
                }
            }

        } catch (IOException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }

    @OnClose
    public void onClose(Session session) {
        clientList.removeElementAt(getIndexClient(session));
        System.out.println("Session Terminada:" + session.getId()); //Cerramos el WebSocket
    }

}

class Client {

    Session clientSession;
    String username;

    public Client(Session session, String username) {
        this.clientSession = session;
        this.username = username;
    }

}
