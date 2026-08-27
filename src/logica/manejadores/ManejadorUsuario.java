package logica.manejadores;
import logica.clases.Usuario;
import logica.clases.Medico;
import logica.clases.Paciente;
import java.time.LocalDate;
import java.util.HashSet;
import logica.datatypes.*;
import logica.excepciones.NicknameAlreadyExistsException;

public class ManejadorUsuario {
	private static ManejadorUsuario instance;
	private HashSet<Usuario> usuarios;
	//private HashSet<Reserva> reservas;
	
	private ManejadorUsuario() {}
	
	public static ManejadorUsuario get_instance() {
		if (instance == null) {
			instance = new ManejadorUsuario();
		}
		return instance;
	}
	
	public boolean existe(String nickname) {
		for (Usuario u: usuarios) {
			if (u.getNickname().equals(nickname)) {
				return true;
			}
		}
		return false;
	}
	//public DTMedico datosMedicos(String nicknameUsu) {
		// diferencia entre DTMedico y Medico?
	//public HashSet set_usuarios() 

	public void agregar_medico(String nickname, String nombre, String apellido, String correo, int nroReg, TipoMes licencia, int porcCost, String link, String espNom) {
		if (existe(nickname)) {
			throw new NicknameAlreadyExistsException("Ya existe usuario con el nickname ingresado");
		}
		Medico m1 = new Medico(nickname, nombre, apellido, correo, nroReg, licencia, porcCost, link);
		usuarios.add(m1);
	}
	
	public void agregar_paciente(String nickname, String nombre, String apellido, String correo, LocalDate _fechaNac, int _doc, LocalDate _fechaIngreso){
		if (existe(nickname)) {
			throw new NicknameAlreadyExistsException("Ya existe usuario con el nickname ingresado");
		}
		LocalDate fecha_ahora = LocalDate.now();
		Paciente p1 = new Paciente(nickname, nombre, apellido, correo, _fechaNac, _doc, fecha_ahora);
		usuarios.add(p1);
	}
}
