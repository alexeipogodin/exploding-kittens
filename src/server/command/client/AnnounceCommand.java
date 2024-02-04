package server.command.client;

import server.command.ClientCommand;
import server.command.ServerCommand;
import server.command.server.WelcomeCommand;

public class AnnounceCommand extends ClientCommand {

    private final char gameMode;
    private final String userName;

    public AnnounceCommand(String userName, char gameMode) {
        this.userName = userName;
        this.gameMode = gameMode;
    }

    @Override
    public void execute() {
        getPlayerController().update(userName);
        getGameController().setMode(gameMode);
    }

    @Override
    public ServerCommand getResponse() {
        return new WelcomeCommand(getPlayerController().getPlayer().getId());
    }
}
