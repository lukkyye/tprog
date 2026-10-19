package com.gateway.connectors;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import com.gateway.interfaces.IClinicController;
import com.models.controladores.ControladorClinica;
import com.models.datatypes.DTClinica;
import com.models.datatypes.DTConsulta;
import com.models.datatypes.DTConsultaDetalle;
import com.models.datatypes.DTDiaConsulta;
import com.models.datatypes.DTDiaSemana;
import com.models.datatypes.DTPaciente;
import com.models.datatypes.DTResConsultaDetalle;
import com.models.datatypes.DTResConsulta;

public class ClinicControllerConnector implements IClinicController {
	private ControladorClinica cc;
	
	public ClinicControllerConnector() {
		cc=new ControladorClinica();
	}

	@Override
	public Set<DTDiaConsulta> listarDatosDiaConsulta() {
		return cc.listarDatosDiaConsulta();
	}

	@Override
	public boolean ingresarClinica(String nombre, String desc, String dir, float costoT, float costoC, String link) {
		return cc.ingresarClinica(nombre, desc, dir, costoT, costoC, link);
	}
	@Override
	public DTClinica seleccionarClinica(String nombre) {
		return cc.seleccionarClinica(nombre);
	}
	
	@Override
	public DTConsultaDetalle listarDatosConsulta(String nomC) {
		return cc.listarDatosConsulta(nomC);
	}

	@Override
	public DTResConsultaDetalle listarDatosResConsultaCC(String nomC, int numReserva) {
		return cc.listarDatosResConsultaCC(nomC, numReserva);
	}

	@Override
	public HashSet<DTResConsulta> listarReservasConsulta(String nomC) {
		return cc.getReservasConsulta(nomC);
	}
	
	@Override
	public Set<DTConsulta> selecListaConsultaDelDia(String nomD) {
		return cc.selecListaConsultaDelDia(nomD);
	}

	@Override
	public DTConsulta seleccionarConsulta(String nomC) {
		return cc.seleccionarConsulta(nomC);
	}

	@Override
	public HashSet<DTPaciente> listarPacientes() {
		return cc.listarPacientes();
	}

	@Override
	public void reservarConsulta(String nicknamePaciente, String nomC, LocalDate fechaReserva, Boolean aplicaBenef) {
		cc.reservarConsulta(nicknamePaciente, nomC, fechaReserva, aplicaBenef);
	}

	@Override
	public Set<DTConsulta> listarConsultas() {
		return cc.listarConsultas();
	}

	@Override
	public void ingresarConsulta(String nomD, String med, String nomC, LocalDate fecha, int sala, int cantNum) {
		cc.ingresarConsulta(nomD, med, nomC, fecha, sala, cantNum);
	}

	@Override
	public Set<String> listarClinicas() {
		return cc.listarClinicas();
	}

	@Override
	public Set<String> listarEspecialidades() {
		return cc.listarEspecialidades();
	}

	@Override
	public void ingresarDiaConsulta(String nombre, String descripcion, DTDiaSemana dia, LocalTime horaInicio,
			String nombreEspecialidad, String nombreClinica) {
		cc.ingresarDiaConsulta(nombre, descripcion, dia, horaInicio, nombreEspecialidad, nombreClinica);
	}

	@Override
	public boolean ingresarClinica(String nombre, String desc, String dir, float costoT, float costoC, String link,
			String img) {
		return cc.ingresarClinica(nombre, desc, dir, costoT, costoC, link, img);
	}
}
