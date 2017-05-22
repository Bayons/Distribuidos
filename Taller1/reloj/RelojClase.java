package reloj;

public class RelojClase implements Runnable {
    public void run() {
        while (true) {
            System.out.println(new java.util.Date());
            try { Thread.sleep(1000); // milis
            } catch (InterruptedException x) { }
        }
    }

    public static void main(String[ ] args) {
        final Runnable tarea = new RelojClase();
        new Thread(tarea, "Hilo de TimePrinter").start();
        // resto del programa
    }
}