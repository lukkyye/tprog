package logica.manejadores;
import logica.clases.Usuario;
import logica.clases.BeneficioAnual;
import logica.clases.Medico;
import logica.clases.Paciente;
import logica.clases.Reserva;
import logica.clases.Especialidad;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import logica.excepciones.BeneficioAlreadyExistsException;
import logica.excepciones.BeneficioNotFoundException;
import logica.excepciones.EmailAlreadyExistsException;
import logica.excepciones.IdAlreadyExistsException;
import logica.excepciones.MedicoSinEspecialidadException;
import logica.excepciones.NicknameAlreadyExistsException;
import logica.excepciones.NumeroDeRegistroAlreadyExistsException;
import logica.excepciones.UserNotFoundException;
import logica.datatypes.DtBeneficioAnual;
import logica.datatypes.DtConsulta;
import logica.datatypes.DtMedico;
import logica.datatypes.DtPaciente;
import logica.datatypes.DtResConsulta;
import logica.datatypes.DtResConsultaDetalle;
import logica.datatypes.DtUsuario;
import logica.datatypes.TipoMes;


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
	

	public Usuario getUsuario(String nickname){
		if (usuarios.containsKey(nickname)) {
			return usuarios.get(nickname);
		}
		throw new UserNotFoundException("No se encontro usuario con el nickname: " + nickname);
	}
	
	public DtMedico datosMedico(String nickname) {
		Usuario usuario = getUsuario(nickname);
		if (usuario instanceof Medico medico) {
			return medico.getDtmedico();
		}
		throw new UserNotFoundException("El usuario no es de tipo Medico");
	}
	
	public DtPaciente datosPaciente(String nickname) {
		Usuario usuario = getUsuario(nickname);
		if (usuario instanceof Paciente paciente) {
			return paciente.getDtpaciente();
		}
		throw new UserNotFoundException("El usuario no es de tipo Paciente");
	}
	
	//Medicos
	public Set<DtConsulta> consultasAsociadas(String nicknameUsu){
		Usuario usuario = getUsuario(nicknameUsu);
		if (usuario instanceof Medico medico) {
			ManejadorConsulta mconsultas = ManejadorConsulta.getInstance();
			Set<DtConsulta> result = new HashSet<DtConsulta>();
			medico.getConsultasAsoc().forEach(
						s -> result.add(mconsultas.getConsulta(s).getDataConsulta())
					);
			return result;
		}
		throw new UserNotFoundException("El usuario encontrado no es de tipo Medico");
	}
	
	
	//Pacientes
	public Set<DtResConsulta> reservasConsultasAsociadas(String nicknameUsu){
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
	public void agregarMedico(String nickname, String password, String nombre, String apellido, String correo, String nroReg, TipoMes licencia, int porcCost, String link, List<String> specs) {
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
		specs.forEach( e -> medico1.agregarEspecialidad(ManejadorEspecialidad.getInstance().getEspecialidad(e)));
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
	
	public void agregarPaciente(String nickname, String password, String nombre, String apellido, String correo, LocalDate _fechaNac, LocalDate _fechaIn, int _doc, List<String> _antSalud){
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
	
	public DtResConsultaDetalle datosResConsulta(String nomC, String nicknameUsu) {
	
		Usuario usuario = getUsuario(nicknameUsu);
	    if (usuario instanceof Paciente paciente) {
	        for (Reserva reserva : paciente.getReservas()) {
	            if (reserva.getConsultasAsoc().equals(nomC)) {
	                return reserva.getDataResConsultaDetalle();
	            }
	        }
	    }
	    return null;
	}
	
	private Paciente getPaciente(String nickname) {
		Usuario usuario = getUsuario(nickname);
		if (usuario instanceof Paciente paciente) {
			return paciente;
		}
		throw new UserNotFoundException("El usuario no es de tipo Paciente");
	}
	
	private Medico getMedico(String nickname) {
		Usuario usuario = getUsuario(nickname);
		if (usuario instanceof Medico medico) {
			return medico;
		}
		throw new UserNotFoundException("El usuario no es de tipo Medico");
	}
	
	private void validarBeneficio(int anio, int cantOrdenes, int cantTicket) {
		if (anio < 1 || cantOrdenes < 0 || cantTicket < 0) {
			throw new IllegalArgumentException("El año debe ser positivo y las cantidades no pueden ser negativas");
		}
	}
	
	public void agregarBeneficio(String nickname, int anio, int cantOrdenes, int cantTicket) {
		validarBeneficio(anio, cantOrdenes, cantTicket);
		Paciente paciente = getPaciente(nickname);
		if (paciente.tieneBeneficio(anio)) {
			throw new BeneficioAlreadyExistsException("El paciente ya tiene un beneficio anual para el año " + anio);
		}
		paciente.agregarBeneficio(new BeneficioAnual(anio, cantOrdenes, cantTicket));
	}
	
	public Set<DtBeneficioAnual> beneficiosAnuales(String nickname) {
		return getPaciente(nickname).getDtbeneficios();
	}
	
	public DtBeneficioAnual datosBeneficio(String nickname, int anio) {
		BeneficioAnual benefAnual = getPaciente(nickname).getBeneficio(anio);
		if (benefAnual == null) {
			throw new BeneficioNotFoundException("El paciente no tiene un beneficio anual para el año " + anio);
		}
		return benefAnual.getDataBeneficioAnual();
	}
	
	public DtUsuario datosUsuario(String nickname) {
		return getUsuario(nickname).getDt();
	}
	
	public List<String> especialidadesMedico(String nickname) {
		return getMedico(nickname).getNombreEspecialidades();
	}
	
	public void modificarMedico(String nickname, String nombre, String apellido, String nroReg, TipoMes mesLic, int porcCost, String link, List<String> especialidades) {
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
	
	public void modificarPaciente(String nickname, String nombre, String apellido, LocalDate fechaNac, LocalDate fechaIn, int doc, List<String> antSalud) {
		Paciente paciente = getPaciente(nickname);
		paciente.setNombre(nombre);
		paciente.setApellido(apellido);
		paciente.setFechaNac(fechaNac);
		paciente.setFechaIn(fechaIn);
		paciente.setDoc(doc);
		paciente.setAntSalud(antSalud);
	}

}
