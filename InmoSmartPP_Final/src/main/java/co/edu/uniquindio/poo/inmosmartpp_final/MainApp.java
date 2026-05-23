package co.edu.uniquindio.poo.inmosmartpp_final;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;
    public static InmoSmartApp inmoSmartApp;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("InmoSmart - Plataforma Inmobiliaria");
        inmoSmartApp = new InmoSmartApp("InmoSmart");
        abrirVistaPrincipal();
    }

    private void abrirVistaPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(
                    MainApp.class.getResource("primary-view.fxml")
            );
            VBox rootLayout = loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch();
    }
}