package co.edu.uniquindio.poo.inmosmartpp_final.model;

import java.time.LocalDate;

/**
 * Clase que representa una transacción inmobiliaria
 * realizada dentro del sistema.
 *
 * Una transacción almacena información relacionada con:
 * - comprador,
 * - vendedor,
 * - inmueble,
 * - valor final,
 * - tipo de operación,
 * - fecha de realización.
 *
 * Al crear una transacción:
 * - se genera automáticamente la fecha,
 * - se actualizan puntos de reputación,
 * - se modifica el estado del inmueble.
 *
 * @author Maria Camila
 */
public class Transaccion {

    /**
     * Código único de la transacción.
     */
    private String codigoTransaccion;

    /**
     * Comprador involucrado en la transacción.
     */
    private Comprador comprador;

    /**
     * Vendedor involucrado en la transacción.
     */
    private Vendedor vendedor;

    /**
     * Inmueble asociado a la transacción.
     */
    private Inmueble inmueble;

    /**
     * Valor final pagado en la operación.
     */
    private double valorFinal;

    /**
     * Tipo de operación realizada.
     */
    private TipoOperacion tipoOperacion;

    /**
     * Fecha en la que se realizó la transacción.
     */
    private LocalDate fecha;

    /**
     * Constructor de la clase Transaccion.
     *
     * Al crear una transacción:
     * - se registra automáticamente la fecha,
     * - el comprador gana puntos de reputación,
     * - el vendedor gana puntos de reputación,
     * - el estado del inmueble cambia según el tipo de operación.
     *
     * @param codigoTransaccion código único de la transacción
     * @param comprador comprador asociado
     * @param vendedor vendedor asociado
     * @param inmueble inmueble involucrado
     * @param valorFinal valor final pagado
     * @param tipoOperacion tipo de operación
     */
    public Transaccion(String codigoTransaccion,
                       Comprador comprador,
                       Vendedor vendedor,
                       Inmueble inmueble,
                       double valorFinal,
                       TipoOperacion tipoOperacion) {

        this.codigoTransaccion = codigoTransaccion;
        this.comprador = comprador;
        this.vendedor = vendedor;
        this.inmueble = inmueble;
        this.valorFinal = valorFinal;
        this.tipoOperacion = tipoOperacion;

        // Fecha automática
        this.fecha = LocalDate.now();

        // Puntos de reputación
        comprador.agregarPuntos(50);
        vendedor.agregarPuntos(100);

        // Cambio de estado del inmueble
        if (tipoOperacion == TipoOperacion.VENTA) {

            inmueble.setEstado(EstadoInmueble.VENDIDO);

        } else {

            inmueble.setEstado(EstadoInmueble.ARRENDADO);
        }
    }

    // =====================================================
    // GETTERS Y SETTERS
    // =====================================================

    /**
     * Obtiene el código de la transacción.
     *
     * @return código de transacción
     */
    public String getCodigoTransaccion() {
        return codigoTransaccion;
    }

    /**
     * Modifica el código de la transacción.
     *
     * @param c nuevo código
     */
    public void setCodigoTransaccion(String c) {
        this.codigoTransaccion = c;
    }

    /**
     * Obtiene el comprador asociado.
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
     * Obtiene el vendedor asociado.
     *
     * @return vendedor
     */
    public Vendedor getVendedor() {
        return vendedor;
    }

    /**
     * Modifica el vendedor asociado.
     *
     * @param vendedor nuevo vendedor
     */
    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    /**
     * Obtiene el inmueble asociado.
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
     * Obtiene el valor final de la transacción.
     *
     * @return valor final
     */
    public double getValorFinal() {
        return valorFinal;
    }

    /**
     * Modifica el valor final.
     *
     * @param valorFinal nuevo valor
     */
    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }

    /**
     * Obtiene el tipo de operación.
     *
     * @return tipo de operación
     */
    public TipoOperacion getTipoOperacion() {
        return tipoOperacion;
    }

    /**
     * Modifica el tipo de operación.
     *
     * @param t nuevo tipo de operación
     */
    public void setTipoOperacion(TipoOperacion t) {
        this.tipoOperacion = t;
    }

    /**
     * Obtiene la fecha de la transacción.
     *
     * @return fecha de realización
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Modifica la fecha de la transacción.
     *
     * @param fecha nueva fecha
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    // =====================================================
    // MÉTODOS
    // =====================================================

    /**
     * Retorna una representación textual de la transacción.
     *
     * @return información resumida de la transacción
     */
    @Override
    public String toString() {

        return codigoTransaccion
                + " | "
                + tipoOperacion
                + " | "
                + inmueble.getCodigo()
                + " - $"
                + valorFinal;
    }
}
