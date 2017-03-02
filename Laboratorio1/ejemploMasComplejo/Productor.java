package ejemploMasComplejo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Productor implements Runnable {

	private Tiempos t;

	public Productor(Tiempos t) {
		this.t = t;
	}

	@Override
	public void run() {
		DateFormat dF = new SimpleDateFormat("HH:mm:ss");
		for (int i = 0; i < 10; i++) {

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println(Thread.currentThread().getName() + "\tAlla va");
			t.push(dF.format(new Date()));
		}
	}

}
