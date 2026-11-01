package server;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import logica.datatypes.DtBeneficioAnual;
import logica.datatypes.DtMedico;
import logica.datatypes.DtUsuario;
import logica.datatypes.TipoMes;
import logica.excepciones.BeneficioAlreadyExistsException;
import logica.excepciones.BeneficioNotFoundException;
import logica.excepciones.BeneficioSinOrdenesException;
import logica.excepciones.ConsultaNombreAlreadyExistsException;
import logica.excepciones.ConsultaNotFoundException;
import logica.excepciones.DiaConsultaAlreadyExistsException;
import logica.excepciones.EmailAlreadyExistsException;
import logica.excepciones.EspecialidadAlreadyExistsException;
import logica.excepciones.FechaDiaSemanaInvalidaException;
import logica.excepciones.IdAlreadyExistsException;
import logica.excepciones.MedicoSinEspecialidadException;
import logica.excepciones.NicknameAlreadyExistsException;
import logica.excepciones.NoNumberAvailableException;
import logica.excepciones.NombreClinicaAlreadyExistsException;
import logica.excepciones.NumeroDeRegistroAlreadyExistsException;
import logica.excepciones.ReservaAlreadyExistsException;
import logica.excepciones.SpecialityNotFoundException;
import logica.excepciones.UserNotFoundException;
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
//	@WebMethod(exclude=true)
//	public void publicar() {
//		endpoint = Endpoint.publish("http://localhost:9128/publisher", this);
//	}
	
	@WebMethod(exclude=true)
	public void publicar() {
	    try { endpoint = Endpoint.publish("http://0.0.0.0:9128/publisher", this);
	    	System.out.println("servicio publicado?: " + endpoint.isPublished());
	    } catch (Exception e) {
	        System.out.println("ERROR AL PUBLICAR SOAP:");
	        e.printStackTrace();
	    }
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
	public boolean ingresarClinica(String nombre, String desc, String dir, float costoT, float costoC, String link) throws NombreClinicaAlreadyExistsException {
		return controladorClinica.ingresarClinica(nombre, desc, dir, costoT, costoC, link);
	}
	@WebMethod
	public DtClinica seleccionarClinica(String nombre)  {
		return controladorClinica.seleccionarClinica(nombre);
	}
	@WebMethod
	public void setEstadoConsulta(EstadoConsulta state, String nombreConsulta) throws ConsultaNotFoundException{
		controladorClinica.setEstadoConsulta(state, nombreConsulta);
	}
	@WebMethod
	public DtConsultaDetalle listarDatosConsulta(String nomC) throws ConsultaNotFoundException  {
		return controladorClinica.listarDatosConsulta(nomC);
	}

	@WebMethod
	public DtResConsultaDetalle listarDatosResConsultaCC(String nomC, int numReserva) throws ConsultaNotFoundException{
		return controladorClinica.listarDatosResConsultaCC(nomC, numReserva);
	}

	@WebMethod
	public HashSet<DtResConsulta> listarReservasConsulta(String nomC) throws ConsultaNotFoundException {
		return (HashSet<DtResConsulta>) controladorClinica.getReservasConsulta(nomC);
	}
	
	@WebMethod
	public HashSet<DtConsulta> selecListaConsultaDelDia(String nomD) {
		return (HashSet<DtConsulta>) controladorClinica.selecListaConsultaDelDia(nomD);
	}

	@WebMethod
	public DtConsulta seleccionarConsulta(String nomC) throws ConsultaNotFoundException {
		return controladorClinica.seleccionarConsulta(nomC);
	}

	@WebMethod
	public HashSet<DtPaciente> listarPacientes() {
		return (HashSet<DtPaciente>) controladorClinica.listarPacientes();
	}

	@WebMethod
	public void reservarConsulta(String nicknamePaciente, String nomC, LocalDate fechaReserva, Boolean aplicaBenef)
	throws ReservaAlreadyExistsException, NoNumberAvailableException, BeneficioSinOrdenesException, ConsultaNotFoundException, UserNotFoundException 
	{
		controladorClinica.reservarConsulta(nicknamePaciente, nomC, fechaReserva, aplicaBenef);
	}

	@WebMethod
	public HashSet<DtConsulta> listarConsultas() {
		return (HashSet<DtConsulta>) controladorClinica.listarConsultas();
	}

	@WebMethod
	public void ingresarConsulta(String nomD, String med, String nomC, LocalDate fecha, int sala, int cantNum)
	throws ConsultaNombreAlreadyExistsException, UserNotFoundException, MedicoSinEspecialidadException, FechaDiaSemanaInvalidaException
	{
		controladorClinica.ingresarConsulta(nomD, med, nomC, fecha, sala, cantNum);
	}

	@WebMethod
	public HashSet<String> listarClinicas() {
		return (HashSet<String>) controladorClinica.listarClinicas();
	}

	@WebMethod
	public String[] listarEspecialidades() {
	    return controladorClinica.listarEspecialidades().toArray(new String[0]);
	}

	@WebMethod
	public void ingresarDiaConsulta(String nombre, String descripcion, DtDiaSemana dia, LocalTime horaInicio,
			String nombreEspecialidad, String nombreClinica) throws
	DiaConsultaAlreadyExistsException, SpecialityNotFoundException, DiaConsultaAlreadyExistsException
	{
		controladorClinica.ingresarDiaConsulta(nombre, descripcion, dia, horaInicio, nombreEspecialidad, nombreClinica);
	}


	@WebMethod
	public DtDiaConsulta[] listarDiaConsultas() {
		return  controladorClinica.listarDiaConsultas().toArray(new DtDiaConsulta[0]);
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
			String nroReg, TipoMes licencia, int porcCost, String link, ArrayList<String> specs) throws NicknameAlreadyExistsException, EmailAlreadyExistsException, NumeroDeRegistroAlreadyExistsException, MedicoSinEspecialidadException, SpecialityNotFoundException{
		return controladorUsuario.ingresarMedico(nickname, password, nombre, apellido, correo, nroReg, licencia, porcCost, link, specs);
	}

	@WebMethod
	public boolean ingresarPaciente(String nickname, String password, String nombre, String apellido, String correo,
			LocalDate fechaNac, LocalDate fechaIn, int doc, ArrayList<String> antSalud)
	throws NicknameAlreadyExistsException, EmailAlreadyExistsException, IdAlreadyExistsException
	{
		return controladorUsuario.ingresarPaciente(nickname, password, nombre, apellido, correo, fechaNac, fechaIn, doc, antSalud);
	}

	@WebMethod
	public boolean login(String nickname, String password) {
		return controladorUsuario.login(nickname, password);
	}

	@WebMethod
	public boolean altaEspecialidad(String espNom, int espPorc) throws EspecialidadAlreadyExistsException{
		return controladorUsuario.altaEspecialidad(espNom, espPorc);
	}

	@WebMethod
	public HashSet<DtUsuario> listarUsuarios() {
		return (HashSet<DtUsuario>) controladorUsuario.listarUsuarios();
	}

	@WebMethod
	public DtMedico listarDatosMedico(String nicknameUsu) throws UserNotFoundException{
		return controladorUsuario.listarDatosMedico(nicknameUsu);
	}

	@WebMethod
	public DtPaciente listarDatosPaciente(String nicknameUsu) throws UserNotFoundException{
		return controladorUsuario.listarDatosPaciente(nicknameUsu);
	}

	@WebMethod
	public HashSet<DtConsulta> listarConsultasAsociadas(String nicknameUsu) throws UserNotFoundException, ConsultaNotFoundException {
		return (HashSet<DtConsulta>) controladorUsuario.listarConsultasAsociadas(nicknameUsu);
	}

	@WebMethod
	public HashSet<DtResConsulta> listarResConsultas(String nicknameUsu) throws UserNotFoundException {
		return (HashSet<DtResConsulta>) controladorUsuario.listarResConsultas(nicknameUsu);
	}

	@WebMethod
	public HashSet<DtMedico> listarMedicos() {
		return (HashSet<DtMedico>) controladorUsuario.listarMedicos();
	}

	@WebMethod
	public DtPaciente seleccionarPaciente(String nickname) throws UserNotFoundException {
		return controladorUsuario.seleccionarPaciente(nickname);
	}

	@WebMethod
	public void altaBeneficioAnual(String nickname, int anio, int cantOrdenes, int cantTicket) throws BeneficioAlreadyExistsException, UserNotFoundException{
		controladorUsuario.altaBeneficioAnual(nickname, anio, cantOrdenes, cantTicket);
	}

	@WebMethod
	public HashSet<DtBeneficioAnual> listarBeneficiosAnuales(String nickname) throws UserNotFoundException {
		return (HashSet<DtBeneficioAnual>) controladorUsuario.listarBeneficiosAnuales(nickname);
	}

	@WebMethod
	public DtBeneficioAnual listarDatosBeneficioAnual(String nickname, int anio) throws BeneficioNotFoundException, UserNotFoundException {
		return controladorUsuario.listarDatosBeneficioAnual(nickname, anio);
	}

	@WebMethod
	public DtUsuario listarDatosUsuario(String nickname) throws UserNotFoundException{
		return controladorUsuario.listarDatosUsuario(nickname);
	}

	@WebMethod
	public ArrayList<String> listarEspecialidadesMedico(String nickname) throws UserNotFoundException {
		return (ArrayList<String>) controladorUsuario.listarEspecialidadesMedico(nickname);
	}

	@WebMethod
	public void modificarMedico(String nickname, String nombre, String apellido, String nroReg, TipoMes mesLic,
			int porcCost, String link, ArrayList<String> especialidades) throws MedicoSinEspecialidadException, NumeroDeRegistroAlreadyExistsException, SpecialityNotFoundException, UserNotFoundException {
		controladorUsuario.modificarMedico(nickname, nombre, apellido, nroReg, mesLic, porcCost, link, especialidades);
	}

	@WebMethod
	public void modificarPaciente(String nickname, String nombre, String apellido, LocalDate fechaNac,
			LocalDate fechaIn, int doc, ArrayList<String> antSalud) throws UserNotFoundException{
		controladorUsuario.modificarPaciente(nickname, nombre, apellido, fechaNac, fechaIn, doc, antSalud);
	}

	@WebMethod
	public DtResConsultaDetalle listarDatosResConsulta(String nomC, String nickname) throws ConsultaNotFoundException, UserNotFoundException {
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
