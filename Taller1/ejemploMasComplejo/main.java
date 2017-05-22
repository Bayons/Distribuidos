package ejemploMasComplejo;

public class main {

	public static void main(String[] args) {
		Tiempos t = new Tiempos();
		Runnable p = new Productor(t);
		Runnable c = new Consumidor(t);

		new Thread(p, "Productor").start();
		new Thread(c, "Consumidor").start();
	}

}
