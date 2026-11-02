package com.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import java.util.List;
import java.util.Set;

import com.gateway.delegators.*;
import com.gateway.delegators.ImageHandlerDelegator;
import com.soap.ConsultaNotFoundException_Exception;
import com.soap.DtConsultaDetalle;
import com.soap.DtResConsulta;
import com.soap.EstadoConsulta;
import com.soap.UserNotFoundException_Exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ConsultaConsultaServlet")
public class ConsultaConsultaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final ClinicControllerDelegator clinicController = new ClinicControllerDelegator();
    private final UserControllerDelegator userController = new UserControllerDelegator();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombreConsulta = request.getParameter("nombreConsulta");
        if (nombreConsulta == null || nombreConsulta.isBlank()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Falta el nombre de la consulta.");
            return;
        }

        DtConsultaDetalle consulta;
        try {
            consulta = clinicController.listarDatosConsulta(nombreConsulta);
        } catch (ConsultaNotFoundException_Exception e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "No se encontró la consulta.");
            return;
        }

        HttpSession sesion = request.getSession(false);
        String nickname = sesion == null ? null : (String) sesion.getAttribute("nickname");
        String tipo = sesion == null ? null : (String) sesion.getAttribute("tipoUsuario");
        boolean esMedico = nickname != null && "Medico".equalsIgnoreCase(tipo);
        boolean esPaciente = nickname != null && "Paciente".equalsIgnoreCase(tipo);

        boolean esMedicoDeLaConsulta = esMedico && consulta.getMedico().equals(nickname);

        if (consulta.getEstado() != EstadoConsulta.ACEPTADA && !esMedicoDeLaConsulta) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "No se encontró la consulta.");
            return;
        }

        List<DtResConsulta> reservas = new ArrayList<>();
        DtResConsulta miReserva = null;
        if (esMedicoDeLaConsulta) {
            reservas.addAll(consulta.getReservas());
            reservas.sort(Comparator.comparingInt(dtrc->dtrc.getNumReserva()));
        } else if (esPaciente) {
        	Set<DtResConsulta> reservasUsuario = null;
			try {
				reservasUsuario = userController.listarResConsultas(nickname);
			} catch (UserNotFoundException_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            if (reservasUsuario != null) {
                for (DtResConsulta r : reservasUsuario) {
                	if (r.getNomC().equals(nombreConsulta)) {
                		miReserva = r;
                	}
                }
            }
        }

        request.setAttribute("consulta", consulta);
        request.setAttribute("imagen", new ImageHandlerDelegator().getImg64("consulta:" + nombreConsulta));
        request.setAttribute("esMedicoDeLaConsulta", esMedicoDeLaConsulta);
        request.setAttribute("reservas", reservas);
        request.setAttribute("miReserva", miReserva);
        request.getRequestDispatcher("/WEB-INF/views/consultaConsulta.jsp").forward(request, response);
    }
}

