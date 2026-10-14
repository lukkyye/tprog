package logica.excepciones;

public class NombreClinicaAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NombreClinicaAlreadyExistsException (){
		super();
	}

	public NombreClinicaAlreadyExistsException(String message) {
		super(message);
	}

	public NombreClinicaAlreadyExistsException(Throwable cause) {
		super(cause);
	}

	public NombreClinicaAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public NombreClinicaAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}