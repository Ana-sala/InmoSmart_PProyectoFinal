module co.edu.uniquindio.poo.inmosmartpp_final {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens co.edu.uniquindio.poo.inmosmartpp_final to javafx.fxml;
    exports co.edu.uniquindio.poo.inmosmartpp_final;
}