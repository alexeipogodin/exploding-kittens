package mvc.model.decks;

import mvc.model.Card;
import mvc.model.Deck;

public class PlayerDeck extends Deck {

    public static final int DEFAULT_INITIAL_HAND_SIZE = 8;

    public PlayerDeck getPlayable() {
        PlayerDeck playable = (PlayerDeck) this.clone();
        for (Card card : this)
            if (!card.canBePlayed())
                playable.remove(card);

        return playable;
    }

    public PlayerDeck getNonPlayable() {
        PlayerDeck nonPlayable = (PlayerDeck) this.clone();
        for (Card card : this)
            if (card.canBePlayed())
                nonPlayable.remove(card);

        return nonPlayable;
    }
}
