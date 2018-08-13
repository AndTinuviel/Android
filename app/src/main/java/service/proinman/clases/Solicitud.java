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

}
