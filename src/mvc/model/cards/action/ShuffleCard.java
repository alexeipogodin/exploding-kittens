package mvc.model.cards.action;

import mvc.model.cards.ActionCard;

public class ShuffleCard extends ActionCard {

    @Override
    public void performAction() {
        game.getDrawPile().shuffle();
    }
}
