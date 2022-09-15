package leeny.edu.controllers;

import leeny.edu.json.ResponseJSON;
import leeny.edu.socket.Client;

import java.util.Queue;

public class ClientController {
    private Client client;
    private MainController mainController;

    public ClientController(MainController mainController){
        this.mainController = mainController;
    }

    public void initClient(String hostname, int port) {
        client = new Client(hostname, port);
        client.execute();
    }
}
