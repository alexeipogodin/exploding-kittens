package mvc.model;

import java.util.ArrayList;

public class Deck extends ArrayList<Card> {
    public boolean hasCardsOfClass(Class c) {
        for (Card card : this)
            if (c.isInstance(card))
                return true;

        return false;
    }

    public Card getCardOfClass(Class c) {
        for (Card card : this) {
            if (c.isInstance(card))
                return card;
        }
        return null;
    }

    public int countCardsOfClass(Class c) {
        int count = 0;
        if (hasCardsOfClass(c))
            for (Card card : this)
                if (c.isInstance(card))
                    count++;

        return count;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("{");
        for (Card card: this) {
            builder.append(card.toString());
            builder.append(",");
        }
        if (builder.length() > 1)
            builder.deleteCharAt(builder.length()-1);
        builder.append("}");

        return builder.toString();
    }
}
