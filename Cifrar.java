import java.io.*;
import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.security.*;
import java.util.*;

public class DESCifrar{

    InOut io = new InOut();

    public void cifrarArchivo(String leer, String escribir,String clave){
        try{
            SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
            DESKeySpec kspec = new DESKeySpec(clave.getBytes());
            SecretKey ks = skf.generateSecret(kspec);
           
           //Inicializar el cifrado
              try{
                    Cipher cifrado = Cipher.getInstance("DES");
              
                    cifrado.init(Cipher.ENCRYPT_MODE, ks);//MODO CIFRAR
                    try {
                        InputStream archivo = new FileInputStream( leer );
                        OutputStream fich_out = new FileOutputStream ( escribir );
         
                        byte[] buffer = new byte[1024];
                        byte[] bloque_cifrado;
                        String textoCifrado = new String();
                        int fin_archivo = -1;
                        int leidos;//numero de bytes leidos
         
                        leidos = archivo.read(buffer);
         
                        while( leidos != fin_archivo ) {
                        bloque_cifrado = cifrado.update(buffer,0,leidos);
                        textoCifrado = textoCifrado + new String(bloque_cifrado,"ISO-8859-1");
                        leidos = archivo.read(buffer);         
                        }
         
         
                        archivo.close();
         
                        bloque_cifrado = cifrado.doFinal();
                        textoCifrado = textoCifrado + new String(bloque_cifrado,"ISO-8859-1");
                        //ISO-8859-1 es ISO-Latin-1
         
                      fich_out.write(textoCifrado.getBytes("ISO-8859-1"));//escribir fichero
                    } catch (Exception e) {
                    System.out.println("Archivo no encontrado");
                    }
                    
                 
            }
            //Inicializacion de cifrado
            catch(Exception e) {System.out.println("error inicializar el cifrado");} //Instanciacion DES
            //catch(javax.crypto.IllegalBlockSizeException ibse) {}//metodo doFinal
            //catch(javax.crypto.BadPaddingException bpe) {}//metodo doFinal
        }
        //pasar clave a la clase SecretKey
        catch(java.security.InvalidKeyException ike) {System.out.println("La clave debe ser de 8 digitos");}
        catch(java.security.spec.InvalidKeySpecException ikse) {System.out.println("error pasar clave a clase secret key ikse");}
        catch(java.security.NoSuchAlgorithmException nsae) {System.out.println("error pasar clave a clase secret key nsae");}
    }
    
    
}