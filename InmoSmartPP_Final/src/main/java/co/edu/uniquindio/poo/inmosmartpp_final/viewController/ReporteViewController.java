package co.edu.uniquindio.poo.inmosmartpp_final.viewController;

import co.edu.uniquindio.poo.inmosmartpp_final.MainApp;
import co.edu.uniquindio.poo.inmosmartpp_final.controller.TransaccionController;
import co.edu.uniquindio.poo.inmosmartpp_final.model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ReporteViewController {
    MainApp app;
    TransaccionController tc;

    @FXML private TableView<Inmueble> tblInmuebles;
    @FXML private TableColumn<Inmueble, String> colCodigo, colTipo, colCiudad, colPrecio;

    @FXML private TableView<Comprador> tblCompradores;
    @FXML private TableColumn<Comprador, String> colNombreC, colIdentC, colPuntosC, colRangoC;

    @FXML private TableView<Vendedor> tblVendedores;
    @FXML private TableColumn<Vendedor, String> colNombreV, colIdentV, colPropiedadesV, colRangoV;

    @FXML private Label lblCiudad;
    @FXML private Label lblTitulo;

    public void setApp(MainApp app) {
        this.app = app;
        this.tc = new TransaccionController(app.inmoSmart);
        ocultarTablas();
    }

    private void ocultarTablas() {
        tblInmuebles.setVisible(false);
        tblCompradores.setVisible(false);
        tblVendedores.setVisible(false);
        lblCiudad.setVisible(false);
        lblTitulo.setText("Selecciona un reporte");
    }

    @FXML void onInmueblesMasVendidos() {
        ocultarTablas();
        lblTitulo.setText("Inmuebles vendidos");
        colCodigo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCodigo()));
        colTipo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTipo().toString()));
        colCiudad.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCiudad()));
        colPrecio.setCellValueFactory(c -> new SimpleStringProperty(
                String.format("$%,.0f", c.getValue().getPrecio())));
        tblInmuebles.setItems(FXCollections.observableArrayList(tc.getInmueblesMasVendidos()));
        tblInmuebles.setVisible(true);
    }

    @FXML void onCompradoresMasActivos() {
        ocultarTablas();
        lblTitulo.setText("Compradores mas activos");
        colNombreC.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombre()));
        colIdentC.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getIdentificacion()));
        colPuntosC.setCellValueFactory(c -> new SimpleStringProperty(
                String.valueOf(c.getValue().getPuntosReputacion())));
        colRangoC.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getRango()));
        tblCompradores.setItems(FXCollections.observableArrayList(tc.getCompradoresMasActivos()));
        tblCompradores.setVisible(true);
    }

    @FXML void onVendedoresMasPropiedades() {
        ocultarTablas();
        lblTitulo.setText("Vendedores con mas propiedades");
        colNombreV.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombre()));
        colIdentV.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getIdentificacion()));
        colPropiedadesV.setCellValueFactory(c -> new SimpleStringProperty(
                String.valueOf(c.getValue().getInmuebles().size())));
        colRangoV.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getRango()));
        tblVendedores.setItems(FXCollections.observableArrayList(tc.getVendedoresConMasPropiedades()));
        tblVendedores.setVisible(true);
    }

    @FXML void onCiudadMayorDemanda() {
        ocultarTablas();
        lblTitulo.setText("Ciudad con mayor demanda");
        lblCiudad.setText("Ciudad: " + tc.getCiudadConMayorDemanda());
        lblCiudad.setVisible(true);
    }

    @FXML void onVolver() { app.openPrimary(); }
}
