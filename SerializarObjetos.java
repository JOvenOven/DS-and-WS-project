import java.io.File;
import java.util.ArrayList;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.lang.NullPointerException;


public class SerializarObjetos {
    /*private ArrayList<Usuario> user;
    private ArrayList<Perro> perro;
    private ArrayList<Gato> gato;
    private ArrayList<Venta> venta;*/
    private ArrayList<Mensaje> mensaje;
    
    //Ejemplo
    /*
    public void serializarArrayVenta(File fichero, ArrayList Venta){
        try{
            ObjectOutputStream seriarObj= new ObjectOutputStream(new FileOutputStream(fichero));
            seriarObj.writeObject(Venta);
            seriarObj.close();
        }catch(Exception e){
            System.out.println("Error al Serializar");
        }
    }

    
    public ArrayList<Venta> deserializarArrayVenta(File fichero){
        try{
                ObjectInputStream deseriarObj= new ObjectInputStream(new FileInputStream(fichero));
                venta=(ArrayList<Venta>)deseriarObj.readObject();
        }catch(Exception e){
            System.out.println("Error al Deserializar");
        }
        return venta;
    }
    */

    
    public void serializarArrayMensaje(File fichero, ArrayList Mensaje){
        try{
            ObjectOutputStream seriarObj= new ObjectOutputStream(new FileOutputStream(fichero));
            seriarObj.writeObject(Mensaje);
            seriarObj.close();
        }catch(Exception e){
            System.out.println("Error al Serializar");
        }
    }
    
    public ArrayList<Mensaje> deserializarArrayMensaje(File fichero){
        try{
                ObjectInputStream deseriarObj= new ObjectInputStream(new FileInputStream(fichero));
                mensaje=(ArrayList<Mensaje>)deseriarObj.readObject();
        }catch(Exception e){
            System.out.println("Error al Deserializar");
        }
        return mensaje;
    }
}
