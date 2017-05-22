package tcpecho;

public class Sirviente implements Runnable {
	public final ProcesoEcho p;
	public final int id;

	Sirviente(ProcesoEcho p, int identificador) {
		this.p = p;
		this.id = identificador;
	}

	public void run() {
		try {
			String entrada, salida;

			while ((entrada = p.getLinea()) != null) {
				System.out.println("[" + this.id + "] Echoing: " + entrada);
				salida = this.p.processLinea(entrada);
				this.p.putLinea(salida);
			}
		} catch (java.net.SocketException sE) {
			System.out.println(">> Conexión "+ id +" cerrada");
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
}