package service.proinman.clases;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by andre on 12/08/2018.
 */

public class ListaTarea implements Serializable{

    private Integer codigoTarea;
    private String estado;
    private Date fechaAsignacion;
    private Date fechaFinalizacion;
    private Date fechaVencimiento;
    private MotorActividad motorActividad;
    private Solicitud solicitud;
    private Usuario usuario;

    public Integer getCodigoTarea() {
        return codigoTarea;
    }

    public String getEstado() {
        return estado;
    }

    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public MotorActividad getMotorActividad() {
        return motorActividad;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
