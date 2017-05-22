package primitivas.servidor;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import primitivas.common.*;

public class Servidor {

	public static final int PUERTO = 2000;

	public static final ColaStrings cola = new ColaStrings();

	public static void main(String[] args) throws IOException {
		ServerSocket socketServidor = new ServerSocket(PUERTO);
		Socket socket;

		InputStream entrada;
		OutputStream salida;

		ObjectInputStream eObjeto;
		ObjectOutputStream sObjeto;

		MensajeProtocolo mp, lastSent;

		System.out.println("---Aceptando conexiones---");
		for (int i = 1; true; i++) {
			socket = socketServidor.accept();
			entrada = socket.getInputStream();
			salida = socket.getOutputStream();
			eObjeto = new ObjectInputStream(entrada);
			sObjeto = new ObjectOutputStream(salida);

			System.out.println("-> Nueva conexion: " + i);
			try {

				// Inicia comunicacion
				do {
					mp = (MensajeProtocolo) eObjeto.readObject();
					if (!mp.getPrimitive().equals(Primitive.HELLO)) {
						mp = new MensajeProtocolo(Primitive.NOTUNDERSTAND);
					} else {
						System.out.println("-> Conexion " + i + " iniciada");
						mp = new MensajeProtocolo(Primitive.HELLO, "hi");
					}
					sObjeto.writeObject(mp);
					lastSent = mp;
				} while (!(mp.getPrimitive().equals(Primitive.HELLO) && mp.getMessage().equals("hi")));

				// Comunicacion inicializada
				do {
					mp = (MensajeProtocolo) eObjeto.readObject();
					System.out.println(mp.toString());

					switch (mp.getPrimitive()) {
					case HELLO:
						mp = new MensajeProtocolo(Primitive.HELLO, mp.getMessage());
						System.out.println("echo " + mp.toString());
						sObjeto.writeObject(mp);
						lastSent = mp;
						break;

					case PUSH:
						cola.add(mp.getMessage());
						mp = new MensajeProtocolo(Primitive.PUSH_OK);
						sObjeto.writeObject(mp);
						lastSent = mp;
						break;

					case PULL:
						if (cola.size() == 0)
							mp = new MensajeProtocolo(Primitive.NOTHING);
						else
							mp = new MensajeProtocolo(Primitive.PULL_OK, cola.get());
						sObjeto.writeObject(mp);
						lastSent = mp;
						break;

					case PULL_WAIT:
						mp = new MensajeProtocolo(Primitive.PULL_OK, cola.get());
						sObjeto.writeObject(mp);
						lastSent = mp;
						break;

					case NOTUNDERSTAND:
						;

					default:
						sObjeto.writeObject(lastSent);
						break;
					}
				} while (!(mp.getPrimitive().equals(Primitive.HELLO) && (mp.getMessage().equals("bye"))));
				socket.close();
			} catch (ClassNotFoundException e) {
			}
		}
	}

}
