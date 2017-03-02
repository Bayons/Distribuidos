package ejemploMasComplejo;

import java.util.ArrayList;

public class Tiempos {
	private ArrayList<String> segundos;

	public Tiempos() {
		segundos = new ArrayList<String>();
	}

	public synchronized void push(String nuevo) {
		segundos.add(nuevo);
		this.notify();
	}

	public synchronized String pull() {
		while (segundos.isEmpty()) {
			try {
				this.wait();
			} catch (InterruptedException e) {
			}
		}
		return segundos.remove(0);
	}
}
