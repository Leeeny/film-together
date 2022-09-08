package leeny.edu.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class Server {
    private int port;
    private Set<String> userNames = new HashSet<>();
    private Set<UserThread> userThreads = new HashSet<>();

    public Server(int port) {
        this.port = port;
    }

    public void executeServer() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Main server starts and listening on port " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("User connected");
                UserThread userThread = new UserThread(socket, this);
                userThreads.add(userThread);
                userThread.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void broadcast(String message, UserThread excludeUser) {
        for (UserThread aUser : userThreads) {
            if (aUser != excludeUser) {
                aUser.sendMessage(message);
            }
        }
    }

    void addUserName(String userName) {
        userNames.add(userName);
    }

    void removeUser(String userName, UserThread aUser) {
        boolean removed = userNames.remove(userName);
        if (removed) {
            userThreads.remove(aUser);
            System.out.println("The user " + userName + " quitted");
        }
    }

    Set<String> getUserNames() {
        return this.userNames;
    }

    boolean hasUsers() {
        return !this.userNames.isEmpty();
    }

    public static void main(String[] args) {
        int port = 8888;
        Server server = new Server(port);
        server.executeServer();
    }
}