package co.edu.uniquindio.poo.inmosmartpp_final.model;

/**
 * Clase abstracta que representa un usuario dentro
 * del sistema inmobiliario.
 *
 * Esta clase sirve como base para los diferentes tipos
 * de usuarios del sistema, como:
 * - compradores,
 * - vendedores.
 *
 * Contiene información general de cada usuario:
 * - identificación,
 * - nombre,
 * - contacto,
 * - reputación,
 * - tipo de usuario.
 *
 * Además, define comportamientos comunes y métodos
 * que deben ser implementados por las clases hijas.
 *
 * @author Maria Camila
 */
public abstract class Usuario {

    /**
     * Identificador único interno del usuario.
     */
    private String id;

    /**
     * Nombre completo del usuario.
     */
    private String nombre;

    /**
     * Documento de identificación del usuario.
     */
    private String identificacion;

    /**
     * Número telefónico del usuario.
     */
    private String telefono;

    /**
     * Correo electrónico del usuario.
     */
    private String correo;

    /**
     * Tipo de usuario dentro del sistema.
     */
    private TipoUsuario tipoUsuario;

    /**
     * Puntos de reputación acumulados.
     */
    private int puntosReputacion;

    /**
     * Constructor de la clase Usuario.
     *
     * Inicializa la información básica del usuario
     * y establece la reputación inicial en 0 puntos.
     *
     * @param id identificador interno
     * @param nombre nombre del usuario
     * @param identificacion documento de identificación
     * @param telefono teléfono de contacto
     * @param correo correo electrónico
     * @param tipoUsuario tipo de usuario
     */
    public Usuario(String id,
                   String nombre,
                   String identificacion,
                   String telefono,
                   String correo,
                   TipoUsuario tipoUsuario) {

        this.id = id;
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.telefono = telefono;
        this.correo = correo;
        this.tipoUsuario = tipoUsuario;

        // Reputación inicial
        this.puntosReputacion = 0;
    }

    // =====================================================
    // MÉTODOS ABSTRACTOS
    // =====================================================

    /**
     * Método abstracto que permite calcular
     * el beneficio generado por el usuario.
     *
     * Cada tipo de usuario implementará
     * este cálculo de manera diferente.
     *
     * @return beneficio calculado
     */
    public abstract double calcularBeneficio();

    // =====================================================
    // MÉTODOS
    // =====================================================

    /**
     * Obtiene el rango del usuario según
     * sus puntos de reputación.
     *
     * Rangos:
     * - Principiante
     * - Inversionista
     * - Experto Inmobiliario
     * - Magnate Inmobiliario
     *
     * @return rango del usuario
     */
    public String getRango() {

        if (puntosReputacion <= 100) {

            return "Principiante";

        } else if (puntosReputacion <= 500) {

            return "Inversionista";

        } else if (puntosReputacion <= 2000) {

            return "Experto Inmobiliario";

        } else {

            return "Magnate Inmobiliario";
        }
    }

    /**
     * Agrega puntos de reputación al usuario.
     *
     * @param puntos cantidad de puntos a agregar
     */
    public void agregarPuntos(int puntos) {

        this.puntosReputacion += puntos;
    }

    // =====================================================
    // GETTERS Y SETTERS
    // =====================================================

    /**
     * Obtiene el identificador interno.
     *
     * @return id del usuario
     */
    public String getId() {
        return id;
    }

    /**
     * Modifica el identificador interno.
     *
     * @param id nuevo identificador
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre del usuario.
     *
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la identificación del usuario.
     *
     * @return identificación
     */
    public String getIdentificacion() {
        return identificacion;
    }

    /**
     * Modifica la identificación del usuario.
     *
     * @param identificacion nueva identificación
     */
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    /**
     * Obtiene el teléfono del usuario.
     *
     * @return teléfono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Modifica el teléfono del usuario.
     *
     * @param telefono nuevo teléfono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene el correo electrónico.
     *
     * @return correo electrónico
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Modifica el correo electrónico.
     *
     * @param correo nuevo correo
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Obtiene el tipo de usuario.
     *
     * @return tipo de usuario
     */
    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    /**
     * Modifica el tipo de usuario.
     *
     * @param tipoUsuario nuevo tipo
     */
    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    /**
     * Obtiene los puntos de reputación.
     *
     * @return puntos de reputación
     */
    public int getPuntosReputacion() {
        return puntosReputacion;
    }

    /**
     * Modifica los puntos de reputación.
     *
     * @param puntosReputacion nuevos puntos
     */
    public void setPuntosReputacion(int puntosReputacion) {
        this.puntosReputacion = puntosReputacion;
    }

    // =====================================================
    // MÉTODOS SOBRESCRITOS
    // =====================================================

    /**
     * Retorna una representación textual del usuario.
     *
     * @return información resumida del usuario
     */
    @Override
    public String toString() {

        return nombre
                + " ("
                + identificacion
                + ") - "
                + getRango();
    }
}