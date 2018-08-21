package service.proinman.clases;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by andre on 12/08/2018.
 */

public class CotizacionItem implements Serializable{

    private Integer codigoItem;
    private BigDecimal cantidad;
    private BigDecimal costo;
    private CatalogoItem catalogoItem;
    private BigDecimal precio;
    private BigDecimal totalCostoItem;
    private BigDecimal totalPrecioItem;
    private Cotizacion cotizacion;

    public Integer getCodigoItem() {
        return codigoItem;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public CatalogoItem getCatalogoItem() {
        return catalogoItem;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public BigDecimal getTotalCostoItem() {
        return totalCostoItem;
    }

    public BigDecimal getTotalPrecioItem() {
        return totalPrecioItem;
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
    }
}
