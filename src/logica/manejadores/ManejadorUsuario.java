package logica.manejadores;
import logica.clases.Usuario;
import logica.clases.Medico;
import logica.clases.Paciente;
import logica.clases.Especialidad;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import logica.datatypes.*;
import logica.excepciones.*;

public class ManejadorUsuario {
	private static ManejadorUsuario instance;
	private HashMap<String, Usuario> usuarios;
	
	private ManejadorUsuario() {
		usuarios = new HashMap<String,Usuario>();
	}
	
	public static ManejadorUsuario get_instance() {
		if (instance == null) {
			instance = new ManejadorUsuario();
		}
		return instance;
	}
	
	public boolean existe(String nickname) {
		if (usuarios.containsKey(nickname)) {
			return true;
		}
		return false;
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
	public HashSet<DTConsulta> reservasConsultasAsociadas(String nicknameUsu){
		Usuario u = get_usuario(nicknameUsu);
		if (u instanceof Paciente p) {
			ManejadorConsulta c = ManejadorConsulta.get_instance();
			HashSet<DTConsulta> result = new HashSet<DTConsulta>();
			p.get_reservas().forEach(
						r-> result.add(c.get_consulta(r.get_consultaAsociada()).getDataConsulta())
					);
			return result;
		}
		throw new UserNotFoundException("No existe paciente con el nickname ingresado.");
	}
	
	public void agregar_medico(String nickname, String nombre, String apellido, String correo, int nroReg, TipoMes licencia, int porcCost, String link, String espNom, int espPorc) {
		if (existe(nickname)) {
			throw new NicknameAlreadyExistsException("Ya existe usuario con el nickname ingresado");
		}
		if (existe_correo(correo)) {
			throw new NicknameAlreadyExistsException("Ya existe usuario con el correo ingresado");
		}
		Medico m1 = new Medico(nickname, nombre, apellido, correo, nroReg, licencia, porcCost, link);
		Especialidad esp = ManejadorEspecialidad.get_instance().find_or_create(espNom, espPorc);
		m1.agregarEspecialidad(esp);
		usuarios.put(nickname, m1);
	}
	
	public void agregar_paciente(String nickname, String nombre, String apellido, String correo, LocalDate _fechaNac, LocalDate _fechaIn, int _doc, ArrayList<String> _antSalud){
		if (existe(nickname)) {
			throw new NicknameAlreadyExistsException("Ya existe usuario con el nickname ingresado");
		}
		if (existe_correo(correo)) {
			throw new NicknameAlreadyExistsException("Ya existe usuario con el correo ingresado");
		}
		Paciente p1 = new Paciente(nickname, nombre, apellido, correo, _fechaNac, _fechaIn, _doc, _antSalud);
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
}
