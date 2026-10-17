package com.gateway.interfaces;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import com.models.datatypes.DTConsulta;
import com.models.datatypes.DTConsultaDetalle;
import com.models.datatypes.DTDiaConsulta;
import com.models.datatypes.DTDiaSemana;
import com.models.datatypes.DTPaciente;
import com.models.datatypes.DTResConsultaDetalle;

public interface IClinicController {
	
	Set<DTDiaConsulta> listarDatosDiaConsulta();
	boolean ingresarClinica(String nombre, String desc, String dir, float costoT, float costoC, String link);
	DTConsultaDetalle listarDatosConsulta(String nomC);
	DTResConsultaDetalle listarDatosResConsultaCC(String nomC, int numReserva);
	Set<DTConsulta> selecListaConsultaDelDia(String nomD);
	DTConsulta seleccionarConsulta(String nomC);
	HashSet<DTPaciente> listarPacientes();
	void reservarConsulta(String nicknamePaciente, String nomC, LocalDate fechaReserva, Boolean aplicaBenef);
	Set<DTConsulta> listarConsultas();
	void ingresarConsulta(String nomD, String med, String nomC, LocalDate fecha, int sala, int cantNum);
	Set<String> listarClinicas();
	Set<String> listarEspecialidades();
	void ingresarDiaConsulta(String nombre, String descripcion, DTDiaSemana dia, LocalTime horaInicio, String nombreEspecialidad, String nombreClinica);
}
