public class UserStoryException extends Exception{

    public UserStoryException(String message){
        super(message);
    }
    public UserStoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
