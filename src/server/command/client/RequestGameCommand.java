package server.command.client;

import server.command.ClientCommand;
import server.command.ServerCommand;
import server.command.server.NullCommand;

public class RequestGameCommand extends ClientCommand {
    private char gameMode;

    public RequestGameCommand(char gameMode) {
        this.gameMode = gameMode;
    }

    @Override
    public ServerCommand getResponse() {
        return new NullCommand();
    }

    @Override
    public void execute() {

    }
}
