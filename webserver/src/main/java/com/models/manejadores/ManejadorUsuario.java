package com.models.manejadores;
import com.models.clases.Usuario;
import com.models.clases.BeneficioAnual;
import com.models.clases.Medico;
import com.models.clases.Paciente;
import com.models.clases.Especialidad;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.models.datatypes.*;
import com.models.excepciones.*;
import com.models.excepciones.IdAlreadyExistsException;
import com.models.datatypes.DTResConsulta;


public class ManejadorUsuario {
	private static ManejadorUsuario instance;
	private Map<String, Usuario> usuarios;
	//private Map<String, Reserva> reservas;
	
	private ManejadorUsuario() {
		usuarios = new HashMap<String,Usuario>();
	}
	
	public boolean login(String nickname, String password) {
		if(usuarios.containsKey(nickname) && usuarios.get(nickname).getPassword().equals(password)) {
			return true;
		}
		return false;
	}
	
	public static ManejadorUsuario get_instance() {
		if (instance == null) {
			instance = new ManejadorUsuario();
		}
		return instance;
	}
	
	public boolean existe_ci(int _doc) {
 		return usuarios.values()
 				.stream()
 				.anyMatch(u -> u instanceof Paciente paciente && paciente.getDoc()==_doc);
 	}
 	
	
	public boolean existe_correo(String correo) {
		for (Usuario u: usuarios.values()) {
			if (u.getCorreo().equals(correo)) {
				return true;
			}
		}
		return false;
	}
	
	// SET_USUARIOS() BAUTIZADA:
	public Set<DTUsuario> get_usuarios() {
		Set<DTUsuario> result = new HashSet<DTUsuario>();
		usuarios.forEach( (k,v) -> result.add(v.get_dt()));
		return result;
	}
	

	public Usuario get_usuario(String nickname){
		if (usuarios.containsKey(nickname)) {
			return usuarios.get(nickname);
		}
		throw new UserNotFoundException("No se encontro usuario con el nickname: " + nickname);
	}
	
	public DTMedico datosMedico(String nickname) {
		Usuario usuario = get_usuario(nickname);
		if (usuario instanceof Medico medico) {
			return medico.get_dtmedico();
		}
		throw new UserNotFoundException("El usuario no es de tipo Medico");
	}
	
	public DTPaciente datosPaciente(String nickname) {
		Usuario usuario = get_usuario(nickname);
		if (usuario instanceof Paciente paciente) {
			return paciente.get_dtpaciente();
		}
		throw new UserNotFoundException("El usuario no es de tipo Paciente");
	}
	
	//Medicos
	public Set<DTConsulta> consultasAsociadas(String nicknameUsu){
		Usuario usuario = get_usuario(nicknameUsu);
		if (usuario instanceof Medico medico) {
			ManejadorConsulta mconsultas = ManejadorConsulta.get_instance();
			Set<DTConsulta> result = new HashSet<DTConsulta>();
			medico.get_consultassoc().forEach(
						s -> result.add(mconsultas.get_consulta(s).getDataConsulta())
					);
			return result;
		}
		throw new UserNotFoundException("El usuario encontrado no es de tipo Medico");
	}
	
	
	//Pacientes
	public Set<DTResConsulta> reservasConsultasAsociadas(String nicknameUsu){
		Usuario usuario = get_usuario(nicknameUsu);
		if (usuario instanceof Paciente paciente) {
			Set<DTResConsulta> result = new HashSet<DTResConsulta>();
			paciente.get_reservas().forEach(
						r-> result.add(r.getDataResConsulta())
					);
			return result;
		}
		throw new UserNotFoundException("No existe paciente con el nickname ingresado.");
	}
	
