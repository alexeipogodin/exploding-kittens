package server;

public class Protocol {
    // Utils
    public static final String SEPARATOR = "|";

    // Errors
    public enum Error {
        TAKEN_USERNAME("Name is already in use. Please try again."),
        INVALID_INDEX("Index is invalid or out of bounds. Please try again."),
        INVALID_COMMAND("Unknown command. Please try again."),
        INVALID_MOVE("This move is invalid. Please try again."),
        INVALID_ARGUMENT("Invalid argument(s). Please try again."),
        CLIENT_DISCONNECTION("Client disconnected. Please try again."),
        SERVER_DISCONNECTION("Server disconnected. Please try again.");
        private String description;

        Error(String description) {
            this.description = description;
        }

        public String getDescription() {
            return this.description;
        }
    }


    public enum BasicCommand implements Command {
        ANNOUNCE,
        WELCOME,
        REQUEST_GAME,
        INFORM_QUEUE,
        START_GAME,
        CURRENT_PLAYER,
        GAME_STATE,
        PLAY,
        PICK,
        CHAT,
        PRINT_CHAT,
        DRAW,
        CONFIRM_DRAW,
        PRINT_CARD,
        PRINT_HAND,
        INFORM_MOVE,
        GAME_OVER,
        START,
        CONFIRM_START;
        public static BasicCommand fromString(String command) {
            return switch (command) {
                case "ANNOUNCE" -> ANNOUNCE;
                case "WELCOME" -> WELCOME;
                case "REQUEST_GAME" -> REQUEST_GAME;
                case "INFORM_QUEUE" -> INFORM_QUEUE;
                case "START_GAME" -> START_GAME;
                case "CURRENT_PLAYER" -> CURRENT_PLAYER;
                case "GAME_STATE" -> GAME_STATE;
                case "PLAY" -> PLAY;
                case "PICK" -> PICK;
                case "CHAT" -> CHAT;
                case "PRINT_CHAT" -> PRINT_CHAT;
                case "DRAW" -> DRAW;
                case "CONFIRM_DRAW" -> CONFIRM_DRAW;
                case "PRINT_CARD" -> PRINT_CARD;
                case "PRINT_HAND" -> PRINT_HAND;
                case "INFORM_MOVE" -> INFORM_MOVE;
                case "GAME_OVER" -> GAME_OVER;
                case "START" -> START;
                case "CONFIRM_START" -> CONFIRM_START;
                default -> null;
            };
        }
    }

    public interface Command {
    }

    public enum Parameters {
        // Card Types
        BOMB,
        DEFUSE,
        NOPE,
        SKIP,
        ATTACK,
        FAVOR,
        SHUFFLE,
        SCRY, // See The Future
        PASS, // Played when one is deciding to not play cards anymore
        // Cat Cards
        TACO,
        POTATO,
        RAINBOW,
        MELON,
        BEARD,
        FERAL,
        // Game Modes
        N,
        T,
        L,
        S,
        C;

        public static Parameters fromString(String parameter) {
            return switch (parameter) {
                case "BOMB" -> BOMB;
                case "DEFUSE" -> DEFUSE;
                case "NOPE" -> NOPE;
                case "SKIP" -> SKIP;
                case "ATTACK" -> ATTACK;
                case "FAVOR" -> FAVOR;
                case "SHUFFLE" -> SHUFFLE;
                case "SCRY" -> SCRY;
                case "PASS" -> PASS;
                case "TACO" -> TACO;
                case "POTATO" -> POTATO;
                case "RAINBOW" -> RAINBOW;
                case "MELON" -> MELON;
                case "BEARD" -> BEARD;
                case "FERAL" -> FERAL;
                case "N" -> N;
                case "T" -> T;
                case "L" -> L;
                case "S" -> S;
                case "C" -> C;
                default -> null;
            };
        }
    }
}