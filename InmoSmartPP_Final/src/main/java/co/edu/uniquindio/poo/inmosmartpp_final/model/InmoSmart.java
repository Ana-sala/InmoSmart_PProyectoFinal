package co.edu.uniquindio.poo.inmosmartpp_final.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase principal del sistema inmobiliario InmoSmart.
 *
 * Esta clase se encarga de administrar:
 * - usuarios,
 * - inmuebles,
 * - publicaciones,
 * - ofertas,
 * - transacciones,
 * - reportes del sistema.
 *
 * Implementa la lógica principal del negocio inmobiliario.
 *
 * @author Maria Camila
 */
public class InmoSmart implements OperacionesInmobiliarias{

    /**
     * Nombre del sistema inmobiliario.
     */
    private String nombre;

    /**
     * Lista de usuarios registrados.
     */
    private List<Usuario> usuarios;

    /**
     * Lista de inmuebles registrados.
     */
    private List<Inmueble> inmuebles;

    /**
     * Lista de publicaciones activas.
     */
    private List<Publicacion> publicaciones;

    /**
     * Lista de ofertas realizadas.
     */
    private List<Oferta> ofertas;

    /**
     * Lista de transacciones realizadas.
     */
    private List<Transaccion> transacciones;

    /**
     * Constructor de la clase InmoSmart.
     *
     * @param nombre nombre del sistema
     */
    public InmoSmart(String nombre) {
        this.nombre = nombre;
        this.usuarios = new ArrayList<>();
        this.inmuebles = new ArrayList<>();
        this.publicaciones = new ArrayList<>();
        this.ofertas = new ArrayList<>();
        this.transacciones = new ArrayList<>();
    }

    // =====================================================
    // USUARIOS
    // =====================================================

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * Verifica que no exista otro usuario con la misma identificación.
     *
     * @param usuario usuario a registrar
     * @return true si el usuario fue registrado, false en caso contrario
     */
    public boolean registrarUsuario(Usuario usuario) {

        // Verifica que no exista un usuario
        // con la misma identificación

        boolean existe = usuarios.stream().anyMatch( //pregunta ¿si existe algun elemento que cumpla esta condicion
                u -> u.getIdentificacion().equals(usuario.getIdentificacion()));

        if (!existe) { // Si el usuario no existe en la lista
            usuarios.add(usuario); // Agrega el nuevo usuario
            return true; // Retorna true el usuario ya existe
        }

        return false;
    }

    /**
     * Elimina un usuario por identificación.
     *
     * @param identificacion identificación del usuario
     * @return true si fue eliminado
     */
    public boolean eliminarUsuario(String identificacion) {

        // Recorre la lista de usuarios y elimina
        return usuarios.removeIf(// elimina autoamticamente los que cumpla la condicion
                u -> u.getIdentificacion().equals(identificacion));
    }

    /**
     * Busca un usuario por identificación.
     *
     * @param identificacion identificación a buscar
     * @return usuario encontrado o null
     */
    public Usuario buscarUsuario(String identificacion) {

        // Recorre la lista de usuarios
        return usuarios.stream()
                // Filtra el usuario cuya identificación
                // sea igual a la recibida por parámetro
                .filter(u -> u.getIdentificacion().equals(identificacion))
                .findFirst()  // Obtiene el primer usuario encontrado

                .orElse(null);  // Retorna el usuario encontrado,
                                      // o null si no existe
    }

    /**
     * Obtiene todos los compradores registrados.
     *
     * @return lista de compradores
     */
    public List<Comprador> getCompradores() {

        return usuarios.stream()
                // Filtra únicamente los usuarios
                // que sean de tipo Comprador
                .filter(u -> u instanceof Comprador)
                .map(u -> (Comprador) u) // Convierte cada usuario a tipo Comprador
                .collect(Collectors.toList());  // Guarda los compradores en una nueva lista
    }

