
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import  loader.CargadorCSV;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import  logica.clases.Especialidad;
import  logica.controladores.ControladorClinica;
import  logica.controladores.ControladorUsuario;
import  logica.datatypes.DtBeneficioAnual;
import  logica.datatypes.DtClinica;
import  logica.datatypes.DtConsulta;
import  logica.datatypes.DtDiaConsulta;
import  logica.datatypes.DtDiaSemana;
import  logica.datatypes.DtMedico;
import  logica.datatypes.DtPaciente;
import  logica.datatypes.DtResConsulta;
import  logica.datatypes.DtResConsultaDetalle;
import  logica.datatypes.DtUsuario;
import  logica.datatypes.EstadoConsulta;
import  logica.datatypes.TipoMes;
import  logica.excepciones.*;
import  logica.manejadores.ManejadorClinica;
import  logica.manejadores.ManejadorEspecialidad;

class CargadorCSVTest {

	private static final ControladorUsuario controladorUsuario = new ControladorUsuario();
	private static final ControladorClinica controladorClinica = new ControladorClinica();
	private static final ManejadorEspecialidad manejadorEspecialidad = ManejadorEspecialidad.getInstance();
	
	@BeforeAll
	static void cargarDatos() {
		CargadorCSV cargador = new CargadorCSV();
		cargador.cargarTodo();
	}

