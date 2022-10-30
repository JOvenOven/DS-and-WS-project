import java.io.*;
import java.rmi.*; 
import java.rmi.registry.Registry; 
import java.rmi.registry.LocateRegistry;

import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.Iterator;

/*
* Esta clase representa el cliente de un objeto 
* distribuido de la clase ImplEjemplo, que implementa la 
* interfaz remota InterfazEjemplo.
*/

public class Cliente{ 
	public static void main(String args[]) { 
		try {
			int puertoRMI; 
			String nombreNodo; 
			String numPuerto = "5000";
			String numPuerto2 = "3000"; 

			String cadena="";
			String password="";
			String password2="";
			String ficherokp="";
			String ficherokPrivKey="";
			String mensaje="";
			String nomkp="";
			String nomPrivKey="";

			int entero;

			ArrayList<Mensaje> listaMensajes;

			// Código que permite obtener el nombre del nodo y el número de puerto del registro
			// Búsqueda del objeto remoto y cast de la 
			// referencia con la correspondiente clase 
			// de la interfaz remota - reemplazar "localhost por el nombre del nodo del objeto remoto.

			//SERVIDOR
			String URLRegistro = "rmi://localhost:" + numPuerto + "/ejemplo"; 
			InterfazRemotaServer s = (InterfazRemotaServer) Naming.lookup (URLRegistro); 
			// invocar el o los métodos remotos 


			

			//ENTIDAD CERTIFICADORA - reemplazar "localhost por el nombre del nodo del objeto remoto.
			String URLRegistro2 = "rmi://localhost:" + numPuerto2 + "/ejemplo2"; 
			InterfazRemotaEntiCerti ec = (InterfazRemotaEntiCerti) Naming.lookup (URLRegistro2); 




			do {

				mostrarInterfaz1();
				entero = leerInt();
				while(entero<1 || entero>4){
					System.out.println("Ingrese un valor permitido");
					mostrarInterfaz1();
					entero = leerInt();
				}

				if (entero==1) {
					while (nomkp.equals("")){
						System.out.println("Ingrese el nombre identificador de llave pública");
						nomkp = leerString();
					}
					while (password.equals("")){
						System.out.println("Ingrese la contraseña del par de llaves");
						password = leerString();
					}
					while (password2.equals("")){
						System.out.println("Vuelva a ingresar la contraseña del par de llaves");
						password2 = leerString();
					}
					if (password.equals(password2)==false) {
						while (password.equals(password2)==false) {
							System.out.println("Las contraseñas no coinciden");
							while (password.equals("")){
								System.out.println("Ingrese la contraseña del par de llaves");
								password = leerString();
							}
							while (password2.equals("")){
								System.out.println("Vuelva a ingresar la contraseña del par de llaves");
								password2 = leerString();
							}
						}
					}
					
					ec.generarClave(password);
					System.out.println("Se han creado las claves exitosamente");
					System.out.println("");
					

					ficherokp = nomkp + ".txt";
					File fichero = new File(ficherokp);
			
					//if (!fichero.exists()) {
						ec.exportarClavePublica(fichero.getAbsolutePath(), password);
						System.out.println("Se ha exportado la llave pública con éxito");
						System.out.println("");

						String ficherokp2 = ficherokp + "2.txt";

						byte[] privada = ec.importarClavePrivada(fichero.getAbsolutePath(), password);
						//String privadaCadena = descifrarClavePrivada(privada, password);
						
						guardarLlavePrivadaEnDiscoLocal(ficherokp2, privada);
						System.out.println("Se ha guardado la llave privada en el disco local");
						System.out.println("");
					//}else{
					//	System.out.println("Esa llave ya exixte");
					//}
				
				

				}else if (entero==2) {

						System.out.println("Ingrese el mensaje");
						mensaje=leerString();
						System.out.println("Ingrese el nombre identificador de llave pública");
						ficherokp=leerString();
						ficherokp=ficherokp + ".txt";


						String publickey = ec.importarClavePublica(ficherokp);



						mensaje = s.cifrarMensaje(mensaje, publickey);

						/*
						 *
						 *
						 *AQUI ES DONDE SE FIRMA****************************************************************************************************************************************
						 */boolean firmado = true;/*
						 *
						 *
						 */

						s.enviarMensaje(mensaje, ficherokp, firmado);

						System.out.println("Se ha cifrado y enviado el mensaje con éxito");
						System.out.println("");


				}else if (entero==3) {


					System.out.println("Escriba el ID del mensaje que quiere descifrar");
					System.out.println("");

					listaMensajes = s.exportarMensajes();


					visualizarMensajes(listaMensajes);

					int id = leerInt();



					mensaje = findMensajeById(listaMensajes, id);  /***************************************************************************************************************************/

					System.out.println("Escriba el nombre identificador de la clave privada: ");
					nomPrivKey = leerString();

					System.out.println("Escriba la contraseña");
					password = leerString();


					byte[] privByteCifrado = leerLlavePrivadaEnDiscoLocal(nomPrivKey);

					mensaje = descifrarClavePrivada(privByteCifrado, password);
					
					System.out.println("El mensaje es: ");

					System.out.println(mensaje);





					

				}	



						

					// el método metodoEj2 puede invocarse del mismo modo 
				}while (entero!=4);

		    }catch (Exception exc) {
				exc.printStackTrace(); 
				System.out.println(exc);
			}
	}








