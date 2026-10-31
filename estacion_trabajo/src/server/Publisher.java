package server;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import logica.datatypes.DtBeneficioAnual;
import logica.datatypes.DtMedico;
import logica.datatypes.DtUsuario;
import logica.datatypes.TipoMes;
import logica.manejadores.ManejadorImagen;
import logica.controladores.ControladorClinica;
import logica.controladores.ControladorUsuario;
import logica.datatypes.DtClinica;
import logica.datatypes.DtConsulta;
import logica.datatypes.DtConsultaDetalle;
import logica.datatypes.DtDiaConsulta;
import logica.datatypes.DtDiaSemana;
import logica.datatypes.DtPaciente;
import logica.datatypes.DtResConsulta;
import logica.datatypes.DtResConsultaDetalle;
import logica.datatypes.EstadoConsulta;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;
	
@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class Publisher {
	private Endpoint endpoint = null;
	private ControladorClinica controladorClinica;
	private ControladorUsuario controladorUsuario;
	private ManejadorImagen manejadorImagen;
	
	public Publisher() {
		controladorClinica=new ControladorClinica();
		controladorUsuario=new ControladorUsuario();
		manejadorImagen=ManejadorImagen.getInstance();
	}
	@WebMethod(exclude=true)
	public void publicar() {
		endpoint = Endpoint.publish("http://localhost:9128/publisher", this);
	}
	@WebMethod(exclude=true)
	public Endpoint getEndpoint() {
		return endpoint;
	}
	
	//======================================================================
	//======================================================================
	//	HASTA ACA EMPIEZA LO DE CLINICA
	//======================================================================
	//======================================================================
	@WebMethod
	public DtDiaConsulta[] listarDatosDiaConsulta() {
	    return controladorClinica.listarDatosDiaConsulta()
	            .toArray(new DtDiaConsulta[0]);
	}

	@WebMethod
	public boolean ingresarClinica(String nombre, String desc, String dir, float costoT, float costoC, String link) {
		return controladorClinica.ingresarClinica(nombre, desc, dir, costoT, costoC, link);
	}
	@WebMethod
	public DtClinica seleccionarClinica(String nombre) {
		return controladorClinica.seleccionarClinica(nombre);
	}
	@WebMethod
	public void setEstadoConsulta(EstadoConsulta state, String nombreConsulta) {
		controladorClinica.setEstadoConsulta(state, nombreConsulta);
	}
	@WebMethod
	public DtConsultaDetalle listarDatosConsulta(String nomC) {
		return controladorClinica.listarDatosConsulta(nomC);
	}

	@WebMethod
	public DtResConsultaDetalle listarDatosResConsultaCC(String nomC, int numReserva) {
		return controladorClinica.listarDatosResConsultaCC(nomC, numReserva);
	}

	@WebMethod
	public HashSet<DtResConsulta> listarReservasConsulta(String nomC) {
		return (HashSet<DtResConsulta>) controladorClinica.getReservasConsulta(nomC);
	}
	
	@WebMethod
	public HashSet<DtConsulta> selecListaConsultaDelDia(String nomD) {
		return (HashSet<DtConsulta>) controladorClinica.selecListaConsultaDelDia(nomD);
	}

	@WebMethod
	public DtConsulta seleccionarConsulta(String nomC) {
		return controladorClinica.seleccionarConsulta(nomC);
	}

	@WebMethod
	public HashSet<DtPaciente> listarPacientes() {
		return (HashSet<DtPaciente>) controladorClinica.listarPacientes();
	}

	@WebMethod
	public void reservarConsulta(String nicknamePaciente, String nomC, LocalDate fechaReserva, Boolean aplicaBenef) {
		controladorClinica.reservarConsulta(nicknamePaciente, nomC, fechaReserva, aplicaBenef);
	}

	@WebMethod
	public HashSet<DtConsulta> listarConsultas() {
		return (HashSet<DtConsulta>) controladorClinica.listarConsultas();
	}

	@WebMethod
	public void ingresarConsulta(String nomD, String med, String nomC, LocalDate fecha, int sala, int cantNum) {
		controladorClinica.ingresarConsulta(nomD, med, nomC, fecha, sala, cantNum);
	}

	@WebMethod
	public HashSet<String> listarClinicas() {
		return (HashSet<String>) controladorClinica.listarClinicas();
	}

	@WebMethod
	public HashSet<String> listarEspecialidades() {
		return (HashSet<String>) controladorClinica.listarEspecialidades();
	}

	@WebMethod
	public void ingresarDiaConsulta(String nombre, String descripcion, DtDiaSemana dia, LocalTime horaInicio,
			String nombreEspecialidad, String nombreClinica) {
		controladorClinica.ingresarDiaConsulta(nombre, descripcion, dia, horaInicio, nombreEspecialidad, nombreClinica);
	}

	@WebMethod
	public ArrayList<DtDiaConsulta> listarDiaConsultas() {
		return (ArrayList<DtDiaConsulta>) controladorClinica.listarDiaConsultas();
	}

	@WebMethod
	public ArrayList<String> getConsultasPorEspecialidad(String especialidad) {
		return (ArrayList<String>) controladorClinica.getConsultasPorEspecialidad(especialidad);
	}
	
	//======================================================================
	//======================================================================
	//	HASTA ACA LLEGA LO DE CLINICA
	//======================================================================
	//======================================================================
	
	
	//======================================================================
	//======================================================================
	//	HASTA ACA EMPIEZA LO DE USUARIO
	//======================================================================
	//======================================================================
	@WebMethod
	public boolean ingresarMedico(String nickname, String password, String nombre, String apellido, String correo,
			String nroReg, TipoMes licencia, int porcCost, String link, ArrayList<String> specs) {
		return controladorUsuario.ingresarMedico(nickname, password, nombre, apellido, correo, nroReg, licencia, porcCost, link, specs);
	}

	@WebMethod
	public boolean ingresarPaciente(String nickname, String password, String nombre, String apellido, String correo,
			LocalDate fechaNac, LocalDate fechaIn, int doc, ArrayList<String> antSalud) {
		return controladorUsuario.ingresarPaciente(nickname, password, nombre, apellido, correo, fechaNac, fechaIn, doc, antSalud);
	}

	@WebMethod
	public boolean login(String nickname, String password) {
		return controladorUsuario.login(nickname, password);
	}

	@WebMethod
	public boolean altaEspecialidad(String espNom, int espPorc) {
		return controladorUsuario.altaEspecialidad(espNom, espPorc);
	}

	@WebMethod
	public HashSet<DtUsuario> listarUsuarios() {
		return (HashSet<DtUsuario>) controladorUsuario.listarUsuarios();
	}

	@WebMethod
	public DtMedico listarDatosMedico(String nicknameUsu) {
		return controladorUsuario.listarDatosMedico(nicknameUsu);
	}

	@WebMethod
	public DtPaciente listarDatosPaciente(String nicknameUsu) {
		return controladorUsuario.listarDatosPaciente(nicknameUsu);
	}

	@WebMethod
	public HashSet<DtConsulta> listarConsultasAsociadas(String nicknameUsu) {
		return (HashSet<DtConsulta>) controladorUsuario.listarConsultasAsociadas(nicknameUsu);
	}

	@WebMethod
	public HashSet<DtResConsulta> listarResConsultas(String nicknameUsu) {
		return (HashSet<DtResConsulta>) controladorUsuario.listarResConsultas(nicknameUsu);
	}

	@WebMethod
	public HashSet<DtMedico> listarMedicos() {
		return (HashSet<DtMedico>) controladorUsuario.listarMedicos();
	}

	@WebMethod
	public DtPaciente seleccionarPaciente(String nickname) {
		return controladorUsuario.seleccionarPaciente(nickname);
	}

	@WebMethod
	public void altaBeneficioAnual(String nickname, int anio, int cantOrdenes, int cantTicket) {
		controladorUsuario.altaBeneficioAnual(nickname, anio, cantOrdenes, cantTicket);
	}

	@WebMethod
	public HashSet<DtBeneficioAnual> listarBeneficiosAnuales(String nickname) {
		return (HashSet<DtBeneficioAnual>) controladorUsuario.listarBeneficiosAnuales(nickname);
	}

	@WebMethod
	public DtBeneficioAnual listarDatosBeneficioAnual(String nickname, int anio) {
		return controladorUsuario.listarDatosBeneficioAnual(nickname, anio);
	}

	@WebMethod
	public DtUsuario listarDatosUsuario(String nickname) {
		return controladorUsuario.listarDatosUsuario(nickname);
	}

	@WebMethod
	public ArrayList<String> listarEspecialidadesMedico(String nickname) {
		return (ArrayList<String>) controladorUsuario.listarEspecialidadesMedico(nickname);
	}

	@WebMethod
	public void modificarMedico(String nickname, String nombre, String apellido, String nroReg, TipoMes mesLic,
			int porcCost, String link, ArrayList<String> especialidades) {
		controladorUsuario.modificarMedico(nickname, nombre, apellido, nroReg, mesLic, porcCost, link, especialidades);
	}

	@WebMethod
	public void modificarPaciente(String nickname, String nombre, String apellido, LocalDate fechaNac,
			LocalDate fechaIn, int doc, ArrayList<String> antSalud) {
		controladorUsuario.modificarPaciente(nickname, nombre, apellido, fechaNac, fechaIn, doc, antSalud);
	}

	@WebMethod
	public DtResConsultaDetalle listarDatosResConsulta(String nomC, String nickname) {
		return controladorUsuario.listarDatosResConsulta(nomC, nickname);
	}
	//======================================================================
	//======================================================================
	//	HASTA LLEGA LO DE USUARIO
	//======================================================================
	//======================================================================
	
	
	//======================================================================
	//======================================================================
	// ACA EMPIEZA LO DE IMAGEN
	//======================================================================
	//======================================================================
	
	@WebMethod
	public void addImg(String b64, String key) {
		manejadorImagen.addImg(b64, key);
	}

	@WebMethod
	public String getImg64(String key) {
		return manejadorImagen.getImg64(key);
	}
}
