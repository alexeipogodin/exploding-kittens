package mvc.controller;

import mvc.model.Card;
import mvc.model.Game;
import mvc.model.Player;
import mvc.model.cards.action.FavorCard;

public class PlayerController {

    private final Player player;

    public PlayerController(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void update(String userName) {
        getPlayer().setName(userName);
    }

    public void ready() {
        getPlayer().setStatus(1);
    }

    public void play(String cardCode, int targetId) {
        Card card = getPlayer().getHand().getCardOfClass(Card.getCardClass(cardCode));
        if (card == null)
            return;

        Player target = null;

        if (card instanceof FavorCard) {
            if (!getPlayer().getGame().validUserID(targetId))
                return;
            target = getPlayer().getGame().getPlayerById(targetId);
        }

        Game.PlayedCard playedCard = new Game.PlayedCard(player, card, target);
        player.removeCardFromHand(card);
        player.getGame().play(playedCard);
    }

    public void giveOut(String cardCode) {
        Card card = getPlayer().getHand().getCardOfClass(Card.getCardClass(cardCode));
        if (card == null)
            return;

        player.getGame().makeAFavor(player, card);
    }
}
