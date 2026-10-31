package logica.excepciones;

import jakarta.xml.ws.WebFault;

@WebFault
public class EmailAlreadyExistsException extends Exception {

	public EmailAlreadyExistsException() {
		// TODO Auto-generated constructor stub
	}

	public EmailAlreadyExistsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public EmailAlreadyExistsException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public EmailAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public EmailAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
