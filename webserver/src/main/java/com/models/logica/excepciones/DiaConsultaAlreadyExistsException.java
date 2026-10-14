package com.models.logica.excepciones;

public class DiaConsultaAlreadyExistsException extends RuntimeException {

    public DiaConsultaAlreadyExistsException(String mensaje) {
        super(mensaje);
    }
}