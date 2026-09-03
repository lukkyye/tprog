package test;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import logica.controladores.ControladorClinica;
import logica.controladores.ControladorUsuario;
import logica.excepciones.*;
import logica.manejadores.ManejadorEspecialidad;

class CargadorCSVTest {

	private static final ControladorUsuario controladorUsuario = new ControladorUsuario();
	private static final ControladorClinica controladorClinica = new ControladorClinica();
	private static final ManejadorEspecialidad manejadorEspecialidad = ManejadorEspecialidad.get_instance();
	

	@Test
	void unicidadNicknamePaciente_AltaDeUsuario() {
		controladorUsuario.ingresarPaciente("nick1", "asd", "asd", "asd1@", LocalDate.now(), LocalDate.now(), 54849456, null);
		assertThrows(NicknameAlreadyExistsException.class, ()->{controladorUsuario.ingresarPaciente("nick1", "asd", "asd", "asd2@", LocalDate.now(), LocalDate.now(), 54849457, null);});
	}
	@Test
	void unicidadEmailPaciente_AltaDeUsuario() {
		controladorUsuario.ingresarPaciente("nick2", "asd2", "asd2", "asd@", LocalDate.now(), LocalDate.now(), 54849458, null);
		assertThrows(EmailAlreadyExistsException.class, ()->{controladorUsuario.ingresarPaciente("nick3", "asd", "asd", "asd@", LocalDate.now(), LocalDate.now(), 54849459, null);});
	}
	
	@Test
	void unicidadNombreEspecialidad_AltaDeEspecialidad() {
		manejadorEspecialidad.altaEspecialidad("esp1", 0);
		assertThrows(EspecialidadAlreadyExistsException.class, ()->{
			manejadorEspecialidad.altaEspecialidad("esp1", 1);
		;});
	}
	
	@Test
	void unicidadNombreClinica_AltaDeClinica() {
		controladorClinica.ingresarClinica("cl1", "", "", 1, 1, "");
		assertThrows(NombreClinicaAlreadyExistsException.class, ()->{
			controladorClinica.ingresarClinica("cl1", "asd", "asd", 1, 2, "web.com");
		});
	}
}
