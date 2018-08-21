package service.proinman.clases;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by andre on 12/08/2018.
 */

public class Cotizacion implements Serializable{

    private Integer codigoCotizacion;
    private BigDecimal costoTotal;
    private BigDecimal precioTotal;
    private BigDecimal precioTotalIva;
    private BigDecimal iva;
    private Solicitud solicitud;
    private List<CotizacionItem> listaCotizacionItems;

    public Integer getCodigoCotizacion() {
        return codigoCotizacion;
    }

    public BigDecimal getCostoTotal() {
        return costoTotal;
    }

    public BigDecimal getPrecioTotal() {
        return precioTotal;
    }

    public BigDecimal getPrecioTotalIva() {
        return precioTotalIva;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public List<CotizacionItem> getListaCotizacionItems() {
        return listaCotizacionItems;
    }
}
