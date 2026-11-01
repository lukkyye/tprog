package test;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import logica.datatypes.EstadoConsulta;

import logica.controladores.ControladorClinica;
import logica.controladores.ControladorUsuario;
import logica.datatypes.DtDiaSemana;
import logica.datatypes.TipoMes;
import logica.excepciones.BeneficioAlreadyExistsException;
import logica.excepciones.BeneficioSinOrdenesException;
import logica.excepciones.ConsultaNombreAlreadyExistsException;
import logica.excepciones.ConsultaNotFoundException;
import logica.excepciones.DiaConsultaAlreadyExistsException;
import logica.excepciones.EmailAlreadyExistsException;
import logica.excepciones.EspecialidadAlreadyExistsException;
import logica.excepciones.FechaDiaSemanaInvalidaException;
import logica.excepciones.IdAlreadyExistsException;
import logica.excepciones.MedicoSinEspecialidadException;
import logica.excepciones.NicknameAlreadyExistsException;
import logica.excepciones.NoNumberAvailableException;
import logica.excepciones.NombreClinicaAlreadyExistsException;
import logica.excepciones.NumeroDeRegistroAlreadyExistsException;
import logica.excepciones.ReservaAlreadyExistsException;
import logica.excepciones.SpecialityNotFoundException;
import logica.excepciones.UserNotFoundException;


public class CargadorCSV {

	private static final DateTimeFormatter FECHA_FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private final ControladorUsuario controladorUsuario = new ControladorUsuario();
	private final ControladorClinica controladorClinica = new ControladorClinica();

	private final Map<String, String> especialidadesPorRef = new HashMap<>();
	private final Map<String, String> clinicasPorRef = new HashMap<>();
	private final Map<String, String> usuariosPorRef = new HashMap<>(); // nickname
	private final Map<String, String> diasConsultaPorRef = new HashMap<>();
	private final Map<String, String> consultasPorRef = new HashMap<>();

	public void cargarTodo() {
		cargarEspecialidades();
		cargarClinicas();
		cargarUsuarios();
		cargarBeneficios();
		cargarDiasConsulta();
		cargarConsultas();
		cargarReservas();
	}

