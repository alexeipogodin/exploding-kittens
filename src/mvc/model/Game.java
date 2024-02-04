package mvc.model;

import mvc.model.cards.action.FavorCard;
import mvc.model.cards.special.DefuseCard;
import mvc.model.cards.special.ExplodingKittenCard;
import mvc.model.decks.DiscardPile;
import mvc.model.decks.DrawPile;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    public static final int DEFAULT_TURNS_STACKED = 1;
    public static final int INITIAL_DEFUSE_COUNT = 6;
    public static final int DEFAULT_HAND_SIZE = 8;
    public static int MIN_PLAYERS = 2;
    public static int MAX_PLAYERS = 5;
    private final DiscardPile discardPile;
    private char mode;
    private static int nextPlayerId = 1;
    private ArrayList<Player> players;
    private ArrayList<Player> losers;
    private ArrayList<PlayedCard> playedCards;
    private DrawPile drawPile;
    private Player currentPlayer;
    private Player prevCurrentPlayer;
    private GameStatus gameStatus;
    private int turnsStacked;
    private Player winner;

    public PlayedCard lastPlayedCard() {
        return playedCards.getLast();
    }

    public void makeAFavor(Player player, Card card) {
        if (!(playedCards.getLast().getCard() instanceof FavorCard)
                || playedCards.getLast().getTarget() == null)
            return;

        player.removeCardFromHand(card);
        playedCards.getLast().getPlayedBy().addCardToHand(card);
    }

    public boolean validUserID(int id) {
        return getPlayerIds().contains(id);
    }

    public void eliminatePlayer() {
        System.out.println(players.size());
        addLoser(currentPlayer);
    }

    private void addLoser(Player player) {
        nextPlayer();
        players.remove(player);
        losers.add(player);
    }

    public boolean isStillAlive(Player player) {
        return players.contains(player);
    }

    public void returnInDrawPile(Card card) {
        if (drawPile.size() == 0)
            drawPile.add(card);
        else
            drawPile.add(new Random().nextInt(drawPile.size()), card);
    }

    public Player getWinner() {
        return winner;
    }

    public void setDrawPile(DrawPile drawPile) {
        this.drawPile = drawPile;
    }

    public enum GameStatus {
        NOT_STARTED,
        IN_PROGRESS,
        FINISHED
    }

    public void playGame() {
        gameStatus = GameStatus.IN_PROGRESS;
        System.out.println("Running game");
    }

    public void play(PlayedCard playedCard) {
        System.out.println(playedCard.getCard() + " played by "
                + playedCard.getPlayedBy().getName() + " to " + (
                        (playedCard.getTarget() == null) ? "" : playedCard.getTarget().getName()
        ));
        playedCards.add(playedCard);
        discardPile.add(playedCard.getCard());
    }

    public void pullCard() {

    }

    private boolean turnsStacked() {
        return turnsStacked > 0;
    }

    public GameStatus getStatus() {
        return gameStatus;
    }

    public static class PlayedCard {
        private final Player PLAYED_BY;
        private final Card CARD;
        private final Player TARGET;
        private boolean cancelled;

        public PlayedCard(Player playedBy, Card card, Player target) {
            this.PLAYED_BY = playedBy;
            this.CARD = card;
            this.TARGET = target;
            this.cancelled = false;
        }
        public PlayedCard(Player playedBy, Card card) {
            this(playedBy, card, null);
        }

        public Player getPlayedBy() {
            return PLAYED_BY;
        }
        public Card getCard() {
            return CARD;
        }
        public Player getTarget() {
            return TARGET;
        }
        public boolean canBeCanceled() {
            return !(CARD instanceof ExplodingKittenCard || CARD instanceof DefuseCard);
        }

        public boolean isCancelled() {
            return cancelled;
        }
        public void cancel() {
            cancelled = true;
        }
    }

    public Game() {
        this.gameStatus = GameStatus.NOT_STARTED;
        this.players = new ArrayList<>();
        this.losers = new ArrayList<>();
        this.winner = null;
        this.discardPile = new DiscardPile();
        this.turnsStacked = DEFAULT_TURNS_STACKED;
        this.playedCards = new ArrayList<>();
    }

    public boolean isFinished() {
        if (players.size() == 1) {
            winner = players.getFirst();
            gameStatus = GameStatus.FINISHED;
        }
        return gameStatus == GameStatus.FINISHED;
    }

    public int getTurnsStacked() {
        return turnsStacked;
    }

    public void resetTurnsStacked() {
        turnsStacked = DEFAULT_TURNS_STACKED;
    }

    public void increaseTurnsStacked() {
        if (turnsStacked < 2) {
            turnsStacked = 2;
        } else {
            turnsStacked++;
            turnsStacked++;
        }
    }

    public void decreaseTurnsStacked() {
        turnsStacked--;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player player) {
        prevCurrentPlayer = currentPlayer;
        currentPlayer = player;
    }

    public void nextPlayer() {
        currentPlayer = (currentPlayer == players.getLast()) ?
                players.getFirst()
                : players.get(players.indexOf(currentPlayer) + 1);
    }

    public void setUp() {
        // dealing each player a defuse card
        for (Player player : players)
            player.addCardToHand(new DefuseCard());

        // creating a default shuffled draw pile with the number of defuse cards
        drawPile = DrawPile.generateDrawPile(players.size());

        // dealing each player a card from the draw pile
        for (int i = 0; i < DEFAULT_HAND_SIZE - 1; i++)
            for (Player player : players)
                player.addCardToHand(drawPile.pullCard());

        for (int i = 0; i < players.size() - 1; i++)
            drawPile.insertRandomly(new ExplodingKittenCard());

        currentPlayer = players.get(new Random().nextInt(players.size()));
    }

    public boolean canStart() {
        boolean countConditionMet = players.size() >= MIN_PLAYERS && players.size() <= MAX_PLAYERS;

        boolean allPlayersReady = true;
        for (Player player: players)
            if (player.getStatus() != 1) {
                allPlayersReady = false;
                break;
            }

        return (countConditionMet && allPlayersReady);
    }

    public void setMode(char mode) {
        this.mode = mode;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
        player.setId(nextPlayerId);
        nextPlayerId++;
    }

    public DrawPile getDrawPile() {
        return drawPile;
    }

    public Player getPlayerById(int userId) {
        for (Player p: players) {
            if (p.getId() == userId) {
                return p;
            }
        }
        return null;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Integer> getPlayerIds() {
        ArrayList<Integer> playerIds = new ArrayList<>();

        for (Player player: players)
            playerIds.add(player.getId());

        return playerIds;
    }
}
