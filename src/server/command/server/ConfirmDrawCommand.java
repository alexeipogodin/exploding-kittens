package server.command.server;

import server.command.ServerCommand;

public class ConfirmDrawCommand extends ServerCommand {
    private int userId;

    public ConfirmDrawCommand(int userId) {
        this.userId = userId;
    }

    @Override
    public void execute() {
        getConnection().broadcast("CONFIRM_DRAW|" +
                Integer.toString(userId)
        );
    }
}
