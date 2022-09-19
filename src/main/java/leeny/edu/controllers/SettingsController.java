package leeny.edu.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SettingsController {

    private MainController mainController;

    @FXML public TextField username;
    @FXML public TextField host;
    @FXML public TextField port;

    public void injectMainController(MainController mainController) {
        this.mainController = mainController;
    }

}
