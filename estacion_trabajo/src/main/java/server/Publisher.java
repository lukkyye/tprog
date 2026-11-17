package server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import  logica.datatypes.DtBeneficioAnual;
import  logica.datatypes.DtMedico;
import  logica.datatypes.DtUsuario;
import  logica.datatypes.TipoMes;
import  logica.excepciones.BeneficioAlreadyExistsException;
import  logica.excepciones.BeneficioNotFoundException;
import  logica.excepciones.BeneficioSinOrdenesException;
import  logica.excepciones.BeneficioSinTicketsException;
import  logica.excepciones.ClinicaNotFoundException;
import  logica.excepciones.ConsultaNombreAlreadyExistsException;
import  logica.excepciones.ConsultaNotFoundException;
import  logica.excepciones.DatosMedicamentoInvalidosException;
import  logica.excepciones.DiaConsultaAlreadyExistsException;
import  logica.excepciones.EmailAlreadyExistsException;
import  logica.excepciones.EspecialidadAlreadyExistsException;
import  logica.excepciones.FechaDiaSemanaInvalidaException;
import  logica.excepciones.FechaEntregaInvalidaException;
import  logica.excepciones.IdAlreadyExistsException;
import  logica.excepciones.MedicamentoAlreadyExistsException;
import  logica.excepciones.MedicamentoNotFoundException;
import  logica.excepciones.MedicamentoVencidoException;
import  logica.excepciones.MedicamentoYaSolicitadoException;
import  logica.excepciones.MedicoSinEspecialidadException;
import  logica.excepciones.NicknameAlreadyExistsException;
import  logica.excepciones.NoNumberAvailableException;
import  logica.excepciones.NombreClinicaAlreadyExistsException;
import  logica.excepciones.NumeroDeRegistroAlreadyExistsException;
import  logica.excepciones.ReservaAlreadyExistsException;
import  logica.excepciones.ReservaDoesntExistsException;
import  logica.excepciones.SpecialityNotFoundException;
import  logica.excepciones.UserNotFoundException;
import  logica.manejadores.ManejadorImagen;
import  logica.controladores.ControladorClinica;
import  logica.controladores.ControladorUsuario;
import  logica.datatypes.DtClinica;
import  logica.datatypes.DtConsulta;
import  logica.datatypes.DtConsultaDetalle;
import  logica.datatypes.DtDiaConsulta;
import  logica.datatypes.DtDiaSemana;
import  logica.datatypes.DtMedicamento;
import  logica.datatypes.DtPaciente;
import  logica.datatypes.DtResAsistio;
import  logica.datatypes.DtResConsulta;
import  logica.datatypes.DtResConsultaDetalle;
import  logica.datatypes.DtSolicitudMedicamento;
import  logica.datatypes.EstadoConsulta;
import  logica.datatypes.DtValoracionConsulta;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import jakarta.xml.ws.Endpoint;


	
@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class Publisher {
	private Endpoint endpoint = null;
	private ControladorClinica controladorClinica;
	private ControladorUsuario controladorUsuario;
	private ManejadorImagen manejadorImagen;
	private String port="9128";
	private String host="localhost";
	
	public Publisher() {
		controladorClinica=new ControladorClinica();
		controladorUsuario=new ControladorUsuario();
		manejadorImagen=ManejadorImagen.getInstance();
	}
//	@WebMethod(exclude=true)
//	public void publicar() {
//		endpoint = Endpoint.publish("http://localhost:9128/publisher", this);
//	}
	
	private void fromProperties() {
		String filePath = System.getProperty("user.home")+File.separator+".saluduy"+File.separator +"config-ws.properties";
		Properties props = new Properties();
		try (FileInputStream input = new FileInputStream(filePath)) {
			props.load(input);
			port=props.getProperty("port");
			host=props.getProperty("host");
			System.out.println("Se usara host:"+host+" y port:"+port);
		} catch (FileNotFoundException e) {
			System.out.println("No se encontro el archivo, se usara host:localhost y port:9128");
		} catch (IOException e) {
			System.out.println("Error en la lectura, se usara host:localhost y port:9128");
		}
	}
	
	@WebMethod(exclude=true)
	public void publicar() {
		fromProperties();
	    try { 
	    	endpoint = Endpoint.publish("http://"+host+":"+port+"/publisher", this);
	    	System.out.println("servicio publicado");
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
	public DtResConsulta[] listarReservasConsulta(String nomC) throws ConsultaNotFoundException {
		return (DtResConsulta[]) controladorClinica.getReservasConsulta(nomC).toArray(new DtResConsulta[0]);
	}
	
	@WebMethod
	public DtConsulta[] selecListaConsultaDelDia(String nomD) {
		return (DtConsulta[]) controladorClinica.selecListaConsultaDelDia(nomD).toArray(new DtConsulta[0]);
	}

	@WebMethod
	public DtConsulta seleccionarConsulta(String nomC) throws ConsultaNotFoundException {
		return controladorClinica.seleccionarConsulta(nomC);
	}

	@WebMethod
	public DtPaciente[] listarPacientes() {
		return  (DtPaciente[]) controladorClinica.listarPacientes().toArray(new DtPaciente[0]);
	}

	@WebMethod
	public void reservarConsulta(String nicknamePaciente, String nomC, @XmlJavaTypeAdapter(logica.datatypes.LocalDateAdapter.class) LocalDate fechaReserva, Boolean aplicaBenef)
	throws ReservaAlreadyExistsException, NoNumberAvailableException, BeneficioSinOrdenesException, ConsultaNotFoundException, UserNotFoundException 
	{
		controladorClinica.reservarConsulta(nicknamePaciente, nomC, fechaReserva, aplicaBenef);
	}

	@WebMethod
	public DtConsulta[] listarConsultas() {
		return (DtConsulta[]) controladorClinica.listarConsultas().toArray(new DtConsulta[0]);
	}

	@WebMethod
	public void ingresarConsulta(String nomD, String med, String nomC, @XmlJavaTypeAdapter(logica.datatypes.LocalDateAdapter.class)LocalDate fecha, int sala, int cantNum)
	throws ConsultaNombreAlreadyExistsException, UserNotFoundException, MedicoSinEspecialidadException, FechaDiaSemanaInvalidaException
	{
		controladorClinica.ingresarConsulta(nomD, med, nomC, fecha, sala, cantNum);
	}

	@WebMethod
	public String[] listarClinicas() {
		return (String[]) controladorClinica.listarClinicas().toArray(new String[0]);
	}

	@WebMethod
	public String[] listarEspecialidades() {
	    return controladorClinica.listarEspecialidades().toArray(new String[0]);
	}

	@WebMethod
	public void ingresarDiaConsulta(String nombre, String descripcion, DtDiaSemana dia, @XmlJavaTypeAdapter(logica.datatypes.LocalTimeAdapter.class) LocalTime horaInicio,
			String nombreEspecialidad, String nombreClinica) throws
	DiaConsultaAlreadyExistsException, SpecialityNotFoundException
	{
		controladorClinica.ingresarDiaConsulta(nombre, descripcion, dia, horaInicio, nombreEspecialidad, nombreClinica);
	}


	@WebMethod
	public DtDiaConsulta[] listarDiaConsultas() {
		return  controladorClinica.listarDiaConsultas().toArray(new DtDiaConsulta[0]);
	}


	@WebMethod
	public String[] getConsultasPorEspecialidad(String especialidad) {
		return (String[]) controladorClinica.getConsultasPorEspecialidad(especialidad).toArray(new String[0]);
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
			String nroReg, TipoMes licencia, int porcCost, String link, String[] specs) throws NicknameAlreadyExistsException, EmailAlreadyExistsException, NumeroDeRegistroAlreadyExistsException, MedicoSinEspecialidadException, SpecialityNotFoundException{
		return controladorUsuario.ingresarMedico(nickname, password, nombre, apellido, correo, nroReg, licencia, porcCost, link, new ArrayList<>(Arrays.asList(specs)));
	}

	@WebMethod
	public boolean ingresarPaciente(String nickname, String password, String nombre, String apellido, String correo,
			@XmlJavaTypeAdapter(logica.datatypes.LocalDateAdapter.class)LocalDate fechaNac, @XmlJavaTypeAdapter(logica.datatypes.LocalDateAdapter.class)LocalDate fechaIn, int doc, String[] antSalud)
	throws NicknameAlreadyExistsException, EmailAlreadyExistsException, IdAlreadyExistsException
	{
		return controladorUsuario.ingresarPaciente(nickname, password, nombre, apellido, correo, fechaNac, fechaIn, doc, new ArrayList<>(Arrays.asList(antSalud)));
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
	public DtUsuario[] listarUsuarios() {
		DtUsuario[] toReturn = (DtUsuario[]) controladorUsuario.listarUsuarios().stream().collect(Collectors.toList()).toArray(new DtUsuario[0]);
		return toReturn;
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
	public DtConsulta[] listarConsultasAsociadas(String nicknameUsu) throws UserNotFoundException, ConsultaNotFoundException {
		return (DtConsulta[]) controladorUsuario.listarConsultasAsociadas(nicknameUsu).toArray(new DtConsulta[0]);
	}

	@WebMethod
	public DtResConsulta[] listarResConsultas(String nicknameUsu) throws UserNotFoundException {
		return (DtResConsulta[]) controladorUsuario.listarResConsultas(nicknameUsu).toArray(new DtResConsulta[0]);
	}

	@WebMethod
	public DtMedico[] listarMedicos() {
		return (DtMedico[]) controladorUsuario.listarMedicos().toArray(new DtMedico[0]);
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
	public DtBeneficioAnual[] listarBeneficiosAnuales(String nickname) throws UserNotFoundException {
		return (DtBeneficioAnual[]) controladorUsuario.listarBeneficiosAnuales(nickname).toArray(new DtBeneficioAnual[0]);
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
	public String[] listarEspecialidadesMedico(String nickname) throws UserNotFoundException {
		return (String[]) controladorUsuario.listarEspecialidadesMedico(nickname).toArray(new String[0]);
	}

	@WebMethod
	public void modificarMedico(String nickname, String nombre, String apellido, String nroReg, TipoMes mesLic,
			int porcCost, String link, ArrayList<String> especialidades) throws MedicoSinEspecialidadException, NumeroDeRegistroAlreadyExistsException, SpecialityNotFoundException, UserNotFoundException {
		controladorUsuario.modificarMedico(nickname, nombre, apellido, nroReg, mesLic, porcCost, link, especialidades);
	}

	@WebMethod
	public void modificarPaciente(String nickname, String nombre, String apellido, @XmlJavaTypeAdapter(logica.datatypes.LocalDateAdapter.class)LocalDate fechaNac,
			@XmlJavaTypeAdapter(logica.datatypes.LocalDateAdapter.class)LocalDate fechaIn, int doc, ArrayList<String> antSalud) throws UserNotFoundException{
		controladorUsuario.modificarPaciente(nickname, nombre, apellido, fechaNac, fechaIn, doc, antSalud);
	}

	@WebMethod
	public DtResConsultaDetalle listarDatosResConsulta(String nomC, String nickname) throws ConsultaNotFoundException, UserNotFoundException {
		return controladorUsuario.listarDatosResConsulta(nomC, nickname);
	}
	
	@WebMethod
	public void recetarMedicamento(String nicknamePaciente, String nomC, String nombre, String posologia, @XmlJavaTypeAdapter(logica.datatypes.LocalDateAdapter.class) LocalDate indicacion)
	throws UserNotFoundException, ConsultaNotFoundException, ClinicaNotFoundException, ReservaDoesntExistsException,
	MedicamentoAlreadyExistsException, DatosMedicamentoInvalidosException
	{
		controladorUsuario.recetarMedicamento(nicknamePaciente, nomC, nombre, posologia, indicacion);
	}
	
	@WebMethod
	public DtMedicamento[] listarMedicamentosRecetados(String nickname) throws UserNotFoundException {
		return (DtMedicamento[]) controladorUsuario.listarMedicamentosRecetados(nickname).toArray(new DtMedicamento[0]);
	}
	
	@WebMethod
	public DtSolicitudMedicamento solicitarMedicamento(String nicknamePaciente, String nombre,
			@XmlJavaTypeAdapter(logica.datatypes.LocalDateAdapter.class) LocalDate indicacion,
			@XmlJavaTypeAdapter(logica.datatypes.LocalDateAdapter.class) LocalDate entrega,
			String direccion, boolean usaBenef)
	throws UserNotFoundException, MedicamentoNotFoundException, MedicamentoYaSolicitadoException, MedicamentoVencidoException,
	FechaEntregaInvalidaException, DatosMedicamentoInvalidosException, BeneficioSinTicketsException
	{
		return controladorUsuario.solicitarMedicamento(nicknamePaciente, nombre, indicacion, entrega, direccion, usaBenef);
	}
	
	
	@WebMethod
	public DtResAsistio getAltaDetalleReserva(String nomC, int numR) throws ConsultaNotFoundException {
		return controladorUsuario.getAltaDetalleReserva(nomC, numR);
	}
	
	@WebMethod
	public void altaDetalleReserva(String nomC, int numRes, String nicknamePac, boolean asistio, String motivo, String diagnostico) throws UserNotFoundException{
		controladorUsuario.altaDetalleReserva(nomC, numRes, nicknamePac, asistio, motivo, diagnostico);
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
	
	@WebMethod
	public DtValoracionConsulta[] listarConsultasAsistidas(String nickname)
	        throws UserNotFoundException, ConsultaNotFoundException {
	    return controladorUsuario.listarConsultasAsistidas(nickname)
	            .toArray(DtValoracionConsulta[]::new);
	}

	@WebMethod
	public DtValoracionConsulta obtenerValoracionConsulta(String nickname, String nombreConsulta)
	        throws UserNotFoundException, ConsultaNotFoundException, ReservaDoesntExistsException {
	    return controladorUsuario.obtenerValoracionConsulta(nickname, nombreConsulta);
	}

	@WebMethod
	public boolean valorarConsulta(String nickname, String nombreConsulta, int puntaje)
	        throws UserNotFoundException, ConsultaNotFoundException, ReservaDoesntExistsException {
	    return controladorUsuario.valorarConsulta(nickname, nombreConsulta, puntaje);
	}
	
}
