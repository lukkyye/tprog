package logica.manejadores;
import logica.clases.Usuario;

import logica.clases.BeneficioAnual;
import logica.clases.Consulta;
import logica.clases.Medico;
import logica.clases.Paciente;
import logica.clases.Reserva;
import logica.clases.Especialidad;
import logica.clases.Medicamento;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import logica.excepciones.BeneficioAlreadyExistsException;
import logica.excepciones.BeneficioNotFoundException;
import logica.excepciones.BeneficioSinTicketsException;
import logica.excepciones.ConsultaNotFoundException;
import logica.excepciones.DatosMedicamentoInvalidosException;
import logica.excepciones.EmailAlreadyExistsException;
import logica.excepciones.FechaEntregaInvalidaException;
import logica.excepciones.IdAlreadyExistsException;
import logica.excepciones.MedicamentoAlreadyExistsException;
import logica.excepciones.MedicamentoNotFoundException;
import logica.excepciones.MedicamentoVencidoException;
import logica.excepciones.MedicamentoYaSolicitadoException;
import logica.excepciones.MedicoSinEspecialidadException;
import logica.excepciones.NicknameAlreadyExistsException;
import logica.excepciones.NumeroDeRegistroAlreadyExistsException;
import logica.excepciones.SpecialityNotFoundException;
import logica.excepciones.UserNotFoundException;
import logica.datatypes.DtBeneficioAnual;
import logica.datatypes.DtConsulta;
import logica.datatypes.DtMedicamento;
import logica.datatypes.DtMedico;
import logica.datatypes.DtPaciente;
import logica.datatypes.DtResAsistio;
import logica.datatypes.DtResConsulta;
import logica.datatypes.DtResConsultaDetalle;
import logica.datatypes.DtSolicitudMedicamento;
import logica.datatypes.DtUsuario;
import logica.datatypes.TipoMes;

import java.util.ArrayList;
import java.util.Comparator;
import logica.datatypes.DtValoracionConsulta;
import logica.excepciones.ReservaDoesntExistsException;


public class ManejadorUsuario {
	private static ManejadorUsuario instance;
	private Map<String, Usuario> usuarios;
	//private Map<String, Reserva> reservas;
	
	private ManejadorUsuario() {
		usuarios = new HashMap<String, Usuario>();
	}
	
	public boolean login(String nickname, String password) {
		if (usuarios.containsKey(nickname) && usuarios.get(nickname).getPassword().equals(password)) {
			return true;
		}
		return false;
	}
	
	public static ManejadorUsuario getInstance() {
		if (instance == null) {
			instance = new ManejadorUsuario();
		}
		return instance;
	}
	
	public boolean existeCi(int _doc) {
 		return usuarios.values()
 				.stream()
 				.anyMatch(u -> u instanceof Paciente paciente && paciente.getDoc()==_doc);
 	}
 	
	
	public boolean existeCorreo(String correo) {
		for (Usuario u: usuarios.values()) {
			if (u.getCorreo().equals(correo)) {
				return true;
			}
		}
		return false;
	}
	
	// SET_USUARIOS() BAUTIZADA:
	public Set<DtUsuario> getUsuarios() {
		Set<DtUsuario> result = new HashSet<DtUsuario>();
		usuarios.forEach( (k, v)-> result.add(v.getDt()));
		return result;
	}
	

	public Usuario getUsuario(String nickname) throws UserNotFoundException{
		if (usuarios.containsKey(nickname)) {
			return usuarios.get(nickname);
		}
		throw new UserNotFoundException("No se encontro usuario con el nickname: " + nickname);
	}
	
	public DtMedico datosMedico(String nickname) throws UserNotFoundException {
		Usuario usuario = getUsuario(nickname);
		if (usuario instanceof Medico medico) {
			return medico.getDtmedico();
		}
		throw new UserNotFoundException("El usuario no es de tipo Medico");
	}
	
	public DtPaciente datosPaciente(String nickname) throws UserNotFoundException{
		Usuario usuario = getUsuario(nickname);
		if (usuario instanceof Paciente paciente) {
			return paciente.getDtpaciente();
		}
		throw new UserNotFoundException("El usuario no es de tipo Paciente");
	}
	
	//Medicos
	public Set<DtConsulta> consultasAsociadas(String nicknameUsu) throws UserNotFoundException, ConsultaNotFoundException{
		Usuario usuario = getUsuario(nicknameUsu);
		if (usuario instanceof Medico medico) {
			ManejadorConsulta mconsultas = ManejadorConsulta.getInstance();
			Set<DtConsulta> result = new HashSet<DtConsulta>();
			for (String consultasAsoc : medico.getConsultasAsoc()) {
				result.add(mconsultas.getConsulta(consultasAsoc).getDataConsulta());
			}
			
			return result;
		}
		throw new UserNotFoundException("El usuario encontrado no es de tipo Medico");
	}
	
