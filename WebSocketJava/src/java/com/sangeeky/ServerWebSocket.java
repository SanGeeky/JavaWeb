/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sangeeky;
import java.io.IOException;
import javax.websocket.OnOpen;
import javax.websocket.OnMessage;
import javax.websocket.OnError;
import javax.websocket.OnClose;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author vauxr
 */
@ServerEndpoint("/servidor")
public class ServerWebSocket {
    
    @OnOpen
    public void onOpen(Session session){
        //Nuevo Ciente se Conecta
        System.out.println("Solicitud conexion "+session.getId());
        try{
            session.getBasicRemote().sendText("Conexion Creada"); // Respuesta al Cliente que se conecto
        }catch(IOException ex){
            System.out.println("Error "+ex.getMessage());
        }
    }
    
    @OnMessage
    public void onMessage(String message,Session session){
        try{
            for(Session s: session.getOpenSessions()){
                 s.getBasicRemote().sendText(message);
                 System.out.println("OK"+message); /// Aqui mandamos a todos los usuarios
            }
            
        }catch(IOException ex){
            System.out.println("Error "+ex.getMessage());
        }
    }
    @OnClose
    public void onClose(Session session){
        System.out.println("Session Terminada:"+session.getId()); //Cerramos el WebSocket
    }
         
}
