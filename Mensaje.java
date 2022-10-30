import java.io.File;
import java.io.Serializable;


public class Mensaje implements Serializable {
    
    private String Mensaje;
    private String IdKeyPublic;
    private boolean Firmado;

    public Mensaje(){

    }
    
    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String Mensaje) {
        this.Mensaje = Mensaje;
    }

    public String getIdKeyPublic() {
        return IdKeyPublic;
    }

    public void setIdKeyPublic(String IdKeyPublic) {
        this.IdKeyPublic = IdKeyPublic;
    }

    public boolean getFirmado() {
        return Firmado;
    }

    public void setFirmado(boolean Firmado) {
        this.Firmado = Firmado;
    }
}
