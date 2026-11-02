package com.gateway.interfaces;

import java.time.LocalDate;
import com.soap.MedicoSinEspecialidadException_Exception;
import com.soap.NicknameAlreadyExistsException_Exception;
import com.soap.NumeroDeRegistroAlreadyExistsException_Exception;
import com.soap.SpecialityNotFoundException_Exception;

import java.util.List;
import java.util.Set;

import com.soap.BeneficioAlreadyExistsException_Exception;
import com.soap.BeneficioNotFoundException_Exception;
import com.soap.ConsultaNotFoundException_Exception;
import com.soap.DtBeneficioAnual;
import com.soap.DtConsulta;
import com.soap.DtConsultaDetalle;
import com.soap.DtMedico;
import com.soap.DtPaciente;
import com.soap.DtResConsulta;
import com.soap.DtResConsultaDetalle;
import com.soap.DtUsuario;
import com.soap.EmailAlreadyExistsException_Exception;
import com.soap.EspecialidadAlreadyExistsException_Exception;
import com.soap.IdAlreadyExistsException_Exception;
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
	
}
