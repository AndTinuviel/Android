package service.proinman.clases;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ReporteTrabajo implements Serializable{

    private Integer codigoReporteTrabajo;

    private String calificacionTrabajo;

    private String comentario;

    private String correo;

    private Date fechaHoraDesde;

    private Date fechaHoraHasta;

    private byte[] firma;

    private String identificacion;

    private String nombreRecibeTrabajo;

    private String observacion;

    private List<ReporteFotografia> fotografias;

    private Solicitud solicitud;

    public Integer getCodigoReporteTrabajo() {
        return codigoReporteTrabajo;
    }

    public String getCalificacionTrabajo() {
        return calificacionTrabajo;
    }

    public String getComentario() {
        return comentario;
    }

    public String getCorreo() {
        return correo;
    }

    public Date getFechaHoraDesde() {
        return fechaHoraDesde;
    }

    public Date getFechaHoraHasta() {
        return fechaHoraHasta;
    }

    public byte[] getFirma() {
        return firma;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getNombreRecibeTrabajo() {
        return nombreRecibeTrabajo;
    }

    public String getObservacion() {
        return observacion;
    }

    public List<ReporteFotografia> getFotografias() {
        return fotografias;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setCodigoReporteTrabajo(Integer codigoReporteTrabajo) {
        this.codigoReporteTrabajo = codigoReporteTrabajo;
    }

    public void setCalificacionTrabajo(String calificacionTrabajo) {
        this.calificacionTrabajo = calificacionTrabajo;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setFechaHoraDesde(Date fechaHoraDesde) {
        this.fechaHoraDesde = fechaHoraDesde;
    }

    public void setFechaHoraHasta(Date fechaHoraHasta) {
        this.fechaHoraHasta = fechaHoraHasta;
    }

    public void setFirma(byte[] firma) {
        this.firma = firma;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public void setNombreRecibeTrabajo(String nombreRecibeTrabajo) {
        this.nombreRecibeTrabajo = nombreRecibeTrabajo;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public void setFotografias(List<ReporteFotografia> fotografias) {
        this.fotografias = fotografias;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }
}
