package co.edu.uniquindio.poo.inmosmartpp_final.viewController;

import co.edu.uniquindio.poo.inmosmartpp_final.MainApp;
import co.edu.uniquindio.poo.inmosmartpp_final.controller.*;
import co.edu.uniquindio.poo.inmosmartpp_final.model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class OfertaViewController {
    MainApp app;
    OfertaController ofertaController;
    InmuebleController inmuebleController;
    UsuarioController usuarioController;
    ObservableList<Oferta> listaOfertas = FXCollections.observableArrayList();
    Oferta ofertaSeleccionada;

    @FXML private TextField txtCodigo, txtValor;
    @FXML private ComboBox<Comprador> cmbComprador;
    @FXML private ComboBox<Inmueble> cmbInmueble;
    @FXML private ComboBox<TipoOperacion> cmbTipoOp;
    @FXML private TableView<Oferta> tblOfertas;
    @FXML private TableColumn<Oferta, String> colCodigo, colComprador, colInmueble, colValor, colEstado;

    @FXML void initialize() {
        cmbTipoOp.setItems(FXCollections.observableArrayList(TipoOperacion.values()));
    }

    public void setApp(MainApp app) {
        this.app = app;
        ofertaController = new OfertaController(app.inmoSmart);
        inmuebleController = new InmuebleController(app.inmoSmart);
        usuarioController = new UsuarioController(app.inmoSmart);
        initView();
    }

    private void initView() {
        colCodigo.setCellValueFactory(c -> new SimpleStringProperty(
                c.getValue().getCodigoOferta()));
        colComprador.setCellValueFactory(c -> new SimpleStringProperty(
                c.getValue().getComprador().getNombre()));
        colInmueble.setCellValueFactory(c -> new SimpleStringProperty(
                c.getValue().getInmueble().getCodigo()));
        colValor.setCellValueFactory(c -> new SimpleStringProperty(
                String.format("$%,.0f", c.getValue().getValorOferta())));
        colEstado.setCellValueFactory(c -> new SimpleStringProperty(
                c.getValue().getEstado().toString()));
        tblOfertas.setItems(listaOfertas);
        listaOfertas.addAll(ofertaController.getOfertas());
        cmbComprador.setItems(FXCollections.observableArrayList(usuarioController.getCompradores()));
        cmbInmueble.setItems(FXCollections.observableArrayList(inmuebleController.getTodosInmuebles()));
        cmbInmueble.setCellFactory(lv -> new ListCell<Inmueble>() {
            @Override
            protected void updateItem(Inmueble item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) setText(null);
                else setText(item.getCodigo() + " - " + item.getTipo() + " en " + item.getCiudad() +
                        " (" + String.format("$%,.0f", item.getPrecio()) + ")");
            }
        });
        cmbInmueble.setButtonCell(new ListCell<Inmueble>() {
            @Override
            protected void updateItem(Inmueble item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) setText(null);
                else setText(item.getCodigo() + " - " + item.getTipo() + " en " + item.getCiudad() +
                        " (" + String.format("$%,.0f", item.getPrecio()) + ")");
            }
        });
        tblOfertas.getSelectionModel().selectedItemProperty().addListener((obs, old, n) -> {
            ofertaSeleccionada = n;
        });
    }

    @FXML void onRealizarOferta() {
        try {
            String valorTexto = txtValor.getText().replace(".", "").replace(",", "");
            boolean ok = ofertaController.realizarOferta(txtCodigo.getText(),
                    cmbComprador.getValue(), cmbInmueble.getValue(),
                    Double.parseDouble(valorTexto));
            if (ok) {
                listaOfertas.setAll(ofertaController.getOfertas());
                limpiar();
                mostrarAlerta("Exito", "Oferta realizada.");
            } else {
                mostrarAlerta("Error", "No se pudo realizar la oferta.");
            }
        } catch (Exception e) {
            mostrarAlerta("Error", "Valor invalido.");
        }
    }

    @FXML void onAceptar() {
        if (ofertaSeleccionada == null || cmbTipoOp.getValue() == null) {
            mostrarAlerta("Error", "Selecciona una oferta y el tipo de operación."); return;
        }
        ofertaController.aceptarOferta(ofertaSeleccionada.getCodigoOferta(), cmbTipoOp.getValue());
        listaOfertas.setAll(ofertaController.getOfertas());
        mostrarAlerta("Éxito", "Oferta aceptada y transacción registrada.");
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Alerta del Sistema");
        alerta.setHeaderText("Oferta aceptada");
        alerta.setContentText("Notificación: La oferta " + ofertaSeleccionada.getCodigoOferta() +
                " fue aceptada. El inmueble ha cambiado de estado.");
        alerta.show();
    }

    @FXML void onRechazar() {
        if (ofertaSeleccionada == null) { mostrarAlerta("Error", "Selecciona una oferta."); return; }
        ofertaController.rechazarOferta(ofertaSeleccionada.getCodigoOferta());
        listaOfertas.setAll(ofertaController.getOfertas());
        mostrarAlerta("Info", "Oferta rechazada.");
    }

    @FXML void onLimpiar() { limpiar(); }
    @FXML void onVolver() { app.openPrimary(); }

    private void limpiar() {
        txtCodigo.clear(); txtValor.clear();
        tblOfertas.getSelectionModel().clearSelection();
    }

    private void mostrarAlerta(String titulo, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(titulo); a.setHeaderText(null); a.setContentText(msg); a.show();
    }

}
