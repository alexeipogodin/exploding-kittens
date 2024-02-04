package server.command.client;

import mvc.controller.GameController;
import server.command.ClientCommand;
import server.command.ServerCommand;
import server.command.server.NullCommand;
import server.command.server.PrintChatCommand;

public class ChatCommand extends ClientCommand {
    private final String message;
    private final int recipientId;

    public ChatCommand(String message, int recipientId) {
        this.message = message;
        this.recipientId = recipientId;
    }

    @Override
    public void execute() {

    }

    @Override
    public ServerCommand getResponse() {
        return new PrintChatCommand(message, recipientId);
    }
}
