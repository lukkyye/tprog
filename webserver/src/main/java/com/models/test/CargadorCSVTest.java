package com.models.test;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.models.clases.Especialidad;
import com.models.controladores.ControladorClinica;
import com.models.controladores.ControladorUsuario;
import com.models.datatypes.DTClinica;
import com.models.datatypes.DTConsulta;
import com.models.datatypes.DTDiaConsulta;
import com.models.datatypes.DTDiaSemana;
import com.models.datatypes.DTMedico;
import com.models.datatypes.DTPaciente;
import com.models.datatypes.DTResConsulta;
import com.models.datatypes.DTResConsultaDetalle;
import com.models.datatypes.DTUsuario;
import com.models.datatypes.EstadoConsulta;
import com.models.datatypes.TipoMes;
import com.models.excepciones.*;
import com.models.manejadores.ManejadorClinica;
import com.models.manejadores.ManejadorEspecialidad;
import com.gateway.delegators.*;

class CargadorCSVTest {

	private static final UserControllerDelegator controladorUsuario = new UserControllerDelegator();
	private static final ClinicControllerDelegator controladorClinica = new ClinicControllerDelegator();
	private static final ManejadorEspecialidad manejadorEspecialidad = ManejadorEspecialidad.get_instance();
	
	@BeforeAll
	static void cargarDatos() {
		CargadorCSV cargador = new CargadorCSV();
		cargador.cargarTodo();
	}

	@Test
	void consultarUsuario_Medico(){
		DTMedico dtmedico_expected = new DTMedico("bsilva", "REG1002", TipoMes.Julio, 5,"");
		DTUsuario dtusuario_expected = new DTUsuario("bsilva", "Bruno", "Silva", "bruno.silva@hospital.com");
		Set<DTConsulta> lis = controladorUsuario.listarConsultasAsociadas("bsilva");
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
		DTPaciente dtpaciente_expected = new DTPaciente("lgomez", LocalDate.of(1990, 4, 12), 0, 0, new ArrayList<String>(List.of("Sin antecedentes relevantes.")),45678901,LocalDate.of(2024, 3,1));
		DTUsuario dtusuario_expected = new DTUsuario("lgomez", "Laura", "Gómez", "laura.gomez@gmail.com");
		DTPaciente dtpaciente = controladorUsuario.listarDatosPaciente("lgomez");
		Set<DTResConsulta> list = controladorUsuario.listarResConsultas("lgomez");
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
	void consultarClinica() {
		DTClinica clinica_expected = new DTClinica("Hospital Británico", "Hospital general privado", "Av. Italia 2420", "https://www.hospitalbritanico.org.uy/", 350, 1200);
		String c = controladorClinica.listarClinicas()
                .stream()
                .filter(b -> b.equals("Hospital Británico"))
                .findFirst()
                .orElse(null);
		DTClinica nclinica = ManejadorClinica.get_instance().findClinica(c).get_dt();
		
		assertAll(
				()->assertEquals(clinica_expected,nclinica)
			);
	}
	
	@Test
	void consultarDiaConsulta() {
		DTDiaConsulta diaconsulta_expected = new DTDiaConsulta("Cardiología - Lunes", "Atención cardiológica semanal", DTDiaSemana.Lunes, LocalTime.of(9, 00), 1440);
		DTDiaConsulta d = controladorClinica.listarDatosDiaConsulta()
                .stream()
                .filter(b -> b.nomD().equals("Cardiología - Lunes"))
                .findFirst()
                .orElse(null);
		assertAll(
				()->assertEquals(diaconsulta_expected,d)
			);
	}

	@Test
	void consultarConsulta() {
		DTConsulta consulta_expected = new DTConsulta("Cardiología 240826", LocalDate.of(2026, 8, 24), 101, 3, 1656, EstadoConsulta.Aceptada );
		DTConsulta co = controladorClinica.listarConsultas()
                .stream()
                .filter(b -> b.nomC().equals("Cardiología 240826"))
                .findFirst()
                .orElse(null);
		assertAll(
				()->assertEquals(consulta_expected,co)
			);
	}
	

	@Test
	void unicidadNicknamePaciente_AltaDeUsuario() {
		assertThrows(NicknameAlreadyExistsException.class, ()->{controladorUsuario.ingresarPaciente("s.lopez", "", "asd", "asd", "asd2@", LocalDate.now(), LocalDate.now(), 54849457, null);});
	}
	@Test
	void unicidadEmailPaciente_AltaDeUsuario() {
		assertThrows(EmailAlreadyExistsException.class, ()->{controladorUsuario.ingresarPaciente("nick3", "", "asd", "asd", "carla.rodriguez@hospital.com", LocalDate.now(), LocalDate.now(), 54849459, null);});
	}
	
	@Test
	void unicidadNicknameMedico_AltaDeUsuario() {
		assertThrows(NicknameAlreadyExistsException.class, ()->{
			controladorUsuario.ingresarMedico("bsilva", "","asd", "asd", "asd", "REG9999", TipoMes.Enero, 0, " ", new ArrayList<String>(List.of("Pediatría")));		
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
		assertThrows(MedicoSinEspecialidadException.class, ()->{ controladorUsuario.ingresarMedico("nick44", "", "asd", "asd", "asd", "REG2003", TipoMes.Enero, 0, " ", new ArrayList<String>());});
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
			controladorUsuario.ingresarMedico("qwertyuiop", "", "asd", "asd", "asd", "REG1001", TipoMes.Enero, 0, " ", new ArrayList<String>(List.of("Pediatría")));		
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
