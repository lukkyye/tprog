package logica.excepciones;
import jakarta.xml.ws.WebFault;

@WebFault
public class IdAlreadyExistsException extends Exception {

	public IdAlreadyExistsException() {
		// TODO Auto-generated constructor stub
	}

	public IdAlreadyExistsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public IdAlreadyExistsException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public IdAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public IdAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
