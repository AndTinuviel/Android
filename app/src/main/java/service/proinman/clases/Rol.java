package service.proinman.clases;

import java.io.Serializable;

/**
 * Created by andre on 12/08/2018.
 */

public class Rol implements Serializable{

    private Integer codigoRol;
    private String estado;
    private String nombre;

    public Integer getCodigoRol() {
        return codigoRol;
    }

    public String getEstado() {
        return estado;
    }

    public String getNombre() {
        return nombre;
    }
}
