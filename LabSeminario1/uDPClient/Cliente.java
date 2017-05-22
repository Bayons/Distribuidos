package uDPClient;

import java.net.*;
import java.util.Scanner;
import java.io.*;

/**
 * Manda un mensaje al servidor y recibe del mismo un mensaje identico
 * 
 * @author Miguel Bayon
 *
 */
public class Cliente {

	static final int BUFFERSIZE = 256;
	static final int PORT = 2000;

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		String host = "localhost";
		String linea;
		InetAddress ia = InetAddress.getByName(host); // transforma una
														// direccion en ip
		DatagramSocket sock = new DatagramSocket();
		sock.connect(ia, PORT);
		Scanner sc = new Scanner(System.in);
		byte[] datout, datin;
		DatagramPacket output, input;

		System.out.println("Enviando:");
		while (!(linea = sc.nextLine()).equals("")) {
			datout = linea.getBytes();
			// creamos el datagrama con la salida en forma de array de bytes
			output = new DatagramPacket(datout, datout.length, ia, PORT);
			sock.send(output); // enviamos datagrama

			datin = new byte[160]; // preparamos para recibir
			input = new DatagramPacket(datin, datin.length);
			sock.receive(input); // recibimos

			linea = new String(input.getData()); // interpreta
			System.out.println("Echo: " + linea);

			if (linea.equals("Adios."))
				return;
		}

	}

}
