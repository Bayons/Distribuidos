package primitivas.cliente;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import primitivas.common.*;

public class Cliente {

	public static final int PUERTO = 2000;

	public static void main(String[] args) {
		Socket socket;
		Scanner sc = new Scanner(System.in);

		InputStream entrada;
		OutputStream salida;

		ObjectInputStream eObjeto;
		ObjectOutputStream sObjeto;

		MensajeProtocolo mp;
		int seleccion;
		String mensaje;

		System.out.println("Iniciando conexion...");
		try {
			socket = new Socket("localhost", PUERTO);
			entrada = socket.getInputStream();
			salida = socket.getOutputStream();
			System.out.println("Iniciando salida de datos...");
			sObjeto = new ObjectOutputStream(salida);
			System.out.println("Iniciando entrada de datos...");
			eObjeto = new ObjectInputStream(entrada);

			// Inicia comunicacion
			System.out.println("Conexion iniciada.\nIniciando comunicacion...");
			do {
				mp = new MensajeProtocolo(Primitive.HELLO, "hi");
				sObjeto.writeObject(mp);
				mp = (MensajeProtocolo) eObjeto.readObject();
				if (mp.getPrimitive().equals(Primitive.NOTUNDERSTAND)) {
					Thread.currentThread().wait(1000);
				}
			} while (!mp.getPrimitive().equals(Primitive.HELLO));
			System.out.println("Comunicacion iniciada.");

			// Comunicacion
			do {
				System.out.print("\nSelecciona una opcion:\n1: Hello\n2: Push\n3: Pull\n4: Salir\nOpcion: ");
				seleccion = sc.nextInt();
				sc.nextLine();
				switch (seleccion) {
				case 1: // HELLO
					System.out.println("\nHELLO realizara un echo en ambos terminales. Indique el mensaje a enviar:");
					mensaje = sc.nextLine();
					mp = new MensajeProtocolo(Primitive.HELLO, mensaje);
					sObjeto.writeObject(mp);

					mp = (MensajeProtocolo) eObjeto.readObject();
					System.out.println("Mensaje recibido: " + mp.toString());
					break;

				case 2:// PUSH
					System.out.println(
							"\nPUSH enviara un mensaje a acumular en la pila de mensajes. Indique el mensaje a enviar:");
					mensaje = sc.nextLine();
					mp = new MensajeProtocolo(Primitive.PUSH, mensaje);
					sObjeto.writeObject(mp);

					for (int i = 0; i <= 3; i++) {
						mp = (MensajeProtocolo) eObjeto.readObject();
						if (!mp.getPrimitive().equals(Primitive.PUSH_OK) && i == 3)
							System.out.println("Se produjo un error en el envio del mensaje");
						else if (!mp.getPrimitive().equals(Primitive.PUSH_OK)) {
							mp = new MensajeProtocolo(Primitive.PUSH, mensaje);
							sObjeto.writeObject(mp);
						} else {
							System.out.println("Mensaje enviado");
							break;
						}
					}
					break;

				case 3:// PULL
					System.out.println("\nPULL Recoge un mensaje de la pila.");
					mp = new MensajeProtocolo(Primitive.PULL);
					sObjeto.writeObject(mp);

					for (int i = 0; i <= 3 || mp.getPrimitive().equals(Primitive.PULL_OK); i++) {
						mp = (MensajeProtocolo) eObjeto.readObject();
						if (!mp.getPrimitive().equals(Primitive.PULL_OK) && i == 3)
							System.out.println("Se produjo un error en el envio del mensaje");
						else if (!mp.getPrimitive().equals(Primitive.PULL_OK)) {
							mp = new MensajeProtocolo(Primitive.PULL_WAIT);
							sObjeto.writeObject(mp);
						} else {
							System.out.println("Mensaje recibido:" + mp.toString());
							break;
						}
					}

					break;

				default:
					mensaje = "bye";
					mp = new MensajeProtocolo(Primitive.HELLO, mensaje);
					sObjeto.writeObject(mp);

					mp = (MensajeProtocolo) eObjeto.readObject();
					break;
				}
			} while (!((mp.getPrimitive()).equals(Primitive.HELLO) && mp.getMessage().equals("bye")));
			socket.close();

		} catch (UnknownHostException e1) {
			System.err.println("Error: host no encontrado");
		} catch (IOException e2) {
			System.err.println("Error I/O");
			e2.printStackTrace();
		} catch (ClassNotFoundException e3) {
			System.err.println("Error: objeto no reconocido");
		} catch (InterruptedException e) {
			System.err.println("Error: fallo de hilo");
		} finally {
			sc.close();
		}

	}

}
