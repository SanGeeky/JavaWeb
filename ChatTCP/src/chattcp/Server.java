
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 package chattcp;
 import java.io.DataInputStream;
 import java.io.DataOutputStream;
 import java.io.IOException;
 import java.net.ServerSocket;
 import java.net.Socket;
 import java.util.logging.Level;
 import java.util.logging.Logger;

 public class Servidor {
 public static void main(String[] ars){
 ServerSocket servidor=null;
 Socket sc=null;
 DataInputStream in=null;
 DataOutputStream out=null;
 final int puerto=4000;
 try {
 servidor = new ServerSocket(puerto);
 System.out.println("Socket de servidor INICIADO");
 while(true){
 sc=servidor.accept();
 System.out.println("CLIENTE CONECTADO");
                
 in = new DataInputStream(sc.getInputStream());
 out=new DataOutputStream(sc.getOutputStream());
 String mensaje=in.readUTF();
 System.out.println("Mensaje de cliente:"+mensaje);
 out.writeUTF("Te conectaste al Servidor Socket....");
 sc.close();
 System.out.println("CLIENTE DESCONECTADO");
 }
            
 } catch (IOException ex) {
 Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
 }
        
        
 }
    
 }
 */
// Java implementation of Server side 
// It contains two classes : Server and ClientHandler 
// Save file as Server.java 
import java.io.*;
import java.util.*;
import java.net.*;

// Server class 
public class Server {

    // Vector to store active clients 
    static Vector<ClientHandler> ar = new Vector<>();

    // counter for clients 
    static int i = 0;
    
    static String username = "";

    public static void main(String[] args) throws IOException {
        // server is listening on port 1234 
        ServerSocket ss = new ServerSocket(1234);

        Socket s;

        // running infinite loop for getting 
        // client request 
        while (true) {
            // Accept the incoming request 
            s = ss.accept();

            System.out.println("New client request received : " + s);

            // obtain input and output streams 
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            
            
            username = dis.readUTF();

            System.out.println("Creating a new handler for this client...");

            // Create a new handler object for handling this request. 
            ClientHandler mtch = new ClientHandler(s, username, dis, dos);

            // Create a new Thread with this object. 
            Thread t = new Thread(mtch);

            System.out.println("Adding this client to active client list");

            // add this client to active clients list 
            ar.add(mtch);
            // start the thread. 
            t.start();

            // increment i for new client. 
            // i is used for naming only, and can be replaced 
            // by any naming scheme 
            i++;

        }
    }
}

// ClientHandler class 
class ClientHandler implements Runnable {

    Scanner scn = new Scanner(System.in);
    private String name;
    final DataInputStream dis;
    final DataOutputStream dos;
    Socket s;
    boolean isloggedin;

    // constructor 
    public ClientHandler(Socket s, String name, DataInputStream dis, DataOutputStream dos) {
        this.dis = dis;
        this.dos = dos;
        this.name = name;
        this.s = s;
        this.isloggedin = true;
    }


    @Override
    public void run() {

        String received;
        while (true) {
            try {
                //Enviamos todos los usuarios Conectados
                String usuariospedos = ";";
                        
                for (ClientHandler mc : Server.ar) {
                    usuariospedos += mc.name + ";";
                }
                for (ClientHandler mc : Server.ar) {
                    mc.dos.writeUTF(usuariospedos);
                }

                // receive the string de mensajes
                received = dis.readUTF();

                System.out.println(received);

                if (received.equals("logout#TODOS")) {
                    int i=0;
                    for (ClientHandler mc : Server.ar) {                    
                        if (mc.name.equals(this.name)) {
                            Server.ar.remove(i);
                            break;
                        }
                        i++;
                    }
                    this.isloggedin = false;
                    this.s.close();
                    break;
                }

                // break the string into message and recipient part 
                StringTokenizer st = new StringTokenizer(received, "#");
                String MsgToSend = st.nextToken();
                String recipient = st.nextToken();
                // ar is the vector storing client of active users
                if (recipient.equals("TODOS")) {
                    for (ClientHandler mc : Server.ar) {
                        // output stream broadcast to all
                        if (mc.isloggedin == true && !mc.s.equals((this.s))) {
                            mc.dos.writeUTF(this.name + ": " + MsgToSend);
                        }
                    }
                } else {
                    for (ClientHandler mc : Server.ar) {
                        if (mc.name.equals(recipient) && mc.isloggedin == true) {
                            mc.dos.writeUTF(this.name + ": " + MsgToSend);
                            break;
                        }
                    }
                }

            } catch (IOException e) {

                e.printStackTrace();
            }

        }
        try {
            // closing resources 
            this.dis.close();
            this.dos.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
