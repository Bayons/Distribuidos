package ejemploSimple;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Simple ejemplo de implementacion de hilos
 * 
 * Basado en el ejemplo del laboratorio y en el ejemplo de Oracle de
 * https://docs.oracle.com/javase/tutorial/essential/concurrency/simple.html
 * 
 * @author Miguel Bayon Sanz
 *
 */
public class Reloj implements Runnable {

	public static void main(String[] args) {
		int espera = 10;
		final Runnable tarea = new Reloj();
		Thread reloj = new Thread(tarea, "Hijo");
		reloj.start();

		while (reloj.isAlive()) {
			espera--;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println();

			if (espera > 0)
				System.out.println(Thread.currentThread().getName() + "\tStill waiting... ¬.¬");
			else {
				System.out.println(Thread.currentThread().getName() + "\tTired of waiting! >:(");
				reloj.interrupt();
				try {
					reloj.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(Thread.currentThread().getName() + "\tFinally! :D");
	}

	@Override
	public void run() {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		try {
			while (true) {
				System.out.println(
						Thread.currentThread().getName() + "\tjijijiji :D (at " + dateFormat.format(new Date()) + ")");
				Thread.sleep(1000);
			}
		} catch (InterruptedException x) {
			System.out.println(Thread.currentThread().getName() + " Why?? :'(");
		}
	}
}
