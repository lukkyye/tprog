package com.gateway.interfaces;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

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
	
	boolean ingresarMedico(String nickname, String password, String nombre, String apellido, String correo, String nroReg, TipoMes licencia, int porcCost, String link, ArrayList<String> specs);

	boolean ingresarPaciente(String nickname, String password, String nombre, String apellido, String correo, LocalDate fechaNac, LocalDate fechaIn, int doc, ArrayList<String> antSalud);

	boolean login(String nickname, String password);

	boolean altaEspecialidad(String espNom, int espPorc);

	HashSet<DTUsuario> listarUsuarios();

	DTMedico listarDatosMedico(String nicknameUsu);

	DTPaciente listarDatosPaciente(String nicknameUsu);

	HashSet<DTConsulta> listarConsultasAsociadas(String nicknameUsu);

	HashSet<DTResConsulta> listarResConsultas(String nicknameUsu);

	HashSet<DTPaciente> listarPacientes();

	HashSet<DTMedico> listarMedicos();

	DTPaciente seleccionarPaciente(String nickname);

	DTResConsultaDetalle listarDatosResConsulta(String nickname, int numReserva);

	DTConsultaDetalle listarDatosConsulta(String nomC);

	void altaBeneficioAnual(String nickname, int anio, int cantOrdenes, int cantTicket);

	HashSet<DTBeneficioAnual> listarBeneficiosAnuales(String nickname);

	DTBeneficioAnual listarDatosBeneficioAnual(String nickname, int anio);

	DTUsuario listarDatosUsuario(String nickname);

	ArrayList<String> listarEspecialidadesMedico(String nickname);

	void modificarMedico(String nickname, String nombre, String apellido, String nroReg, TipoMes mesLic, int porcCost, String link, ArrayList<String> especialidades);

	void modificarPaciente(String nickname, String nombre, String apellido, LocalDate fechaNac, LocalDate fechaIn, int doc, ArrayList<String> antSalud);
	
}
