package server.command.server;

import mvc.model.Player;
import server.ClientHandler;
import server.command.ServerCommand;
import utils.MessageFormatter;

public class PrintHandCommand extends ServerCommand {

    private final Player player;

    public PrintHandCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        ClientHandler connection = getConnection(player);
        getConnection().messageTo(connection, "PRINT_HAND|" +
                MessageFormatter.handFormat(player.getHand()));
    }
}
