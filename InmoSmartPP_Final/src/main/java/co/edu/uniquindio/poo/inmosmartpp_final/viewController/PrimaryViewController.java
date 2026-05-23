package co.edu.uniquindio.poo.inmosmartpp_final.viewController;

import co.edu.uniquindio.poo.inmosmartpp_final.MainApp;
import javafx.fxml.FXML;

public class PrimaryViewController {
    MainApp app;

    public void setApp(MainApp app) { this.app = app; }

    @FXML void onIrUsuarios() { app.openUsuarios(); }
    @FXML void onIrInmuebles() { app.openInmuebles(); }
    @FXML void onIrOfertas() { app.openOfertas(); }
    @FXML void onIrTransacciones() { app.openTransacciones(); }
    @FXML void onIrReportes() { app.openReportes(); }
    @FXML void initialize() {}
}
