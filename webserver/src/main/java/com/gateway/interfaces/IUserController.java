package com.gateway.interfaces;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.models.datatypes.DTBeneficioAnual;
import com.models.datatypes.DTConsulta;
import com.models.datatypes.DTConsultaDetalle;
import com.models.datatypes.DTMedico;
import com.models.datatypes.DTPaciente;
import com.models.datatypes.DTResConsulta;
import com.models.datatypes.DTResConsultaDetalle;
import com.models.datatypes.DTUsuario;
import com.models.datatypes.TipoMes;

public interface IUserController {
	
	boolean ingresarMedico(String nickname, String password, String nombre, String apellido, String correo, String nroReg, TipoMes licencia, int porcCost, String link, List<String> specs);

	boolean ingresarPaciente(String nickname, String password, String nombre, String apellido, String correo, LocalDate fechaNac, LocalDate fechaIn, int doc, List<String> antSalud);

	boolean login(String nickname, String password);

	boolean altaEspecialidad(String espNom, int espPorc);

	Set<DTUsuario> listarUsuarios();

	DTMedico listarDatosMedico(String nicknameUsu);

	DTPaciente listarDatosPaciente(String nicknameUsu);

	Set<DTConsulta> listarConsultasAsociadas(String nicknameUsu);

	Set<DTResConsulta> listarResConsultas(String nicknameUsu);

	Set<DTPaciente> listarPacientes();

	Set<DTMedico> listarMedicos();

	DTPaciente seleccionarPaciente(String nickname);

	DTConsultaDetalle listarDatosConsulta(String nomC);

	void altaBeneficioAnual(String nickname, int anio, int cantOrdenes, int cantTicket);

	Set<DTBeneficioAnual> listarBeneficiosAnuales(String nickname);

	DTBeneficioAnual listarDatosBeneficioAnual(String nickname, int anio);

	DTUsuario listarDatosUsuario(String nickname);

	List<String> listarEspecialidadesMedico(String nickname);

	void modificarMedico(String nickname, String nombre, String apellido, String nroReg, TipoMes mesLic, int porcCost, String link, List<String> especialidades);

	void modificarPaciente(String nickname, String nombre, String apellido, LocalDate fechaNac, LocalDate fechaIn, int doc, List<String> antSalud);

	DTResConsultaDetalle listarDatosResConsulta(String nomC, String nickname);
	
}
