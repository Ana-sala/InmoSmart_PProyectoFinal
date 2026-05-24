package co.edu.uniquindio.poo.inmosmartpp_final.model;

/**
 * Enumeración que representa los tipos de usuarios
 * registrados dentro del sistema inmobiliario.
 *
 * Los usuarios pueden clasificarse según el rol
 * que desempeñan en la plataforma.
 *
 * Tipos disponibles:
 *
 * COMPRADOR -> Usuario interesado en realizar ofertas
 * y adquirir o arrendar inmuebles.
 *
 * VENDEDOR -> Usuario encargado de publicar y ofrecer
 * inmuebles dentro del sistema.
 *
 * @author Maria Camila
 */
public enum TipoUsuario {

    /**
     * Usuario comprador.
     */
    COMPRADOR,

    /**
     * Usuario vendedor.
     */
    VENDEDOR
}