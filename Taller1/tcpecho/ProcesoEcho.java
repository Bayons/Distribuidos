package tcpecho;

import java.io.*;
import java.net.Socket;

public class ProcesoEcho implements Proceso {
	private final PrintStream out;
	private final BufferedReader in;

	public ProcesoEcho(Socket s) throws IOException {
		this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		this.out = new PrintStream(s.getOutputStream());
	}

	public String getLinea() throws IOException {
		return in.readLine();
	}

	public String processLinea(String linea) {
		return linea;
	}

	public void putLinea(String linea) throws IOException {
		out.println(linea);
	}

	public void close() throws IOException {
		out.close();
		in.close();
	}
}