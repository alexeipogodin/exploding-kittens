package server.command.server;

import mvc.model.Game;
import server.command.ServerCommand;

public class InformMoveCommand extends ServerCommand {
    @Override
    public void execute() {
        Game.PlayedCard playedCard = getController().getLastPlayedCard();
        getConnection().broadcast("INFORM_MOVE|" + playedCard.getPlayedBy().getId()
                + "|" + playedCard.getCard().getCode());
    }
}
