import java.rmi.*;
import java.rmi.server.*;

public class ImplInterRemotaEC extends UnicastRemoteObject implements InterfazRemotaEntiCerti{

	private GeneradorDeClaves generadorDeClaves;
	private Firmador firmador;
	private Verificador verificador;
	private RSA rsa;

	public ImplInterRemotaEC() throws RemoteException{
		super();

		try{
			generadorDeClaves = new GeneradorDeClaves();
			firmador = new Firmador();
			verificador = new Verificador();
		}catch(java.security.NoSuchAlgorithmException e){}
		
	}



	public void generarClave(String password) throws RemoteException
	{
		generadorDeClaves.generarClave(password);
		
	}

	public void exportarClavePublica(String rutaClavePublica, String passwordKeyPair) throws Exception,RemoteException
	{
		generadorDeClaves.exportarClavePublica(rutaClavePublica, passwordKeyPair);
	}


	public String importarClavePublica(String rutaClavePublica) throws Exception,RemoteException
	{
		return generadorDeClaves.importarClavePublica(rutaClavePublica);
	}



	public byte[] importarClavePrivada(String rutaClavePrivada, String passwordKeyPair) throws Exception,RemoteException
	{

		String privateKey = generadorDeClaves.importarClavePrivada(rutaClavePrivada);
		/***********************************************AQUI SE DEBE CIFRAR LA LLAVE PRIVADA PARA QUE NO SEA ACCESIBLE MIENTRAS QUE SE ENVIA*********************************************************/
		
		DESCifrar dc = new DESCifrar();
        byte[] bb = dc.cifrar(privateKey, passwordKeyPair);

		return bb;
	}







	/*public void generarClaves() throws RemoteException
	{
		Controlador controlador = inicio.getControlador();
		
		String pass1 = String.valueOf(passFieldContrasena.getPassword());
		String pass2 = String.valueOf(passFieldIngreseNuevamenteContrasena.getPassword());
		
		if(pass1.equals("") || pass2.equals(""))
		{
			JOptionPane.showMessageDialog(this, "El password no puede ser vacio","Error",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			if(pass1.equals(pass2)==false)
			{
				JOptionPane.showMessageDialog(this, "No coindice el password","Error",JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				controlador.generarClave(pass1);
				JOptionPane.showMessageDialog(this, "Se generaron correctamente las claves","Respuesta",JOptionPane.INFORMATION_MESSAGE);
				
				passFieldContrasena.setText("");
				passFieldIngreseNuevamenteContrasena.setText("");
			}
		}
	}*/




}