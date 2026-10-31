package logica.excepciones;
import jakarta.xml.ws.WebFault;

@WebFault
public class ReservaAlreadyExistsException extends Exception {

	public ReservaAlreadyExistsException() {
		// TODO Auto-generated constructor stub
	}

	public ReservaAlreadyExistsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ReservaAlreadyExistsException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public ReservaAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ReservaAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
}