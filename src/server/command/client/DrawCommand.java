package server.command.client;

import mvc.model.Card;
import server.command.ActivePlayerCommandInterface;
import server.command.ClientCommand;
import server.command.ServerCommand;
import server.command.server.*;

import java.util.ArrayList;
import java.util.Arrays;

public class DrawCommand extends ClientCommand implements ActivePlayerCommandInterface {
    Card card;
    @Override
    public ServerCommand getResponse() {
        if (getGameController().getGame().isFinished()) {
            return new GameOverCommand();
        }
        ArrayList<ServerCommand> commands;
        commands = new ArrayList<>(Arrays.asList(
                new ConfirmDrawCommand(getPlayerController().getPlayer().getId()),
                new PrintCardCommand(card),
                new GameStateCommand()
        ));

        if (getGameController().isStillAlive(getPlayerController().getPlayer()))
            commands.addAll(new ArrayList<>(Arrays.asList(
                new CurrentPlayerCommand(),
                new PrintHandCommand(getGameController().getCurrentPlayer())
            )));


        return new MultiCommand(commands);
    }

    @Override
    public void execute() {
        if (getPlayerController().getPlayer().getId() != getGameController().getCurrentPlayer().getId())
            return;
        card = getGameController().draw();
    }
}
