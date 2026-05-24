package co.edu.uniquindio.poo.inmosmartpp_final.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un vendedor dentro del sistema inmobiliario.
 *
 * Un vendedor hereda de la clase Usuario y puede:
 * - registrar inmuebles,
 * - publicar propiedades,
 * - calcular beneficios obtenidos por ventas o arriendos.
 *
 * @author Maria Camila
 */
public class Vendedor extends Usuario {

    /**
     * Lista de inmuebles asociados al vendedor.
     */
    private List<Inmueble> inmuebles;

    /**
     * Constructor de la clase Vendedor.
     *
     * Inicializa la información básica del vendedor
     * y crea la lista de inmuebles vacía.
     *
     * @param id identificador interno del vendedor
     * @param nombre nombre del vendedor
     * @param identificacion documento de identificación
     * @param telefono número telefónico
     * @param correo correo electrónico
     */
    public Vendedor(String id,
                    String nombre,
                    String identificacion,
                    String telefono,
                    String correo) {

        // Llamado al constructor de la clase padre
        super(id,
                nombre,
                identificacion,
                telefono,
                correo,
                TipoUsuario.VENDEDOR);

        // Inicialización de la lista de inmuebles
        this.inmuebles = new ArrayList<>();
    }

    // =====================================================
    // MÉTODOS
    // =====================================================

    /**
     * Calcula el beneficio total generado por el vendedor.
     *
     * El beneficio se calcula sumando el precio
     * de los inmuebles que:
     * - fueron vendidos,
     * - o fueron arrendados.
     *
     * Utiliza programación funcional con streams.
     *
     * @return beneficio total del vendedor
     */
    @Override
    public double calcularBeneficio() {

        return inmuebles.stream()

                .filter(i ->
                        i.getEstado() == EstadoInmueble.VENDIDO

                                || i.getEstado() == EstadoInmueble.ARRENDADO)

                .mapToDouble(Inmueble::getPrecio)

                .sum();
    }

    // =====================================================
    // GETTERS Y SETTERS
    // =====================================================

    /**
     * Obtiene la lista de inmuebles del vendedor.
     *
     * @return lista de inmuebles
     */
    public List<Inmueble> getInmuebles() {
        return inmuebles;
    }

    /**
     * Modifica la lista de inmuebles del vendedor.
     *
     * @param inmuebles nueva lista de inmuebles
     */
    public void setInmuebles(List<Inmueble> inmuebles) {
        this.inmuebles = inmuebles;
    }
}
