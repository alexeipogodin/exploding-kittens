package server.command.client;

import server.command.ClientCommand;
import server.command.ServerCommand;

public class ConditionalCommand extends ClientCommand {
    ClientCommand command;
    boolean execute;

    public ConditionalCommand(ClientCommand command, boolean execute) {
        this.command = command;
        this.execute = execute;
    }

    @Override
    public ServerCommand getResponse() {
        return command.getResponse();
    }

    @Override
    public void execute() {
        if (execute) {
            command.execute();
        }
    }
}