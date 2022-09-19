package leeny.edu.socket;

import leeny.edu.controllers.MainController;

import java.util.concurrent.Exchanger;

public class ActionHandler {

    private final Exchanger<String> toSend;
    private final Exchanger<String> toReceive;
    private final MainController mainController;

    public ActionHandler(Exchanger<String> toSend, Exchanger<String> toReceive, MainController mainController) {
        this.toSend = toSend;
        this.toReceive = toReceive;
        this.mainController = mainController;
    }

    public void execute() {
        Thread sender = new Thread(() -> {
            // TODO написать логику
        });
        sender.start();

        Thread receiver = new Thread(() -> {
            // TODO написать логику
        });
        receiver.start();
    }
}
