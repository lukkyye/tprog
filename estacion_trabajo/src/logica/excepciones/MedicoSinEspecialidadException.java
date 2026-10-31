package logica.excepciones;
import jakarta.xml.ws.WebFault;

@WebFault
public class MedicoSinEspecialidadException extends Exception {

	public MedicoSinEspecialidadException() {
		// TODO Auto-generated constructor stub
	}

	public MedicoSinEspecialidadException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public MedicoSinEspecialidadException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public MedicoSinEspecialidadException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public MedicoSinEspecialidadException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
