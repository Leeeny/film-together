package leeny.edu.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainController {

    @FXML private MediaPlayerController mediaPlayerController;

    private ClientController clientController;

    @FXML private BorderPane mainComponent;

    @FXML
    private void initialize() {
        mediaPlayerController.injectMainController(this);
        clientController = new ClientController(this);
    }

    public void testHideComponents() {
        ((Stage)mainComponent.getScene().getWindow()).setFullScreen(true);
        System.out.println("скрыли фигню");
    }

    public void testSeekComponents() {
        ((Stage)mainComponent.getScene().getWindow()).setFullScreen(false);
        System.out.println("открыли) фигню");
    }
}
