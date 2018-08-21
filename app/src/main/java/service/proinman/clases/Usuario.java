package service.proinman.clases;

import java.io.Serializable;
import java.util.List;

/**
 * Created by andre on 12/08/2018.
 */

public class Usuario implements Serializable {

    private Integer codigoUsuario;
    private String apellido;
    private String cedula;
    private String estado;
    private String nombre;
    private String password;
    private String username;
    private List<UsuarioRol> listaUsuarioRol;

    public Integer getCodigoUsuario() {
        return codigoUsuario;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public String getEstado() {
        return estado;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public List<UsuarioRol> getListaUsuarioRol() {
        return listaUsuarioRol;
    }
}
