import java.rmi.*; 
import java.rmi.server.* ;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.net.*;
import java.io.*; 

public class Servidor{
    public static void main(String args[]) {
      String numPuertoRMI, URLRegistro;
      String numPuerto = "5000";
      try{ 
        ImplInterRemota objExportado = new ImplInterRemota(); 
        //arrancarRegistro (numPuertoRMI);
        URLRegistro = "rmi://localhost:" + numPuerto + "/ejemplo";
        Naming.rebind(URLRegistro, objExportado); 
        System.out.println( "Servidor preparado.");
      } catch (Exception excr) {
            System.out.println("Excepción en Servidor.main: " + excr);
        } 
      } 

      /*private static void arrancarRegistro (int numPuertoRMI) throws RemoteException {
        try{
            Registry registro = LocateRegistry.getRegistry(numPuertoRMI);
            registro.list(); 
            }catch (RemoteException exc){
                System.out.println("El registro RMI no se puede localizar en el puerto:" + RMIPortNum);
                Registry registro =IocateRegistry.createRegistry(numPuertoRMI) ; 
                System.out.println("Registro RMI creado en el puerto " + RMIPortNum); 
            } 
        }*/
      }
