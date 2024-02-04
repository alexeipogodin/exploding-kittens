package mvc.view;

import mvc.model.Card;
import server.Protocol;
import utils.MessageFormatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClientPlayerUI {
    protected static int playerId;
    protected static int currentPlayerId;
    public static void welcomeServer(int id) {

        System.out.println("Welcome to the game server! Your player id is " + playerId + ".");
        System.out.println("Type 'START' and get ready for the game.");
    }

    private static void chatMessageServer(int senderId, String message) {
        System.out.println("Player " + senderId + ": " + message);
    }

    private static void startGameServer(ArrayList<Integer> playerIds) {
        System.out.println("The game has started!");
        System.out.println("You are playing with " + (playerIds.size() - 1) + " other player(s).");
    }
    private static void currentPlayerServer(int id) {
        System.out.println("Current player's id is " + id + ".");
    }
    public static void parseServerMessage(String serverMessage) {
        String[] parts = serverMessage.split("\\Q" + Protocol.SEPARATOR + "\\E");
        String messageType = parts[0];

        switch (messageType) {
            case "WELCOME":
                int id = Integer.parseInt(parts[1]);
                playerId = id;
                welcomeServer(id);
                break;
            case "PRINT_CHAT":
                int senderId = Integer.parseInt(parts[2]);
                String message = parts[1];
                chatMessageServer(senderId, message);
                break;
            case "START_GAME":
                startGameServer(MessageFormatter.arrayFromString(parts[1]));
                break;
            case "CURRENT_PLAYER":
                currentPlayerId = Integer.parseInt(parts[1]);
                currentPlayerServer(currentPlayerId);
                break;
            case "PRINT_HAND":
                ArrayList<String> cards = MessageFormatter.deckFromString(parts[1]);
                printHandServer(cards);
                break;
            case "GAME_STATE":
                HashMap<Integer, Integer> gameState = MessageFormatter.gameStateFromString(parts[1]);
                int drawPileSize = Integer.parseInt(serverMessage.
                        substring(serverMessage.lastIndexOf(",") + 1));
                gameStateServer(gameState, drawPileSize);
                break;
            case "INFORM_MOVE":
                int userId = Integer.parseInt(parts[1]);
                String cardCode = parts[2];
                informMoveServer(userId, cardCode);
                break;
            case "PRINT_CARD":
                printCardServer(parts[1]);
                break;
            case "CONFIRM_DRAW":
                confirmDrawServer(Integer.parseInt(parts[1]));
                break;
            default:
                System.out.println(serverMessage);
        }
    }

    private static void confirmDrawServer(int userId) {
        if (playerId == userId) {
            System.out.println("You've drawn a card.");
        } else {
            System.out.println("Player " + userId + " has drawn a card.");
        }
    }

    private static void printCardServer(String cardCode) {
        System.out.println("Here is the card: " + Card.getCardClass(cardCode).getSimpleName());
    }

    private static void informMoveServer(int userId, String cardCode) {
        System.out.println("Player " + userId + " plays " + Card.getCardClass(cardCode).getSimpleName() + ".");
    }

    private static void gameStateServer(HashMap<Integer, Integer> gameState, int drawPileSize) {
        for (Map.Entry playerInfo: gameState.entrySet()) {
            System.out.println("Player " + playerInfo.getKey() + " has " + playerInfo.getValue() + " cards.");
        }

        System.out.println("The draw pile has " + drawPileSize + " cards.");
    }

    private static void printHandServer(ArrayList<String> cards) {
        System.out.println("Here is your deck: ");
        int i = 1;
        for (String card: cards) {
            System.out.printf("(%d) %s\n", i, card);
            i++;
        }
        System.out.println("Type PLAY|[CARD_NAME]|[TARGET] if you want to play a card.");
    }

    public static String parseCommandFrom(String s) {
        String[] parts = s.split("\\Q" + Protocol.SEPARATOR + "\\E");
        switch (parts[0]) {
            case "PLAY":
                String cmd = parts[0];
                String cardName = Card.getCodeFromSimpleName(parts[1]);
                int amount = 1;
                String target = "";
                if (parts.length >= 3)
                    target = parts[2];
                return cmd + Protocol.SEPARATOR + cardName
                        + Protocol.SEPARATOR + amount
                        + Protocol.SEPARATOR + target;

            default:
                return s;
        }
    }
}
