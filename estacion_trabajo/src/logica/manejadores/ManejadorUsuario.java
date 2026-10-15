package logica.manejadores;
import logica.clases.Usuario;
import logica.clases.BeneficioAnual;
import logica.clases.Medico;
import logica.clases.Paciente;
import logica.clases.Especialidad;
import logica.clases.Reserva;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import logica.datatypes.*;
import logica.excepciones.*;

public class ManejadorUsuario {
	private static ManejadorUsuario instance;
	private HashMap<String, Usuario> usuarios;
	private HashMap<String, Reserva> reservas;
	
	private ManejadorUsuario() {
		usuarios = new HashMap<String,Usuario>();
	}
	
	public static ManejadorUsuario get_instance() {
		if (instance == null) {
			instance = new ManejadorUsuario();
		}
		return instance;
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
	public HashSet<DTUsuario> get_usuarios() {
		HashSet<DTUsuario> result = new HashSet<DTUsuario>();
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
		Usuario u = get_usuario(nickname);
		if (u instanceof Medico m) {
			return m.get_dtmedico();
		}
		throw new UserNotFoundException("El usuario no es de tipo Medico");
	}
	
	public DTPaciente datosPaciente(String nickname) {
		Usuario u = get_usuario(nickname);
		if (u instanceof Paciente p) {
			return p.get_dtpaciente();
		}
		throw new UserNotFoundException("El usuario no es de tipo Paciente");
	}
	
	//Medicos
	public HashSet<DTConsulta> consultasAsociadas(String nicknameUsu){
		Usuario u = get_usuario(nicknameUsu);
		if (u instanceof Medico m) {
			ManejadorConsulta mconsultas = ManejadorConsulta.get_instance();
			HashSet<DTConsulta> result = new HashSet<DTConsulta>();
			m.get_consultassoc().forEach(
						s -> result.add(mconsultas.get_consulta(s).getDataConsulta())
					);
			return result;
		}
		throw new UserNotFoundException("El usuario encontrado no es de tipo Medico");
	}
	
	
	//Pacientes
	public HashSet<DTResConsulta> reservasConsultasAsociadas(String nicknameUsu){
		Usuario u = get_usuario(nicknameUsu);
		if (u instanceof Paciente p) {
			HashSet<DTResConsulta> result = new HashSet<DTResConsulta>();
			p.get_reservas().forEach(
						r-> result.add(r.getDataResConsulta())
					);
			return result;
		}
		throw new UserNotFoundException("No existe paciente con el nickname ingresado.");
	}
	
	public boolean existe_nroReg(String nroReg) {
		for (Usuario u: usuarios.values()) {
			if (u instanceof Medico m) {
				if (m.get_nroReg().equals(nroReg)) {
					return true;
				}
			}
		}
		return false;
	}
	public void agregar_medico(String nickname, String password, String nombre, String apellido, String correo, String nroReg, TipoMes licencia, int porcCost, String link, ArrayList<String> specs) {
		if (exists(nickname)) {
			throw new NicknameAlreadyExistsException("Ya existe usuario con el nickname ingresado");
		}
		if (existe_correo(correo)) {
			throw new NicknameAlreadyExistsException("Ya existe usuario con el correo ingresado");
		}
		if (existe_nroReg(nroReg)) {
			throw new NumeroDeRegistroAlreadyExistsException("Ya existe un numero de registro en el sistema");
		}
		if (specs.isEmpty()) {
			throw new MedicoSinEspecialidadException();
		}
		Medico m1 = new Medico(nickname, password, nombre, apellido, correo, nroReg, licencia, porcCost, link);
		specs.forEach( e -> m1.agregarEspecialidad(ManejadorEspecialidad.get_instance().getEspecialidad(e)));
		usuarios.put(nickname, m1);
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
	
	public void agregar_paciente(String nickname, String password, String nombre, String apellido, String correo, LocalDate _fechaNac, LocalDate _fechaIn, int _doc, ArrayList<String> _antSalud){
		if (exists(nickname)) {
			throw new NicknameAlreadyExistsException("Ya existe usuario con el nickname ingresado");
		}
		if (existe_correo(correo)) {
			throw new EmailAlreadyExistsException("Ya existe usuario con el correo ingresado");
		}
		Paciente p1 = new Paciente(nickname, password, nombre, apellido, correo, _fechaNac, _fechaIn, _doc, _antSalud);
		usuarios.put(nickname, p1);
	}
	
	public HashSet<DTPaciente> get_dtpacientes(){
		HashSet<DTPaciente> result = new HashSet<DTPaciente>();
		usuarios.forEach(
					(name, user) -> {
						if (user instanceof Paciente p) {
							result.add(p.get_dtpaciente());
						}
					}
				);
		return result;
	}
	
	public HashSet<DTMedico> get_dtmedicos(){
		HashSet<DTMedico> result = new HashSet<DTMedico>();
		usuarios.forEach(
					(name, user) -> {
						if (user instanceof Medico m) {
							result.add(m.get_dtmedico());
						}
					}
				);
		return result;
	}
	
	public DTResConsultaDetalle datosResConsulta(String nicknameUsu, int numReserva) {
	
		Usuario u = get_usuario(nicknameUsu);
	    if (u instanceof Paciente p) {
	        for (logica.clases.Reserva r : p.get_reservas()) {
	            if (r.getNumReserva() == numReserva) {
	                return r.getDataResConsultaDetalle();
	            }
	        }
	        throw new UserNotFoundException(
	            "No se encontró la reserva seleccionada."
	        );
	    }
	    return null;
	}
	
	private Paciente get_paciente(String nickname) {
		Usuario u = get_usuario(nickname);
		if (u instanceof Paciente p) {
			return p;
		}
		throw new UserNotFoundException("El usuario no es de tipo Paciente");
	}
	
	private Medico get_medico(String nickname) {
		Usuario u = get_usuario(nickname);
		if (u instanceof Medico m) {
			return m;
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
		Paciente p = get_paciente(nickname);
		if (p.tieneBeneficio(anio)) {
			throw new BeneficioAlreadyExistsException("El paciente ya tiene un beneficio anual para el año " + anio);
		}
		p.agregarBeneficio(new BeneficioAnual(anio, cantOrdenes, cantTicket));
	}
	
	public HashSet<DTBeneficioAnual> beneficiosAnuales(String nickname) {
		return get_paciente(nickname).get_dtbeneficios();
	}
	
	public DTBeneficioAnual datosBeneficio(String nickname, int anio) {
		BeneficioAnual b = get_paciente(nickname).getBeneficio(anio);
		if (b == null) {
			throw new BeneficioNotFoundException("El paciente no tiene un beneficio anual para el año " + anio);
		}
		return b.getDataBeneficioAnual();
	}
	
	public DTUsuario datosUsuario(String nickname) {
		return get_usuario(nickname).get_dt();
	}
	
	public ArrayList<String> especialidadesMedico(String nickname) {
		return get_medico(nickname).get_nombresEspecialidades();
	}
	
	public void modificar_medico(String nickname, String nombre, String apellido, String nroReg, TipoMes mesLic, int porcCost, String link, ArrayList<String> especialidades) {
		Medico m = get_medico(nickname);
		if (especialidades == null || especialidades.isEmpty()) {
			throw new MedicoSinEspecialidadException("El médico debe tener al menos una especialidad");
		}
		if (!nroReg.equals(m.get_nroReg()) && existe_nroReg(nroReg)) {
			throw new NumeroDeRegistroAlreadyExistsException("Ya existe este número de registro en el sistema");
		}
		HashSet<Especialidad> nuevas = new HashSet<Especialidad>();
		for (String esp : especialidades) {
			nuevas.add(ManejadorEspecialidad.get_instance().getEspecialidad(esp));
		}
		m.setNombre(nombre);
		m.setApellido(apellido);
		m.set_nroReg(nroReg);
		m.set_mesLic(mesLic);
		m.set_porcentajeCosto(porcCost);
		m.set_link(link);
		m.set_especialidades(nuevas);
	}
	
	public void modificar_paciente(String nickname, String nombre, String apellido, LocalDate fechaNac, LocalDate fechaIn, int doc, ArrayList<String> antSalud) {
		Paciente p = get_paciente(nickname);
		p.setNombre(nombre);
		p.setApellido(apellido);
		p.setFechaNac(fechaNac);
		p.setFechaIn(fechaIn);
		p.setDoc(doc);
		p.setAntSalud(antSalud);
	}

}
