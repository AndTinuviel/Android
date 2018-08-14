package service.proinman.clases;

import java.util.Date;
import java.util.List;

/**
 * Created by andre on 12/08/2018.
 */

public class Solicitud {


    private Integer codigoSolicitud;
    private String descipcion;
    private String direccion;
    private Date fechaRecepcion;
    private Date fechaRegistro;
    private List<Cotizacion> listaCotizaciones;
    private Cliente cliente;
    private UbicacionGeografica ciudad;
    private Usuario usuario;
    private String requiereCotizacion;

    public Integer getCodigoSolicitud() {
        return codigoSolicitud;
    }

    public String getDescipcion() {
        return descipcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public List<Cotizacion> getListaCotizaciones() {
        return listaCotizaciones;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public UbicacionGeografica getCiudad() {
        return ciudad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getRequiereCotizacion() {
        return requiereCotizacion;
    }
}
