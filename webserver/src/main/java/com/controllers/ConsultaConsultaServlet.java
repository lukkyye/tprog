package com.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import com.gateway.delegators.*;
import com.gateway.delegators.ImageHandlerDelegator;
import com.models.datatypes.DTConsultaDetalle;
import com.models.datatypes.DTResConsulta;
import com.models.datatypes.EstadoConsulta;
import com.models.excepciones.ConsultaNotFoundException;

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

        String nombreConsulta = request.getParameter("nombre");
        if (nombreConsulta == null || nombreConsulta.isBlank()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Falta el nombre de la consulta.");
            return;
        }

        DTConsultaDetalle consulta;
        try {
            consulta = clinicController.listarDatosConsulta(nombreConsulta);
        } catch (ConsultaNotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "No se encontró la consulta.");
            return;
        }

        HttpSession sesion = request.getSession(false);
        String nickname = sesion == null ? null : (String) sesion.getAttribute("nickname");
        String tipo = sesion == null ? null : (String) sesion.getAttribute("tipoUsuario");
        boolean esMedico = nickname != null && "Medico".equalsIgnoreCase(tipo);
        boolean esPaciente = nickname != null && "Paciente".equalsIgnoreCase(tipo);

        boolean esMedicoDeLaConsulta = esMedico && consulta.medico().equals(nickname);

        if (consulta.estado() != EstadoConsulta.Aceptada && !esMedicoDeLaConsulta) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "No se encontró la consulta.");
            return;
        }

        List<DTResConsulta> reservas = new ArrayList<>();
        DTResConsulta miReserva = null;
        if (esMedicoDeLaConsulta) {
            reservas.addAll(consulta.reservas());
            reservas.sort(Comparator.comparingInt(DTResConsulta::numReserva));
        } else if (esPaciente) {
        	HashSet<DTResConsulta> reservasUsuario = userController.listarResConsultas(nickname);
            if (reservasUsuario != null) {
                for (DTResConsulta r : reservasUsuario) {
                	if (nombreConsulta != null && nombreConsulta.equals(userController.listarDatosResConsulta(nickname, r.numReserva()).nomC())) {
                		miReserva = r;
                	}
                }
            }
        }

        request.setAttribute("consulta", consulta);
        request.setAttribute("imagen", new ImageHandlerDelegator().get_img64("consulta:" + nombreConsulta));
        request.setAttribute("esMedicoDeLaConsulta", esMedicoDeLaConsulta);
        request.setAttribute("reservas", reservas);
        request.setAttribute("miReserva", miReserva);
        request.getRequestDispatcher("/WEB-INF/views/consultaConsulta.jsp").forward(request, response);
    }
}

