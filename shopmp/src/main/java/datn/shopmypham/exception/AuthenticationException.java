package datn.shopmypham.exception;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message) {
        super(message);
    }


    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }


    public AuthenticationException(Throwable cause) {
        super(cause);
    }
}
