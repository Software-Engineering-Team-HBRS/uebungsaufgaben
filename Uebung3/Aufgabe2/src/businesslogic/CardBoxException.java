package businesslogic;

public class CardBoxException extends Exception {
    public CardBoxException(String message) {
        super(message);
    }
    public CardBoxException(String message, Throwable cause) { super (message, cause); }
}