	public boolean existe_nroReg(String nroReg) {
		for (Usuario usuario: usuarios.values()) {
			if (usuario instanceof Medico medico) {
				if (medico.get_nroReg().equals(nroReg)) {
					return true;
				}
			}
		}
		return false;
	}
	public void agregar_medico(String nickname, String password, String nombre, String apellido, String correo, String nroReg, TipoMes licencia, int porcCost, String link, List<String> specs) {
		if (exists(nickname)) {
			throw new NicknameAlreadyExistsException("Ya existe usuario con el nickname ingresado");
		}
		if (existe_correo(correo)) {
			throw new EmailAlreadyExistsException("Ya existe usuario con el correo ingresado");
		}
		if (existe_nroReg(nroReg)) {
			throw new NumeroDeRegistroAlreadyExistsException("Ya existe un numero de registro en el sistema");
		}
		if (specs.isEmpty()) {
			throw new MedicoSinEspecialidadException();
		}
		Medico medico1 = new Medico(nickname, password, nombre, apellido, correo, nroReg, licencia, porcCost, link);
		specs.forEach( e -> medico1.agregarEspecialidad(ManejadorEspecialidad.get_instance().getEspecialidad(e)));
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
	
	public void agregar_paciente(String nickname, String password, String nombre, String apellido, String correo, LocalDate _fechaNac, LocalDate _fechaIn, int _doc, List<String> _antSalud){
		if (exists(nickname)) {
			throw new NicknameAlreadyExistsException("Ya existe usuario con el nickname ingresado");
		}
		if (existe_correo(correo)) {
			throw new EmailAlreadyExistsException("Ya existe usuario con el correo ingresado");
		}
		if (existe_ci(_doc)) {
			throw new IdAlreadyExistsException("Ya existe un usuario con la cedula ingresada");
		}
		Paciente paciente1 = new Paciente(nickname, password, nombre, apellido, correo, _fechaNac, _fechaIn, _doc, _antSalud);
		usuarios.put(nickname, paciente1);
	}
	
	public Set<DTPaciente> get_dtpacientes(){
		Set<DTPaciente> result = new HashSet<DTPaciente>();
		usuarios.forEach(
					(name, user) -> {
						if (user instanceof Paciente paciente) {
							result.add(paciente.get_dtpaciente());
						}
					}
				);
		return result;
	}
	
	public Set<DTMedico> get_dtmedicos(){
		Set<DTMedico> result = new HashSet<DTMedico>();
		usuarios.forEach(
					(name, user) -> {
						if (user instanceof Medico medico) {
							result.add(medico.get_dtmedico());
						}
					}
				);
		return result;
	}
	
	public DTResConsultaDetalle datosResConsulta(String nomC, String nicknameUsu) {
	
		Usuario usuario = get_usuario(nicknameUsu);
	    if (usuario instanceof Paciente paciente) {
	        for (com.models.clases.Reserva reserva : paciente.get_reservas()) {
	            if (reserva.get_consultaAsociada().equals(nomC)) {
	                return reserva.getDataResConsultaDetalle();
	            }
	        }
	    }
	    return null;
	}
	
	private Paciente get_paciente(String nickname) {
		Usuario usuario = get_usuario(nickname);
		if (usuario instanceof Paciente paciente) {
			return paciente;
		}
		throw new UserNotFoundException("El usuario no es de tipo Paciente");
	}
	
	private Medico get_medico(String nickname) {
		Usuario usuario = get_usuario(nickname);
		if (usuario instanceof Medico medico) {
			return medico;
		}
		throw new UserNotFoundException("El usuario no es de tipo Medico");
	}
	
	private void validar_beneficio(int anio, int cantOrdenes, int cantTicket) {
		if (anio < 1 || cantOrdenes < 0 || cantTicket < 0) {
			throw new IllegalArgumentException("El año debe ser positivo y las cantidades no pueden ser negativas");
		}
	}
	
	public void agregar_beneficio(String nickname, int anio, int cantOrdenes, int cantTicket) {
		validar_beneficio(anio, cantOrdenes, cantTicket);
		Paciente paciente = get_paciente(nickname);
		if (paciente.tieneBeneficio(anio)) {
			throw new BeneficioAlreadyExistsException("El paciente ya tiene un beneficio anual para el año " + anio);
		}
		paciente.agregarBeneficio(new BeneficioAnual(anio, cantOrdenes, cantTicket));
	}
	
	public Set<DTBeneficioAnual> beneficiosAnuales(String nickname) {
		return get_paciente(nickname).get_dtbeneficios();
	}
	
	public DTBeneficioAnual datosBeneficio(String nickname, int anio) {
		BeneficioAnual benefAnual = get_paciente(nickname).getBeneficio(anio);
		if (benefAnual == null) {
			throw new BeneficioNotFoundException("El paciente no tiene un beneficio anual para el año " + anio);
		}
		return benefAnual.getDataBeneficioAnual();
	}
	
	public DTUsuario datosUsuario(String nickname) {
		return get_usuario(nickname).get_dt();
	}
	
	public List<String> especialidadesMedico(String nickname) {
		return get_medico(nickname).get_nombresEspecialidades();
	}
	
	public void modificar_medico(String nickname, String nombre, String apellido, String nroReg, TipoMes mesLic, int porcCost, String link, List<String> especialidades) {
		Medico medico = get_medico(nickname);
		if (especialidades == null || especialidades.isEmpty()) {
			throw new MedicoSinEspecialidadException("El médico debe tener al menos una especialidad");
		}
		if (!nroReg.equals(medico.get_nroReg()) && existe_nroReg(nroReg)) {
			throw new NumeroDeRegistroAlreadyExistsException("Ya existe este número de registro en el sistema");
		}
		Set<Especialidad> nuevas = new HashSet<Especialidad>();
		for (String esp : especialidades) {
			nuevas.add(ManejadorEspecialidad.get_instance().getEspecialidad(esp));
		}
		medico.setNombre(nombre);
		medico.setApellido(apellido);
		medico.set_nroReg(nroReg);
		medico.set_mesLic(mesLic);
		medico.set_porcentajeCosto(porcCost);
		medico.set_link(link);
		medico.set_especialidades(nuevas);
	}
	
	public void modificar_paciente(String nickname, String nombre, String apellido, LocalDate fechaNac, LocalDate fechaIn, int doc, List<String> antSalud) {
		Paciente paciente = get_paciente(nickname);
		paciente.setNombre(nombre);
		paciente.setApellido(apellido);
		paciente.setFechaNac(fechaNac);
		paciente.setFechaIn(fechaIn);
		paciente.setDoc(doc);
		paciente.setAntSalud(antSalud);
	}

}
