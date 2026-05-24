package co.edu.uniquindio.poo.inmosmartpp_final.model;

/**
 * Enumeración que representa los diferentes estados
 * en los que puede encontrarse un inmueble dentro del sistema.
 *
 * Los estados permiten controlar la disponibilidad
 * y situación actual de cada propiedad.
 *
 * Estados disponibles:
 *
 * DISPONIBLE -> El inmueble puede ser vendido o arrendado.
 *
 * VENDIDO -> El inmueble ya fue vendido y no está disponible.
 *
 * RESERVADO -> El inmueble tiene una oferta o reserva temporal.
 *
 * ARRENDADO -> El inmueble se encuentra alquilado actualmente.
 *
 * @author Maria Camila
 */
public enum EstadoInmueble {

    /**
     * El inmueble se encuentra disponible.
     */
    DISPONIBLE,

    /**
     * El inmueble ya fue vendido.
     */
    VENDIDO,

    /**
     * El inmueble se encuentra reservado temporalmente.
     */
    RESERVADO,

    /**
     * El inmueble se encuentra arrendado.
     */
    ARRENDADO
}