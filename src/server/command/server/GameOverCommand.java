package server.command.server;

import server.command.ServerCommand;

public class GameOverCommand extends ServerCommand {
    @Override
    public void execute() {
        getConnection().broadcast("GAME_OVER|" + getController().getWinnerId());
    }
}
