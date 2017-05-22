package echosencillo;

import java.net.*;
import java.io.*;

public class Servidor {
	public static final int PUERTO = 2000;

	public static void main(String[] args) throws IOException {
		/* creación del socket */
		ServerSocket servidor = new ServerSocket(PUERTO); // crea socket

		for (;;) {
			try {
				System.out.println("----Servidor aceptando conexiones----");
				try (Socket sock = servidor.accept()) { // socket acepta
					/********************************************
					 * Zona de servicio.
					 */
					InputStream in = sock.getInputStream(); // socket recibe

					Reader r1 = new InputStreamReader(in); // pasa bits a ascii
					PrintStream outred; // salida de texto

					try (BufferedReader inred = new BufferedReader(r1)) {
						OutputStream out = sock.getOutputStream(); // salida por
																	// el mismo
																	// sock
						outred = new PrintStream(out);
						String linea;
						
						/* bucle de ECHO */
						while ((linea = inred
								.readLine()) != null) { /* lee de la red */

							System.out.println("Echoing: "
									+ linea); /* echo por la pantalla */
							outred.println(linea); /* echo al cliente */
							if (linea.equals("Adios.")) {
								break;
							}
						}

					} /* salida socket */

					outred.close();
					outred.close();
					/********************************************
					 * Fin de la zona de servicio
					 */
				}
			} catch (IOException e) {
				e.printStackTrace(System.err);
			}
		}
	}
}