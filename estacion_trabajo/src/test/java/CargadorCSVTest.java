
import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
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
import logica.datatypes.DtConsultaDetalle;
import  logica.datatypes.DtDiaConsulta;
import  logica.datatypes.DtDiaSemana;
import logica.datatypes.DtMedicamento;
import  logica.datatypes.DtMedico;
import  logica.datatypes.DtPaciente;
import logica.datatypes.DtResAsistio;
import  logica.datatypes.DtResConsulta;
import  logica.datatypes.DtResConsultaDetalle;
import logica.datatypes.DtSolicitudMedicamento;
import  logica.datatypes.DtUsuario;
import logica.datatypes.DtValoracionConsulta;
import  logica.datatypes.EstadoConsulta;
import  logica.datatypes.TipoMes;
import  logica.excepciones.*;
import  logica.manejadores.ManejadorClinica;
import  logica.manejadores.ManejadorEspecialidad;
import logica.manejadores.ManejadorImagen;

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
	void consultarUsuario_Medico() {

	    DtMedico dtmedico_expected = new DtMedico("bsilva", "REG1002", TipoMes.Julio, 5,"");
	    DtUsuario dtusuario_expected = new DtUsuario("bsilva", "Bruno", "Silva", "bruno.silva@hospital.com");

	    try {
	        Set<DtConsulta> lis = controladorUsuario.listarConsultasAsociadas("bsilva");
	    } catch (UserNotFoundException | ConsultaNotFoundException e) {
	        e.printStackTrace();
	    }

	    DtMedico dtmedico = assertDoesNotThrow(()->controladorUsuario.listarDatosMedico("bsilva"));

	    DtUsuario dtusuario = controladorUsuario.listarUsuarios()
	            .stream()
	            .filter(u->u.getNickname().equals("bsilva"))
	            .findFirst()
	            .orElse(null);

	    assertAll(
	        () -> assertEquals(dtmedico_expected.getNickname(), dtmedico.getNickname()),
	        () -> assertEquals(dtmedico_expected.getNroReg(), dtmedico.getNroReg()),
	        () -> assertEquals(dtmedico_expected.getMesLic(), dtmedico.getMesLic()),
	        () -> assertEquals(dtmedico_expected.getPorcentajeCost(), dtmedico.getPorcentajeCost()),
	        () -> assertEquals(dtmedico_expected.getLink(), dtmedico.getLink()),

	        () -> assertEquals(dtusuario_expected.getNickname(), dtusuario.getNickname()),
	        () -> assertEquals(dtusuario_expected.getNombre(), dtusuario.getNombre()),
	        () -> assertEquals(dtusuario_expected.getApellido(), dtusuario.getApellido()),
	        () -> assertEquals(dtusuario_expected.getCorreo(), dtusuario.getCorreo())
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
	        e.printStackTrace();
	    }

	    DtUsuario dtusuario = controladorUsuario.listarUsuarios()
	            .stream()
	            .filter(u->u.getNickname().equals("lgomez"))
	            .findFirst()
	            .orElse(null);

	    assertAll(
	        () -> assertEquals(dtpaciente_expected.getNickname(), dtpaciente.getNickname()),
	        () -> assertEquals(dtpaciente_expected.getFechaNac(), dtpaciente.getFechaNac()),
	        () -> assertEquals(dtpaciente_expected.getCantOrdenesAcum(), dtpaciente.getCantOrdenesAcum()),
	        () -> assertEquals(dtpaciente_expected.getCantTicketAcum(), dtpaciente.getCantTicketAcum()),
	        () -> assertEquals(dtpaciente_expected.getAntSalud(), dtpaciente.getAntSalud()),
	        () -> assertEquals(dtpaciente_expected.getDoc(), dtpaciente.getDoc()),
	        () -> assertEquals(dtpaciente_expected.getFechaIngreso(), dtpaciente.getFechaIngreso()),

	        () -> assertEquals(dtusuario_expected.getNickname(), dtusuario.getNickname()),
	        () -> assertEquals(dtusuario_expected.getNombre(), dtusuario.getNombre()),
	        () -> assertEquals(dtusuario_expected.getApellido(), dtusuario.getApellido()),
	        () -> assertEquals(dtusuario_expected.getCorreo(), dtusuario.getCorreo())
	    );
	}

	@Test
	void consultarEspecialidad() {
		manejadorEspecialidad.getEspecialidades();
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
	        () -> assertEquals(clinica_expected.getNombre(), nclinica.getNombre()),
	        () -> assertEquals(clinica_expected.getDesc(), nclinica.getDesc()),
	        () -> assertEquals(clinica_expected.getDir(), nclinica.getDir()),
	        () -> assertEquals(clinica_expected.getWeb(), nclinica.getWeb()),
	        () -> assertEquals(clinica_expected.getCTicket(), nclinica.getCTicket()),
	        () -> assertEquals(clinica_expected.getCConsulta(), nclinica.getCConsulta())
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
	        () -> assertEquals(diaconsulta_expected.getNomD(), d.getNomD()),
	        () -> assertEquals(diaconsulta_expected.getDescD(), d.getDescD()),
	        () -> assertEquals(diaconsulta_expected.getDia(), d.getDia()),
	        () -> assertEquals(diaconsulta_expected.getHoraIni(), d.getHoraIni()),
	        () -> assertEquals(diaconsulta_expected.getCostoD(), d.getCostoD())
	    );
	}

	@Test
	void consultarConsulta() {
	    DtConsulta consulta_expected = new DtConsulta(
	        "Cardiología 240826",
	        LocalDate.of(2026, 8, 24),
	        101, 3, 1656,
	        EstadoConsulta.Aceptada
	    );

	    DtConsulta co = controladorClinica.listarConsultas()
	            .stream()
	            .filter(b -> b.getNomC().equals("Cardiología 240826"))
	            .findFirst()
	            .orElse(null);

	    assertAll(
	        () -> assertEquals(consulta_expected.getNomC(), co.getNomC()),
	        () -> assertEquals(consulta_expected.getFechaC(), co.getFechaC()),
	        () -> assertEquals(consulta_expected.getSala(), co.getSala()),
	        () -> assertEquals(consulta_expected.getCantNum(), co.getCantNum()),
	        () -> assertEquals(consulta_expected.getCostoC(), co.getCostoC()),
	        () -> assertEquals(consulta_expected.getEstado(), co.getEstado())
	    );
	}
	
	@Test
	void consultarDeReservaDeConsulta() {
	    DtResConsultaDetalle reserva_expected = new DtResConsultaDetalle(
	        LocalDate.of(2026, 8, 1),
	        1,
	        false,
	        "ana.perez",
	        "Cardiología 240826",
	        LocalDate.of(2026, 8, 24),
	        101, 3, 1656, 1656,
	        "lgomez"
	    );

	    String nomP = "lgomez";
	    String nomC = "Cardiología 240826";

	    DtResConsultaDetalle res = assertDoesNotThrow(
	        () -> controladorUsuario.listarDatosResConsulta(nomC, nomP)
	    );

	    assertAll(
	        () -> assertEquals(reserva_expected.getFechaRes(), res.getFechaRes()),
	        () -> assertEquals(reserva_expected.getNumReserva(), res.getNumReserva()),
	        () -> assertEquals(reserva_expected.getAplicaBenef(), res.getAplicaBenef()),
	        () -> assertEquals(reserva_expected.getMedicoC(), res.getMedicoC()),
	        () -> assertEquals(reserva_expected.getNomC(), res.getNomC()),
	        () -> assertEquals(reserva_expected.getFechaC(), res.getFechaC()),
	        () -> assertEquals(reserva_expected.getSalaC(), res.getSalaC()),
	        () -> assertEquals(reserva_expected.getCantNumC(), res.getCantNumC()),
	        () -> assertEquals(reserva_expected.getCostoC(), res.getCostoC()),
	        () -> assertEquals(reserva_expected.getCostoR(), res.getCostoR()),
	        () -> assertEquals(reserva_expected.getNicknamePacienteRes(), res.getNicknamePacienteRes())
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
    void recetarMedicamento_DuplicadoContraRegistroDelCSV() {
        assertThrows(MedicamentoAlreadyExistsException.class, () ->
                controladorUsuario.recetarMedicamento(
                        "lgomez",
                        "Cardiología 240826",
                        "Medicamento A",
                        "1 unidad cada 24 horas durante 7 días",
                        LocalDate.of(2026, 8, 24)
                )
        );
    }
	
	@Test
	void listarDatosConsultaUsuario_CSV() throws Exception {
	    DtConsultaDetalle detalle = controladorUsuario.listarDatosConsulta("Cardiología 240826");

	    assertAll(
	        () -> assertEquals("Cardiología 240826", detalle.getNomC()),
	        () -> assertEquals("ana.perez", detalle.getMedico()),
	        () -> assertEquals(LocalDate.of(2026, 8, 24), detalle.getFechaC()),
	        () -> assertEquals(101, detalle.getSala()),
	        () -> assertEquals(3, detalle.getCantNum()),
	        () -> assertEquals(1656f, detalle.getCostoC(), 0.01f),
	        () -> assertEquals(EstadoConsulta.Aceptada, detalle.getEstado()),
	        () -> assertEquals(2, detalle.getReservas().size())
	    );

	    DtConsultaDetalle sinReservas = controladorUsuario.listarDatosConsulta("Cardiología 130826");
	    assertAll(
	        () -> assertEquals("Cardiología 130826", sinReservas.getNomC()),
	        () -> assertEquals("carla.rodriguez", sinReservas.getMedico()),
	        () -> assertEquals(EstadoConsulta.Rechazada, sinReservas.getEstado()),
	        () -> assertTrue(sinReservas.getReservas().isEmpty())
	    );

	    assertThrows(ConsultaNotFoundException.class, () ->
	        controladorUsuario.listarDatosConsulta("Consulta Inexistente XYZ")
	    );
	}
	
	@Test
	void listarDatosConsulta_CSV() throws Exception {
	    DtConsultaDetalle detalle = controladorClinica.listarDatosConsulta("Cardiología 240826");

	    assertAll(
	        () -> assertEquals("Cardiología 240826", detalle.getNomC()),
	        () -> assertEquals("ana.perez", detalle.getMedico()),
	        () -> assertEquals(LocalDate.of(2026, 8, 24), detalle.getFechaC()),
	        () -> assertEquals(101, detalle.getSala()),
	        () -> assertEquals(3, detalle.getCantNum()),
	        () -> assertEquals(1656f, detalle.getCostoC(), 0.01f),
	        () -> assertEquals(EstadoConsulta.Aceptada, detalle.getEstado()),
	        () -> assertEquals(2, detalle.getReservas().size())
	    );

	    assertThrows(ConsultaNotFoundException.class, () ->
	        controladorClinica.listarDatosConsulta("Consulta Inexistente XYZ")
	    );
	}

	@Test
	void listarDatosResConsultaCC_CSV() throws Exception {
	    // Reserva 1 de Cardiología 240826 -> RC01 (lgomez)
	    DtResConsultaDetalle res1 = controladorClinica.listarDatosResConsultaCC("Cardiología 240826", 1);
	    assertNotNull(res1);
	    assertAll(
	        () -> assertEquals("Cardiología 240826", res1.getNomC()),
	        () -> assertEquals("ana.perez", res1.getMedicoC()),
	        () -> assertEquals("lgomez", res1.getNicknamePacienteRes()),
	        () -> assertEquals(1, res1.getNumReserva()),
	        () -> assertEquals(LocalDate.of(2026, 8, 1), res1.getFechaRes()),
	        () -> assertFalse(res1.getAplicaBenef()),
	        () -> assertEquals(1656f, res1.getCostoR(), 0.01f)
	    );

	    // Reserva 2 de Cardiología 240826 -> RC02 (mar.fernandez, mayor de 60 años)
	    DtResConsultaDetalle res2 = controladorClinica.listarDatosResConsultaCC("Cardiología 240826", 2);
	    assertNotNull(res2);
	    assertEquals("mar.fernandez", res2.getNicknamePacienteRes());
	    assertEquals(1324.8f, res2.getCostoR(), 0.01f);

	    // Número de reserva inexistente en consulta existente devuelve null
	    assertNull(controladorClinica.listarDatosResConsultaCC("Cardiología 240826", 99));

	    // Consulta inexistente lanza ConsultaNotFoundException
	    assertThrows(ConsultaNotFoundException.class, () ->
	        controladorClinica.listarDatosResConsultaCC("Consulta Inexistente XYZ", 1)
	    );
	}

	@Test
	void getReservasConsulta_CSV() throws Exception {
	    Set<DtResConsulta> reservas = controladorClinica.getReservasConsulta("Cardiología 240826");

	    assertEquals(2, reservas.size());
	    assertTrue(reservas.stream().anyMatch(r -> r.getNumReserva() == 1 && r.getNicknamePacienteRes().equals("lgomez")));
	    assertTrue(reservas.stream().anyMatch(r -> r.getNumReserva() == 2 && r.getNicknamePacienteRes().equals("mar.fernandez")));

	    assertThrows(ConsultaNotFoundException.class, () ->
	        controladorClinica.getReservasConsulta("Consulta Inexistente XYZ")
	    );
	}

	@Test
	void getConsultasPorEspecialidad_CSV() throws UserNotFoundException {
	    // Fisioterapia (ES04) -> solo tiene DC08 -> CO13 ("Fisioterapia 151026 13")
	    List<String> fisio = controladorClinica.getConsultasPorEspecialidad("Fisioterapia");
	    assertNotNull(fisio);
	    assertEquals(1, fisio.size());
	    assertTrue(fisio.contains("Fisioterapia 151026 13"));
	    controladorUsuario.listarEspecialidadesMedico("ana.perez");
	    // Pediatría (ES02) -> tiene CO02 ("Pediatría 080926"), CO07 ("Pediatría 131026 7") y CO11 ("Pediatría 131026 11")
	    List<String> pediatria = controladorClinica.getConsultasPorEspecialidad("Pediatría");
	    assertNotNull(pediatria);
	    assertTrue(pediatria.containsAll(List.of("Pediatría 080926", "Pediatría 131026 7", "Pediatría 131026 11")));

	    // Traumatología (ES10) -> existe en el CSV pero no tiene consultas asociadas -> lista vacía
	    List<String> trauma = controladorClinica.getConsultasPorEspecialidad("Traumatología");
	    assertNotNull(trauma);
	    assertTrue(trauma.isEmpty());

	    // Especialidad inexistente -> devuelve null
	    assertNull(controladorClinica.getConsultasPorEspecialidad("Especialidad Inexistente"));
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
	
	@Test
    void manejadorImagen_GuardarYRecuperar() {
        ManejadorImagen mImg = ManejadorImagen.getInstance();
        assertFalse(mImg.addImg((BufferedImage) null, "nula"));
        assertNull(mImg.getImg("inexistente"));
        assertEquals("", mImg.getImg64("inexistente"));

        BufferedImage img = new BufferedImage(2, 2, BufferedImage.TYPE_INT_RGB);
        assertTrue(mImg.addImg(img, "imgTest"));
        assertNotNull(mImg.getImg("imgTest"));

        String dataUrl = mImg.getImg64("imgTest");
        assertTrue(dataUrl.startsWith("data:image/png;base64,"));

        String rawBase64 = dataUrl.substring("data:image/png;base64,".length());
        mImg.addImg(rawBase64, "imgDesdeB64");
        assertNotNull(mImg.getImg("imgDesdeB64"));
    }
	
	@Test
    void verificarCargaMedicamentosYSolicitudes() throws Exception {
       
        List<DtMedicamento> medsLgomez = controladorUsuario.listarMedicamentosRecetados("lgomez");
        assertTrue(medsLgomez.size() >= 2);
        DtMedicamento medA = medsLgomez.stream()
                .filter(m -> m.getNombre().equals("Medicamento A") && m.getIndicacon().equals(LocalDate.of(2026, 8, 24)))
                .findFirst()
                .orElse(null);
        assertNotNull(medA);
        assertTrue(medA.getUsado());
        assertEquals(350f, medA.getCostoMed(), 0.01f);
        assertNotNull(medA.getSolMed());
        assertEquals("Av. Rivera 2500, Montevideo", medA.getSolMed().getDireccion());
        assertThrows(MedicamentoYaSolicitadoException.class, () ->
                controladorUsuario.solicitarMedicamento(
                        "lgomez",
                        "Medicamento A",
                        LocalDate.of(2026, 8, 24),
                        LocalDate.of(2026, 8, 26),
                        LocalDate.of(2026, 8, 29),
                        "Otra dir",
                        false,
                        null
                )
        );
        DtBeneficioAnual ba03 = controladorUsuario.listarDatosBeneficioAnual("mar.fernandez", 2026);
        assertTrue(ba03.getTicketUsados() >= 1);
    }
	
	@Test
    void verificarCargaValoracionesYPromedios() throws Exception {
        
        DtValoracionConsulta valLgomez = controladorUsuario.obtenerValoracionConsulta("lgomez", "Cardiología 240826");
        assertEquals(5, valLgomez.getValoracionPaciente());
        assertEquals(4.0f, valLgomez.getPromedioConsulta(), 0.01f);

        DtValoracionConsulta valMario = controladorUsuario.obtenerValoracionConsulta("mar.fernandez", "Cardiología 240826");
        assertEquals(3, valMario.getValoracionPaciente());
        assertEquals(4.0f, valMario.getPromedioConsulta(), 0.01f);

        assertFalse(controladorUsuario.valorarConsulta("lgomez", "Cardiología 240826", 2));

        assertThrows(ReservaDoesntExistsException.class, () ->
                controladorUsuario.valorarConsulta("s.lopez", "Pediatría 080926", 5)
        );

        List<DtValoracionConsulta> asistidasAndres = controladorUsuario.listarConsultasAsistidas("andrés.núñez19");
        assertEquals(1, asistidasAndres.size());
        assertEquals("Pediatría 131026 7", asistidasAndres.get(0).getNombreConsulta());
        assertEquals(3, asistidasAndres.get(0).getValoracionPaciente());
    }
	@Test
    void verificarLoginConUsuariosCSV() {
        assertTrue(controladorUsuario.login("ana.perez", "aperez123"));
        assertTrue(controladorUsuario.login("lgomez", "lgomez123"));
        assertFalse(controladorUsuario.login("lgomez", "wrongpass"));
    }
	@Test
    void verificarCargaDetallesAsistencia() throws Exception {
        DtResAsistio dt01 = controladorUsuario.getAltaDetalleReserva("Cardiología 240826", 1);
        assertNotNull(dt01);
        assertTrue(dt01.getAsistio());
        assertEquals("Control cardiológico preventivo", dt01.getMotivo());
        assertEquals("Sin hallazgos relevantes", dt01.getDiag());

        DtResAsistio dt03 = controladorUsuario.getAltaDetalleReserva("Pediatría 080926", 1);
        assertNotNull(dt03);
        assertFalse(dt03.getAsistio());
    }
} 
