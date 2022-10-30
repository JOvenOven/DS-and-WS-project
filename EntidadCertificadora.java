import java.rmi.*; 
import java.rmi.server.* ;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.net.*;
import java.io.*; 

public class EntidadCertificadora{
    public static void main(String args[]) {
      String numPuertoRMI, URLRegistro;
      String numPuerto = "3000";
      try{ 
        
        ImplInterRemotaEC objExportado = new ImplInterRemotaEC(); 
        //arrancarRegistro (numPuertoRMI); 
        //Registrar objeto bajo el nombre ejemplo
        URLRegistro = "rmi://localhost:" + numPuerto + "/ejemplo2";
        Naming.rebind(URLRegistro, objExportado); 
        System.out.println( "Entidad certificadora preparada.");


      } catch (Exception excr) {
            System.out.println("Excepción en EntidadCertificadora.main: " + excr);
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
