package com.gateway.interfaces;

import com.soap.LocalDate;
import java.util.List;
import java.util.Set;

import com.soap.DtBeneficioAnual;
import com.soap.DtConsulta;
import com.soap.DtConsultaDetalle;
import com.soap.DtMedico;
import com.soap.DtPaciente;
import com.soap.DtResConsulta;
import com.soap.DtResConsultaDetalle;
import com.soap.DtUsuario;
import com.soap.TipoMes;

public interface IUserController {
	
	boolean ingresarMedico(String nickname, String password, String nombre, String apellido, String correo, String nroReg, TipoMes licencia, int porcCost, String link, List<String> specs);

	boolean ingresarPaciente(String nickname, String password, String nombre, String apellido, String correo, LocalDate fechaNac, LocalDate fechaIn, int doc, List<String> antSalud);

	boolean login(String nickname, String password);

	boolean altaEspecialidad(String espNom, int espPorc);

	Set<DtUsuario> listarUsuarios();

	DtMedico listarDatosMedico(String nicknameUsu);

	DtPaciente listarDatosPaciente(String nicknameUsu);

	Set<DtConsulta> listarConsultasAsociadas(String nicknameUsu);

	Set<DtResConsulta> listarResConsultas(String nicknameUsu);

	Set<DtPaciente> listarPacientes();

	Set<DtMedico> listarMedicos();

	DtPaciente seleccionarPaciente(String nickname);

	DtConsultaDetalle listarDatosConsulta(String nomC);

	void altaBeneficioAnual(String nickname, int anio, int cantOrdenes, int cantTicket);

	Set<DtBeneficioAnual> listarBeneficiosAnuales(String nickname);

	DtBeneficioAnual listarDatosBeneficioAnual(String nickname, int anio);

	DtUsuario listarDatosUsuario(String nickname);

	List<String> listarEspecialidadesMedico(String nickname);

	void modificarMedico(String nickname, String nombre, String apellido, String nroReg, TipoMes mesLic, int porcCost, String link, List<String> especialidades);

	void modificarPaciente(String nickname, String nombre, String apellido, LocalDate fechaNac, LocalDate fechaIn, int doc, List<String> antSalud);

	DtResConsultaDetalle listarDatosResConsulta(String nomC, String nickname);
	
}
