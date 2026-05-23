package co.edu.uniquindio.poo.inmosmartpp_final.viewController;

import co.edu.uniquindio.poo.inmosmartpp_final.MainApp;
import co.edu.uniquindio.poo.inmosmartpp_final.controller.InmuebleController;
import co.edu.uniquindio.poo.inmosmartpp_final.controller.UsuarioController;
import co.edu.uniquindio.poo.inmosmartpp_final.model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class InmuebleViewController {
    MainApp app;
    InmuebleController inmuebleController;
    UsuarioController usuarioController;
    ObservableList<Inmueble> listaInmuebles = FXCollections.observableArrayList();

    @FXML private TextField txtCodigo, txtDireccion, txtCiudad, txtArea, txtPrecio, txtDescripcion;
    @FXML private ComboBox<TipoInmueble> cmbTipo;
    @FXML private ComboBox<Vendedor> cmbVendedor;
    @FXML private TableView<Inmueble> tblInmuebles;
    @FXML private TableColumn<Inmueble, String> colCodigo, colTipo, colCiudad, colPrecio, colEstado;

    @FXML void initialize() {
        cmbTipo.setItems(FXCollections.observableArrayList(TipoInmueble.values()));
    }

    public void setApp(MainApp app) {
        this.app = app;
        this.inmuebleController = new InmuebleController(app.inmoSmart);
        this.usuarioController = new UsuarioController(app.inmoSmart);
        initView();
    }

    private void initView() {
        colCodigo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCodigo()));
        colTipo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTipo().toString()));
        colCiudad.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCiudad()));
        colPrecio.setCellValueFactory(c -> new SimpleStringProperty("$" + c.getValue().getPrecio()));
        colEstado.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getEstado().toString()));
        tblInmuebles.setItems(listaInmuebles);
        listaInmuebles.addAll(inmuebleController.getTodosInmuebles());
        cmbVendedor.setItems(FXCollections.observableArrayList(usuarioController.getVendedores()));
    }

    @FXML void onPublicar() {
        try {
            boolean ok = inmuebleController.publicarInmueble(
                    txtCodigo.getText(), cmbTipo.getValue(), txtDireccion.getText(),
                    txtCiudad.getText(), Double.parseDouble(txtArea.getText()),
                    Double.parseDouble(txtPrecio.getText()), cmbVendedor.getValue(),
                    txtDescripcion.getText());
            if (ok) { listaInmuebles.setAll(inmuebleController.getTodosInmuebles()); limpiar(); mostrarAlerta("Éxito", "Inmueble publicado."); }
            else mostrarAlerta("Error", "No se pudo publicar.");
        } catch (Exception e) { mostrarAlerta("Error", "Verifica los campos numéricos."); }
    }

    @FXML void onLimpiar() { limpiar(); }
    @FXML void onVolver() { app.openPrimary(); }

    private void limpiar() {
        txtCodigo.clear(); txtDireccion.clear(); txtCiudad.clear();
        txtArea.clear(); txtPrecio.clear(); txtDescripcion.clear();
    }

    private void mostrarAlerta(String titulo, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(titulo); a.setHeaderText(null); a.setContentText(msg); a.show();
    }
}
