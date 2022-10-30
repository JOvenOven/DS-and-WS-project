import java.rmi.*;
import java.rmi.server.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class ImplInterRemota extends UnicastRemoteObject implements InterfazRemotaServer{

	private File ficheroMensajes = new File("Mensaje.txt");
	private ArrayList<Mensaje> listaMensajes;
	private String cifrado;

	public ImplInterRemota() throws RemoteException{
		super();
	}


	public String cifrarMensaje(String mensaje, String publicKey) throws RemoteException{

		try{


			RSA rsa = new RSA();

			rsa.setPublicKeyString(publicKey);
			this.cifrado = rsa.Encrypt(mensaje);
			//rsa.openFromDiskPublicKeyString(rutaClavePublica + ".txt");
		


		}catch(Exception e){
			//Errores
		}

		return cifrado;
	}


	public void enviarMensaje(String mensaje, String ficherokp, boolean firmado) throws RemoteException{


		/* EJEMPLO
		File ficheroGatos = new File("Gatos.txt");
		ArrayList<Gato> listaGatos;
		Gato g = new Gato();
        g.setRaza(jTextFieldRaza.getText());
        g.setPromedioVida(jTextFieldPromedioVida.getText());
        g.setCuidados(cortarlinea);
        g.setPrecio(precio);
        */


        listaMensajes = new ArrayList<Mensaje>();
        
        Mensaje m = new Mensaje();
        m.setMensaje(mensaje);
        m.setIdKeyPublic(ficherokp);
        m.setFirmado(firmado);

        SerializarObjetos so = new SerializarObjetos();
        listaMensajes = so.deserializarArrayMensaje(ficheroMensajes);
        listaMensajes.add(m);
        so.serializarArrayMensaje(ficheroMensajes, listaMensajes);



        /* EJEMPLO
		ArrayList<Gato> listaGatos = new ArrayList<Gato>();

		SerializarObjetos so = new SerializarObjetos();
		listaGatos = so.deserializarArrayGato(ficheroGatos);
        listaGatos.add(g);
        so.serializarArrayGato(ficheroGatos, listaGatos);
        */

	}


	public ArrayList<Mensaje> exportarMensajes() throws RemoteException{

		listaMensajes = new ArrayList<Mensaje>();

		SerializarObjetos so = new SerializarObjetos();
        listaMensajes = so.deserializarArrayMensaje(ficheroMensajes);





        return listaMensajes;
        /*
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
		*/

		/*Mensaje m = new Mensaje();
        m.setMensaje(mensaje);
        m.setIdKeyPublic(ficherokp);
        m.setFirmado(firmado);

        SerializarObjetos so = new SerializarObjetos();
        listaMensajes = so.deserializarArrayMensaje(ficheroMensajes);
        listaMensajes.add(m);*/
		
		}


        
        
        

	public void descifrarMensaje() throws RemoteException{

		listaMensajes = new ArrayList<Mensaje>();

		SerializarObjetos so = new SerializarObjetos();
        listaMensajes = so.deserializarArrayMensaje(ficheroMensajes);

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


        so.serializarArrayMensaje(ficheroMensajes, listaMensajes);

	}



}