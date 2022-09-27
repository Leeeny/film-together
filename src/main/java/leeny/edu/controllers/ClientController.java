package leeny.edu.controllers;

import leeny.edu.socket.Client;

import java.util.concurrent.Exchanger;

public class ClientController {
    private Client client;

    private MainController mainController;

    public ClientController(MainController mainController){
        this.mainController = mainController;
    }

    public void initClient(String hostname, int port, String username) {

        client = new Client(mainController, hostname, port, username);

        client.execute();

    }

    public void send(String msg) {
        client.send(msg);
    }
}
