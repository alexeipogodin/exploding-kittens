package server.command.server;

import mvc.model.Player;
import server.command.ServerCommand;
import utils.MessageFormatter;

import java.util.ArrayList;

public class GameStateCommand extends ServerCommand {
    @Override
    public void execute() {
        getConnection().broadcast("GAME_STATE|" + MessageFormatter.gameStateFormat(
            getController().getPlayers(),
            getController().getDrawPile().size()
        ));
    }
}
