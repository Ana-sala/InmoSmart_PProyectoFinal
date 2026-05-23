package co.edu.uniquindio.poo.inmosmartpp_final.viewController;

import co.edu.uniquindio.poo.inmosmartpp_final.MainApp;
import co.edu.uniquindio.poo.inmosmartpp_final.controller.UsuarioController;
import co.edu.uniquindio.poo.inmosmartpp_final.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.beans.property.SimpleStringProperty;

public class UsuarioViewController {
    MainApp app;
    UsuarioController usuarioController;
    ObservableList<Usuario> listaUsuarios = FXCollections.observableArrayList();
    Usuario usuarioSeleccionado;

    @FXML private TextField txtId, txtNombre, txtIdentificacion, txtTelefono, txtCorreo;
    @FXML private ComboBox<String> cmbTipo;
    @FXML private TableView<Usuario> tblUsuarios;
    @FXML private TableColumn<Usuario, String> colNombre, colIdentificacion, colTipo, colRango;

    @FXML void initialize() {
        cmbTipo.setItems(FXCollections.observableArrayList("COMPRADOR", "VENDEDOR"));
        cmbTipo.setValue("COMPRADOR");
    }

    public void setApp(MainApp app) {
        this.app = app;
        this.usuarioController = new UsuarioController(app.inmoSmart);
        initView();
    }

    private void initView() {
        colNombre.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombre()));
        colIdentificacion.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getIdentificacion()));
        colTipo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTipoUsuario().toString()));
        colRango.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getRango()));
        tblUsuarios.getItems().clear();
        tblUsuarios.setItems(listaUsuarios);
        listaUsuarios.addAll(usuarioController.getTodosUsuarios());
        tblUsuarios.getSelectionModel().selectedItemProperty().addListener((obs, old, n) -> {
            usuarioSeleccionado = n;
            if (n != null) {
                txtId.setText(n.getId());
                txtNombre.setText(n.getNombre());
                txtIdentificacion.setText(n.getIdentificacion());
                txtTelefono.setText(n.getTelefono());
                txtCorreo.setText(n.getCorreo());
                cmbTipo.setValue(n.getTipoUsuario().toString());
            }
        });
    }

    @FXML void onRegistrar() {
        boolean ok;
        String id = txtId.getText(); String nombre = txtNombre.getText();
        String ident = txtIdentificacion.getText(); String tel = txtTelefono.getText();
        String correo = txtCorreo.getText();
        if ("COMPRADOR".equals(cmbTipo.getValue()))
            ok = usuarioController.registrarComprador(id, nombre, ident, tel, correo);
        else
            ok = usuarioController.registrarVendedor(id, nombre, ident, tel, correo);
        if (ok) { listaUsuarios.setAll(usuarioController.getTodosUsuarios()); limpiar(); mostrarAlerta("Éxito", "Usuario registrado."); }
        else mostrarAlerta("Error", "No se pudo registrar. Revisa los datos.");
    }

    @FXML void onEliminar() {
        if (usuarioSeleccionado == null) { mostrarAlerta("Error", "Selecciona un usuario."); return; }
        if (usuarioController.eliminarUsuario(usuarioSeleccionado.getIdentificacion())) {
            listaUsuarios.setAll(usuarioController.getTodosUsuarios()); limpiar();
        }
    }

    @FXML void onLimpiar() { limpiar(); }

    @FXML void onVolver() { app.openPrimary(); }

    private void limpiar() {
        txtId.clear(); txtNombre.clear(); txtIdentificacion.clear();
        txtTelefono.clear(); txtCorreo.clear();
        tblUsuarios.getSelectionModel().clearSelection();
    }

    private void mostrarAlerta(String titulo, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(titulo); a.setHeaderText(null); a.setContentText(msg); a.show();
    }
}
