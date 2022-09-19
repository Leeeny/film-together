package leeny.edu.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class ChatController {

    @FXML private BorderPane mainComponent;

    public void setVisible(boolean flag) {
        mainComponent.setVisible(flag);
        mainComponent.setManaged(flag);
    }

    public void addMessage(String mes) {

    }

    public void addUser(String username) {

    }
}
