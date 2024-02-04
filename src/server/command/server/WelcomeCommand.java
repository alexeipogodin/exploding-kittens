package server.command.server;

import server.command.ServerCommand;

public class WelcomeCommand extends ServerCommand {

    private final int id;

    public WelcomeCommand(int id) {
        this.id = id;
    }

    @Override
    public void execute() {
        getConnection().messageTo(getConnection(), "WELCOME|" + id);
    }
}
