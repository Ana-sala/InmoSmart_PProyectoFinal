package co.edu.uniquindio.poo.inmosmartpp_final.model;

/**
 * Enumeración que representa los posibles estados
 * de una oferta realizada dentro del sistema inmobiliario.
 *
 * Estos estados permiten llevar el control del proceso
 * de negociación entre comprador y vendedor.
 *
 * Estados disponibles:
 *
 * PENDIENTE -> La oferta fue creada y aún no ha sido revisada.
 *
 * ACEPTADA -> La oferta fue aprobada por el vendedor.
 *
 * RECHAZADA -> La oferta fue rechazada por el vendedor.
 *
 * @author Maria Camila
 */
public enum EstadoOferta {

    /**
     * La oferta está pendiente de revisión.
     */
    PENDIENTE,

    /**
     * La oferta fue aceptada.
     */
    ACEPTADA,

    /**
     * La oferta fue rechazada.
     */
    RECHAZADA
}