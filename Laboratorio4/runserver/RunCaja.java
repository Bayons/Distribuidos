package runserver;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import cajaserver.*;
import util.Contador;

/**
 * Lanza el servidor de Caja(s) de Contador(es)
 * 
 * @author cllamas
 */
public class RunCaja {
	public static void main(String[] args) {
        try {
            CajaImpl<Contador> cc = new CajaImpl<>();

            Registry registro = LocateRegistry.createRegistry(1099);

            registro.rebind("Cofre", cc);
            System.out.println("Objeto remoto enlazado");
        } catch (RemoteException re) {
            re.printStackTrace(System.err);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}