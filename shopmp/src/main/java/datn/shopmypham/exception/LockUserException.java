package datn.shopmypham.exception;

public class LockUserException extends RuntimeException {
    public LockUserException(String message) {
        super(message);
    }


    public LockUserException(String message, Throwable cause) {
        super(message, cause);
    }


    public LockUserException(Throwable cause) {
        super(cause);
    }
}
