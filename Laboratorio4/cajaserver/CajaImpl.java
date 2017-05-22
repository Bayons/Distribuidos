package cajaserver;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Implementaci�n de la interfaz remota Caja.
 * @author cllamas
 * @param <T> Tipo de dato a almacenar en la Caja.
 */
public class CajaImpl<T extends Serializable> extends UnicastRemoteObject implements Caja<T> {
    private T contenido = null;

    public CajaImpl() throws RemoteException{
        super();
    }

    @Override
    public void pon(T a) throws RemoteException {
        this.contenido = a;
    }

    @Override
    public T quita() throws RemoteException {
        T x = contenido;
        contenido = null;
        return x;
    }

    @Override
    public T lee() throws RemoteException {
        return contenido;
    }
}