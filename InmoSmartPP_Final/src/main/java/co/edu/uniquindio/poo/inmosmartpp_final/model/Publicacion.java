package co.edu.uniquindio.poo.inmosmartpp_final.model;

import java.time.LocalDate;

/**
 * Clase que representa una publicación de un inmueble
 * dentro del sistema inmobiliario.
 *
 * Una publicación contiene información relacionada con:
 * - código de publicación,
 * - fecha de publicación,
 * - descripción,
 * - inmueble asociado.
 *
 * La fecha de publicación se genera automáticamente
 * al crear la publicación.
 *
 * @author Maria Camila
 */
public class Publicacion {

    /**
     * Código único de la publicación.
     */
    private String codigoPublicacion;

    /**
     * Fecha en la que fue creada la publicación.
     */
    private LocalDate fechaPublicacion;

    /**
     * Descripción de la publicación.
     */
    private String descripcion;

    /**
     * Inmueble asociado a la publicación.
     */
    private Inmueble inmueble;

    /**
     * Constructor de la clase Publicacion.
     *
     * La fecha se asigna automáticamente con la fecha actual.
     *
     * @param codigoPublicacion código único de la publicación
     * @param descripcion descripción de la publicación
     * @param inmueble inmueble asociado
     */
    public Publicacion(String codigoPublicacion,
                       String descripcion,
                       Inmueble inmueble) {

        this.codigoPublicacion = codigoPublicacion;
        this.descripcion = descripcion;
        this.inmueble = inmueble;

        // Fecha automática de creación
        this.fechaPublicacion = LocalDate.now();
    }

    // =====================================================
    // GETTERS Y SETTERS
    // =====================================================

    /**
     * Obtiene el código de la publicación.
     *
     * @return código de publicación
     */
    public String getCodigoPublicacion() {
        return codigoPublicacion;
    }

    /**
     * Modifica el código de la publicación.
     *
     * @param codigoPublicacion nuevo código
     */
    public void setCodigoPublicacion(String codigoPublicacion) {
        this.codigoPublicacion = codigoPublicacion;
    }

    /**
     * Obtiene la fecha de publicación.
     *
     * @return fecha de publicación
     */
    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    /**
     * Modifica la fecha de publicación.
     *
     * @param fechaPublicacion nueva fecha
     */
    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    /**
     * Obtiene la descripción de la publicación.
     *
     * @return descripción
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Modifica la descripción de la publicación.
     *
     * @param descripcion nueva descripción
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el inmueble asociado.
     *
     * @return inmueble asociado
     */
    public Inmueble getInmueble() {
        return inmueble;
    }

    /**
     * Modifica el inmueble asociado.
     *
     * @param inmueble nuevo inmueble
     */
    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    // =====================================================
    // MÉTODOS
    // =====================================================

    /**
     * Retorna una representación textual de la publicación.
     *
     * @return información resumida de la publicación
     */
    @Override
    public String toString() {

        return codigoPublicacion
                + " - "
                + inmueble.getCodigo()
                + " | "
                + fechaPublicacion;
    }
}