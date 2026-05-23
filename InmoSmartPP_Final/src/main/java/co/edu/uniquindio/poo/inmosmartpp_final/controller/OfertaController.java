package co.edu.uniquindio.poo.inmosmartpp_final.controller;

import co.edu.uniquindio.poo.inmosmartpp_final.model.*;
import java.util.List;

public class OfertaController {
    private InmoSmart inmoSmart;

    public OfertaController(InmoSmart inmoSmart) {
        this.inmoSmart = inmoSmart;
    }

    public boolean realizarOferta(String codigo, Comprador comprador,
                                  Inmueble inmueble, double valor) {
        if (comprador == null || inmueble == null || valor <= 0) return false;
        Oferta oferta = new Oferta(codigo, comprador, inmueble, valor);
        return inmoSmart.realizarOferta(oferta);
    }

    public boolean aceptarOferta(String codigoOferta, TipoOperacion tipoOp) {
        return inmoSmart.responderOferta(codigoOferta, true, tipoOp);
    }

    public boolean rechazarOferta(String codigoOferta) {
        return inmoSmart.responderOferta(codigoOferta, false, null);
    }

    public List<Oferta> getOfertas() { return inmoSmart.getOfertas(); }
}
