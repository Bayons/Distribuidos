package cajaserver;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Almacena un dato accesible v�a RMI. (Versi�n incompleta)
 *
 * El dato persiste en el servidor hasta que un cliente lo sobreescribe.
 * 
 * @author cllamas
 * @param <T>
 *            es el tipo de dato que guardamos en la Caja
 */
public interface Caja<T extends Serializable> extends Remote {
	/**
	 * Sobreescribe el dato en la caja.
	 * 
	 * @param a
	 *            el dato pasa a ser el contenido actual de la caja.
	 * @throws RemoteException
	 */
	public void pon(T a) throws RemoteException;

	/**
	 * Retira el contenido de la caja y la vac�a.
	 * 
	 * @return el contenido actual de la caja.
	 * @throws RemoteException
	 */
	public T quita() throws RemoteException;

	/**
	 * Consulta el contenido de la caja.
	 * 
	 * @return el contenido actual de la caja.
	 * @throws RemoteException
	 */
	public T lee() throws RemoteException;
}