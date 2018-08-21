package service.proinman.clases;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by andre on 12/08/2018.
 */

public class CatalogoItem implements Serializable{

    private Integer codigoItem;
    private String descripcion;
    private String estado;
    private Date fechaRegistro;
    private String material;
    private String tipoItem;

    public Integer getCodigoItem() {
        return codigoItem;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public String getMaterial() {
        return material;
    }

    public String getTipoItem() {
        return tipoItem;
    }
}