    /**
     * Obtiene todos los vendedores registrados.
     *
     * @return lista de vendedores
     */
    public List<Vendedor> getVendedores() {

        return usuarios.stream()
                // Filtra únicamente los usuarios
                // que sean de tipo Vendedor
                .filter(u -> u instanceof Vendedor)

                // Convierte cada usuario a tipo Vendedor
                .map(u -> (Vendedor) u)
                .collect(Collectors.toList()); // Guarda los vendedores en una nueva lista
    }

    // =====================================================
    // INMUEBLES
    // =====================================================

    /**
     * Publica un inmueble en el sistema.
     *
     * Además:
     * - agrega el inmueble al vendedor,
     * - suma puntos de reputación,
     * - crea una publicación automáticamente.
     *
     * @param inmueble inmueble a publicar
     * @param descripcion descripción de la publicación
     * @return true si se publicó correctamente
     */
    public boolean publicarInmueble(Inmueble inmueble, String descripcion) {

        boolean existe = inmuebles.stream()
                .anyMatch(i -> i.getCodigo().equals(inmueble.getCodigo()));

        if (!existe) { // Si el inmueble no existe, se procede a publicarlo

            inmuebles.add(inmueble);   // Agrega el inmueble a la lista general de inmuebles

            // Agrega el inmueble a la lista de inmuebles del vendedor
            inmueble.getVendedor()
                    .getInmuebles()
                    .add(inmueble);

            // Se le otorgan 10 puntos al vendedor por publicar
            inmueble.getVendedor()
                    .agregarPuntos(10);

            // Genera un código para la publicación
            String codPub = "PUB-" + (publicaciones.size() + 1);

            // Crea y agrega la nueva publicación a la lista
            publicaciones.add(
                    new Publicacion(codPub, descripcion, inmueble));

            // Retorna true porque la publicación fue exitosa
            return true;
        }
        // Retorna false porque el inmueble ya existía
        return false;
    }

    /**
     * Busca inmuebles aplicando filtros.
     *
     * @param ciudad ciudad del inmueble
     * @param tipo tipo de inmueble
     * @param precioMin precio mínimo
     * @param precioMax precio máximo
     * @param areaMin área mínima
     * @return lista de inmuebles encontrados
     */
    public List<Inmueble> buscarInmuebles(String ciudad,
                                          TipoInmueble tipo,
                                          double precioMin,
                                          double precioMax,
                                          double areaMin) {

        return inmuebles.stream()

                .filter(i -> i.getEstado()
                        == EstadoInmueble.DISPONIBLE)

                .filter(i -> ciudad == null
                        || ciudad.isEmpty()
                        || i.getCiudad().equalsIgnoreCase(ciudad))

                .filter(i -> tipo == null
                        || i.getTipo() == tipo)

                .filter(i -> i.getPrecio() >= precioMin
                        && i.getPrecio() <= precioMax)

                .filter(i -> i.getArea() >= areaMin)

                .collect(Collectors.toList());
    }

    // =====================================================
    // OFERTAS
    // =====================================================

    /**
     * Permite realizar una oferta sobre un inmueble.
     *
     * La oferta solo será válida si:
     * - el inmueble está disponible,
     * - el valor de la oferta es positivo.
     *
     * @param oferta oferta realizada
     * @return true si la oferta fue registrada
     */
    public boolean realizarOferta(Oferta oferta) {

        if (oferta.getInmueble().getEstado()
                != EstadoInmueble.DISPONIBLE) {
            return false;
        }

        if (oferta.getValorOferta() <= 0) {
            return false;
        }

        ofertas.add(oferta);

        oferta.getComprador()
                .getOfertas()
                .add(oferta);

        return true;
    }

