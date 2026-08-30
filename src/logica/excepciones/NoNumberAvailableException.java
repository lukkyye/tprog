package logica.excepciones;

public class NoNumberAvailableException extends RuntimeException {

    public NoNumberAvailableException() {
        super();
    }

    public NoNumberAvailableException(String message) {
        super(message);
    }

    public NoNumberAvailableException(Throwable cause) {
        super(cause);
    }

    public NoNumberAvailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoNumberAvailableException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}