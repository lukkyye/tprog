package com.gateway.connectors;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
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
import com.models.datatypes.EstadoConsulta;
import com.models.datatypes.DTResConsulta;

public class ClinicControllerConnector implements IClinicController {
	private ControladorClinica controladorClinica;
	
	public ClinicControllerConnector() {
		controladorClinica=new ControladorClinica();
	}

	@Override
	public Set<DTDiaConsulta> listarDatosDiaConsulta() {
		return controladorClinica.listarDatosDiaConsulta();
	}

	@Override
	public boolean ingresarClinica(String nombre, String desc, String dir, float costoT, float costoC, String link) {
		return controladorClinica.ingresarClinica(nombre, desc, dir, costoT, costoC, link);
	}
	@Override
	public DTClinica seleccionarClinica(String nombre) {
		return controladorClinica.seleccionarClinica(nombre);
	}
	@Override
	public void setEstadoConsulta(EstadoConsulta state, String nombreConsulta) {
		controladorClinica.setEstadoConsulta(state, nombreConsulta);
	}
	@Override
	public DTConsultaDetalle listarDatosConsulta(String nomC) {
		return controladorClinica.listarDatosConsulta(nomC);
	}

	@Override
	public DTResConsultaDetalle listarDatosResConsultaCC(String nomC, int numReserva) {
		return controladorClinica.listarDatosResConsultaCC(nomC, numReserva);
	}

	@Override
	public Set<DTResConsulta> listarReservasConsulta(String nomC) {
		return controladorClinica.getReservasConsulta(nomC);
	}
	
	@Override
	public Set<DTConsulta> selecListaConsultaDelDia(String nomD) {
		return controladorClinica.selecListaConsultaDelDia(nomD);
	}

	@Override
	public DTConsulta seleccionarConsulta(String nomC) {
		return controladorClinica.seleccionarConsulta(nomC);
	}

	@Override
	public Set<DTPaciente> listarPacientes() {
		return controladorClinica.listarPacientes();
	}

	@Override
	public void reservarConsulta(String nicknamePaciente, String nomC, LocalDate fechaReserva, Boolean aplicaBenef) {
		controladorClinica.reservarConsulta(nicknamePaciente, nomC, fechaReserva, aplicaBenef);
	}

	@Override
	public Set<DTConsulta> listarConsultas() {
		return controladorClinica.listarConsultas();
	}

	@Override
	public void ingresarConsulta(String nomD, String med, String nomC, LocalDate fecha, int sala, int cantNum) {
		controladorClinica.ingresarConsulta(nomD, med, nomC, fecha, sala, cantNum);
	}

	@Override
	public Set<String> listarClinicas() {
		return controladorClinica.listarClinicas();
	}

	@Override
	public Set<String> listarEspecialidades() {
		return controladorClinica.listarEspecialidades();
	}

	@Override
	public void ingresarDiaConsulta(String nombre, String descripcion, DTDiaSemana dia, LocalTime horaInicio,
			String nombreEspecialidad, String nombreClinica) {
		controladorClinica.ingresarDiaConsulta(nombre, descripcion, dia, horaInicio, nombreEspecialidad, nombreClinica);
	}

	@Override
	public boolean ingresarClinica(String nombre, String desc, String dir, float costoT, float costoC, String link,
			String img) {
		return controladorClinica.ingresarClinica(nombre, desc, dir, costoT, costoC, link, img);
	}

	@Override
	public List<DTDiaConsulta> listarDiaConsultas() {
		return controladorClinica.listarDiaConsultas();
	}

	@Override
	public List<String> getConsultasPorEspecialidad(String especialidad) {
		return controladorClinica.getConsultasPorEspecialidad(especialidad);
	}
}
