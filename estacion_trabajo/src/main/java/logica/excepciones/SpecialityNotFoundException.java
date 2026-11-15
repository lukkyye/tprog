package logica.excepciones;
import jakarta.xml.ws.WebFault;

@WebFault
public class SpecialityNotFoundException extends Exception {

	public SpecialityNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public SpecialityNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public SpecialityNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public SpecialityNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public SpecialityNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
