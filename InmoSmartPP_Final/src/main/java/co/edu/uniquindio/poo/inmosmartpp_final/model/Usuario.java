package co.edu.uniquindio.poo.inmosmartpp_final.model;

public abstract class Usuario {
    private String id;
    private String nombre;
    private String identificacion;
    private String telefono;
    private String correo;
    private TipoUsuario tipoUsuario;
    private int puntosReputacion;

    public Usuario(String id, String nombre, String identificacion,
                   String telefono, String correo, TipoUsuario tipoUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.telefono = telefono;
        this.correo = correo;
        this.tipoUsuario = tipoUsuario;
        this.puntosReputacion = 0;
    }

    public abstract double calcularBeneficio();

    public String getRango() {
        if (puntosReputacion <= 100) return "Principiante";
        else if (puntosReputacion <= 500) return "Inversionista";
        else if (puntosReputacion <= 2000) return "Experto Inmobiliario";
        else return "Magnate Inmobiliario";
    }

    public void agregarPuntos(int puntos) {
        this.puntosReputacion += puntos;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getIdentificacion() { return identificacion; }
    public void setIdentificacion(String identificacion) { this.identificacion = identificacion; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public TipoUsuario getTipoUsuario() { return tipoUsuario; }
    public void setTipoUsuario(TipoUsuario tipoUsuario) { this.tipoUsuario = tipoUsuario; }
    public int getPuntosReputacion() { return puntosReputacion; }
    public void setPuntosReputacion(int puntosReputacion) { this.puntosReputacion = puntosReputacion; }

    @Override
    public String toString() {
        return nombre + " (" + identificacion + ") - " + getRango();
    }
}
