package ejemploMasComplejo;

public class Consumidor implements Runnable {

	private Tiempos t;

	public Consumidor(Tiempos t) {
		this.t = t;
	}

	@Override
	public void run() {
		String hora;
		for (int i = 0; i < 10; i++) {
			hora = t.pull();
			System.out.println(Thread.currentThread().getName() + "\tHe cogido " + hora + "\n");
		}

	}

}
