package server.command.server;

import server.command.ServerCommand;

public class CurrentPlayerCommand extends ServerCommand {
    @Override
    public void execute() {
        getConnection().broadcast("CURRENT_PLAYER|" + getController().getCurrentPlayer().getId());
    }
}
