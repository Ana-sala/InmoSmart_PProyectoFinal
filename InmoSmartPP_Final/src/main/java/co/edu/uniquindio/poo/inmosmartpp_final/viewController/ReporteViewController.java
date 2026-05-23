package co.edu.uniquindio.poo.inmosmartpp_final.viewController;

import co.edu.uniquindio.poo.inmosmartpp_final.MainApp;
import co.edu.uniquindio.poo.inmosmartpp_final.controller.TransaccionController;
import co.edu.uniquindio.poo.inmosmartpp_final.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ReporteViewController {
    MainApp app;
    TransaccionController tc;

    @FXML private TextArea txtReporte;

    public void setApp(MainApp app) {
        this.app = app;
        this.tc = new TransaccionController(app.inmoSmart);
    }

    @FXML void onInmueblesMasVendidos() {
        StringBuilder sb = new StringBuilder("=== Inmuebles más vendidos ===\n");
        for (Inmueble i : tc.getInmueblesMasVendidos())
            sb.append(i).append("\n");
        txtReporte.setText(sb.toString());
    }

    @FXML void onCompradoresMasActivos() {
        StringBuilder sb = new StringBuilder("=== Compradores más activos ===\n");
        for (Comprador c : tc.getCompradoresMasActivos())
            sb.append(c).append(" - Puntos: ").append(c.getPuntosReputacion()).append("\n");
        txtReporte.setText(sb.toString());
    }

    @FXML void onVendedoresMasPropiedades() {
        StringBuilder sb = new StringBuilder("=== Vendedores con más propiedades ===\n");
        for (Vendedor v : tc.getVendedoresConMasPropiedades())
            sb.append(v).append(" - Propiedades: ").append(v.getInmuebles().size()).append("\n");
        txtReporte.setText(sb.toString());
    }

    @FXML void onCiudadMayorDemanda() {
        txtReporte.setText("Ciudad con mayor demanda: " + tc.getCiudadConMayorDemanda());
    }

    @FXML void onVolver() { app.openPrimary(); }
}
