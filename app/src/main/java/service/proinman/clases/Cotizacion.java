package service.proinman.clases;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by andre on 12/08/2018.
 */

public class Cotizacion {

    private Integer codigoCotizacion;
    private BigDecimal costoTotal;
    private BigDecimal precioTotal;
    private BigDecimal precioTotalIva;
    private BigDecimal iva;
    private Solicitud solicitud;
    private List<CotizacionItem> listaCotizacionItems;


}
