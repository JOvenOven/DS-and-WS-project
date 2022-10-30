
import java.rmi.*;

public interface InterfazRemotaEntiCerti extends Remote{


public void generarClave(String password) throws RemoteException;


public void exportarClavePublica(String rutaClavePublica, String passwordKeyPair) throws Exception,RemoteException;


public String importarClavePublica(String rutaClavePublica) throws Exception,RemoteException;


public byte[] importarClavePrivada(String rutaClavePrivada, String passwordKeyPair) throws Exception,RemoteException;






}