	private void cargarEspecialidades() {
		for (String[] fila : leerCsv("2026Especialidades.csv")) {
			String ref = fila[0];
			String nombre = fila[1];
			int porcentaje = Integer.parseInt(fila[2].trim());

			try {
				controladorUsuario.altaEspecialidad(nombre, porcentaje);
			} catch (EspecialidadAlreadyExistsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			especialidadesPorRef.put(ref, nombre);
		}
	}

	private void cargarClinicas() {
		for (String[] fila : leerCsv("2026Clinicas.csv")) {
			String ref = fila[0];
			String nombre = fila[1];
			String desc = fila[2];
			String dir = fila[3];
			String link = fila[4];
			float costoTicket = Float.parseFloat(fila[5].trim());
			float costoConsulta = Float.parseFloat(fila[6].trim());

			try {
				controladorClinica.ingresarClinica(nombre, desc, dir, costoTicket, costoConsulta, link);
			} catch (NombreClinicaAlreadyExistsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			clinicasPorRef.put(ref, nombre);
		}
	}

	private void cargarUsuarios() {
		Map<String, String[]> medicosPorRef = indexarPorRef("2026Medicos.csv");
		Map<String, String[]> pacientesPorRef = indexarPorRef("2026Pacientes.csv");

		for (String[] fila : leerCsv("2026Usuarios.csv")) {
			String ref = fila[0];
			String tipo = fila[1];
			String nickname = fila[2];
			String nombre = fila[3];
			String apellido = fila[4];
			String correo = fila[5];
			String password = fila[6];
			
			if (tipo.equals("M")) {
				String[] datosMedico = medicosPorRef.get(ref);
				String nroReg = datosMedico[1]; //replace later;
				TipoMes mesLic = TipoMes.valueOf(datosMedico[2]);
				String sitioWeb = datosMedico[3].isEmpty() ? "" : datosMedico[3];
				int porcAdicional = Integer.parseInt(datosMedico[4].trim());
				String[] refsEspecialidades = datosMedico[5].split(",");

				ArrayList<String> nombresEspecialidades = new ArrayList<>();
				for (String refEsp : refsEspecialidades) {
					nombresEspecialidades.add(especialidadesPorRef.get(refEsp.trim()));
				}
				try {
					controladorUsuario.ingresarMedico(nickname, password, nombre, apellido, correo,
							nroReg, mesLic, porcAdicional, sitioWeb, nombresEspecialidades);
				} catch (NicknameAlreadyExistsException | EmailAlreadyExistsException
						| NumeroDeRegistroAlreadyExistsException | MedicoSinEspecialidadException
						| SpecialityNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (tipo.equals("P")) {
				String[] datosPaciente = pacientesPorRef.get(ref);
				LocalDate fechaNac = LocalDate.parse(datosPaciente[1], FECHA_FMT);
				LocalDate fechaIngreso = LocalDate.parse(datosPaciente[2], FECHA_FMT);
				int doc = Integer.parseInt(datosPaciente[3].trim());
				String antecedentes = datosPaciente[4];

				ArrayList<String> antSalud = new ArrayList<>();
				if (!antecedentes.isBlank()) {
					antSalud.add(antecedentes);
				}

				try {
					controladorUsuario.ingresarPaciente(nickname, password, nombre, apellido, correo,
							fechaNac, fechaIngreso, doc, antSalud);
				} catch (NicknameAlreadyExistsException | EmailAlreadyExistsException | IdAlreadyExistsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			usuariosPorRef.put(ref, nickname);
		}
	}
	
	private void cargarBeneficios() {
		for (String[] fila : leerCsv("2026BeneficiosAnuales.csv")) {
			String nicknamePaciente = usuariosPorRef.get(fila[1]);
			int anio = Integer.parseInt(fila[2]);
			int cantOrdenes = Integer.parseInt(fila[3]);
			int cantTicket = Integer.parseInt(fila[4]);
			System.out.printf("%s,%s,%s,%s,%s\n",fila);
			try {
				controladorUsuario.altaBeneficioAnual(nicknamePaciente, anio, cantOrdenes, cantTicket);
			} catch (UserNotFoundException | BeneficioAlreadyExistsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void cargarDiasConsulta() {
		for (String[] fila : leerCsv("2026DiaConsulta.csv")) {
			String ref = fila[0];
			String nombre = fila[1];
			String desc = fila[2];
			DtDiaSemana dia = parseDia(fila[3]);
			LocalTime horaInicio = LocalTime.parse(fila[4].trim());
			String nombreEspecialidad = especialidadesPorRef.get(fila[5]);
			String nombreClinica = clinicasPorRef.get(fila[6]);
			
			try {
				controladorClinica.ingresarDiaConsulta(nombre, desc, dia, horaInicio,
						nombreEspecialidad, nombreClinica);
			} catch (DiaConsultaAlreadyExistsException | SpecialityNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			diasConsultaPorRef.put(ref, nombre);
		}
	}

	private void cargarConsultas() {
		for (String[] fila : leerCsv("2026Consultas.csv")) {
			String ref = fila[0];
			String nombre = fila[1];
			LocalDate fecha = LocalDate.parse(fila[2], FECHA_FMT);
			
			int sala = Integer.parseInt(fila[3].trim());
			int cantNum = Integer.parseInt(fila[4].trim());
			String nombreDia = diasConsultaPorRef.get(fila[5]);
			String nicknameMedico = usuariosPorRef.get(fila[6]);
			//String costo = fila[7];
			String state = fila[8];
			
			try {
				controladorClinica.ingresarConsulta(nombreDia, nicknameMedico, nombre, fecha, sala, cantNum);
			} catch (MedicoSinEspecialidadException | ConsultaNombreAlreadyExistsException
					| FechaDiaSemanaInvalidaException | UserNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				controladorClinica.setEstadoConsulta(EstadoConsulta.fromString(state), nombre);
			} catch (ConsultaNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			consultasPorRef.put(ref, nombre);
		}
	}
	

	private void cargarReservas() {
		for (String[] fila : leerCsv("2026Reservas.csv")) {
			LocalDate fechaReserva = LocalDate.parse(fila[1], FECHA_FMT);
			String nicknamePaciente = usuariosPorRef.get(fila[3]);
			String nombreConsulta = consultasPorRef.get(fila[4]);
			boolean aplicaBenef = false;
			if (nicknamePaciente == "lgomez" && nombreConsulta == "Cardiología 121026 6") {
				aplicaBenef = true;
			}

			try {
				controladorClinica.reservarConsulta(nicknamePaciente, nombreConsulta, fechaReserva, aplicaBenef);
			} catch (ReservaAlreadyExistsException | NoNumberAvailableException | UserNotFoundException
					| BeneficioSinOrdenesException | ConsultaNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	private DtDiaSemana parseDia(String valorCsv) {
		String sinAcentos = Normalizer.normalize(valorCsv.trim(), Normalizer.Form.NFD)
				.replaceAll("\\p{M}", "");
		return DtDiaSemana.valueOf(sinAcentos);
	}

	private Map<String, String[]> indexarPorRef(String archivo) {
		Map<String, String[]> result = new HashMap<>();
		for (String[] fila : leerCsv(archivo)) {
			result.put(fila[0], fila);
		}
		return result;
	}

	private List<String[]> leerCsv(String archivo) {
		List<String[]> filas = new ArrayList<>();
		InputStream is = getClass().getResourceAsStream(archivo);
		if (is == null) {
			throw new RuntimeException("No se encontró el CSV en el classpath: /csv/" + archivo
					+ " (¿está la carpeta test/csv marcada como source folder?)");
		}
		try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
			String linea;
			boolean primera = true;
			while ((linea = br.readLine()) != null) {
				if (primera) {
					primera = false;
					continue;
				}
				if (linea.isBlank()) {
					continue;
				}
				filas.add(linea.split(";", -1));
			}
		} catch (IOException e) {
			throw new RuntimeException("Error leyendo " + archivo, e);
		}
		return filas;
	}
}
