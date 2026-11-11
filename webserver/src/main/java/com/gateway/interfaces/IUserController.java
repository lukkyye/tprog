package com.gateway.interfaces;

import java.time.LocalDate;
import com.soap.MedicoSinEspecialidadException_Exception;
import com.soap.NicknameAlreadyExistsException_Exception;
import com.soap.NumeroDeRegistroAlreadyExistsException_Exception;
import com.soap.ReservaDoesntExistsException_Exception;
import com.soap.SpecialityNotFoundException_Exception;

import java.util.List;
import java.util.Set;

import com.soap.BeneficioAlreadyExistsException_Exception;
import com.soap.BeneficioNotFoundException_Exception;
import com.soap.BeneficioSinTicketsException_Exception;
import com.soap.ClinicaNotFoundException_Exception;
import com.soap.ConsultaNotFoundException_Exception;
import com.soap.DatosMedicamentoInvalidosException_Exception;
import com.soap.DtBeneficioAnual;
import com.soap.DtConsulta;
import com.soap.DtConsultaDetalle;
import com.soap.DtMedico;
import com.soap.DtPaciente;
import com.soap.DtResConsulta;
import com.soap.DtSolicitudMedicamento;
import com.soap.DtMedicamento;
import com.soap.DtResConsultaDetalle;
import com.soap.DtUsuario;
import com.soap.EmailAlreadyExistsException_Exception;
import com.soap.EspecialidadAlreadyExistsException_Exception;
import com.soap.FechaEntregaInvalidaException_Exception;
import com.soap.IdAlreadyExistsException_Exception;
import com.soap.MedicamentoAlreadyExistsException_Exception;
import com.soap.MedicamentoNotFoundException_Exception;
import com.soap.MedicamentoVencidoException_Exception;
import com.soap.MedicamentoYaSolicitadoException_Exception;
import com.soap.TipoMes;
import com.soap.UserNotFoundException_Exception;





public interface IUserController {
	
	boolean ingresarMedico(String nickname, String password, String nombre, String apellido, String correo, String nroReg, TipoMes licencia, int porcCost, String link, List<String> specs) throws EmailAlreadyExistsException_Exception, MedicoSinEspecialidadException_Exception, NicknameAlreadyExistsException_Exception, NumeroDeRegistroAlreadyExistsException_Exception, SpecialityNotFoundException_Exception;

	boolean ingresarPaciente(String nickname, String password, String nombre, String apellido, String correo, LocalDate fechaNac, LocalDate fechaIn, int doc, List<String> antSalud) throws EmailAlreadyExistsException_Exception, IdAlreadyExistsException_Exception, NicknameAlreadyExistsException_Exception;

	boolean login(String nickname, String password);

	boolean altaEspecialidad(String espNom, int espPorc) throws EspecialidadAlreadyExistsException_Exception;

	Set<DtUsuario> listarUsuarios();

	DtMedico listarDatosMedico(String nicknameUsu) throws UserNotFoundException_Exception;

	DtPaciente listarDatosPaciente(String nicknameUsu) throws UserNotFoundException_Exception;

	Set<DtConsulta> listarConsultasAsociadas(String nicknameUsu) throws ConsultaNotFoundException_Exception, UserNotFoundException_Exception;

	Set<DtResConsulta> listarResConsultas(String nicknameUsu) throws UserNotFoundException_Exception;

	Set<DtPaciente> listarPacientes();

	Set<DtMedico> listarMedicos();

	DtPaciente seleccionarPaciente(String nickname) throws UserNotFoundException_Exception;

	DtConsultaDetalle listarDatosConsulta(String nomC) throws ConsultaNotFoundException_Exception;

	void altaBeneficioAnual(String nickname, int anio, int cantOrdenes, int cantTicket) throws UserNotFoundException_Exception, BeneficioAlreadyExistsException_Exception;

	Set<DtBeneficioAnual> listarBeneficiosAnuales(String nickname) throws UserNotFoundException_Exception;

	DtBeneficioAnual listarDatosBeneficioAnual(String nickname, int anio) throws BeneficioNotFoundException_Exception, UserNotFoundException_Exception;

	DtUsuario listarDatosUsuario(String nickname) throws UserNotFoundException_Exception;

	List<String> listarEspecialidadesMedico(String nickname) throws UserNotFoundException_Exception;

	void modificarMedico(String nickname, String nombre, String apellido, String nroReg, TipoMes mesLic, int porcCost, String link, List<String> especialidades) throws MedicoSinEspecialidadException_Exception, NumeroDeRegistroAlreadyExistsException_Exception, SpecialityNotFoundException_Exception, UserNotFoundException_Exception;

	void modificarPaciente(String nickname, String nombre, String apellido, LocalDate fechaNac, LocalDate fechaIn, int doc, List<String> antSalud) throws UserNotFoundException_Exception;
	DtResConsultaDetalle listarDatosResConsulta(String nomC, String nickname) throws ConsultaNotFoundException_Exception, UserNotFoundException_Exception;
	
	public DtSolicitudMedicamento solicitarMedicamento(String nicknamePaciente, String nombre,
			 LocalDate indicacion,
			 LocalDate entrega,
			String direccion, boolean usaBenef) throws UserNotFoundException_Exception, MedicamentoNotFoundException_Exception, MedicamentoYaSolicitadoException_Exception, MedicamentoVencidoException_Exception,
	FechaEntregaInvalidaException_Exception, DatosMedicamentoInvalidosException_Exception, BeneficioSinTicketsException_Exception;
	
	public List<DtMedicamento> listarMedicamentosRecetados(String nickname) throws UserNotFoundException_Exception;
	
	public void recetarMedicamento(String nicknamePaciente, String nomC, String nombre, String posologia)
			throws UserNotFoundException_Exception, ConsultaNotFoundException_Exception, ClinicaNotFoundException_Exception, ReservaDoesntExistsException_Exception,
			MedicamentoAlreadyExistsException_Exception, DatosMedicamentoInvalidosException_Exception;
	
}
