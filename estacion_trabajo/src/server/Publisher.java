package server;
	
import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import logica.datatypes.DTBeneficioAnual;
import logica.datatypes.DTMedico;
import logica.datatypes.DTUsuario;
import logica.datatypes.TipoMes;
import logica.manejadores.ManejadorImagen;
import logica.controladores.ControladorClinica;
import logica.controladores.ControladorUsuario;
import logica.datatypes.DTClinica;
import logica.datatypes.DTConsulta;
import logica.datatypes.DTConsultaDetalle;
import logica.datatypes.DTDiaConsulta;
import logica.datatypes.DTDiaSemana;
import logica.datatypes.DTPaciente;
import logica.datatypes.DTResConsulta;
import logica.datatypes.DTResConsultaDetalle;
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
	public Set<DTDiaConsulta> listarDatosDiaConsulta() {
		return controladorClinica.listarDatosDiaConsulta();
	}

	@WebMethod
	public boolean ingresarClinica(String nombre, String desc, String dir, float costoT, float costoC, String link) {
		return controladorClinica.ingresarClinica(nombre, desc, dir, costoT, costoC, link);
	}
	@WebMethod
	public DTClinica seleccionarClinica(String nombre) {
		return controladorClinica.seleccionarClinica(nombre);
	}
	@WebMethod
	public void setEstadoConsulta(EstadoConsulta state, String nombreConsulta) {
		controladorClinica.setEstadoConsulta(state, nombreConsulta);
	}
	@WebMethod
	public DTConsultaDetalle listarDatosConsulta(String nomC) {
		return controladorClinica.listarDatosConsulta(nomC);
	}

	@WebMethod
	public DTResConsultaDetalle listarDatosResConsultaCC(String nomC, int numReserva) {
		return controladorClinica.listarDatosResConsultaCC(nomC, numReserva);
	}

	@WebMethod
	public Set<DTResConsulta> listarReservasConsulta(String nomC) {
		return controladorClinica.getReservasConsulta(nomC);
	}
	
	@WebMethod
	public Set<DTConsulta> selecListaConsultaDelDia(String nomD) {
		return controladorClinica.selecListaConsultaDelDia(nomD);
	}

	@WebMethod
	public DTConsulta seleccionarConsulta(String nomC) {
		return controladorClinica.seleccionarConsulta(nomC);
	}

	@WebMethod
	public Set<DTPaciente> listarPacientes() {
		return controladorClinica.listarPacientes();
	}

	@WebMethod
	public void reservarConsulta(String nicknamePaciente, String nomC, LocalDate fechaReserva, Boolean aplicaBenef) {
		controladorClinica.reservarConsulta(nicknamePaciente, nomC, fechaReserva, aplicaBenef);
	}

	@WebMethod
	public Set<DTConsulta> listarConsultas() {
		return controladorClinica.listarConsultas();
	}

	@WebMethod
	public void ingresarConsulta(String nomD, String med, String nomC, LocalDate fecha, int sala, int cantNum) {
		controladorClinica.ingresarConsulta(nomD, med, nomC, fecha, sala, cantNum);
	}

	@WebMethod
	public Set<String> listarClinicas() {
		return controladorClinica.listarClinicas();
	}

	@WebMethod
	public Set<String> listarEspecialidades() {
		return controladorClinica.listarEspecialidades();
	}

	@WebMethod
	public void ingresarDiaConsulta(String nombre, String descripcion, DTDiaSemana dia, LocalTime horaInicio,
			String nombreEspecialidad, String nombreClinica) {
		controladorClinica.ingresarDiaConsulta(nombre, descripcion, dia, horaInicio, nombreEspecialidad, nombreClinica);
	}

	@WebMethod
	public boolean ingresarClinica(String nombre, String desc, String dir, float costoT, float costoC, String link,
			String img) {
		return controladorClinica.ingresarClinica(nombre, desc, dir, costoT, costoC, link, img);
	}

	@WebMethod
	public List<DTDiaConsulta> listarDiaConsultas() {
		return controladorClinica.listarDiaConsultas();
	}

	@WebMethod
	public List<String> getConsultasPorEspecialidad(String especialidad) {
		return controladorClinica.getConsultasPorEspecialidad(especialidad);
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
			String nroReg, TipoMes licencia, int porcCost, String link, List<String> specs) {
		return controladorUsuario.ingresarMedico(nickname, password, nombre, apellido, correo, nroReg, licencia, porcCost, link, specs);
	}

	@WebMethod
	public boolean ingresarPaciente(String nickname, String password, String nombre, String apellido, String correo,
			LocalDate fechaNac, LocalDate fechaIn, int doc, List<String> antSalud) {
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
	public Set<DTUsuario> listarUsuarios() {
		return controladorUsuario.listarUsuarios();
	}

	@WebMethod
	public DTMedico listarDatosMedico(String nicknameUsu) {
		return controladorUsuario.listarDatosMedico(nicknameUsu);
	}

	@WebMethod
	public DTPaciente listarDatosPaciente(String nicknameUsu) {
		return controladorUsuario.listarDatosPaciente(nicknameUsu);
	}

	@WebMethod
	public Set<DTConsulta> listarConsultasAsociadas(String nicknameUsu) {
		return controladorUsuario.listarConsultasAsociadas(nicknameUsu);
	}

	@WebMethod
	public Set<DTResConsulta> listarResConsultas(String nicknameUsu) {
		return controladorUsuario.listarResConsultas(nicknameUsu);
	}

	@WebMethod
	public Set<DTMedico> listarMedicos() {
		return controladorUsuario.listarMedicos();
	}

	@WebMethod
	public DTPaciente seleccionarPaciente(String nickname) {
		return controladorUsuario.seleccionarPaciente(nickname);
	}

	@WebMethod
	public void altaBeneficioAnual(String nickname, int anio, int cantOrdenes, int cantTicket) {
		controladorUsuario.altaBeneficioAnual(nickname, anio, cantOrdenes, cantTicket);
	}

	@WebMethod
	public Set<DTBeneficioAnual> listarBeneficiosAnuales(String nickname) {
		return controladorUsuario.listarBeneficiosAnuales(nickname);
	}

	@WebMethod
	public DTBeneficioAnual listarDatosBeneficioAnual(String nickname, int anio) {
		return controladorUsuario.listarDatosBeneficioAnual(nickname, anio);
	}

	@WebMethod
	public DTUsuario listarDatosUsuario(String nickname) {
		return controladorUsuario.listarDatosUsuario(nickname);
	}

	@WebMethod
	public List<String> listarEspecialidadesMedico(String nickname) {
		return controladorUsuario.listarEspecialidadesMedico(nickname);
	}

	@WebMethod
	public void modificarMedico(String nickname, String nombre, String apellido, String nroReg, TipoMes mesLic,
			int porcCost, String link, List<String> especialidades) {
		controladorUsuario.modificarMedico(nickname, nombre, apellido, nroReg, mesLic, porcCost, link, especialidades);
	}

	@WebMethod
	public void modificarPaciente(String nickname, String nombre, String apellido, LocalDate fechaNac,
			LocalDate fechaIn, int doc, List<String> antSalud) {
		controladorUsuario.modificarPaciente(nickname, nombre, apellido, fechaNac, fechaIn, doc, antSalud);
	}

	@WebMethod
	public DTResConsultaDetalle listarDatosResConsulta(String nomC, String nickname) {
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
	public BufferedImage getImg(String key) {
		return manejadorImagen.getImg(key);
	}

	@WebMethod
	public String getImg64(String key) {
		return manejadorImagen.getImg64(key);
	}
}
