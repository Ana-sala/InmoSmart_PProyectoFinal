package co.edu.uniquindio.poo.inmosmartpp_final.model;

/**
 * Clase que representa un inmueble dentro del sistema inmobiliario.
 *
 * Un inmueble contiene información relacionada con:
 * - código,
 * - tipo,
 * - dirección,
 * - ciudad,
 * - área,
 * - precio,
 * - estado,
 * - vendedor propietario.
 *
 * @author Maria Camila
 */
public class Inmueble {

    /**
     * Código único del inmueble.
     */
    private String codigo;

    /**
     * Tipo de inmueble.
     */
    private TipoInmueble tipo;

    /**
     * Dirección del inmueble.
     */
    private String direccion;

    /**
     * Ciudad donde se encuentra ubicado el inmueble.
     */
    private String ciudad;

    /**
     * Área del inmueble en metros cuadrados.
     */
    private double area;

    /**
     * Precio del inmueble.
     */
    private double precio;

    /**
     * Estado actual del inmueble.
     */
    private EstadoInmueble estado;

    /**
     * Vendedor propietario del inmueble.
     */
    private Vendedor vendedor;

    /**
     * Constructor de la clase Inmueble.
     *
     * Por defecto el inmueble se crea en estado DISPONIBLE.
     *
     * @param codigo código único del inmueble
     * @param tipo tipo de inmueble
     * @param direccion dirección del inmueble
     * @param ciudad ciudad del inmueble
     * @param area área en metros cuadrados
     * @param precio precio del inmueble
     * @param vendedor vendedor propietario
     */
    public Inmueble(String codigo,
                    TipoInmueble tipo,
                    String direccion,
                    String ciudad,
                    double area,
                    double precio,
                    Vendedor vendedor) {

        this.codigo = codigo;
        this.tipo = tipo;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.area = area;
        this.precio = precio;

        // Estado inicial del inmueble
        this.estado = EstadoInmueble.DISPONIBLE;

        this.vendedor = vendedor;
    }

    // =====================================================
    // GETTERS Y SETTERS
    // =====================================================

    /**
     * Obtiene el código del inmueble.
     *
     * @return código del inmueble
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Modifica el código del inmueble.
     *
     * @param codigo nuevo código
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene el tipo del inmueble.
     *
     * @return tipo de inmueble
     */
    public TipoInmueble getTipo() {
        return tipo;
    }

    /**
     * Modifica el tipo del inmueble.
     *
     * @param tipo nuevo tipo
     */
    public void setTipo(TipoInmueble tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtiene la dirección del inmueble.
     *
     * @return dirección
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Modifica la dirección del inmueble.
     *
     * @param direccion nueva dirección
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtiene la ciudad del inmueble.
     *
     * @return ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Modifica la ciudad del inmueble.
     *
     * @param ciudad nueva ciudad
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * Obtiene el área del inmueble.
     *
     * @return área del inmueble
     */
    public double getArea() {
        return area;
    }

    /**
     * Modifica el área del inmueble.
     *
     * @param area nueva área
     */
    public void setArea(double area) {
        this.area = area;
    }

    /**
     * Obtiene el precio del inmueble.
     *
     * @return precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Modifica el precio del inmueble.
     *
     * @param precio nuevo precio
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene el estado actual del inmueble.
     *
     * @return estado del inmueble
     */
    public EstadoInmueble getEstado() {
        return estado;
    }

    /**
     * Modifica el estado del inmueble.
     *
     * @param estado nuevo estado
     */
    public void setEstado(EstadoInmueble estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el vendedor propietario del inmueble.
     *
     * @return vendedor
     */
    public Vendedor getVendedor() {
        return vendedor;
    }

    /**
     * Modifica el vendedor del inmueble.
     *
     * @param vendedor nuevo vendedor
     */
    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    // =====================================================
    // MÉTODOS
    // =====================================================

    /**
     * Retorna una representación textual del inmueble.
     *
     * @return información resumida del inmueble
     */
    @Override
    public String toString() {

        return codigo + " - "
                + tipo
                + " en "
                + ciudad
                + " ($"
                + precio
                + ")";
    }
}