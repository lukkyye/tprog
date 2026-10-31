package logica.excepciones;

import jakarta.xml.ws.WebFault;

@WebFault
public class BeneficioSinOrdenesException extends Exception {

	public BeneficioSinOrdenesException() {
		// TODO Auto-generated constructor stub
	}

	public BeneficioSinOrdenesException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BeneficioSinOrdenesException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public BeneficioSinOrdenesException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BeneficioSinOrdenesException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
