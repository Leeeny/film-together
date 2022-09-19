package leeny.edu.controllers;

import leeny.edu.socket.ActionHandler;
import leeny.edu.socket.Client;

import java.util.concurrent.Exchanger;

public class ClientController {
    private Client client;
    private ActionHandler actionHandler;
    private MainController mainController;

    public ClientController(MainController mainController){
        this.mainController = mainController;
    }

    public void initClient(String hostname, int port) {
        Exchanger<String> toSend = new Exchanger<>();
        Exchanger<String> toReceive = new Exchanger<>();

        client = new Client(hostname, port, toSend, toReceive);
        actionHandler = new ActionHandler(toSend, toReceive, mainController);

        client.execute();
        actionHandler.execute();
    }
}
