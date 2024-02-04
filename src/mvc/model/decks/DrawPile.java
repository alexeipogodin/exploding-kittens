package mvc.model.decks;

import mvc.model.Card;
import mvc.model.Deck;
import mvc.model.Game;
import mvc.model.cards.cat.*;
import mvc.model.cards.action.*;
import mvc.model.cards.special.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DrawPile extends Deck {

    public DrawPile() {
        super();
    }

    public DrawPile(List<Card> cards) {
        super();
        this.addAll(cards);
    }

    public void shuffle() {
        Collections.shuffle(this);
    }
    public void insertRandomly(Card card) {
        add((int) (Math.random() * size()), card);
    }

    public Card pullCard() {
        return remove(size() - 1);
    }

    public static DrawPile generateDrawPile(int playersCount) {
        DrawPile drawPile = new DrawPile(List.of(
                /* Cat Cards */
                new TacoCard(), new TacoCard(),
                new TacoCard(), new TacoCard(), // 4
                new BeardCard(), new BeardCard(),
                new BeardCard(), new BeardCard(), // 4
                new CattermelonCard(), new CattermelonCard(),
                new CattermelonCard(), new CattermelonCard(), // 4
                new HairyPotatoCard(), new HairyPotatoCard(),
                new HairyPotatoCard(), new HairyPotatoCard() // 4
//                new RainbowRalphingCard(), new RainbowRalphingCard(),
//                new RainbowRalphingCard(), new RainbowRalphingCard(), // 4

//                /* Action Cards */
//                new ShuffleCard(), new ShuffleCard(),
//                new ShuffleCard(), new ShuffleCard(), // 4
//                new FavorCard(), new FavorCard(),
//                new FavorCard(), new FavorCard(), // 4
//                new AttackCard(), new AttackCard(),
//                new AttackCard(), new AttackCard(), // 4
//                new SkipCard(), new SkipCard(),
//                new SkipCard(), new SkipCard(), // 4
//                new SeeTheFutureCard(), new SeeTheFutureCard(),
//                new SeeTheFutureCard(), new SeeTheFutureCard(),
//                new SeeTheFutureCard(), // 5
//                new NopeCard(), new NopeCard(),
//                new NopeCard(), new NopeCard(),
//                new NopeCard() // 5
        ));

        for (int i =0; i < Game.INITIAL_DEFUSE_COUNT - playersCount; i++)
            drawPile.add(new DefuseCard());

        drawPile.shuffle();

        return drawPile;
    }
}
