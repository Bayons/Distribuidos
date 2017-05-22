package tcpecho;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente implements Runnable {

	public static final int PUERTO = 2000;

	public static Socket cliente;

	Cliente(Socket cliente) {
		this.cliente = cliente;
	}

	public static void main(String args[]) throws IOException {
		Socket cliente = new Socket("localhost", PUERTO);
		(new Thread(new Cliente(cliente), "Cliente")).start();
	}

	@Override
	public void run() {
		String linea;
		Scanner sc = new Scanner(System.in);

		OutputStream out;
		try {
			out = cliente.getOutputStream();
			/* salida socket */
			PrintStream outred = new PrintStream(out);

			while ((linea = sc.nextLine()) != null) { /* lee de la red */
				outred.println(linea);

				if (linea.equals("Adios.")) {
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
