module co.edu.uniquindio.poo.inmosmartpp_final {
    requires javafx.controls;
    requires javafx.fxml;

    opens co.edu.uniquindio.poo.inmosmartpp_final to javafx.fxml;
    exports co.edu.uniquindio.poo.inmosmartpp_final;

    exports co.edu.uniquindio.poo.inmosmartpp_final.viewController;
    opens co.edu.uniquindio.poo.inmosmartpp_final.viewController to javafx.fxml;

    exports co.edu.uniquindio.poo.inmosmartpp_final.model;
    exports co.edu.uniquindio.poo.inmosmartpp_final.controller;
}