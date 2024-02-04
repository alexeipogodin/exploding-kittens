package server.command.client;

import mvc.model.Card;
import mvc.model.cards.action.*;
import mvc.model.decks.DrawPile;
import server.command.ClientCommand;
import server.command.ServerCommand;
import server.command.server.*;

import java.util.ArrayList;
import java.util.Arrays;

public class PlayCommand extends ClientCommand {

    public String cardCode;
    public int targetId;
    public PlayCommand(String cardCode, int targetId) {
        this.cardCode = cardCode;
        this.targetId = targetId;
    }
    @Override
    public ServerCommand getResponse() {
        if ((getGameController().getCurrentPlayer() != getPlayerController().getPlayer()
                && Card.getCardClass(cardCode) != NopeCard.class)
                || ((Card.getCardClass(cardCode) == FavorCard.class) && (!getGameController().getGame().validUserID(targetId)
                || targetId == getGameController().getCurrentPlayer().getId())))
            return new NullCommand();

        ArrayList<ServerCommand> commands = new ArrayList<>(Arrays.asList(
                new InformMoveCommand(),
                new CurrentPlayerCommand(),
                new GameStateCommand()
        ));

        if (Card.getCardClass(cardCode) == SeeTheFutureCard.class) {
            DrawPile drawPileCopy = (DrawPile) getGameController().getDrawPile().clone();
            for (int i = 0; i < 3; i++)
                commands.add(new PrintCardCommand(drawPileCopy.pullCard()));
        }

        if (cardCode == "FAVOR") {
            commands.add(new PrintHandCommand(getGameController().getPlayerById(targetId)));
        }

        return new MultiCommand(commands);
    }

    @Override
    public void execute() {
        if (getGameController().getCurrentPlayer() != getPlayerController().getPlayer()
                && Card.getCardClass(cardCode) != NopeCard.class)
            return;
        if (Card.getCardClass(cardCode) == FavorCard.class) {
            if (!getGameController().getGame().validUserID(targetId)
                    || targetId == getGameController().getCurrentPlayer().getId())
                return;

            getGameController().setCurrentPlayer(targetId);
        }

        if (Card.getCardClass(cardCode) == AttackCard.class) {
            getGameController().getGame().increaseTurnsStacked();
            getGameController().getGame().nextPlayer();
        }

        if (Card.getCardClass(cardCode) == SkipCard.class)
            getGameController().getGame().nextPlayer();

        getPlayerController().play(cardCode, targetId);
    }
}
