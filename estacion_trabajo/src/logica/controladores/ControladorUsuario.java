package logica.controladores;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import logica.manejadores.ManejadorConsulta;
import logica.manejadores.ManejadorEspecialidad;
import logica.manejadores.ManejadorUsuario;
import logica.clases.Consulta;
import logica.datatypes.DTBeneficioAnual;
import logica.datatypes.DTConsulta;
import logica.datatypes.DTConsultaDetalle;
import logica.datatypes.DTMedico;
import logica.datatypes.DTPaciente;
import logica.datatypes.DTResConsulta;
import logica.datatypes.DTResConsultaDetalle;
import logica.datatypes.DTUsuario;
import logica.datatypes.TipoMes;


public class ControladorUsuario {
	
	
	public boolean ingresarMedico(String nickname, String password, String nombre, String apellido, String correo, String nroReg, TipoMes licencia, int porcCost, String link, List<String> specs) {
		ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
		manejadorUsuario.agregarMedico(nickname, password, nombre, apellido, correo, nroReg, licencia, porcCost, link, specs);
		return true;
	}
	
	public boolean ingresarPaciente(String nickname, String password, String nombre, String apellido, String correo, LocalDate fechaNac, LocalDate fechaIn, int doc, List<String> antSalud) {
		ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
		manejadorUsuario.agregarPaciente(nickname, password, nombre, apellido, correo, fechaNac, fechaIn, doc, antSalud);
		return true;
	}//Falta
	
	
	
	public boolean login(String nickname, String password) {
		return ManejadorUsuario.getInstance().login(nickname, password);
	}
	
	public boolean altaEspecialidad(String espNom, int espPorc) {
		ManejadorEspecialidad.getInstance().altaEspecialidad(espNom, espPorc);
		return true;
	}
	
	public Set<DTUsuario> listarUsuarios() {
		return ManejadorUsuario.getInstance().getUsuarios();
	}
	
	public DTMedico listarDatosMedico(String nicknameUsu) {
		return ManejadorUsuario.getInstance().datosMedico(nicknameUsu);
	}
	
	public DTPaciente listarDatosPaciente(String nicknameUsu) {
		return ManejadorUsuario.getInstance().datosPaciente(nicknameUsu);
	}
	
	public Set<DTConsulta> listarConsultasAsociadas(String nicknameUsu) {
		return ManejadorUsuario.getInstance().consultasAsociadas(nicknameUsu);
	}
	
	public Set<DTResConsulta> listarResConsultas(String nicknameUsu) {
		return ManejadorUsuario.getInstance().reservasConsultasAsociadas(nicknameUsu);
	}
	
	public Set<DTPaciente> listarPacientes() {
		return ManejadorUsuario.getInstance().getDtpacientes();
	}
	
	public Set<DTMedico> listarMedicos() {
		return ManejadorUsuario.getInstance().getDtmedicos();
	}
	
	public DTPaciente seleccionarPaciente(String nickname) {
		return ManejadorUsuario.getInstance().datosPaciente(nickname);
	}
	
	public DTResConsultaDetalle listarDatosResConsulta(String nomC, String nickname) { // Seguir aca
		ManejadorUsuario musu = ManejadorUsuario.getInstance();
		return musu.datosResConsulta(nomC, nickname);
	}
	
	public DTConsultaDetalle listarDatosConsulta(String nomC) {
	    ManejadorConsulta manejador = ManejadorConsulta.getInstance();

	    Consulta consulta = manejador.getConsulta(nomC);

	    return consulta.getDataConsultaDetalle();
	}
	
	public void altaBeneficioAnual(String nickname, int anio, int cantOrdenes, int cantTicket) {
		ManejadorUsuario.getInstance().agregarBeneficio(nickname, anio, cantOrdenes, cantTicket);
	}
	
	public Set<DTBeneficioAnual> listarBeneficiosAnuales(String nickname) {
		return ManejadorUsuario.getInstance().beneficiosAnuales(nickname);
	}
	
	public DTBeneficioAnual listarDatosBeneficioAnual(String nickname, int anio) {
		return ManejadorUsuario.getInstance().datosBeneficio(nickname, anio);
	}
	
	public DTUsuario listarDatosUsuario(String nickname) {
		return ManejadorUsuario.getInstance().datosUsuario(nickname);
	}
	
	public List<String> listarEspecialidadesMedico(String nickname) {
		return ManejadorUsuario.getInstance().especialidadesMedico(nickname);
	}
	
	public void modificarMedico(String nickname, String nombre, String apellido, String nroReg, TipoMes mesLic, int porcCost, String link, List<String> especialidades) {
		ManejadorUsuario.getInstance().modificarMedico(nickname, nombre, apellido, nroReg, mesLic, porcCost, link, especialidades);
	}
	
	public void modificarPaciente(String nickname, String nombre, String apellido, LocalDate fechaNac, LocalDate fechaIn, int doc, List<String> antSalud) {
		ManejadorUsuario.getInstance().modificarPaciente(nickname, nombre, apellido, fechaNac, fechaIn, doc, antSalud);
	}
	
}
