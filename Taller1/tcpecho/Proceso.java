package tcpecho;

import java.io.IOException;
import java.net.Socket;

public interface Proceso extends AutoCloseable {
    public String  getLinea() throws IOException;
    public String  processLinea(String linea);
    public void    putLinea(String linea) throws IOException;
}