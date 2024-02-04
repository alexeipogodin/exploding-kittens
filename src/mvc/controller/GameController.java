package mvc.controller;

import mvc.model.Card;
import mvc.model.Game;
import mvc.model.Player;
import mvc.model.cards.special.DefuseCard;
import mvc.model.cards.special.ExplodingKittenCard;
import mvc.model.decks.DrawPile;

import java.util.ArrayList;

public class GameController {

    private Game game;

    public boolean canStart() {
        return game.canStart();
    }

    public Game createGame(char gameMode) {
        Game game = new Game();
        game.setMode('N');

        this.game = game;

        return game;
    }

    public void addPlayer(Player player) {
        game.addPlayer(player);
    }

    public void setMode(char gameMode) {
        game.setMode(gameMode);
    }

    public DrawPile getDrawPile() {
        return game.getDrawPile();
    }

    public ArrayList<Player> getPlayers() {
        return game.getPlayers();
    }

    public ArrayList<Integer> getPlayerIds() {
        return game.getPlayerIds();
    }

    public Player getCurrentPlayer() {
        return game.getCurrentPlayer();
    }

    public void startGame() {
        game.setUp();
        game.playGame();
    }

    public boolean isStarted() {
        return game.getStatus() == Game.GameStatus.IN_PROGRESS
                || game.getStatus() == Game.GameStatus.FINISHED;
    }

    public Card draw() {
        Card card = game.getDrawPile().pullCard();
        if (card instanceof ExplodingKittenCard) {
            if (!game.getCurrentPlayer().getHand().hasCardsOfClass(DefuseCard.class)) {
                game.eliminatePlayer();
                return card;
            }

            game.play(new Game.PlayedCard(getCurrentPlayer(),
                    getCurrentPlayer().getHand()
                            .getCardOfClass(DefuseCard.class), null));

            game.returnInDrawPile(card);
            game.nextPlayer();
            return card;
        }

        game.getCurrentPlayer().addCardToHand(card);
        game.nextPlayer();
        return card;
    }

    public void checkGameStatus() {

    }

    public Game getGame() {
        return game;
    }

    public Game.PlayedCard getLastPlayedCard() {
        return game.lastPlayedCard();
    }

    public void setCurrentPlayer(int targetId) {
        game.setCurrentPlayer(game.getPlayerById(targetId));
    }

    public Player getPlayerById(int userId) {
        return game.getPlayerById(userId);
    }

    public boolean isStillAlive(Player player) {
        return game.isStillAlive(player);
    }

    public int getWinnerId() {
        return game.getWinner().getId();
    }
}
