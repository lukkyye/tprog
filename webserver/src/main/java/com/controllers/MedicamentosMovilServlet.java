package com.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import com.gateway.delegators.UserControllerDelegator;
import com.soap.BeneficioSinTicketsException_Exception;
import com.soap.DatosMedicamentoInvalidosException_Exception;
import com.soap.DtMedicamento;
import com.soap.DtSolicitudMedicamento;
import com.soap.FechaEntregaInvalidaException_Exception;
import com.soap.MedicamentoNotFoundException_Exception;
import com.soap.MedicamentoVencidoException_Exception;
import com.soap.MedicamentoYaSolicitadoException_Exception;
import com.soap.UserNotFoundException_Exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/movil/medicamentos")
public class MedicamentosMovilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VISTA_LISTA = "/WEB-INF/views/medicamentos.jsp";
	private static final String VISTA_FORM = "/WEB-INF/views/solicitarMedicamento.jsp";
	private static final String MENSAJE_EXITO = "solicitudExito";

	private final UserControllerDelegator userController = new UserControllerDelegator();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nickname = pacienteLogueado(request, response);
		if (nickname == null) {
			return;
		}
		List<DtMedicamento> medicamentos = recetados(nickname, response);
		if (medicamentos == null) {
			return;
		}
		String nombre = request.getParameter("nombre");
		String indicacion = request.getParameter("indicacion");
		if (nombre != null && indicacion != null) {
			DtMedicamento elegido = buscar(medicamentos, nombre, indicacion);
			String motivo = motivoNoSolicitable(elegido);
			if (motivo == null) {
				mostrarFormulario(request, response, elegido);
				return;
			}
			request.setAttribute("error", motivo);
		}
		mostrarLista(request, response, medicamentos);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String nickname = pacienteLogueado(request, response);
		if (nickname == null) {
			return;
		}
		String nombre = request.getParameter("nombre");
		String indicacionTxt = request.getParameter("indicacion");
		String entregaTxt = request.getParameter("entrega");
		String direccion = request.getParameter("direccion");
		boolean usaBenef = "beneficio".equals(request.getParameter("pago"));

		String error;
		if (nombre == null || indicacionTxt == null || entregaTxt == null || entregaTxt.isBlank()) {
			error = "Ingrese una fecha de entrega válida.";
		} else {
			try {
				LocalDate indicacion = LocalDate.parse(indicacionTxt);
				LocalDate entrega = LocalDate.parse(entregaTxt);
				DtSolicitudMedicamento solicitud = userController.solicitarMedicamento(nickname, nombre, indicacion,
						entrega, direccion, usaBenef);
				String costo = solicitud.getCostoMed() == 0f ? "sin costo (beneficio anual)" : "$ " + solicitud.getCostoMed();
				request.getSession().setAttribute(MENSAJE_EXITO, "Pedido registrado: " + nombre + " se entregará el " + entrega + " en " + solicitud.getDireccion() + ". Costo: " + costo + ".");
				response.sendRedirect(request.getContextPath() + "/movil/medicamentos");
				return;
			} catch (DateTimeParseException e) {
				error = "Ingrese una fecha de entrega válida.";
			} catch (FechaEntregaInvalidaException_Exception e) {
				error = "La fecha de entrega debe ser posterior a hoy.";
			} catch (DatosMedicamentoInvalidosException_Exception e) {
				error = "Ingrese la dirección de entrega.";
			} catch (BeneficioSinTicketsException_Exception e) {
				error = "No tienes tickets disponibles en tus beneficios anuales. Elige el pago general.";
			} catch (MedicamentoYaSolicitadoException_Exception e) {
				error = "Este medicamento ya fue solicitado.";
			} catch (MedicamentoVencidoException_Exception e) {
				error = "Pasaron más de 30 días desde la indicación: ya no se puede solicitar.";
			} catch (MedicamentoNotFoundException_Exception e) {
				error = "No se encontró el medicamento.";
			} catch (UserNotFoundException_Exception e) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "No se encontró al paciente.");
				return;
			}
		}

		List<DtMedicamento> medicamentos = recetados(nickname, response);
		if (medicamentos == null) {
			return;
		}
		DtMedicamento elegido = buscar(medicamentos, nombre, indicacionTxt);
		request.setAttribute("error", error);
		request.setAttribute("entregaIngresada", entregaTxt);
		request.setAttribute("direccionIngresada", direccion);
		request.setAttribute("pagoIngresado", usaBenef ? "beneficio" : "general");
		if (elegido == null) {
			mostrarLista(request, response, medicamentos);
		} else {
			mostrarFormulario(request, response, elegido);
		}
	}

	private void mostrarLista(HttpServletRequest request, HttpServletResponse response, List<DtMedicamento> medicamentos) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object exito = session.getAttribute(MENSAJE_EXITO);
		if (exito != null) {
			request.setAttribute("exito", exito);
			session.removeAttribute(MENSAJE_EXITO);
		}
		request.setAttribute("medicamentos", medicamentos);
		request.setAttribute("hoy", LocalDate.now().toString());
		request.getRequestDispatcher(VISTA_LISTA).forward(request, response);
	}

	private void mostrarFormulario(HttpServletRequest request, HttpServletResponse response, DtMedicamento medicamento) throws ServletException, IOException {
		request.setAttribute("medicamento", medicamento);
		request.setAttribute("minEntrega", LocalDate.now().plusDays(1).toString());
		request.getRequestDispatcher(VISTA_FORM).forward(request, response);
	}

	private String pacienteLogueado(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(false);
		String nickname = session == null ? null : (String) session.getAttribute("nickname");
		String tipo = session == null ? null : (String) session.getAttribute("tipoUsuario");
		if (nickname == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return null;
		}
		if (!"Paciente".equalsIgnoreCase(tipo)) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "Solo los pacientes pueden solicitar medicamentos.");
			return null;
		}
		return nickname;
	}

	private List<DtMedicamento> recetados(String nickname, HttpServletResponse response) throws IOException {
		try {
			return userController.listarMedicamentosRecetados(nickname);
		} catch (UserNotFoundException_Exception e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "No se encontró al paciente.");
			return null;
		}
	}

	private static DtMedicamento buscar(List<DtMedicamento> medicamentos, String nombre, String indicacion) {
		for (DtMedicamento m : medicamentos) {
			if (m.getNombre().equalsIgnoreCase(nombre) && m.getIndicacion().equals(indicacion)) {
				return m;
			}
		}
		return null;
	}

	private static String motivoNoSolicitable(DtMedicamento m) {
		if (m == null) {
			return "No se encontró el medicamento.";
		}
		if (m.isUsado()) {
			return "Este medicamento ya fue solicitado.";
		}
		if (LocalDate.now().isAfter(LocalDate.parse(m.getVencimiento()))) {
			return "Pasaron más de 30 días desde la indicación: ya no se puede solicitar.";
		}
		return null;
	}
}
