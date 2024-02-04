package server.command.server;

import server.ClientHandler;
import server.command.ServerCommand;

public class PrintChatCommand extends ServerCommand {

    private final String message;
    private final int recipientId;

    public PrintChatCommand(String message, int recipientId) {
        this.message = message;
        this.recipientId = recipientId;
    }
    @Override
    public void execute() {
        int senderId = getConnection().getPlayerController().getPlayer().getId();
        for (ClientHandler clientHandler : ClientHandler.getHandlers()) {
            if (clientHandler.getPlayerController().getPlayer().getId() == recipientId) {
                clientHandler.messageTo(clientHandler, "PRINT_CHAT|" + message + "|" + senderId);
            }
        }
    }
}
