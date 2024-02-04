package mvc.model;

import mvc.model.cards.action.*;
import mvc.model.cards.cat.*;
import mvc.model.cards.special.DefuseCard;
import mvc.model.cards.special.ExplodingKittenCard;

public abstract class Card {
    protected Game game;

    public String getTitle() {
        return getClass().getSimpleName();
    }

    public String getCode() {
        return switch (this) {
            case AttackCard card -> "ATTACK";
            case ShuffleCard card -> "SHUFFLE";
            case SkipCard card -> "SKIP";
            case SeeTheFutureCard card -> "SCRY";
            case NopeCard card -> "NOPE";
            case FavorCard card -> "FAVOR";
            case BeardCard card -> "BEARD";
            case HairyPotatoCard card -> "POTATO";
            case RainbowRalphingCard card -> "RAINBOW";
            case CattermelonCard card -> "MELON";
            case TacoCard card -> "TACO";
            case DefuseCard card -> "DEFUSE";
            case ExplodingKittenCard card -> "BOMB";
            default -> "UNKNOWN";
        };
    }

    public static Class getCardClass(String code) {
        return switch (code) {
            case "ATTACK" -> AttackCard.class;
            case "SHUFFLE" -> ShuffleCard.class;
            case "SKIP" -> SkipCard.class;
            case "SCRY" -> SeeTheFutureCard.class;
            case "NOPE" -> NopeCard.class;
            case "FAVOR" -> FavorCard.class;
            case "BEARD" -> BeardCard.class;
            case "POTATO" -> HairyPotatoCard.class;
            case "RAINBOW" -> RainbowRalphingCard.class;
            case "MELON" -> CattermelonCard.class;
            case "TACO" -> TacoCard.class;
            case "DEFUSE" -> DefuseCard.class;
            case "BOMB" -> ExplodingKittenCard.class;
            default -> null;
        };
    }

    public static String getCodeFromSimpleName(String simpleName) {
        return switch (simpleName) {
            case "AttackCard" -> "ATTACK";
            case "ShuffleCard" -> "SHUFFLE";
            case "SkipCard" -> "SKIP";
            case "SeeTheFutureCard" -> "SCRY";
            case "NopeCard" -> "NOPE";
            case "FavorCard" -> "FAVOR";
            case "BeardCard" -> "BEARD";
            case "HairyPotatoCard" -> "POTATO";
            case "RainbowRalphingCard" -> "RAINBOW";
            case "CattermelonCard" -> "MELON";
            case "TacoCard" -> "TACO";
            case "DefuseCard" -> "DEFUSE";
            case "ExplodingKittenCard" -> "BOMB";
            default -> null;
        };
    }

    /**
     * A method which checks if this instance of the card
     * can be played during players turn 'session'
     *
     * @return true if card can be played.
     */
    public boolean canBePlayed() {
        return !(this instanceof ExplodingKittenCard
                || this instanceof DefuseCard
                || this instanceof NopeCard);
    }

    @Override
    public String toString() {
        return getTitle();
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
