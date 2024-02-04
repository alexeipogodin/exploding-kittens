package server.command.server;

import server.command.ServerCommand;
import utils.MessageFormatter;

import java.util.ArrayList;

public class StartGameCommand extends ServerCommand {
    @Override
    public void execute() {
        getController().startGame();
        getConnection().broadcast("START_GAME|"
                + MessageFormatter.listFormat(getController().getPlayerIds()));
    }
}
