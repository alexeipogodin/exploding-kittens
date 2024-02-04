package server.command.client;

import mvc.model.Player;
import server.command.ClientCommand;
import server.command.ServerCommand;
import server.command.server.*;

import java.util.ArrayList;
import java.util.Arrays;

public class StartCommand extends ClientCommand {

    @Override
    public ServerCommand getResponse() {
        if (getGameController().canStart() && !getGameController().isStarted()) {
            ArrayList<ServerCommand> commands = new ArrayList<>(Arrays.asList(
                    new StartGameCommand(),
                    new CurrentPlayerCommand(),
                    new GameStateCommand()
            ));

            // finally show hands to all players
            for (Player player: getGameController().getPlayers()) {
                ServerCommand printHandCommand = new PrintHandCommand(player);
                commands.add(printHandCommand);
            }

            return new MultiCommand(commands);
        }
        return new NullCommand();
    }

    @Override
    public void execute() {
        getPlayerController().ready();
    }
}