    /**
     * Permite aceptar o rechazar una oferta.
     *
     * Si la oferta es aceptada:
     * - se crea una transacción,
     * - se agrega al historial del comprador.
     *
     * @param codigoOferta código de la oferta
     * @param aceptar true para aceptar
     * @param tipoOp tipo de operación
     * @return true si la operación fue exitosa
     */
    public boolean responderOferta(String codigoOferta,
                                   boolean aceptar,
                                   TipoOperacion tipoOp) {

        Oferta oferta = ofertas.stream()

                .filter(o -> o.getCodigoOferta()
                        .equals(codigoOferta))

                .findFirst()
                .orElse(null);

        if (oferta == null) {
            return false;
        }

        if (aceptar) {

            oferta.setEstado(EstadoOferta.ACEPTADA);

            String codTx = "TX-" + (transacciones.size() + 1);

            Transaccion tx = new Transaccion(
                    codTx,
                    oferta.getComprador(),
                    oferta.getInmueble().getVendedor(),
                    oferta.getInmueble(),
                    oferta.getValorOferta(),
                    tipoOp
            );

            transacciones.add(tx);

            oferta.getComprador()
                    .getTransacciones()
                    .add(tx);

        } else {

            oferta.setEstado(EstadoOferta.RECHAZADA);
        }

        return true;
    }

    // =====================================================
    // REPORTES
    // =====================================================

    /**
     * Obtiene los inmuebles vendidos o arrendados.
     *
     * @return lista de inmuebles más vendidos
     */
    public List<Inmueble> inmueblesMasVendidos() {

        return inmuebles.stream()

                .filter(i -> i.getEstado()
                        == EstadoInmueble.VENDIDO

                        || i.getEstado()
                        == EstadoInmueble.ARRENDADO)

                .collect(Collectors.toList());
    }

    /**
     * Obtiene compradores ordenados por reputación.
     *
     * @return lista de compradores más activos
     */
    public List<Comprador> compradoresMasActivos() {

        return getCompradores().stream()

                .sorted((a, b) ->
                        b.getPuntosReputacion()
                                - a.getPuntosReputacion())

                .collect(Collectors.toList());
    }

    /**
     * Obtiene vendedores con más propiedades registradas.
     *
     * @return lista ordenada de vendedores
     */
    public List<Vendedor> vendedoresConMasPropiedades() {

        return getVendedores().stream()

                .sorted((a, b) ->
                        b.getInmuebles().size()
                                - a.getInmuebles().size())

                .collect(Collectors.toList());
    }

    /**
     * Obtiene la ciudad con mayor demanda inmobiliaria.
     *
     * La demanda se calcula según el número de transacciones.
     *
     * @return nombre de la ciudad con mayor demanda
     */
    public String ciudadConMayorDemanda() {

        return transacciones.stream()

                .collect(Collectors.groupingBy(
                        t -> t.getInmueble().getCiudad(),
                        Collectors.counting()))

                .entrySet()
                .stream()

                .max(java.util.Map.Entry.comparingByValue())

                .map(java.util.Map.Entry::getKey)

                .orElse("Sin datos");
    }

    // =====================================================
    // GETTERS
    // =====================================================

    /**
     * Obtiene el nombre del sistema.
     *
     * @return nombre del sistema
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la lista de usuarios.
     *
     * @return lista de usuarios
     */
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * Obtiene la lista de inmuebles.
     *
     * @return lista de inmuebles
     */
    public List<Inmueble> getInmuebles() {
        return inmuebles;
    }

    /**
     * Obtiene la lista de publicaciones.
     *
     * @return lista de publicaciones
     */
    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    /**
     * Obtiene la lista de ofertas.
     *
     * @return lista de ofertas
     */
    public List<Oferta> getOfertas() {
        return ofertas;
    }

    /**
     * Obtiene la lista de transacciones.
     *
     * @return lista de transacciones
     */
    public List<Transaccion> getTransacciones() {
        return transacciones;
    }
    @Override
    public boolean comprarInmueble(String codigoOferta, TipoOperacion tipoOp) {
        return responderOferta(codigoOferta, true, tipoOp);
    }

    @Override
    public String generarReporte() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Reporte InmoSmart ===\n");
        sb.append("Total usuarios: ").append(usuarios.size()).append("\n");
        sb.append("Total inmuebles: ").append(inmuebles.size()).append("\n");
        sb.append("Total ofertas: ").append(ofertas.size()).append("\n");
        sb.append("Total transacciones: ").append(transacciones.size()).append("\n");
        sb.append("Ciudad con mayor demanda: ").append(ciudadConMayorDemanda()).append("\n");
        return sb.toString();
    }
}