	@Test
	void consultarUsuario_Medico(){
		DtMedico dtmedico_expected = new DtMedico("bsilva", "REG1002", TipoMes.Julio, 5,"");
		DtUsuario dtusuario_expected = new DtUsuario("bsilva", "Bruno", "Silva", "bruno.silva@hospital.com");
		try {
			Set<DtConsulta> lis = controladorUsuario.listarConsultasAsociadas("bsilva");
		} catch (UserNotFoundException | ConsultaNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DtMedico dtmedico=assertDoesNotThrow(()->controladorUsuario.listarDatosMedico("bsilva"));
		DtUsuario dtusuario = controladorUsuario.listarUsuarios()
				.stream()
				.filter(u->u.getNickname().equals("bsilva"))
				.findFirst()
				.orElse(null);

		assertAll(
				()->assertEquals(dtusuario, dtusuario_expected),
				()->assertEquals(dtmedico, dtmedico_expected)
				);
	}

	@Test
	void consultarUsuario_Paciente() {
		DtPaciente dtpaciente_expected = new DtPaciente("lgomez", LocalDate.of(1990, 4, 12), 5, 7, new ArrayList<String>(List.of("Sin antecedentes relevantes.")),45678901,LocalDate.of(2024, 3,1));
		DtUsuario dtusuario_expected = new DtUsuario("lgomez", "Laura", "Gómez", "laura.gomez@gmail.com");
		DtPaciente dtpaciente = assertDoesNotThrow(()->controladorUsuario.listarDatosPaciente("lgomez"));
		try {
			Set<DtResConsulta> list = controladorUsuario.listarResConsultas("lgomez");
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DtUsuario dtusuario = controladorUsuario.listarUsuarios()
				.stream()
				.filter(u->u.getNickname().equals("lgomez"))
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
	    Especialidad especialidad = assertDoesNotThrow(
	        () -> manejadorEspecialidad.getEspecialidad("Cardiología")
	    );

	    assertAll(
	        () -> assertEquals("Cardiología", especialidad.getNombre()),
	        () -> assertEquals(20, especialidad.getPorcentajeAdicional())
	    );
	}

	@Test
	void consultarClinica() {
		DtClinica clinica_expected = new DtClinica("Hospital Británico", "Hospital general privado", "Av. Italia 2420", "https://www.hospitalbritanico.org.uy/", 350, 1200);
		String c = controladorClinica.listarClinicas()
                .stream()
                .filter(b -> b.equals("Hospital Británico"))
                .findFirst()
                .orElse(null);
		DtClinica nclinica = ManejadorClinica.getInstance().findClinica(c).getDt();
		
		assertAll(
				()->assertEquals(clinica_expected,nclinica)
			);
	}
	
	@Test
	void consultarDiaConsulta() {
		DtDiaConsulta diaconsulta_expected = new DtDiaConsulta("Cardiología - Lunes", "Atención cardiológica semanal", DtDiaSemana.Lunes, LocalTime.of(9, 00), 1440);
		DtDiaConsulta d = controladorClinica.listarDatosDiaConsulta()
                .stream()
                .filter(b -> b.getNomD().equals("Cardiología - Lunes"))
                .findFirst()
                .orElse(null);
		assertAll(
				()->assertEquals(diaconsulta_expected,d)
			);
	}

	@Test
	void consultarConsulta() {
		DtConsulta consulta_expected = new DtConsulta("Cardiología 240826", LocalDate.of(2026, 8, 24), 101, 3, 1656, EstadoConsulta.Aceptada );
		DtConsulta co = controladorClinica.listarConsultas()
                .stream()
                .filter(b -> b.getNomC().equals("Cardiología 240826"))
                .findFirst()
                .orElse(null);
		assertAll(
				()->assertEquals(consulta_expected,co)
			);
	}
	
	@Test
	void consultarDeReservaDeConsulta() {
		DtResConsultaDetalle reserva_expected = new DtResConsultaDetalle(LocalDate.of(2026, 8, 1), 1, false, "ana.perez", "Cardiología 240826", LocalDate.of(2026, 8, 24),  101, 3, 1656, 1656, "lgomez");
		DtPaciente dtpaciente_expected = new DtPaciente("lgomez", LocalDate.of(1990, 4, 12), 0, 0, new ArrayList<String>(List.of("Sin antecedentes relevantes.")),45678901,LocalDate.of(2024, 3,1));
		DtPaciente dtpaciente = assertDoesNotThrow(()->controladorUsuario.seleccionarPaciente("lgomez"));
		
		String nomP = dtpaciente.getNickname();
		String nomC = "Cardiología 240826";
		DtResConsultaDetalle res = assertDoesNotThrow(()->controladorUsuario.listarDatosResConsulta(nomC, nomP));
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
		assertDoesNotThrow(()->controladorClinica.ingresarClinica("cl1", "", "", 1, 1, ""));
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
			controladorClinica.ingresarDiaConsulta("Pediatría - Martes", " ", DtDiaSemana.Martes, LocalTime.now(), "Pediatría", "CASMU");
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
	    Set<DtConsulta> consultas =
	            controladorClinica.selecListaConsultaDelDia(
	                "Pediatría - Martes"
	            );

	    assertNotNull(consultas);
	    assertFalse(consultas.isEmpty());

	    assertTrue(
	        consultas.stream()
	            .anyMatch(c -> c.getNomC().equals("Pediatría 080926"))
	    );
	}
	
	@Test
	void listarPacientes() {
	    Set<DtPaciente> pacientes = controladorUsuario.listarPacientes();

	    assertNotNull(pacientes);
	    assertFalse(pacientes.isEmpty());

	    assertTrue(
	        pacientes.stream()
	            .anyMatch(p -> p.getNickname().equals("lgomez"))
	    );
	}
	
	@Test
	void listarMedicos() {
	    Set<DtMedico> medicos = controladorUsuario.listarMedicos();

	    assertNotNull(medicos);
	    assertFalse(medicos.isEmpty());

	    assertTrue(
	        medicos.stream()
	            .anyMatch(m -> m.getNickname().equals("bsilva"))
	    );
	}
	
	
	@Test
	void altaBeneficioAnual() {
	    try {
			controladorUsuario.altaBeneficioAnual("daniel.quintana22", 2030, 5, 10);
		} catch (UserNotFoundException | BeneficioAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    DtBeneficioAnual beneficio = assertDoesNotThrow(()->controladorUsuario.listarDatosBeneficioAnual("daniel.quintana22", 2030));
	    assertAll(
	        () -> assertEquals(2030, beneficio.getAnio()),
	        () -> assertEquals(5, beneficio.getCantOrdenes()),
	        () -> assertEquals(10, beneficio.getCantTicket())
	    );
	}

	@Test
	void altaBeneficioAnualDuplicado() {
	    assertDoesNotThrow(()->controladorUsuario.altaBeneficioAnual("daniel.quintana22", 2031, 5, 10));

	    assertThrows(BeneficioAlreadyExistsException.class, () -> {
	        controladorUsuario.altaBeneficioAnual("daniel.quintana22", 2031, 8, 20);
	    });
	}

	@Test
	void consultarBeneficiosAnuales() {
		assertDoesNotThrow(()->controladorUsuario.altaBeneficioAnual("lgomez", 2032, 3, 7));

	    Set<DtBeneficioAnual> beneficios = assertDoesNotThrow( ()->
	            controladorUsuario.listarBeneficiosAnuales("lgomez"));

	    assertTrue(
	        beneficios.stream()
	            .anyMatch(b -> b.getAnio() == 2032
	                    && b.getCantOrdenes() == 3
	                    && b.getCantTicket() == 7)
	    );
	}

	@Test
	void consultarDatosBeneficioAnual() {
		assertDoesNotThrow(()->controladorUsuario.altaBeneficioAnual("emilia.ramos23", 2033, 4, 8));

	    DtBeneficioAnual beneficio =assertDoesNotThrow(()->
	            controladorUsuario.listarDatosBeneficioAnual("emilia.ramos23", 2033));

	    assertEquals(2033, beneficio.getAnio());
	    assertEquals(4, beneficio.getCantOrdenes());
	    assertEquals(8, beneficio.getCantTicket());
	}
	
	@Test
	void modificarPerfilPaciente() {
	    assertDoesNotThrow(()->controladorUsuario.modificarPaciente(
	        "lgomez",
	        "LauraNueva",
	        "GomezNueva",
	        LocalDate.of(1991, 5, 10),
	        LocalDate.of(2024, 3, 1),
	        45678901,
	        new ArrayList<>(List.of("Antecedente nuevo"))
	    ));

	    DtPaciente paciente = assertDoesNotThrow(()->
	            controladorUsuario.listarDatosPaciente("lgomez"));

	    assertAll(
	        () -> assertEquals("LauraNueva",
	                controladorUsuario.listarUsuarios().stream()
	                    .filter(u -> u.getNickname().equals("lgomez"))
	                    .findFirst()
	                    .get()
	                    .getNombre()),

	        () -> assertEquals("GomezNueva",
	                controladorUsuario.listarUsuarios().stream()
	                    .filter(u -> u.getNickname().equals("lgomez"))
	                    .findFirst()
	                    .get()
	                    .getApellido()),

	        () -> assertEquals(
	                LocalDate.of(1991, 5, 10),
	                paciente.getFechaNac()),

	        () -> assertEquals(
	                LocalDate.of(2024, 3, 1),
	                paciente.getFechaIngreso()),

	        () -> assertEquals(45678901, paciente.getDoc()),

	        () -> assertEquals(
	                List.of("Antecedente nuevo"),
	                paciente.getAntSalud())
	    );
	}
	
	@Test
	void modificarPerfilMedico() {
		assertDoesNotThrow( ()->
	    controladorUsuario.modificarMedico(
	        "bsilva",
	        "BrunoNuevo",
	        "SilvaNuevo",
	        "REG9998",
	        TipoMes.Agosto,
	        15,
	        "https://nuevo-link.com",
	        new ArrayList<>(List.of("Cardiología"))
	    ));

	    DtMedico medico = assertDoesNotThrow(()->
	            controladorUsuario.listarDatosMedico("bsilva"));

	    assertAll(
	        () -> assertEquals("BrunoNuevo",
	                controladorUsuario.listarUsuarios().stream()
	                    .filter(u -> u.getNickname().equals("bsilva"))
	                    .findFirst()
	                    .get()
	                    .getNombre()),

	        () -> assertEquals("SilvaNuevo",
	                controladorUsuario.listarUsuarios().stream()
	                    .filter(u -> u.getNickname().equals("bsilva"))
	                    .findFirst()
	                    .get()
	                    .getApellido()),

	        () -> assertEquals("REG9998", medico.getNroReg()),
	        () -> assertEquals(TipoMes.Agosto, medico.getMesLic()),
	        () -> assertEquals(15, medico.getPorcentajeCost()),
	        () -> assertEquals("https://nuevo-link.com", medico.getLink())
	    );
	}

	@Test
	void aceptarConsulta() {
		assertDoesNotThrow(()->
	    controladorClinica.setEstadoConsulta(
	        EstadoConsulta.Aceptada,
	        "Pediatría 080926"
	    ));

	    DtConsulta consulta = assertDoesNotThrow(()->
	            controladorClinica.seleccionarConsulta("Pediatría 080926"));

	    assertEquals(
	        EstadoConsulta.Aceptada,
	        consulta.getEstado()
	    );
	}

	@Test
	void rechazarConsulta() {
		assertDoesNotThrow(()->
	    controladorClinica.setEstadoConsulta(
	        EstadoConsulta.Rechazada,
	        "Pediatría 080926"
	    ));

	    DtConsulta consulta = assertDoesNotThrow(()->
	            controladorClinica.seleccionarConsulta("Pediatría 080926"));

	    assertEquals(
	        EstadoConsulta.Rechazada,
	        consulta.getEstado()
	    );
	}
} 
