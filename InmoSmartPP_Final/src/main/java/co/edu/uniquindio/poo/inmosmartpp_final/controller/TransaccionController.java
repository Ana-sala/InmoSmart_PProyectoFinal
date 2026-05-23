package co.edu.uniquindio.poo.inmosmartpp_final.controller;

import co.edu.uniquindio.poo.inmosmartpp_final.model.*;
import java.util.List;

public class TransaccionController {
    private InmoSmart inmoSmart;

    public TransaccionController(InmoSmart inmoSmart) {
        this.inmoSmart = inmoSmart;
    }

    public List<Transaccion> getTransacciones() { return inmoSmart.getTransacciones(); }
    public List<Inmueble> getInmueblesMasVendidos() { return inmoSmart.inmueblesMasVendidos(); }
    public List<Comprador> getCompradoresMasActivos() { return inmoSmart.compradoresMasActivos(); }
    public List<Vendedor> getVendedoresConMasPropiedades() { return inmoSmart.vendedoresConMasPropiedades(); }
    public String getCiudadConMayorDemanda() { return inmoSmart.ciudadConMayorDemanda(); }
}
