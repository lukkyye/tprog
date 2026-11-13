package com.controllers;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.gateway.delegators.ClinicControllerDelegator;
import com.gateway.delegators.UserControllerDelegator;
import com.soap.ClinicaNotFoundException_Exception;
import com.soap.ConsultaNotFoundException_Exception;
import com.soap.DatosMedicamentoInvalidosException_Exception;
import com.soap.DtConsultaDetalle;
import com.soap.DtUsuario;
import com.soap.MedicamentoAlreadyExistsException_Exception;
import com.soap.ReservaDoesntExistsException_Exception;
import com.soap.UserNotFoundException_Exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/RecetarMedicamentoServlet")
public class RecetarMedicamentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VISTA = "/WEB-INF/views/recetarMedicamento.jsp";
	private static final String MENSAJE_EXITO = "recetaExito";

	private final UserControllerDelegator userController = new UserControllerDelegator();
	private final ClinicControllerDelegator clinicController = new ClinicControllerDelegator();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!prepararDatos(request, response)) {
			return;
		}
		HttpSession session = request.getSession();
		Object exito = session.getAttribute(MENSAJE_EXITO);
		if (exito != null) {
			request.setAttribute("exito", exito);
			session.removeAttribute(MENSAJE_EXITO);
		}
		request.getRequestDispatcher(VISTA).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		if (!prepararDatos(request, response)) {
			return;
		}
		String nomC = (String) request.getAttribute("consulta");
		String paciente = (String) request.getAttribute("nicknamePaciente");
		String nombre = request.getParameter("nombre");
		String posologia = request.getParameter("posologia");

		String error;
		try {
			userController.recetarMedicamento(paciente, nomC, nombre, posologia);
			request.getSession().setAttribute(MENSAJE_EXITO, "Se recetó " + nombre.trim() + " a " + paciente + ".");
			response.sendRedirect(request.getContextPath() + "/RecetarMedicamentoServlet?consulta=" + enc(nomC) + "&nicknamePaciente=" + enc(paciente));
			return;
		} catch (DatosMedicamentoInvalidosException_Exception e) {
			error = "Ingrese el nombre del medicamento y la posología.";
		} catch (MedicamentoAlreadyExistsException_Exception e) {
			error = "Ese medicamento ya fue recetado hoy a este paciente.";
		} catch (ReservaDoesntExistsException_Exception e) {
			error = "El paciente no tiene una reserva en esta consulta.";
		} catch (UserNotFoundException_Exception e) {
			error = "No se encontró al paciente.";
		} catch (ConsultaNotFoundException_Exception | ClinicaNotFoundException_Exception e) {
			error = "No se encontró la consulta o su clínica.";
		}
		request.setAttribute("error", error);
		request.setAttribute("nombre", nombre);
		request.setAttribute("posologia", posologia);
		request.getRequestDispatcher(VISTA).forward(request, response);
	}

	private boolean prepararDatos(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(false);
		String nickname = session == null ? null : (String) session.getAttribute("nickname");
		String tipo = session == null ? null : (String) session.getAttribute("tipoUsuario");
		if (nickname == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return false;
		}
		if (!"Medico".equalsIgnoreCase(tipo)) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "Solo los médicos pueden recetar medicamentos.");
			return false;
		}
		String nomC = request.getParameter("consulta");
		String paciente = request.getParameter("nicknamePaciente");
		if (nomC == null || nomC.isBlank() || paciente == null || paciente.isBlank()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Faltan la consulta o el paciente.");
			return false;
		}
		DtConsultaDetalle consulta;
		try {
			consulta = clinicController.listarDatosConsulta(nomC);
		} catch (ConsultaNotFoundException_Exception e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "No se encontró la consulta.");
			return false;
		}
		if (!nickname.equals(consulta.getMedico())) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "Solo el médico de la consulta puede recetar.");
			return false;
		}
		DtUsuario dtPaciente;
		try {
			userController.seleccionarPaciente(paciente);
			dtPaciente = userController.listarDatosUsuario(paciente);
		} catch (UserNotFoundException_Exception e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "No se encontró al paciente.");
			return false;
		}
		request.setAttribute("consulta", nomC);
		request.setAttribute("nicknamePaciente", paciente);
		request.setAttribute("paciente", dtPaciente);
		return true;
	}

	private static String enc(String valor) {
		return URLEncoder.encode(valor, StandardCharsets.UTF_8);
	}
}