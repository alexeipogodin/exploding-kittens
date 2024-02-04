package server.command;

import mvc.controller.GameController;
import mvc.controller.PlayerController;

public abstract class ClientCommand extends Command {
    private GameController gameController;
    private PlayerController playerController;

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public void setPlayerController(PlayerController playerController) {
        this.playerController = playerController;
    }

    public GameController getGameController() {
        return gameController;
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public abstract ServerCommand getResponse();
}
