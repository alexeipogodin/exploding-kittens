package mvc.model.cards.action;

import mvc.model.Card;
import mvc.model.Game;
import mvc.model.cards.ActionCard;

import java.util.ArrayList;

public class FavorCard extends ActionCard {

    @Override
    public void performAction() {
//        Game.PlayedCard playedCard = findPlayedCard();
//        System.out.println(playedCard.getPlayedBy() + " asking " + playedCard.getTarget() + " for a favor ");
//
//        Card card = playedCard.getTarget().askForFavor();
//
//        playedCard.getTarget().removeCardFromHand(card);
//        playedCard.getPlayedBy().addCardToHand(card);
    }
//
//    private Game.PlayedCard findPlayedCard() {
//        ArrayList<Game.PlayedCard> history = (ArrayList<Game.PlayedCard>) game.getPlayedCards().clone();
//        while (!(history.getLast().getCard() instanceof FavorCard))
//            history.removeLast();
//
//        return history.getLast();
//    }
}
