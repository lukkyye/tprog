package logica.controladores;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import logica.manejadores.ManejadorConsulta;
import logica.manejadores.ManejadorEspecialidad;
import logica.manejadores.ManejadorUsuario;
import logica.clases.Consulta;
import logica.datatypes.DtBeneficioAnual;
import logica.datatypes.DtConsulta;
import logica.datatypes.DtConsultaDetalle;
import logica.datatypes.DtMedico;
import logica.datatypes.DtPaciente;
import logica.datatypes.DtResConsulta;
import logica.datatypes.DtResConsultaDetalle;
import logica.datatypes.DtUsuario;
import logica.datatypes.TipoMes;
import logica.excepciones.BeneficioAlreadyExistsException;
import logica.excepciones.BeneficioNotFoundException;
import logica.excepciones.ConsultaNotFoundException;
import logica.excepciones.EmailAlreadyExistsException;
import logica.excepciones.EspecialidadAlreadyExistsException;
import logica.excepciones.IdAlreadyExistsException;
import logica.excepciones.MedicoSinEspecialidadException;
import logica.excepciones.NicknameAlreadyExistsException;
import logica.excepciones.NumeroDeRegistroAlreadyExistsException;
import logica.excepciones.SpecialityNotFoundException;
import logica.excepciones.UserNotFoundException;


public class ControladorUsuario {
	
	
	public boolean ingresarMedico(String nickname, String password, String nombre, String apellido, String correo, String nroReg, TipoMes licencia, int porcCost, String link, List<String> specs)
	throws NicknameAlreadyExistsException, EmailAlreadyExistsException, NumeroDeRegistroAlreadyExistsException, MedicoSinEspecialidadException, SpecialityNotFoundException{
		ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
		manejadorUsuario.agregarMedico(nickname, password, nombre, apellido, correo, nroReg, licencia, porcCost, link, specs);
		return true;
	}
	
	public boolean ingresarPaciente(String nickname, String password, String nombre, String apellido, String correo, LocalDate fechaNac, LocalDate fechaIn, int doc, List<String> antSalud) throws NicknameAlreadyExistsException, EmailAlreadyExistsException, IdAlreadyExistsException {
		ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
		manejadorUsuario.agregarPaciente(nickname, password, nombre, apellido, correo, fechaNac, fechaIn, doc, antSalud);
		return true;
	}//Falta
	
	
	
	public boolean login(String nickname, String password) {
		return ManejadorUsuario.getInstance().login(nickname, password);
	}
	
	public boolean altaEspecialidad(String espNom, int espPorc) throws EspecialidadAlreadyExistsException {
		ManejadorEspecialidad.getInstance().altaEspecialidad(espNom, espPorc);
		return true;
	}
	
	public Set<DtUsuario> listarUsuarios() {
		return ManejadorUsuario.getInstance().getUsuarios();
	}
	
	public DtMedico listarDatosMedico(String nicknameUsu) throws UserNotFoundException {
		return ManejadorUsuario.getInstance().datosMedico(nicknameUsu);
	}
	
	public DtPaciente listarDatosPaciente(String nicknameUsu) throws UserNotFoundException {
		return ManejadorUsuario.getInstance().datosPaciente(nicknameUsu);
	}
	
	public Set<DtConsulta> listarConsultasAsociadas(String nicknameUsu) throws UserNotFoundException, ConsultaNotFoundException {
		return ManejadorUsuario.getInstance().consultasAsociadas(nicknameUsu);
	}
	
	public Set<DtResConsulta> listarResConsultas(String nicknameUsu) throws UserNotFoundException {
		return ManejadorUsuario.getInstance().reservasConsultasAsociadas(nicknameUsu);
	}
	
	public Set<DtPaciente> listarPacientes() {
		return ManejadorUsuario.getInstance().getDtpacientes();
	}
	
	public Set<DtMedico> listarMedicos() {
		return ManejadorUsuario.getInstance().getDtmedicos();
	}
	
	public DtPaciente seleccionarPaciente(String nickname) throws UserNotFoundException {
		return ManejadorUsuario.getInstance().datosPaciente(nickname);
	}
	
	public DtResConsultaDetalle listarDatosResConsulta(String nomC, String nickname) throws UserNotFoundException, ConsultaNotFoundException { // Seguir aca
		ManejadorUsuario musu = ManejadorUsuario.getInstance();
		return musu.datosResConsulta(nomC, nickname);
	}
	
	public DtConsultaDetalle listarDatosConsulta(String nomC) throws ConsultaNotFoundException {
	    ManejadorConsulta manejador = ManejadorConsulta.getInstance();

	    Consulta consulta = manejador.getConsulta(nomC);

	    return consulta.getDataConsultaDetalle();
	}
	
	public void altaBeneficioAnual(String nickname, int anio, int cantOrdenes, int cantTicket) throws UserNotFoundException, BeneficioAlreadyExistsException {
		ManejadorUsuario.getInstance().agregarBeneficio(nickname, anio, cantOrdenes, cantTicket);
	}
	
	public Set<DtBeneficioAnual> listarBeneficiosAnuales(String nickname) throws UserNotFoundException {
		return ManejadorUsuario.getInstance().beneficiosAnuales(nickname);
	}
	
	public DtBeneficioAnual listarDatosBeneficioAnual(String nickname, int anio) throws BeneficioNotFoundException, UserNotFoundException {
		return ManejadorUsuario.getInstance().datosBeneficio(nickname, anio);
	}
	
	public DtUsuario listarDatosUsuario(String nickname) throws UserNotFoundException {
		return ManejadorUsuario.getInstance().datosUsuario(nickname);
	}
	
	public List<String> listarEspecialidadesMedico(String nickname) throws UserNotFoundException {
		return ManejadorUsuario.getInstance().especialidadesMedico(nickname);
	}
	
	public void modificarMedico(String nickname, String nombre, String apellido, String nroReg, TipoMes mesLic, int porcCost, String link, List<String> especialidades) throws MedicoSinEspecialidadException, NumeroDeRegistroAlreadyExistsException, SpecialityNotFoundException, UserNotFoundException {
		ManejadorUsuario.getInstance().modificarMedico(nickname, nombre, apellido, nroReg, mesLic, porcCost, link, especialidades);
	}
	
	public void modificarPaciente(String nickname, String nombre, String apellido, LocalDate fechaNac, LocalDate fechaIn, int doc, List<String> antSalud) throws UserNotFoundException {
		ManejadorUsuario.getInstance().modificarPaciente(nickname, nombre, apellido, fechaNac, fechaIn, doc, antSalud);
	}
	
}
