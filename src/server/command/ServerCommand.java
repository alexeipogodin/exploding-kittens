package server.command;

import mvc.controller.GameController;
import mvc.model.Player;
import server.ClientHandler;

public abstract class ServerCommand extends Command {
    private boolean isPrivate = true;
    private ClientHandler connection;
    private GameController controller;

    public void setConnection(ClientHandler connection) {
        this.connection = connection;
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }

    public ClientHandler getConnection() {
        return connection;
    }

    public ClientHandler getConnection(Player player) {
        for (ClientHandler c: ClientHandler.getHandlers()) {
            if (c.getPlayerController().getPlayer() == player) {
                return c;
            }
        }
        return null;
    }

    public GameController getController() {
        return controller;
    }
}
