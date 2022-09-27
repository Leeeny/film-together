package leeny.edu.socket;

import leeny.edu.json.Parser;

import java.io.*;
import java.net.Socket;

public class UserThread extends Thread {
    private Socket socket;
    private Server server;
    private PrintWriter writer;

    public UserThread(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            InputStream in = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            OutputStream out = socket.getOutputStream();
            writer = new PrintWriter(out, true);

            String msg = br.readLine();
            String username = Parser.getObjectFromJson(msg).getUsername();
            server.addUserName(username);

            String serverMsg = "User with username \"" + username + "\" connected";
            System.out.println(serverMsg);
            server.broadcast(serverMsg, this);

            do {
                msg = br.readLine();
                serverMsg = "@" + username + "> " + msg;
                System.out.println(serverMsg);
                server.broadcast(serverMsg, this);
            } while (!msg.equals("quit"));

            server.removeUser(username, this);
            socket.close();
            serverMsg = username + " has qiutted the chat";
            server.broadcast(serverMsg, this);
        } catch (IOException e) {
            System.out.println("Exception in run method in UserThread: " + e);

        }
    }

    void printUsers() {
        if (server.hasUsers()) {
            writer.println("Connected users: " + server.getUserNames());
        } else {
            writer.println("No other users connected");
        }
    }

    void sendMessage(String message) {
        writer.println(message);
    }

}
