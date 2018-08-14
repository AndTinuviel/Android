package service.proinman.clases;

/**
 * Created by andre on 12/08/2018.
 */

public class MotorActividad {

    private Integer codigoActividad;
    private String direccionPagina;
    private String estado;
    private String nombre;
    private Integer numeroDiasPorVencer;
    private Integer numeroDiasVencimiento;

    public Integer getCodigoActividad() {
        return codigoActividad;
    }

    public String getDireccionPagina() {
        return direccionPagina;
    }

    public String getEstado() {
        return estado;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getNumeroDiasPorVencer() {
        return numeroDiasPorVencer;
    }

    public Integer getNumeroDiasVencimiento() {
        return numeroDiasVencimiento;
    }
}
