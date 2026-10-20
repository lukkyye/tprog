package test;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import logica.clases.Especialidad;
import logica.controladores.ControladorClinica;
import logica.controladores.ControladorUsuario;
import logica.datatypes.DTBeneficioAnual;
import logica.datatypes.DTClinica;
import logica.datatypes.DTConsulta;
import logica.datatypes.DTDiaConsulta;
import logica.datatypes.DTDiaSemana;
import logica.datatypes.DTMedico;
import logica.datatypes.DTPaciente;
import logica.datatypes.DTResConsulta;
import logica.datatypes.DTResConsultaDetalle;
import logica.datatypes.DTUsuario;
import logica.datatypes.EstadoConsulta;
import logica.datatypes.TipoMes;
import logica.excepciones.*;
import logica.manejadores.ManejadorClinica;
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
		DTMedico dtmedico_expected = new DTMedico("bsilva", "REG1002", TipoMes.Julio, 5,"");
		DTUsuario dtusuario_expected = new DTUsuario("bsilva", "Bruno", "Silva", "bruno.silva@hospital.com");
		HashSet<DTConsulta> lis = controladorUsuario.listarConsultasAsociadas("bsilva");
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
		DTPaciente dtpaciente_expected = new DTPaciente("lgomez", LocalDate.of(1990, 4, 12), 5, 7, new ArrayList<String>(List.of("Sin antecedentes relevantes.")),45678901,LocalDate.of(2024, 3,1));
		DTUsuario dtusuario_expected = new DTUsuario("lgomez", "Laura", "Gómez", "laura.gomez@gmail.com");
		DTPaciente dtpaciente = controladorUsuario.listarDatosPaciente("lgomez");
		HashSet<DTResConsulta> list = controladorUsuario.listarResConsultas("lgomez");
		DTUsuario dtusuario = controladorUsuario.listarUsuarios()
				.stream()
				.filter(u->u.nickname().equals("lgomez"))
				.findFirst()
				.orElse(null);
		System.out.println("ESPERADO: " + dtpaciente_expected);
		System.out.println("OBTENIDO: " + dtpaciente);
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
	void consultarDeReservaDeConsulta() {
		DTResConsultaDetalle reserva_expected = new DTResConsultaDetalle(LocalDate.of(2026, 8, 1), 1, false, "ana.perez", "Cardiología 240826", LocalDate.of(2026, 8, 24),  101, 3, 1656, 1656);
		DTPaciente dtpaciente_expected = new DTPaciente("lgomez", LocalDate.of(1990, 4, 12), 0, 0, new ArrayList<String>(List.of("Sin antecedentes relevantes.")),45678901,LocalDate.of(2024, 3,1));
		DTPaciente dtpaciente = controladorUsuario.seleccionarPaciente("lgomez");
		
		String nomP = dtpaciente.nickname();
		String nomC = "Cardiología 240826";
		DTResConsultaDetalle res = controladorUsuario.listarDatosResConsulta(nomC, nomP);
		assertAll(
				()->assertEquals(reserva_expected,res)
			);
		
		
		//hacer
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
	
	@Test
	void consultarConsultasDeUnDia() {
	    Set<DTConsulta> consultas =
	            controladorClinica.selecListaConsultaDelDia(
	                "Pediatría - Martes"
	            );

	    assertNotNull(consultas);
	    assertFalse(consultas.isEmpty());

	    assertTrue(
	        consultas.stream()
	            .anyMatch(c -> c.nomC().equals("Pediatría 080926"))
	    );
	}
	
	@Test
	void listarPacientes() {
	    HashSet<DTPaciente> pacientes = controladorUsuario.listarPacientes();

	    assertNotNull(pacientes);
	    assertFalse(pacientes.isEmpty());

	    assertTrue(
	        pacientes.stream()
	            .anyMatch(p -> p.nickname().equals("lgomez"))
	    );
	}
	
	@Test
	void listarMedicos() {
	    HashSet<DTMedico> medicos = controladorUsuario.listarMedicos();

	    assertNotNull(medicos);
	    assertFalse(medicos.isEmpty());

	    assertTrue(
	        medicos.stream()
	            .anyMatch(m -> m.nickname().equals("bsilva"))
	    );
	}
	
	
	@Test
	void altaBeneficioAnual() {
	    controladorUsuario.altaBeneficioAnual("daniel.quintana22", 2030, 5, 10);

	    DTBeneficioAnual beneficio =
	            controladorUsuario.listarDatosBeneficioAnual("daniel.quintana22", 2030);

	    assertAll(
	        () -> assertEquals(2030, beneficio.anio()),
	        () -> assertEquals(5, beneficio.cantOrdenes()),
	        () -> assertEquals(10, beneficio.cantTicket())
	    );
	}

	@Test
	void altaBeneficioAnualDuplicado() {
	    controladorUsuario.altaBeneficioAnual("daniel.quintana22", 2031, 5, 10);

	    assertThrows(BeneficioAlreadyExistsException.class, () -> {
	        controladorUsuario.altaBeneficioAnual("daniel.quintana22", 2031, 8, 20);
	    });
	}

	@Test
	void consultarBeneficiosAnuales() {
	    controladorUsuario.altaBeneficioAnual("lgomez", 2032, 3, 7);

	    HashSet<DTBeneficioAnual> beneficios =
	            controladorUsuario.listarBeneficiosAnuales("lgomez");

	    assertTrue(
	        beneficios.stream()
	            .anyMatch(b -> b.anio() == 2032
	                    && b.cantOrdenes() == 3
	                    && b.cantTicket() == 7)
	    );
	}

	@Test
	void consultarDatosBeneficioAnual() {
	    controladorUsuario.altaBeneficioAnual("emilia.ramos23", 2033, 4, 8);

	    DTBeneficioAnual beneficio =
	            controladorUsuario.listarDatosBeneficioAnual("emilia.ramos23", 2033);

	    assertEquals(2033, beneficio.anio());
	    assertEquals(4, beneficio.cantOrdenes());
	    assertEquals(8, beneficio.cantTicket());
	}
	
	@Test
	void modificarPerfilPaciente() {
	    controladorUsuario.modificarPaciente(
	        "lgomez",
	        "LauraNueva",
	        "GomezNueva",
	        LocalDate.of(1991, 5, 10),
	        LocalDate.of(2024, 3, 1),
	        45678901,
	        new ArrayList<>(List.of("Antecedente nuevo"))
	    );

	    DTPaciente paciente =
	            controladorUsuario.listarDatosPaciente("lgomez");

	    assertAll(
	        () -> assertEquals("LauraNueva",
	                controladorUsuario.listarUsuarios().stream()
	                    .filter(u -> u.nickname().equals("lgomez"))
	                    .findFirst()
	                    .get()
	                    .nombre()),

	        () -> assertEquals("GomezNueva",
	                controladorUsuario.listarUsuarios().stream()
	                    .filter(u -> u.nickname().equals("lgomez"))
	                    .findFirst()
	                    .get()
	                    .apellido()),

	        () -> assertEquals(
	                LocalDate.of(1991, 5, 10),
	                paciente.fechaNac()),

	        () -> assertEquals(
	                LocalDate.of(2024, 3, 1),
	                paciente.fechaIngreso()),

	        () -> assertEquals(45678901, paciente.doc()),

	        () -> assertEquals(
	                List.of("Antecedente nuevo"),
	                paciente.antSalud())
	    );
	}
	
	@Test
	void modificarPerfilMedico() {
	    controladorUsuario.modificarMedico(
	        "bsilva",
	        "BrunoNuevo",
	        "SilvaNuevo",
	        "REG9998",
	        TipoMes.Agosto,
	        15,
	        "https://nuevo-link.com",
	        new ArrayList<>(List.of("Cardiología"))
	    );

	    DTMedico medico =
	            controladorUsuario.listarDatosMedico("bsilva");

	    assertAll(
	        () -> assertEquals("BrunoNuevo",
	                controladorUsuario.listarUsuarios().stream()
	                    .filter(u -> u.nickname().equals("bsilva"))
	                    .findFirst()
	                    .get()
	                    .nombre()),

	        () -> assertEquals("SilvaNuevo",
	                controladorUsuario.listarUsuarios().stream()
	                    .filter(u -> u.nickname().equals("bsilva"))
	                    .findFirst()
	                    .get()
	                    .apellido()),

	        () -> assertEquals("REG9998", medico.nroReg()),
	        () -> assertEquals(TipoMes.Agosto, medico.mesLic()),
	        () -> assertEquals(15, medico.porcentajeCost()),
	        () -> assertEquals("https://nuevo-link.com", medico.link())
	    );
	}

	@Test
	void aceptarConsulta() {
	    controladorClinica.setEstadoConsulta(
	        EstadoConsulta.Aceptada,
	        "Pediatría 080926"
	    );

	    DTConsulta consulta =
	            controladorClinica.seleccionarConsulta("Pediatría 080926");

	    assertEquals(
	        EstadoConsulta.Aceptada,
	        consulta.estado()
	    );
	}

	@Test
	void rechazarConsulta() {
	    controladorClinica.setEstadoConsulta(
	        EstadoConsulta.Rechazada,
	        "Pediatría 080926"
	    );

	    DTConsulta consulta =
	            controladorClinica.seleccionarConsulta("Pediatría 080926");

	    assertEquals(
	        EstadoConsulta.Rechazada,
	        consulta.estado()
	    );
	}
} 
