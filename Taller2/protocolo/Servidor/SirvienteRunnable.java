package protocolo.Servidor;

import java.util.*;
import java.io.*;
import java.net.*;
import java.util.concurrent.*;

import primitivas.common.MensajeProtocolo;
import primitivas.common.Primitive;

class SirvienteRunnable implements Runnable {
	private final Socket socket;
	private final ColaStrings cola;
	private final int nConexion;

	SirvienteRunnable(Socket s, ColaStrings c, int n) {
		this.socket = s;
		this.cola = c;
		this.nConexion = n;
	}

	public void run() {
		System.out.println("Cliente " + nConexion + " conectado");

		try {
			conexion();
		} catch (IOException e) {
			System.err.println("Sesion " + nConexion + " cerrada de manera forzosa.");
		} catch (ClassNotFoundException e) {
			System.err.println("Tipo del objeto en mensaje del cliente " + nConexion + " no encontrado.");
		}
	}

	public void conexion() throws IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
		ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
		MensajeProtocolo mp;
		do {
			mp = (MensajeProtocolo) in.readObject();

			switch (mp.getPrimitive()) {
			case HELLO:
				out.writeObject(mp);
				break;

			case PUSH:
				out.writeObject(mp);
				break;

			case PULL:
				out.writeObject(mp);
				break;

			case PULL_WAIT:
				out.writeObject(mp);
				// socket.close();
				break;

			default:
				mp = new MensajeProtocolo(Primitive.NOTUNDERSTAND);
				break;
			}
		} while (!(mp.getPrimitive().equals(Primitive.HELLO)&&mp.getMessage().equals("bye")));
		socket.close();
	}
}