	//ACA VA ALTADETALLERESEVA QUE EJECUTA EL MEDICO
	public void altaDetalleReserva(String nomC, int numRes, String nicknamePac, boolean asistio, String motivo, String diagnostico) throws UserNotFoundException{
		Usuario usuario = getUsuario(nicknamePac);
		if (usuario instanceof Paciente paciente) {
	        for (Reserva reserva : paciente.getReservas()) {
	            if (reserva.getConsultasAsoc().equals(nomC)) {
	                if(reserva.tieneDetalleReserva()) {
	                	return; //YA TIENE DETALLE LA RESERVA 
	                } else {
	                	reserva.setAsistio(asistio);
	                	reserva.setMotivo(motivo);
	                	reserva.setDiagnostico(diagnostico);
	                }
	            }
	        }
	    }
	}
	
	//ACA VA GET ALTA DETALLE RESERVA
	public DtResAsistio getAltaDetalleReserva(String nomC, int numR) throws ConsultaNotFoundException {
		ManejadorConsulta mconsultas = ManejadorConsulta.getInstance();
		Consulta consulta = mconsultas.getConsulta(nomC);
		for (Reserva reserva : consulta.getReservas()) {
			if (reserva.getNumReserva() == numR) {
				return reserva.getDataResAsistio();
			}
		}
		return null;
	}
	
	public List<DtValoracionConsulta> listarConsultasAsistidas(String nickname)
	        throws UserNotFoundException, ConsultaNotFoundException {
	    Paciente paciente = pacienteParaValorar(nickname);
	    List<DtValoracionConsulta> resultado = new ArrayList<>();

	    for (Reserva reserva : paciente.getReservas()) {
	        if (reserva.getAsistio()) {
	            Consulta consulta = ManejadorConsulta.getInstance()
	                    .getConsulta(reserva.getConsultasAsoc());
	            resultado.add(consulta.datosValoracion(reserva));
	        }
	    }

	    resultado.sort(Comparator.comparing(DtValoracionConsulta::getFechaConsulta)
	            .reversed().thenComparing(DtValoracionConsulta::getNombreConsulta));
	    return resultado;
	}

	public DtValoracionConsulta obtenerValoracionConsulta(String nickname, String nombreConsulta)
	        throws UserNotFoundException, ConsultaNotFoundException, ReservaDoesntExistsException {
	    Reserva reserva = buscarReservaAsistida(nickname, nombreConsulta);
	    return ManejadorConsulta.getInstance()
	            .getConsulta(nombreConsulta).datosValoracion(reserva);
	}

	public boolean valorarConsulta(String nickname, String nombreConsulta, int puntaje)
	        throws UserNotFoundException, ConsultaNotFoundException, ReservaDoesntExistsException {
	    Reserva reserva = buscarReservaAsistida(nickname, nombreConsulta);
	    return ManejadorConsulta.getInstance()
	            .getConsulta(nombreConsulta).registrarValoracion(reserva, puntaje);
	}

	private Reserva buscarReservaAsistida(String nickname, String nombreConsulta)
	        throws UserNotFoundException, ReservaDoesntExistsException {
	    for (Reserva reserva : pacienteParaValorar(nickname).getReservas()) {
	        if (reserva.getConsultasAsoc().equals(nombreConsulta) && reserva.getAsistio()) {
	            return reserva;
	        }
	    }
	    throw new ReservaDoesntExistsException("No hay una reserva asistida para esa consulta.");
	}

	private Paciente pacienteParaValorar(String nickname) throws UserNotFoundException {
	    if (getUsuario(nickname) instanceof Paciente paciente) {
	        return paciente;
	    }
	    throw new UserNotFoundException("El usuario no es de tipo Paciente.");
	}
	
	
	//Pacientes
	public Set<DtResConsulta> reservasConsultasAsociadas(String nicknameUsu) throws UserNotFoundException{
		Usuario usuario = getUsuario(nicknameUsu);
		if (usuario instanceof Paciente paciente) {
			Set<DtResConsulta> result = new HashSet<DtResConsulta>();
			paciente.getReservas().forEach(
						r-> result.add(r.getDataResConsulta())
					);
			return result;
		}
		throw new UserNotFoundException("No existe paciente con el nickname ingresado.");
	}
	
