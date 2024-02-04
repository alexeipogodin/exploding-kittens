package server.command.server;

import mvc.model.Card;
import server.command.ClientCommand;
import server.command.ServerCommand;

public class PrintCardCommand extends ServerCommand {
    private final Card cardToPrint;

    public PrintCardCommand(Card card) {
        cardToPrint = card;
    }
    @Override
    public void execute() {
        getConnection().messageTo(getConnection(), "PRINT_CARD|" + cardToPrint.getCode());
    }
}
