package co.edu.uniquindio.poo.inmosmartpp_final.model;

import java.util.ArrayList;
import java.util.List;

public class Vendedor extends Usuario {
    private List<Inmueble> inmuebles;

    public Vendedor(String id, String nombre, String identificacion,
                    String telefono, String correo) {
        super(id, nombre, identificacion, telefono, correo, TipoUsuario.VENDEDOR);
        this.inmuebles = new ArrayList<>();
    }

    @Override
    public double calcularBeneficio() {
        return inmuebles.stream()
                .filter(i -> i.getEstado() == EstadoInmueble.VENDIDO || i.getEstado() == EstadoInmueble.ARRENDADO)
                .mapToDouble(Inmueble::getPrecio)
                .sum();
    }

    public List<Inmueble> getInmuebles() { return inmuebles; }
    public void setInmuebles(List<Inmueble> inmuebles) { this.inmuebles = inmuebles; }
}