	public boolean existeNroReg(String nroReg){
		for (Usuario usuario: usuarios.values()) {
			if (usuario instanceof Medico medico) {
				if (medico.getNroReg().equals(nroReg)) {
					return true;
				}
			}
		}
		return false;
	}
	public void agregarMedico(String nickname, String password, String nombre, String apellido, String correo, String nroReg, TipoMes licencia, int porcCost, String link, List<String> specs)
			throws NicknameAlreadyExistsException, EmailAlreadyExistsException, NumeroDeRegistroAlreadyExistsException, MedicoSinEspecialidadException, SpecialityNotFoundException
	{
		if (exists(nickname)) {
			throw new NicknameAlreadyExistsException("Ya existe usuario con el nickname ingresado");
		}
		if (existeCorreo(correo)) {
			throw new EmailAlreadyExistsException("Ya existe usuario con el correo ingresado");
		}
		if (existeNroReg(nroReg)) {
			throw new NumeroDeRegistroAlreadyExistsException("Ya existe un numero de registro en el sistema");
		}
		if (specs.isEmpty()) {
			throw new MedicoSinEspecialidadException();
		}
		Medico medico1 = new Medico(nickname, password, nombre, apellido, correo, nroReg, licencia, porcCost, link);
		for (String esp_nom : specs) {
			Especialidad e = ManejadorEspecialidad.getInstance().getEspecialidad(esp_nom);
			medico1.agregarEspecialidad(e);
		}
		usuarios.put(nickname, medico1);
	}
	
	
	/**
	 * Chequea existencia de usuario dado un nickname
	 * @param nickname Nombre de usuario
	 * @return True si y solo si existe un usuario con el nickname pasado
	 * */
	public boolean exists(String nickname) {
		if (usuarios.containsKey(nickname)) {
			return true;
		}
		return false;
	}
	
	public void agregarPaciente(String nickname, String password, String nombre, String apellido, String correo, LocalDate _fechaNac, LocalDate _fechaIn, int _doc, List<String> _antSalud)
	throws NicknameAlreadyExistsException, EmailAlreadyExistsException, IdAlreadyExistsException
	{
		if (exists(nickname)) {
			throw new NicknameAlreadyExistsException("Ya existe usuario con el nickname ingresado");
		}
		if (existeCorreo(correo)) {
			throw new EmailAlreadyExistsException("Ya existe usuario con el correo ingresado");
		}
		if (existeCi(_doc)) {
			throw new IdAlreadyExistsException("Ya existe un usuario con la cedula ingresada");
		}
		Paciente paciente1 = new Paciente(nickname, password, nombre, apellido, correo, _fechaNac, _fechaIn, _doc, _antSalud);
		usuarios.put(nickname, paciente1);
	}
	
	public Set<DtPaciente> getDtpacientes(){
		Set<DtPaciente> result = new HashSet<DtPaciente>();
		usuarios.forEach(
					(name, user) -> {
						if (user instanceof Paciente paciente) {
							result.add(paciente.getDtpaciente());
						}
					}
				);
		return result;
	}
	
	public Set<DtMedico> getDtmedicos(){
		Set<DtMedico> result = new HashSet<DtMedico>();
		usuarios.forEach(
					(name, user) -> {
						if (user instanceof Medico medico) {
							result.add(medico.getDtmedico());
						}
					}
				);
		return result;
	}
	
	public DtResConsultaDetalle datosResConsulta(String nomC, String nicknameUsu) throws UserNotFoundException, ConsultaNotFoundException {
	
		Usuario usuario = getUsuario(nicknameUsu);
	    if (usuario instanceof Paciente paciente) {
	        for (Reserva reserva : paciente.getReservas()) {
	            if (reserva.getConsultasAsoc().equals(nomC)) {
	                return reserva.getDataResConsultaDetalle();
	            }
	        }
	    }
	    throw new ConsultaNotFoundException();
	}
	
	private Paciente getPaciente(String nickname) throws UserNotFoundException{
		Usuario usuario = getUsuario(nickname);
		if (usuario instanceof Paciente paciente) {
			return paciente;
		}
		throw new UserNotFoundException("El usuario no es de tipo Paciente");
	}
	
	private Medico getMedico(String nickname) throws UserNotFoundException{
		Usuario usuario = getUsuario(nickname);
		if (usuario instanceof Medico medico) {
			return medico;
		}
		throw new UserNotFoundException("El usuario no es de tipo Medico");
	}
	
