package co.edu.uniquindio.poo.inmosmartpp_final.model;

/**
 * Interfaz que define las operaciones principales
 * del sistema inmobiliario.
 *
 * Esta interfaz establece los métodos que deben
 * implementar las clases encargadas de gestionar
 * procesos inmobiliarios como:
 * - publicación de inmuebles,
 * - realización de ofertas,
 * - compra o arriendo de propiedades,
 * - generación de reportes.
 *
 * Las interfaces permiten aplicar:
 * - abstracción,
 * - polimorfismo,
 * - desacoplamiento en el sistema.
 *
 * @author Maria Camila
 */
public interface OperacionesInmobiliarias {

    /**
     * Publica un inmueble dentro del sistema.
     *
     * @param inmueble inmueble a publicar
     * @param descripcion descripción de la publicación
     * @return true si la publicación fue exitosa
     */
    boolean publicarInmueble(Inmueble inmueble,
                             String descripcion);

    /**
     * Permite realizar una oferta sobre un inmueble.
     *
     * @param oferta oferta realizada
     * @return true si la oferta fue registrada correctamente
     */
    boolean realizarOferta(Oferta oferta);

    /**
     * Permite comprar o arrendar un inmueble
     * a partir de una oferta existente.
     *
     * @param codigoOferta código de la oferta
     * @param tipoOp tipo de operación inmobiliaria
     * @return true si la operación fue exitosa
     */
    boolean comprarInmueble(String codigoOferta,
                            TipoOperacion tipoOp);

    /**
     * Genera un reporte general del sistema inmobiliario.
     *
     * @return reporte en formato texto
     */
    String generarReporte();}
