package leeny.edu.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private String hostname;
    private int port;
    private String userName;
    BufferedReader in;
    PrintWriter out;
    Scanner sc = new Scanner(System.in);

    public Client(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
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

            System.out.println("Enter username");
            String username =  sc.nextLine();
            send(username);

            Thread sender = new Thread(new Runnable() {
                String msg;

                @Override
                public void run() {
                    while (true) {
                        String msg = sc.nextLine();
                        send(msg);
                    }
                }
            });
            sender.start();
            Thread receiver = new Thread(new Runnable() {
                String msg;

                @Override
                public void run() {
                    try {
                        msg = in.readLine();
                        while (msg != null) {
                            System.out.println(msg);
                            msg = in.readLine();
                        }
                        System.out.println("Connection is closed");
                        out.close();
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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


    public static void main(String[] args) {
        String hostname = "26.70.165.194";
        int port = 8888;
        Client client = new Client(hostname, port);
        client.execute();
    }
}