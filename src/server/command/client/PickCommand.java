package server.command.client;

import mvc.model.Card;
import server.command.ClientCommand;
import server.command.ServerCommand;
import server.command.server.NullCommand;

public class PickCommand extends ClientCommand {
    private String cardCode;
    public PickCommand(String cardCode) {
        this.cardCode = cardCode;
    }
    @Override
    public ServerCommand getResponse() {
        return new NullCommand();
    }

    @Override
    public void execute() {
        getPlayerController().giveOut(cardCode);
    }
}
