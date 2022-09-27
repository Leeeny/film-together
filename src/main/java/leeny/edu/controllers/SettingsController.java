package leeny.edu.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import leeny.edu.enums.ClientStatus;
import leeny.edu.json.Parser;
import leeny.edu.json.ResponseJSON;
import leeny.edu.socket.Client;

import java.util.Objects;

public class SettingsController {

    private MainController mainController;

    @FXML
    private AnchorPane mainComponent;

    @FXML
    private TextField username;
    @FXML
    private TextField host;
    @FXML
    private TextField port;

    public void injectMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setVisible(boolean flag) {
        mainComponent.setVisible(flag);
        mainComponent.setManaged(flag);
    }

    public String getUsername() {
        return username.getText();
    }

    public String getHost() {
        return host.getText();
    }

    public String getPort() {
        return port.getText();
    }

    @FXML
    private void handleConnect(MouseEvent event) {
        if(getHost().isBlank() || getPort().isBlank())
            return;
        mainController.getClientController().initClient(getHost(), Integer.parseInt(getPort()), getUsername());
        ResponseJSON response = ResponseJSON.builder()
                .username(getUsername())
                .clientStatus(ClientStatus.CONNECTED)
                .isConnected(false)
                .isUploaded(false)
                .videoDuration(0L)
                .isPlayed(false)
                .isRewind(false)
                .rewindTo(0L)
                .chatMessage("")
                .build();
        mainController.getClientController().send(Parser.getJsonFromObject(response));
    }
}
