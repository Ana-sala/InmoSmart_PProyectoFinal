package co.edu.uniquindio.poo.inmosmartpp_final.model;

import java.util.ArrayList;
import java.util.List;

public class Comprador extends Usuario {
    private List<Oferta> ofertas;
    private List<Transaccion> transacciones;

    public Comprador(String id, String nombre, String identificacion,
                     String telefono, String correo) {
        super(id, nombre, identificacion, telefono, correo, TipoUsuario.COMPRADOR);
        this.ofertas = new ArrayList<>();
        this.transacciones = new ArrayList<>();
    }

    @Override
    public double calcularBeneficio() {
        return transacciones.stream()
                .mapToDouble(t -> t.getInmueble().getPrecio() - t.getValorFinal())
                .sum();
    }

    public List<Oferta> getOfertas() { return ofertas; }
    public void setOfertas(List<Oferta> ofertas) { this.ofertas = ofertas; }
    public List<Transaccion> getTransacciones() { return transacciones; }
    public void setTransacciones(List<Transaccion> transacciones) { this.transacciones = transacciones; }
}