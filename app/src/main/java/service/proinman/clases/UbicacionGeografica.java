package service.proinman.clases;

import java.io.Serializable;

/**
 * Created by andre on 11/08/2018.
 */

public class UbicacionGeografica implements Serializable{

    private Integer codigoUbicacionGeografica;
    private String descripcion;
    private String estado;
    private Integer nivel;

    public Integer getCodigoUbicacionGeografica() {
        return codigoUbicacionGeografica;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public Integer getNivel() {
        return nivel;
    }
}
