package utils;

import mvc.model.Card;
import mvc.model.Player;
import mvc.model.decks.PlayerDeck;

import java.util.ArrayList;
import java.util.HashMap;

public class MessageFormatter {
    public static String listFormat(ArrayList<Integer> list) {
        StringBuilder builder = new StringBuilder("{");
        for (int i: list)
            builder.append(i).append(',');
        builder.deleteCharAt(builder.length() - 1);
        builder.append("}");

        return builder.toString();
    }

    public static String handFormat(PlayerDeck cards) {
        StringBuilder builder = new StringBuilder("{");
        for (Card c: cards)
            builder.append(c.getCode()).append(',');
        builder.deleteCharAt(builder.length() - 1);
        builder.append("}");

        return builder.toString();
    }

    public static String gameStateFormat(ArrayList<Player> players, int drawPileSize) {
        StringBuilder sb = new StringBuilder("{");
        for (Player player: players) {
            sb.append("(").append(player.getId()).append(",");
            sb.append(player.getHand().size()).append("),");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("},").append(drawPileSize);

        return sb.toString();
    }

    public static ArrayList<Integer> arrayFromString(String s) {
        s = s.substring(1, s.length() - 1);
        String[] parts = s.split(",");
        ArrayList<Integer> list = new ArrayList<>();

        for (String part: parts)
            list.add(Integer.parseInt(part));

        return list;
    }

    public static ArrayList<String> deckFromString(String s) {
        s = s.substring(1, s.length() - 1);
        String[] parts = s.split(",");
        ArrayList<String> list = new ArrayList<>();

        for (String part: parts)
            list.add(Card.getCardClass(part).getSimpleName());

        return list;
    }

    public static HashMap<Integer, Integer> gameStateFromString(String s) {
        s = s.substring(2, s.lastIndexOf('}'));
        String[] parts = s.split(",\\(");
        HashMap<Integer, Integer> map = new HashMap<>();

        for (String part: parts) {
            String[] pair = part.split(",");
            int playerId = Integer.parseInt(pair[0]);
            int handSize = Integer.parseInt(pair[1].substring(0, pair[1].length() - 1));
            map.put(playerId, handSize);
        }

        return map;
    }
}
