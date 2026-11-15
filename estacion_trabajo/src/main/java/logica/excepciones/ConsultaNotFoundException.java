package logica.excepciones;

import jakarta.xml.ws.WebFault;

@WebFault
public class ConsultaNotFoundException extends Exception {

	public ConsultaNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public ConsultaNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ConsultaNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public ConsultaNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ConsultaNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
