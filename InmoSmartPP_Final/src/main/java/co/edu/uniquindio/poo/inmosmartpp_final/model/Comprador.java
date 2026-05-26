package co.edu.uniquindio.poo.inmosmartpp_final.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un comprador dentro del sistema.
 *
 * Un comprador hereda de Usuario y puede:
 * - realizar ofertas,
 * - realizar transacciones,
 * - calcular el beneficio obtenido en sus compras.
 *
 * @author Maria Camila
 */
public class Comprador extends Usuario {

    /**
     * Lista de ofertas realizadas por el comprador.
     */
    private List<Oferta> ofertas;

    /**
     * Lista de transacciones realizadas por el comprador.
     */
    private List<Transaccion> transacciones;

    /**
     * Constructor de la clase Comprador.
     *
     * @param id Identificador único del comprador
     * @param nombre Nombre del comprador
     * @param identificacion Documento de identificación
     * @param telefono Número de teléfono
     * @param correo Correo electrónico
     */
    public Comprador(String id,
                     String nombre,
                     String identificacion,
                     String telefono,
                     String correo) {

        // Llamado al constructor de la clase padre Usuario
        super(id,
                nombre,
                identificacion,
                telefono,
                correo,
                TipoUsuario.COMPRADOR);

        // Inicialización de listas
        this.ofertas = new ArrayList<>();
        this.transacciones = new ArrayList<>();
    }

    // =====================================================
    // MÉTODOS
    // =====================================================

    /**
     * Calcula el beneficio total obtenido por el comprador.
     *
     * El beneficio se calcula como:
     * precio original del inmueble - valor final pagado.
     *
     * @return beneficio total acumulado
     */
    @Override
    public double calcularBeneficio() {

        return transacciones.stream() // Recorre todas las transacciones

                // Calcula el beneficio de cada transacción:
                .mapToDouble(t ->  // Convierte la lista en un flujo de datos
                        t.getInmueble().getPrecio() - t.getValorFinal()) // precio del inmueble - valor final de venta

                .sum(); // Suma todos los beneficios obtenidos
    }

    /**
     * Agrega una oferta a la lista de ofertas.
     *
     * @param oferta oferta a agregar
     */
    public void agregarOferta(Oferta oferta) {

        ofertas.add(oferta);
    }

    /**
     * Agrega una transacción a la lista de transacciones.
     *
     * @param transaccion transacción a agregar
     */
    public void agregarTransaccion(Transaccion transaccion) {

        transacciones.add(transaccion);
    }

    // =====================================================
    // GETTERS Y SETTERS
    // =====================================================

    /**
     * Obtiene la lista de ofertas del comprador.
     *
     * @return lista de ofertas
     */
    public List<Oferta> getOfertas() {
        return ofertas;
    }

    /**
     * Modifica la lista de ofertas.
     *
     * @param ofertas nueva lista de ofertas
     */
    public void setOfertas(List<Oferta> ofertas) {
        this.ofertas = ofertas;
    }

    /**
     * Obtiene la lista de transacciones.
     *
     * @return lista de transacciones
     */
    public List<Transaccion> getTransacciones() {
        return transacciones;
    }

    /**
     * Modifica la lista de transacciones.
     *
     * @param transacciones nueva lista de transacciones
     */
    public void setTransacciones(List<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }
}