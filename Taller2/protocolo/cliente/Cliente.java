package protocolo.cliente;

import java.util.*;

import primitivas.common.*;

import java.io.*;
import java.net.*;

public class Cliente {
	final private int PUERTO = 2000;

	public static void main(String[] args) throws IOException {
		String linea;

		Socket sock = new Socket("localhost", 2000); /* creaci�n del socket */
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;

		try {
			/** Creaci�n de los canales de serializaci�n de objetos */
			oos = new ObjectOutputStream(sock.getOutputStream());
			ois = new ObjectInputStream(sock.getInputStream());

			/*
			 * no vamos a utilizar teclado, y vamos a probar las primitivas por
			 * programa
			 */
			System.out.println("Pulsa <Enter> para comenzar: envia HELLO");
			System.in.read();
			/* Escenario 1 */
			oos.writeObject(new MensajeProtocolo(Primitive.HELLO, "Pedro"));
			System.out.println((MensajeProtocolo) ois.readObject());
			System.out.println("Pulsa <Enter> para continuar");
			System.in.read();

			oos.writeObject(new MensajeProtocolo(Primitive.PUSH, "Estamos en Estambul."));
			System.out.println((MensajeProtocolo) ois.readObject());
			System.out.println("Pulsa <Enter> para continuar");
			System.in.read();

			oos.writeObject(new MensajeProtocolo(Primitive.PULL));
			System.out.println((MensajeProtocolo) ois.readObject());
			System.out.println("Pulsa <Enter> para continuar");
			System.in.read();

			oos.writeObject(new MensajeProtocolo(Primitive.PULL_WAIT));
			System.out.println((MensajeProtocolo) ois.readObject());
			System.out.println("Pulsa <Enter> para finalizar");
			System.in.read();
			
			oos.writeObject(new MensajeProtocolo(Primitive.HELLO, "bye"));

			/***
			 * aqu� se supone que tiene que llegar otro cliente e insertar un
			 * mensaje en la cola
			 */

			/* FIN del escenario 1 */
		} catch (IOException e) {
			System.err.println("Cliente: Error de apertura o E/S sobre objetos: " + e);
		} catch (Exception e) {
			System.err.println("Cliente: Excepci�n. Cerrando Sockets: " + e);
		} finally {
			ois.close();
			oos.close();
			sock.close();
		}
	}
}