	public static void mostrarInterfaz1(){
		System.out.println("*******************Mensajes Seguros*******************");
		System.out.println("Ingrese el numero de la actividad que quiere realizar");
		System.out.println("1. Generar par de llaves");
		System.out.println("2. Enviar un mensaje cifrado");
		System.out.println("3. Descifrar mensaje");
		System.out.println("4. Salir");
		System.out.println("");
	}

	public static String leerString(){

		Scanner sc = new Scanner(System.in);
        String datos = sc.nextLine();

        return datos;
	}

	public static int leerInt(){

		Scanner sc = new Scanner(System.in);
        int datos = sc.nextInt();

        return datos;
	}	



	public static String descifrarClavePrivada(byte[] privateKey, String clave){

		String txt="";

		try{
			DESDescifrar dd = new DESDescifrar();

        	txt = dd.descifrar(privateKey, clave);

        	

		}catch(Exception e){}

		return txt;

	}

	public static byte[] leerLlavePrivadaEnDiscoLocal(String ruta) {

		byte[] bArray=null;

		try{
			String ficherokPrivKey = ruta + ".txt";
			File fichero = new File(ficherokPrivKey);
			bArray = new byte[(int) fichero.length()];

			FileInputStream fos = new FileInputStream(fichero);
			fos.read(bArray);
			fos.close();



		

		}catch(Exception e){}

		return bArray;


	}

	public static void guardarLlavePrivadaEnDiscoLocal(String nomFileClavPriv, byte[] privateKey){

		try{

				
			String nomPrivKey = nomFileClavPriv;
			File ficherokPrivKey = new File(nomPrivKey);

			FileOutputStream fos = new FileOutputStream(ficherokPrivKey);
			fos.write(privateKey);
			fos.close();

		}catch(Exception e){}

	}


	public static void visualizarMensajes(ArrayList<Mensaje> listaMensajes){
		Iterator<Mensaje> itrListaMensajes = listaMensajes.iterator();

		int id = 0;
		while(itrListaMensajes.hasNext()){

			Mensaje mensaje = itrListaMensajes.next();
			System.out.println("ID: " + id);
			System.out.println("Cifrado con llave publica: " + mensaje.getIdKeyPublic());
			System.out.println("Firmado: " + mensaje.getFirmado());
			System.out.println("Mensaje:");
			System.out.println(mensaje.getMensaje());
			System.out.println("");
			System.out.println("");

			id++;
		}
	}

	public static String findMensajeById(ArrayList<Mensaje> listaMensajes, int id){


		return listaMensajes.get(id).getMensaje();
		
	}







// Posible definición de otros métodos de la clase 
}
