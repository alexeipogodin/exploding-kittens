package server.command.server;

import server.command.Command;
import server.command.ServerCommand;

import java.util.ArrayList;

public class MultiCommand extends ServerCommand {
    private final ArrayList<ServerCommand> commands;

    public MultiCommand(ArrayList<ServerCommand> commands) {
        this.commands = commands;
    }

    @Override
    public void execute() {
        for (ServerCommand command: commands) {
            command.setController(getController());
            command.setConnection(getConnection());
            command.execute();
        }
    }
}
