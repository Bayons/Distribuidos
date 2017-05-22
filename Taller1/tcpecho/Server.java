package tcpecho;

import java.net.*;
import java.io.*;

public class Server {

    public static final int PUERTO = 2000;

    public static void main(String[] args) throws IOException {
        ServerSocket servidor = new ServerSocket(PUERTO);
        
        System.out.println("---Aceptando conexiones---");
        for (int i=1; true;i++) {
            try {
                Socket sock = servidor.accept(); /* ojo: no usar try-with-res */
                ProcesoEcho echo = new ProcesoEcho(sock);
                (new Thread(new Sirviente(echo, i), "ECO")).start();
                System.out.println("->Nueva conexion: " + i);
            } catch (IOException e) {
            	System.out.println("Conexión cerrada");
                e.printStackTrace(System.err);
            }
        }
    }
}