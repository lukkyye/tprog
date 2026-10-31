package logica.excepciones;

import jakarta.xml.ws.WebFault;

@WebFault
public class DiaConsultaAlreadyExistsException extends Exception {

    public DiaConsultaAlreadyExistsException(String mensaje) {
        super(mensaje);
    }
}