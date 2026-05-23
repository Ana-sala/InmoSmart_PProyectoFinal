package co.edu.uniquindio.poo.inmosmartpp_final.viewController;

import co.edu.uniquindio.poo.inmosmartpp_final.MainApp;
import co.edu.uniquindio.poo.inmosmartpp_final.controller.TransaccionController;
import co.edu.uniquindio.poo.inmosmartpp_final.model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TransaccionViewController {
    MainApp app;
    TransaccionController transaccionController;
    ObservableList<Transaccion> lista = FXCollections.observableArrayList();

    @FXML private TableView<Transaccion> tblTransacciones;
    @FXML private TableColumn<Transaccion, String> colCodigo, colComprador, colVendedor, colInmueble, colValor, colTipo;
    @FXML private Label lblCiudad;

    public void setApp(MainApp app) {
        this.app = app;
        this.transaccionController = new TransaccionController(app.inmoSmart);
        initView();
    }

    private void initView() {
        colCodigo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCodigoTransaccion()));
        colComprador.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getComprador().getNombre()));
        colVendedor.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getVendedor().getNombre()));
        colInmueble.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getInmueble().getCodigo()));
        colValor.setCellValueFactory(c -> new SimpleStringProperty("$" + c.getValue().getValorFinal()));
        colTipo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTipoOperacion().toString()));
        tblTransacciones.setItems(lista);
        lista.addAll(transaccionController.getTransacciones());
        lblCiudad.setText("Ciudad con mayor demanda: " + transaccionController.getCiudadConMayorDemanda());
    }

    @FXML void onActualizar() {
        lista.setAll(transaccionController.getTransacciones());
        lblCiudad.setText("Ciudad con mayor demanda: " + transaccionController.getCiudadConMayorDemanda());
    }

    @FXML void onVolver() { app.openPrimary(); }
}