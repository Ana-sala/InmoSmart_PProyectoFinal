package co.edu.uniquindio.poo.inmosmartpp_final.controller;

import co.edu.uniquindio.poo.inmosmartpp_final.model.*;
import java.util.List;

public class UsuarioController {
    private InmoSmart inmoSmart;

    public UsuarioController(InmoSmart inmoSmart) {
        this.inmoSmart = inmoSmart;
    }

    public boolean registrarComprador(String id, String nombre, String identificacion,
                                      String telefono, String correo) {
        if (nombre.isEmpty() || identificacion.isEmpty()) return false;
        Comprador c = new Comprador(id, nombre, identificacion, telefono, correo);
        return inmoSmart.registrarUsuario(c);
    }

    public boolean registrarVendedor(String id, String nombre, String identificacion,
                                     String telefono, String correo) {
        if (nombre.isEmpty() || identificacion.isEmpty()) return false;
        Vendedor v = new Vendedor(id, nombre, identificacion, telefono, correo);
        return inmoSmart.registrarUsuario(v);
    }

    public boolean eliminarUsuario(String identificacion) {
        return inmoSmart.eliminarUsuario(identificacion);
    }

    public Usuario buscarUsuario(String identificacion) {
        return inmoSmart.buscarUsuario(identificacion);
    }

    public List<Comprador> getCompradores() { return inmoSmart.getCompradores(); }
    public List<Vendedor> getVendedores() { return inmoSmart.getVendedores(); }
    public List<Usuario> getTodosUsuarios() { return inmoSmart.getUsuarios(); }
}