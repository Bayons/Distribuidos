package echosencillo;

import java.net.*;
import java.io.*;

public class Cliente {
	public static final int PUERTO = 2000;

	public static void main(String[] args) throws IOException {
		String linea = null;
		Socket cliente = new Socket("localhost", PUERTO); // crea socket en
															// direccion del
															// server
		try {
			Reader t1 = new InputStreamReader(System.in);
			BufferedReader inteclado = new BufferedReader(t1);

			InputStream in = cliente.getInputStream(); /* entrada socket */
			Reader r1 = new InputStreamReader(in);
			BufferedReader inred = new BufferedReader(r1);

			OutputStream out = cliente.getOutputStream(); /* salida socket */
			PrintStream outred = new PrintStream(out);

			while ((linea = inteclado.readLine()) != null) { /* lee de la red */
				outred.println(linea);

				linea = inred.readLine();

				System.out.println("Recibido: " + linea);
				if (linea.equals("Adios.")) {
					break;
				}
			}
		} catch (Exception e) {
		}
	}
}