	private void validarBeneficio(int anio, int cantOrdenes, int cantTicket) throws IllegalArgumentException {
		if (anio < 1 || cantOrdenes < 0 || cantTicket < 0) {
			throw new IllegalArgumentException("El año debe ser positivo y las cantidades no pueden ser negativas");
		}
	}
	
	public void agregarBeneficio(String nickname, int anio, int cantOrdenes, int cantTicket) throws UserNotFoundException, BeneficioAlreadyExistsException {
		validarBeneficio(anio, cantOrdenes, cantTicket);
		Paciente paciente = getPaciente(nickname);
		if (paciente.tieneBeneficio(anio)) {
			throw new BeneficioAlreadyExistsException("El paciente ya tiene un beneficio anual para el año " + anio);
		}
		paciente.agregarBeneficio(new BeneficioAnual(anio, cantOrdenes, cantTicket));
	}
	
	public Set<DtBeneficioAnual> beneficiosAnuales(String nickname) throws UserNotFoundException {
		return getPaciente(nickname).getDtbeneficios();
	}
	
	public DtBeneficioAnual datosBeneficio(String nickname, int anio) throws BeneficioNotFoundException, UserNotFoundException{
		BeneficioAnual benefAnual = getPaciente(nickname).getBeneficio(anio);
		if (benefAnual == null) {
			throw new BeneficioNotFoundException("El paciente no tiene un beneficio anual para el año " + anio);
		}
		return benefAnual.getDataBeneficioAnual();
	}
	
	public DtUsuario datosUsuario(String nickname) throws UserNotFoundException {
		return getUsuario(nickname).getDt();
	}
	
	public List<String> especialidadesMedico(String nickname) throws UserNotFoundException {
		return getMedico(nickname).getNombreEspecialidades();
	}
	
	public void modificarMedico(String nickname, String nombre, String apellido, String nroReg, TipoMes mesLic, int porcCost, String link, List<String> especialidades) throws
	MedicoSinEspecialidadException, NumeroDeRegistroAlreadyExistsException, SpecialityNotFoundException, UserNotFoundException
	{
		Medico medico = getMedico(nickname);
		if (especialidades == null || especialidades.isEmpty()) {
			throw new MedicoSinEspecialidadException("El médico debe tener al menos una especialidad");
		}
		if (!nroReg.equals(medico.getNroReg()) && existeNroReg(nroReg)) {
			throw new NumeroDeRegistroAlreadyExistsException("Ya existe este número de registro en el sistema");
		}
		Set<Especialidad> nuevas = new HashSet<Especialidad>();
		for (String esp : especialidades) {
			nuevas.add(ManejadorEspecialidad.getInstance().getEspecialidad(esp));
		}
		medico.setNombre(nombre);
		medico.setApellido(apellido);
		medico.setNroReg(nroReg);
		medico.setMesLic(mesLic);
		medico.setPorcentajeCosto(porcCost);
		medico.setLink(link);
		medico.setEspecialidades(nuevas);
	}
	
	public void modificarPaciente(String nickname, String nombre, String apellido, LocalDate fechaNac, LocalDate fechaIn, int doc, List<String> antSalud) throws UserNotFoundException {
		Paciente paciente = getPaciente(nickname);
		paciente.setNombre(nombre);
		paciente.setApellido(apellido);
		paciente.setFechaNac(fechaNac);
		paciente.setFechaIn(fechaIn);
		paciente.setDoc(doc);
		paciente.setAntSalud(antSalud);
	}
	
	public void agregarMedicamento(String nickname, String nombre, String posologia, LocalDate indicacion, float costoMed, String nomC) throws UserNotFoundException, MedicamentoAlreadyExistsException {
		Paciente paciente = getPaciente(nickname);
		paciente.agregarMedicamento(new Medicamento(nombre, posologia, indicacion, costoMed, nomC));
	}
	
	public List<DtMedicamento> medicamentosRecetados(String nickname) throws UserNotFoundException {
		return getPaciente(nickname).getDtMedicamentos();
	}
	
	public DtSolicitudMedicamento solicitarMedicamento(String nickname, String nombre, LocalDate indicacion, LocalDate hoy, LocalDate entrega, String direccion, boolean usaBenef) throws UserNotFoundException, MedicamentoNotFoundException, MedicamentoYaSolicitadoException, MedicamentoVencidoException, FechaEntregaInvalidaException, DatosMedicamentoInvalidosException, BeneficioSinTicketsException {
		return getPaciente(nickname).solicitarMedicamento(nombre, indicacion, hoy, entrega, direccion, usaBenef);
	}


}
