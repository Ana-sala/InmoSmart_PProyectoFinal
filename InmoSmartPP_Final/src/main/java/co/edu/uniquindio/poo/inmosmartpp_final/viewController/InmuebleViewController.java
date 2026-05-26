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

import java.util.List;

public class InmuebleViewController {
    MainApp app;
    InmuebleController inmuebleController;
    UsuarioController usuarioController;
    ObservableList<Inmueble> listaInmuebles = FXCollections.observableArrayList();

    @FXML private TextField txtCodigo, txtDireccion, txtCiudad, txtArea, txtPrecio, txtDescripcion;
    @FXML private ComboBox<TipoInmueble> cmbTipo;
    @FXML private ComboBox<Vendedor> cmbVendedor;
    @FXML private TableView<Inmueble> tblInmuebles;
    @FXML private TableColumn<Inmueble, String> colCodigo, colTipo, colCiudad, colPrecio, colEstado, colArea;
    @FXML private TextField txtNuevoPrecio;
    @FXML private TextField txtBuscarCiudad, txtPrecioMin, txtPrecioMax, txtAreaMin;
    @FXML private ComboBox<TipoInmueble> cmbBuscarTipo;

    @FXML void initialize() {
        cmbTipo.setItems(FXCollections.observableArrayList(TipoInmueble.values()));
        cmbBuscarTipo.setItems(FXCollections.observableArrayList(TipoInmueble.values()));
    }

    public void setApp(MainApp app) {
        this.app = app;
        this.inmuebleController = new InmuebleController(app.inmoSmart);
        this.usuarioController = new UsuarioController(app.inmoSmart);
        initView();
    }

    private void initView() {
        colCodigo.setCellValueFactory(c -> new SimpleStringProperty(
                c.getValue().getCodigo()));
        colTipo.setCellValueFactory(c -> new SimpleStringProperty(
                c.getValue().getTipo().toString()));
        colCiudad.setCellValueFactory(c -> new SimpleStringProperty(
                c.getValue().getCiudad()));
        colPrecio.setCellValueFactory(c -> new SimpleStringProperty(
                String.format("$%,.0f", c.getValue().getPrecio())));
        colArea.setCellValueFactory(c -> new SimpleStringProperty(
                c.getValue().getArea() + " m²"));
        colEstado.setCellValueFactory(c -> new SimpleStringProperty(
                c.getValue().getEstado().toString()));
        tblInmuebles.setItems(listaInmuebles);
        cmbVendedor.setItems(FXCollections.observableArrayList(usuarioController.getVendedores()));
    }

    @FXML void onPublicar() {
        try {
            String precioTexto = txtPrecio.getText().replace(".", "").replace(",", "");
            String areaTexto = txtArea.getText().replace(".", "").replace(",", "");

            boolean ok = inmuebleController.publicarInmueble(
                    txtCodigo.getText(), cmbTipo.getValue(), txtDireccion.getText(),
                    txtCiudad.getText(), Double.parseDouble(areaTexto),
                    Double.parseDouble(precioTexto), cmbVendedor.getValue(),
                    txtDescripcion.getText());
            if (ok) {
                listaInmuebles.setAll(inmuebleController.getTodosInmuebles());
                limpiar();
                mostrarAlerta("Exito", "Inmueble publicado.");
            } else {
                mostrarAlerta("Error", "No se pudo publicar.");
            }
        } catch (Exception e) {
            mostrarAlerta("Error", "Verifica los campos numericos.");
        }
    }


    @FXML void onActualizarPrecio() {
        Inmueble seleccionado = tblInmuebles.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta("Error", "Selecciona un inmueble de la tabla.");
            return;
        }
        try {
            String precioTexto = txtNuevoPrecio.getText().replace(".", "").replace(",", "");
            double nuevoPrecio = Double.parseDouble(precioTexto);
            double precioAnterior = seleccionado.getPrecio();
            seleccionado.setPrecio(nuevoPrecio);
            listaInmuebles.setAll(inmuebleController.getTodosInmuebles());

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Alerta Automatica del Sistema");
            alerta.setHeaderText("Cambio de Precio Detectado");
            alerta.setContentText(
                    "El inmueble " + seleccionado.getCodigo() + " cambio de precio.\n" +
                            "Precio anterior: " + String.format("$%,.0f", precioAnterior) + "\n" +
                            "Precio nuevo: " + String.format("$%,.0f", nuevoPrecio) + "\n\n" +
                            "Notificacion enviada por: Correo / SMS / WhatsApp"
            );
            alerta.show();
            txtNuevoPrecio.clear();
        } catch (Exception e) {
            mostrarAlerta("Error", "Ingresa un precio valido.");
        }
    }


    @FXML void onMostrarTodos() {
        listaInmuebles.setAll(inmuebleController.getTodosInmuebles());
        txtBuscarCiudad.clear();
        txtPrecioMin.clear();
        txtPrecioMax.clear();
        txtAreaMin.clear();
        cmbBuscarTipo.setValue(null);
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