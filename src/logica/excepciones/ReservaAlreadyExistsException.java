package logica.excepciones;

public class ReservaAlreadyExistsException extends RuntimeException {

    public ReservaAlreadyExistsException() {
        super();
    }

    public ReservaAlreadyExistsException(String message) {
        super(message);
    }

    public ReservaAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public ReservaAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReservaAlreadyExistsException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}