package leeny.edu.socket;

import leeny.edu.controllers.MainController;
import leeny.edu.json.Parser;
import leeny.edu.json.ResponseJSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.Exchanger;

public class Client {

    MainController mainController;
    private String hostname;
    private int port;
    private String userName;
    private BufferedReader in;
    private PrintWriter out;
    private String message;

    public Client(MainController mainController, String hostname, int port, String username) {
        this.mainController = mainController;
        this.hostname = hostname;
        this.port = port;
        this.userName = username;
    }

    public void send(String msg) {
        out.println(msg);
        out.flush();
    }

    public void execute() {
        try {
            Socket socket = new Socket(hostname, port);

            System.out.println("Connected to the chat server");
            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Thread receiver = new Thread(() -> {
                try {
                    message = in.readLine();
                    //!!
                    ResponseJSON response = Parser.getObjectFromJson(message);
                    while (message != null) {
                        message = in.readLine();
                        response = Parser.getObjectFromJson(message);
                        parseResponse(response);
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Connection is closed");
                out.close();
                try {
                    socket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            receiver.start();

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O Error: " + ex.getMessage());
        }
    }

    void setUserName(String userName) {
        this.userName = userName;
    }

    String getUserName() {
        return this.userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private void parseResponse(ResponseJSON response) {
        switch (response.getClientStatus()) {
            case PLAY -> {
                mainController.getMediaPlayerController().playVideo();
            }
            case PAUSE -> {
                mainController.getMediaPlayerController().stopVideo();
            }
            case REWIND -> {

            }
            case UPLOAD -> {

            }
            case MESSAGE -> {

            }
            case CONNECTED -> {

            }
        }
    }

    public static void main(String[] args) {
       /* String hostname = "26.70.165.194";
        int port = 8888;
        Client client = new Client(hostname, port);
        client.execute();*/
    }
}