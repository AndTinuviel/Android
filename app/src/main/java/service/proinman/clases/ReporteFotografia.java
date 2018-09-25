package service.proinman.clases;

import java.io.Serializable;

public class ReporteFotografia implements Serializable{

    private Integer codigoReporeteFotografia;

    private byte[] fotografia;

    private String nombre;

    private ReporteTrabajo reporteTrabajo;

    public Integer getCodigoReporeteFotografia() {
        return codigoReporeteFotografia;
    }

    public byte[] getFotografia() {
        return fotografia;
    }

    public String getNombre() {
        return nombre;
    }

    public ReporteTrabajo getReporteTrabajo() {
        return reporteTrabajo;
    }

    public void setCodigoReporeteFotografia(Integer codigoReporeteFotografia) {
        this.codigoReporeteFotografia = codigoReporeteFotografia;
    }

    public void setFotografia(byte[] fotografia) {
        this.fotografia = fotografia;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setReporteTrabajo(ReporteTrabajo reporteTrabajo) {
        this.reporteTrabajo = reporteTrabajo;
    }
}
