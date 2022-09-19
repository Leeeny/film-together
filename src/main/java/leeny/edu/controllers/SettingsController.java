package leeny.edu.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class SettingsController {

    private MainController mainController;

    @FXML private AnchorPane mainComponent;

    @FXML private TextField username;
    @FXML private TextField host;
    @FXML private TextField port;

    public void injectMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setVisible(boolean flag) {
        mainComponent.setVisible(flag);
        mainComponent.setManaged(flag);
    }

}
