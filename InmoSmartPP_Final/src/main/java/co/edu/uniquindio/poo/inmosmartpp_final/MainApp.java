package co.edu.uniquindio.poo.inmosmartpp_final;

import co.edu.uniquindio.poo.inmosmartpp_final.model.*;
import co.edu.uniquindio.poo.inmosmartpp_final.viewController.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

public class MainApp extends Application {
    private Stage primaryStage;
    public static InmoSmart inmoSmart = new InmoSmart("InmoSmart");

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("InmoSmart - Plataforma Inmobiliaria");
        inicializarDatos();
        openPrimary();
    }

    private void inicializarDatos() {
        Vendedor v1 = new Vendedor("V1", "Carlos López", "100200300", "3101234567", "carlos@mail.com");
        Comprador c1 = new Comprador("C1", "María García", "200300400", "3209876543", "maria@mail.com");
        inmoSmart.registrarUsuario(v1);
        inmoSmart.registrarUsuario(c1);
        Inmueble i1 = new Inmueble("INM001", TipoInmueble.APARTAMENTO, "Cra 15 #23-45", "Armenia", 80, 250000000, v1);
        inmoSmart.publicarInmueble(i1, "Apartamento moderno en el centro");
        Inmueble i2 = new Inmueble("INM002", TipoInmueble.CASA, "Cll 8 #12-30", "Calarcá", 120, 180000000, v1);
        inmoSmart.publicarInmueble(i2, "Casa familiar amplia");
    }

    public void openPrimary() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("primary.fxml"));
            VBox root = loader.load();
            PrimaryViewController ctrl = loader.getController();
            ctrl.setApp(this);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void openUsuarios() { cargarVista("usuarios.fxml", UsuarioViewController.class); }
    public void openInmuebles() { cargarVista("inmuebles.fxml", InmuebleViewController.class); }
    public void openOfertas() { cargarVista("ofertas.fxml", OfertaViewController.class); }
    public void openTransacciones() { cargarVista("transacciones.fxml", TransaccionViewController.class); }
    public void openReportes() { cargarVista("reportes.fxml", ReporteViewController.class); }

    @SuppressWarnings("unchecked")
    private <T> void cargarVista(String fxml, Class<T> controllerClass) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(fxml));
            AnchorPane root = loader.load();
            Object ctrl = loader.getController();
            controllerClass.getMethod("setApp", MainApp.class).invoke(ctrl, this);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static void main(String[] args) { launch(); }
}