package buisnesslogic;

public class CardBoxStorageException extends CardBoxException {
    public CardBoxStorageException(String message) {
        super(message);
    }
    public CardBoxStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
