package test;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import logica.clases.Especialidad;
import logica.controladores.ControladorClinica;
import logica.controladores.ControladorUsuario;
import logica.datatypes.DTDiaConsulta;
import logica.datatypes.DTDiaSemana;
import logica.datatypes.DTMedico;
import logica.datatypes.DTPaciente;
import logica.datatypes.DTUsuario;
import logica.datatypes.TipoMes;
import logica.excepciones.*;
import logica.manejadores.ManejadorEspecialidad;

class CargadorCSVTest {

	private static final ControladorUsuario controladorUsuario = new ControladorUsuario();
	private static final ControladorClinica controladorClinica = new ControladorClinica();
	private static final ManejadorEspecialidad manejadorEspecialidad = ManejadorEspecialidad.get_instance();
	
	@BeforeAll
	static void cargarDatos() {
		CargadorCSV cargador = new CargadorCSV();
		cargador.cargarTodo();
	}

	@Test
	void consultarUsuario_Medico(){
		//s.perez
		DTMedico dtmedico_expected = new DTMedico("bsilva", "REG1002", TipoMes.Julio, 5,"");
		DTUsuario dtusuario_expected = new DTUsuario("bsilva", "Bruno", "Silva", "bruno.silva@hospital.com");
		
		DTMedico dtmedico = controladorUsuario.listarDatosMedico("bsilva");
		DTUsuario dtusuario = controladorUsuario.listarUsuarios()
				.stream()
				.filter(u->u.nickname().equals("bsilva"))
				.findFirst()
				.orElse(null);
		
		assertAll(
				()->assertEquals(dtusuario, dtusuario_expected),
				()->assertEquals(dtmedico, dtmedico_expected)
				);
	}
	
	@Test
	void consultarUsuario_Paciente() {
		//String nickname,LocalDate fechaNac, int cantOrdenesAcum, int cantTicketAcum, ArrayList<String> antSalud, int doc, LocalDate fechaIngreso
		DTPaciente dtpaciente_expected = new DTPaciente("lgomez", LocalDate.of(1990, 4, 12), 0, 0, new ArrayList<String>(List.of("Sin antecedentes relevantes.")),45678901,LocalDate.of(2024, 3,1));
		DTUsuario dtusuario_expected = new DTUsuario("lgomez", "Laura", "Gómez", "laura.gomez@gmail.com");
		
		DTPaciente dtpaciente = controladorUsuario.listarDatosPaciente("lgomez");
		DTUsuario dtusuario = controladorUsuario.listarUsuarios()
				.stream()
				.filter(u->u.nickname().equals("lgomez"))
				.findFirst()
				.orElse(null);
		
		assertAll(
				()->assertEquals(dtpaciente, dtpaciente_expected),
				()->assertEquals(dtusuario, dtusuario_expected)
				);
	}
	
	@Test
	void consultarEspecialidad() {
		Especialidad especialidad = manejadorEspecialidad.getEspecialidad("Cardiología");
		assertAll(
				()->assertEquals("Cardiología", especialidad.getNombre()),
				()->assertEquals(20,especialidad.getPorcentajeAdicional())
				);
	}
	
	@Test
	void consultarDiaConsulta() {

	}
	
	@Test
	void unicidadNicknamePaciente_AltaDeUsuario() {
		assertThrows(NicknameAlreadyExistsException.class, ()->{controladorUsuario.ingresarPaciente("s.lopez", "asd", "asd", "asd2@", LocalDate.now(), LocalDate.now(), 54849457, null);});
	}
	@Test
	void unicidadEmailPaciente_AltaDeUsuario() {
		assertThrows(EmailAlreadyExistsException.class, ()->{controladorUsuario.ingresarPaciente("nick3", "asd", "asd", "carla.rodriguez@hospital.com", LocalDate.now(), LocalDate.now(), 54849459, null);});
	}
	
	@Test
	void unicidadNicknameMedico_AltaDeUsuario() {
		assertThrows(NicknameAlreadyExistsException.class, ()->{
			controladorUsuario.ingresarMedico("bsilva", "asd", "asd", "asd", "REG9999", TipoMes.Enero, 0, " ", new ArrayList<String>(List.of("Pediatría")));		
		});
	}
	@Test
	void unicidadNombreEspecialidad_AltaDeEspecialidad() {
		assertThrows(EspecialidadAlreadyExistsException.class, ()->{
			manejadorEspecialidad.altaEspecialidad("Pediatría", 1);
		;});
	}
	
	@Test
	void unicidadNombreClinica_AltaDeClinica() {
		controladorClinica.ingresarClinica("cl1", "", "", 1, 1, "");
		assertThrows(NombreClinicaAlreadyExistsException.class, ()->{
			controladorClinica.ingresarClinica("cl1", "asd", "asd", 1, 2, "web.com");
		});
	}
	
	@Test
	void medicoDebeTenerAlMenosUnaEspecialidad() {
		assertThrows(MedicoSinEspecialidadException.class, ()->{ controladorUsuario.ingresarMedico("nick44", "asd", "asd", "asd", "REG2003", TipoMes.Enero, 0, " ", new ArrayList<String>());});
	}
	
	@Test
	void unicidadReserva_ReservaDeConsulta() {
		assertThrows(ReservaAlreadyExistsException.class, ()->{controladorClinica.reservarConsulta("s.lopez", "Pediatría 080926", LocalDate.of(1990,9,4), false);});
	}
	
	@Test 
	void unicidadDiaDeConsulta_AltaDiaDeConsulta(){
		assertThrows(DiaConsultaAlreadyExistsException.class, ()->{
			controladorClinica.ingresarDiaConsulta("Pediatría - Martes", " ", DTDiaSemana.Martes, LocalTime.now(), "Pediatría", "CASMU");
		});
	}
	
	@Test
	void unicidadNumRegistro_AltaDeUsuario() {
		assertThrows(NumeroDeRegistroAlreadyExistsException.class, ()->{
			controladorUsuario.ingresarMedico("qwertyuiop", "asd", "asd", "asd", "REG1001", TipoMes.Enero, 0, " ", new ArrayList<String>(List.of("Pediatría")));		
		});
	}
	
	@Test
	void consultaFueraDeFecha() {
		assertThrows(FechaDiaSemanaInvalidaException.class, () -> {
			controladorClinica.ingresarConsulta("Pediatría - Martes", "bsilva", "asd", LocalDate.of(2000, 9, 7), 1, 1);
		});
	};
	
	
	@Test
	void reservarSinCupos() {
		assertThrows(NoNumberAvailableException.class, ()->{
			controladorClinica.reservarConsulta("mar.fernandez", "Pediatría 080926", LocalDate.of(1990, 9, 7), false);
		});
	}
	
	@Test
	void unicidadConsulta() {
		assertThrows(ConsultaNombreAlreadyExistsException.class, ()->{
			controladorClinica.ingresarConsulta("Pediatría - Martes", "bsilva", "Pediatría 080926", LocalDate.of(2026, 9, 15), 1, 1);
		});
	}
	
	
	
	

} 
