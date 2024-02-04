package mvc.model;

import mvc.controller.PlayerController;
import mvc.model.cards.special.DefuseCard;
import mvc.model.decks.PlayerDeck;

import java.util.Collection;

public class Player {
    private String name;
    private int id;
    private int status;
    private PlayerDeck hand;
    private Game game;

    public Player() {
        hand = new PlayerDeck();
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getName() {
        return name;
    }

    public void setName(String userName) {
        this.name = userName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setStatus(int status) {
        // 0
        // 1 - ready
        this.status = status;
    }

    public int getStatus() {
        return status;
    }


    public void addCardToHand(Card card) {
        hand.add(card);
    }

    public PlayerDeck getHand() {
        return hand;
    }

    public Game getGame() {
        return game;
    }

    public void removeCardFromHand(Card card) {
        hand.remove(card);
    }
}
