package com.gateway.interfaces;

import com.soap.LocalDate;
import com.soap.LocalTime;

import java.util.List;
import java.util.Set;

import com.soap.DtClinica;
import com.soap.DtConsulta;
import com.soap.DtConsultaDetalle;
import com.soap.DtDiaConsulta;
import com.soap.DtDiaSemana;
import com.soap.DtPaciente;
import com.soap.DtResConsulta;
import com.soap.DtResConsultaDetalle;
import com.soap.EstadoConsulta;


public interface IClinicController {
	
	Set<DtDiaConsulta> listarDatosDiaConsulta();
	boolean ingresarClinica(String nombre, String desc, String dir, float costoT, float costoC, String link);
	DtConsultaDetalle listarDatosConsulta(String nomC);
	DtResConsultaDetalle listarDatosResConsultaCC(String nomC, int numReserva);
	Set<DtResConsulta> listarReservasConsulta(String nomC);
	Set<DtConsulta> selecListaConsultaDelDia(String nomD);
	DtConsulta seleccionarConsulta(String nomC);
	Set<DtPaciente> listarPacientes();
	void reservarConsulta(String nicknamePaciente, String nomC, LocalDate fechaReserva, boolean aplicaBenef);
	Set<DtConsulta> listarConsultas();
	public DtClinica seleccionarClinica(String nombre);
	void ingresarConsulta(String nomD, String med, String nomC, LocalDate fecha, int sala, int cantNum);
	Set<String> listarClinicas();
	public void setEstadoConsulta(EstadoConsulta state, String nombreConsulta);
	Set<String> listarEspecialidades();
	public List<String> getConsultasPorEspecialidad(String especialidad);
	List<DtDiaConsulta> listarDiaConsultas();
	void ingresarDiaConsulta(String nombre, String descripcion, DtDiaSemana dia, LocalTime horaInicio, String nombreEspecialidad, String nombreClinica);
	boolean ingresarClinica(String nombre, String desc, String dir, float costoT, float costoC, String link,
			String img);
}
