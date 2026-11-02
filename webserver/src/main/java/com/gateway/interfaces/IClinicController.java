package com.gateway.interfaces;

import java.time.LocalDate;
import java.time.LocalTime;
import com.soap.MedicoSinEspecialidadException_Exception;
import com.soap.NoNumberAvailableException_Exception;
import com.soap.NombreClinicaAlreadyExistsException_Exception;
import com.soap.ReservaAlreadyExistsException_Exception;
import com.soap.SpecialityNotFoundException_Exception;
import com.soap.UserNotFoundException_Exception;

import java.util.List;
import java.util.Set;

import com.soap.BeneficioSinOrdenesException_Exception;
import com.soap.ConsultaNombreAlreadyExistsException_Exception;
import com.soap.ConsultaNotFoundException_Exception;
import com.soap.DiaConsultaAlreadyExistsException_Exception;
import com.soap.DtClinica;
import com.soap.DtConsulta;
import com.soap.DtConsultaDetalle;
import com.soap.DtDiaConsulta;
import com.soap.DtDiaSemana;
import com.soap.DtPaciente;
import com.soap.DtResConsulta;
import com.soap.DtResConsultaDetalle;
import com.soap.EstadoConsulta;
import com.soap.FechaDiaSemanaInvalidaException_Exception;


public interface IClinicController {
	
	Set<DtDiaConsulta> listarDatosDiaConsulta();
	boolean ingresarClinica(String nombre, String desc, String dir, float costoT, float costoC, String link) throws NombreClinicaAlreadyExistsException_Exception;
	DtConsultaDetalle listarDatosConsulta(String nomC) throws ConsultaNotFoundException_Exception;
	DtResConsultaDetalle listarDatosResConsultaCC(String nomC, int numReserva) throws ConsultaNotFoundException_Exception;
	Set<DtResConsulta> listarReservasConsulta(String nomC) throws ConsultaNotFoundException_Exception;
	Set<DtConsulta> selecListaConsultaDelDia(String nomD);
	DtConsulta seleccionarConsulta(String nomC) throws ConsultaNotFoundException_Exception;
	Set<DtPaciente> listarPacientes();
	void reservarConsulta(String nicknamePaciente, String nomC, LocalDate fechaReserva, boolean aplicaBenef) throws BeneficioSinOrdenesException_Exception, ConsultaNotFoundException_Exception, NoNumberAvailableException_Exception, ReservaAlreadyExistsException_Exception, UserNotFoundException_Exception;
	Set<DtConsulta> listarConsultas();
	public DtClinica seleccionarClinica(String nombre);
	void ingresarConsulta(String nomD, String med, String nomC, LocalDate fecha, int sala, int cantNum) throws ConsultaNombreAlreadyExistsException_Exception, FechaDiaSemanaInvalidaException_Exception, MedicoSinEspecialidadException_Exception, UserNotFoundException_Exception;
	Set<String> listarClinicas();
	public void setEstadoConsulta(EstadoConsulta state, String nombreConsulta) throws ConsultaNotFoundException_Exception;
	Set<String> listarEspecialidades();
	public List<String> getConsultasPorEspecialidad(String especialidad);
	List<DtDiaConsulta> listarDiaConsultas();
	void ingresarDiaConsulta(String nombre, String descripcion, DtDiaSemana dia, LocalTime horaInicio, String nombreEspecialidad, String nombreClinica) throws DiaConsultaAlreadyExistsException_Exception, SpecialityNotFoundException_Exception;
	boolean ingresarClinica(String nombre, String desc, String dir, float costoT, float costoC, String link,
			String img) throws NombreClinicaAlreadyExistsException_Exception;
}
