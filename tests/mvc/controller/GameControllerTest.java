package mvc.controller;

import mvc.model.Card;
import mvc.model.Player;
import mvc.model.cards.special.DefuseCard;
import mvc.model.cards.special.ExplodingKittenCard;
import mvc.model.decks.DrawPile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {
    GameController controller;
    @BeforeEach
    void setUp() {
        controller = new GameController();
        controller.createGame('N');
        controller.addPlayer(new Player());
        controller.addPlayer(new Player());
        controller.addPlayer(new Player());
    }

    @Test
    void canStart() {
        assertFalse(controller.canStart());
        for (Player p: controller.getPlayers()) {
            p.setStatus(1); //start
        }
        assertTrue(controller.canStart());

        controller.getPlayers().getFirst().setStatus(0);
        assertFalse(controller.canStart());
    }

    @Test
    void addPlayer() {
    }

    class DrawPileTest extends DrawPile {
        @Override
        public Card pullCard() {
            return new ExplodingKittenCard();
        }
    }

    @Test
    void draw() {
        for (Player p: controller.getPlayers()) {
            p.setStatus(1); //start
        }

        controller.startGame();
        controller.getGame().setDrawPile(new DrawPileTest());
        int initialDefCount = controller.getCurrentPlayer().
                getHand().countCardsOfClass(DefuseCard.class);
        controller.draw();
        int afterDefCount = controller.getCurrentPlayer().
                getHand().countCardsOfClass(DefuseCard.class);
        assertTrue(initialDefCount + 1 == afterDefCount);
    }
}