package logica.excepciones;
import jakarta.xml.ws.WebFault;

@WebFault
public class NicknameAlreadyExistsException extends Exception {

	private static final long serialVersionUID = -8639665549147272081L;

	public NicknameAlreadyExistsException() {
		// TODO Auto-generated constructor stub
	}

	public NicknameAlreadyExistsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NicknameAlreadyExistsException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public NicknameAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NicknameAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
