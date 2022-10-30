import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;


public class DESCifrar {
    public static byte[] cifrar(String txt, String clave) throws Exception
{
    SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
    DESKeySpec kspec = new DESKeySpec(clave.getBytes());
    SecretKey ks = skf.generateSecret(kspec);

    Cipher cifrado = Cipher.getInstance("DES");
    cifrado.init(Cipher.ENCRYPT_MODE, ks);

    String s1 = null;
    String s2 = "";

    try {
        s1 = new String(cifrado.update(txt.getBytes()), "ISO-8859-1");
        s2 = new String(cifrado.doFinal(), "ISO-8859-1");
        }   
    catch (Exception e) {
        System.out.println(e);
        System.err.println("Excepcion controlada cifrando: " + e.toString());
    }

    return (s1+s2).getBytes("ISO-8859-1");
}
}