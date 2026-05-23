package co.edu.uniquindio.poo.inmosmartpp_final.controller;

import co.edu.uniquindio.poo.inmosmartpp_final.model.*;
import java.util.List;

public class InmuebleController {
    private InmoSmart inmoSmart;

    public InmuebleController(InmoSmart inmoSmart) {
        this.inmoSmart = inmoSmart;
    }

    public boolean publicarInmueble(String codigo, TipoInmueble tipo, String direccion,
                                    String ciudad, double area, double precio,
                                    Vendedor vendedor, String descripcion) {
        if (codigo.isEmpty() || direccion.isEmpty() || ciudad.isEmpty()) return false;
        if (precio <= 0 || area <= 0 || vendedor == null) return false;
        Inmueble inmueble = new Inmueble(codigo, tipo, direccion, ciudad, area, precio, vendedor);
        return inmoSmart.publicarInmueble(inmueble, descripcion);
    }

    public List<Inmueble> buscarInmuebles(String ciudad, TipoInmueble tipo,
                                          double precioMin, double precioMax, double areaMin) {
        return inmoSmart.buscarInmuebles(ciudad, tipo, precioMin, precioMax, areaMin);
    }

    public List<Inmueble> getTodosInmuebles() { return inmoSmart.getInmuebles(); }
    public List<Publicacion> getPublicaciones() { return inmoSmart.getPublicaciones(); }
}
