package co.edu.uniquindio.poo.inmosmartpp_final.model;

import java.time.LocalDate;

/**
 * Clase que representa una oferta realizada por un comprador
 * sobre un inmueble dentro del sistema inmobiliario.
 *
 * Una oferta contiene información relacionada con:
 * - código de la oferta,
 * - comprador,
 * - inmueble,
 * - valor ofertado,
 * - fecha,
 * - estado de la oferta.
 *
 * Al crear una oferta:
 * - la fecha se asigna automáticamente,
 * - el estado inicia como PENDIENTE,
 * - el comprador gana puntos de reputación.
 *
 * @author Maria Camila
 */
public class Oferta {

    /**
     * Código único de la oferta.
     */
    private String codigoOferta;

    /**
     * Comprador que realiza la oferta.
     */
    private Comprador comprador;

    /**
     * Inmueble asociado a la oferta.
     */
    private Inmueble inmueble;

    /**
     * Valor económico ofertado por el comprador.
     */
    private double valorOferta;

    /**
     * Fecha en la que se realizó la oferta.
     */
    private LocalDate fechaOferta;

    /**
     * Estado actual de la oferta.
     */
    private EstadoOferta estado;

    /**
     * Constructor de la clase Oferta.
     *
     * Al crear la oferta:
     * - la fecha se establece automáticamente,
     * - el estado inicia como PENDIENTE,
     * - el comprador recibe puntos de reputación.
     *
     * @param codigoOferta código único de la oferta
     * @param comprador comprador que realiza la oferta
     * @param inmueble inmueble ofertado
     * @param valorOferta valor ofrecido
     */
    public Oferta(String codigoOferta,
                  Comprador comprador,
                  Inmueble inmueble,
                  double valorOferta) {

        this.codigoOferta = codigoOferta;
        this.comprador = comprador;
        this.inmueble = inmueble;
        this.valorOferta = valorOferta;

        // Fecha automática
        this.fechaOferta = LocalDate.now();

        // Estado inicial
        this.estado = EstadoOferta.PENDIENTE;

        // Recompensa al comprador
        comprador.agregarPuntos(5);
    }

    // =====================================================
    // GETTERS Y SETTERS
    // =====================================================

    /**
     * Obtiene el código de la oferta.
     *
     * @return código de la oferta
     */
    public String getCodigoOferta() {
        return codigoOferta;
    }

    /**
     * Modifica el código de la oferta.
     *
     * @param codigoOferta nuevo código
     */
    public void setCodigoOferta(String codigoOferta) {
        this.codigoOferta = codigoOferta;
    }

    /**
     * Obtiene el comprador de la oferta.
     *
     * @return comprador
     */
    public Comprador getComprador() {
        return comprador;
    }

    /**
     * Modifica el comprador asociado.
     *
     * @param comprador nuevo comprador
     */
    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    /**
     * Obtiene el inmueble ofertado.
     *
     * @return inmueble
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

    /**
     * Obtiene el valor de la oferta.
     *
     * @return valor ofertado
     */
    public double getValorOferta() {
        return valorOferta;
    }

    /**
     * Modifica el valor de la oferta.
     *
     * @param valorOferta nuevo valor
     */
    public void setValorOferta(double valorOferta) {
        this.valorOferta = valorOferta;
    }

    /**
     * Obtiene la fecha de la oferta.
     *
     * @return fecha de realización
     */
    public LocalDate getFechaOferta() {
        return fechaOferta;
    }

    /**
     * Modifica la fecha de la oferta.
     *
     * @param fechaOferta nueva fecha
     */
    public void setFechaOferta(LocalDate fechaOferta) {
        this.fechaOferta = fechaOferta;
    }

    /**
     * Obtiene el estado actual de la oferta.
     *
     * @return estado de la oferta
     */
    public EstadoOferta getEstado() {
        return estado;
    }

    /**
     * Modifica el estado de la oferta.
     *
     * @param estado nuevo estado
     */
    public void setEstado(EstadoOferta estado) {
        this.estado = estado;
    }

    // =====================================================
    // MÉTODOS
    // =====================================================

    /**
     * Retorna una representación textual de la oferta.
     *
     * @return información resumida de la oferta
     */
    @Override
    public String toString() {

        return codigoOferta
                + " - "
                + comprador.getNombre()
                + " oferta $"
                + valorOferta
                + " ["
                + estado
                + "]";
    }
}