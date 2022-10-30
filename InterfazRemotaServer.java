
import java.rmi.*;
import java.util.ArrayList;

public interface InterfazRemotaServer extends Remote{


public String cifrarMensaje(String mensaje, String publicKey) throws RemoteException;


public void enviarMensaje(String mensaje, String ficherokp, boolean firmado) throws RemoteException;


public ArrayList<Mensaje> exportarMensajes() throws RemoteException;


public void descifrarMensaje() throws RemoteException;



}
