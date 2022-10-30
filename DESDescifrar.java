import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DESDescifrar {
    public static String descifrar(byte[] bufferCifrado, String clave) throws Exception
{
    SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
    DESKeySpec kspec = new DESKeySpec(clave.getBytes());
    SecretKey ks = skf.generateSecret(kspec);

    Cipher cifrado = Cipher.getInstance("DES");
    cifrado.init(Cipher.DECRYPT_MODE, ks);

    String s1 = null;
    String s2 = "";

    try {
        s1 = new String(cifrado.update(bufferCifrado), "ISO-8859-1");
        s2 = new String(cifrado.doFinal(), "ISO-8859-1");
        }
    catch (Exception e) {
        System.err.println("Excepcion controlada descifrando: " + e.toString());
    }

    return s1 + s2;
}
}
