package service.proinman.clases;

import java.io.Serializable;

/**
 * Created by andre on 12/08/2018.
 */

public class UsuarioRol implements Serializable{

    private Integer codigoUsuarioRol;
    private Rol rol;
    private Usuario usuario;
    private String estado;

    public Integer getCodigoUsuarioRol() {
        return codigoUsuarioRol;
    }

    public Rol getRol() {
        return rol;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getEstado() {
        return estado;
    }
}
