package co.edu.uniquindio.poo.inmosmartpp_final.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InmoSmart {
    private String nombre;
    private List<Usuario> usuarios;
    private List<Inmueble> inmuebles;
    private List<Publicacion> publicaciones;
    private List<Oferta> ofertas;
    private List<Transaccion> transacciones;

    public InmoSmart(String nombre) {
        this.nombre = nombre;
        this.usuarios = new ArrayList<>();
        this.inmuebles = new ArrayList<>();
        this.publicaciones = new ArrayList<>();
        this.ofertas = new ArrayList<>();
        this.transacciones = new ArrayList<>();
    }

    // ---- Usuarios ----
    public boolean registrarUsuario(Usuario usuario) {
        boolean existe = usuarios.stream()
                .anyMatch(u -> u.getIdentificacion().equals(usuario.getIdentificacion()));
        if (!existe) { usuarios.add(usuario); return true; }
        return false;
    }

    public boolean eliminarUsuario(String identificacion) {
        return usuarios.removeIf(u -> u.getIdentificacion().equals(identificacion));
    }

    public Usuario buscarUsuario(String identificacion) {
        return usuarios.stream()
                .filter(u -> u.getIdentificacion().equals(identificacion))
                .findFirst().orElse(null);
    }

    public List<Comprador> getCompradores() {
        return usuarios.stream()
                .filter(u -> u instanceof Comprador)
                .map(u -> (Comprador) u)
                .collect(Collectors.toList());
    }

    public List<Vendedor> getVendedores() {
        return usuarios.stream()
                .filter(u -> u instanceof Vendedor)
                .map(u -> (Vendedor) u)
                .collect(Collectors.toList());
    }

    // ---- Inmuebles ----
    public boolean publicarInmueble(Inmueble inmueble, String descripcion) {
        boolean existe = inmuebles.stream()
                .anyMatch(i -> i.getCodigo().equals(inmueble.getCodigo()));
        if (!existe) {
            inmuebles.add(inmueble);
            inmueble.getVendedor().getInmuebles().add(inmueble);
            inmueble.getVendedor().agregarPuntos(10);
            String codPub = "PUB-" + (publicaciones.size() + 1);
            publicaciones.add(new Publicacion(codPub, descripcion, inmueble));
            return true;
        }
        return false;
    }

    public List<Inmueble> buscarInmuebles(String ciudad, TipoInmueble tipo,
                                          double precioMin, double precioMax, double areaMin) {
        return inmuebles.stream()
                .filter(i -> i.getEstado() == EstadoInmueble.DISPONIBLE)
                .filter(i -> ciudad == null || ciudad.isEmpty() || i.getCiudad().equalsIgnoreCase(ciudad))
                .filter(i -> tipo == null || i.getTipo() == tipo)
                .filter(i -> i.getPrecio() >= precioMin && i.getPrecio() <= precioMax)
                .filter(i -> i.getArea() >= areaMin)
                .collect(Collectors.toList());
    }

    // ---- Ofertas ----
    public boolean realizarOferta(Oferta oferta) {
        if (oferta.getInmueble().getEstado() != EstadoInmueble.DISPONIBLE) return false;
        if (oferta.getValorOferta() <= 0) return false;
        ofertas.add(oferta);
        oferta.getComprador().getOfertas().add(oferta);
        return true;
    }

    public boolean responderOferta(String codigoOferta, boolean aceptar, TipoOperacion tipoOp) {
        Oferta oferta = ofertas.stream()
                .filter(o -> o.getCodigoOferta().equals(codigoOferta))
                .findFirst().orElse(null);
        if (oferta == null) return false;
        if (aceptar) {
            oferta.setEstado(EstadoOferta.ACEPTADA);
            String codTx = "TX-" + (transacciones.size() + 1);
            Transaccion tx = new Transaccion(codTx, oferta.getComprador(),
                    oferta.getInmueble().getVendedor(),
                    oferta.getInmueble(), oferta.getValorOferta(), tipoOp);
            transacciones.add(tx);
            oferta.getComprador().getTransacciones().add(tx);
        } else {
            oferta.setEstado(EstadoOferta.RECHAZADA);
        }
        return true;
    }

    // ---- Reportes ----
    public List<Inmueble> inmueblesMasVendidos() {
        return inmuebles.stream()
                .filter(i -> i.getEstado() == EstadoInmueble.VENDIDO || i.getEstado() == EstadoInmueble.ARRENDADO)
                .collect(Collectors.toList());
    }

    public List<Comprador> compradoresMasActivos() {
        return getCompradores().stream()
                .sorted((a, b) -> b.getPuntosReputacion() - a.getPuntosReputacion())
                .collect(Collectors.toList());
    }

    public List<Vendedor> vendedoresConMasPropiedades() {
        return getVendedores().stream()
                .sorted((a, b) -> b.getInmuebles().size() - a.getInmuebles().size())
                .collect(Collectors.toList());
    }

    public String ciudadConMayorDemanda() {
        return transacciones.stream()
                .collect(Collectors.groupingBy(t -> t.getInmueble().getCiudad(), Collectors.counting()))
                .entrySet().stream()
                .max(java.util.Map.Entry.comparingByValue())
                .map(java.util.Map.Entry::getKey)
                .orElse("Sin datos");
    }

    // ---- Getters y setters ----
    public String getNombre() { return nombre; }
    public List<Usuario> getUsuarios() { return usuarios; }
    public List<Inmueble> getInmuebles() { return inmuebles; }
    public List<Publicacion> getPublicaciones() { return publicaciones; }
    public List<Oferta> getOfertas() { return ofertas; }
    public List<Transaccion> getTransacciones() { return transacciones; }
}
