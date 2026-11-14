package com.controllers;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import com.gateway.delegators.UserControllerDelegator;
import com.soap.ConsultaNotFoundException_Exception;
import com.soap.ReservaDoesntExistsException_Exception;
import com.soap.UserNotFoundException_Exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/movil/valorar-consulta")
public class ValorarConsultaMovilServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String TOKEN = "tokenValorarConsulta";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        atender(request, response, false);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        atender(request, response, true);
    }

    private void atender(HttpServletRequest request, HttpServletResponse response, boolean guardar)
            throws ServletException, IOException {
        request.setCharacterEncoding(StandardCharsets.UTF_8.name());

        String nickname = ConsultasAsistidasMovilServlet.pacienteLogueado(request, response);
        if (nickname == null) {
            return;
        }

        String nombre = request.getParameter("consulta");
        if (nombre == null || nombre.isBlank()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Seleccioná una consulta.");
            return;
        }

        UserControllerDelegator usuarios = new UserControllerDelegator();
        try {
            if (guardar) {
                guardarValoracion(request, response, usuarios, nickname, nombre);
                return;
            }

            request.setAttribute("consulta", usuarios.obtenerValoracionConsulta(nickname, nombre));

            HttpSession session = request.getSession();
            if (session.getAttribute(TOKEN) == null) {
                session.setAttribute(TOKEN, UUID.randomUUID().toString());
            }
            request.setAttribute("valGuardada", session.getAttribute("valGuardada"));
            session.removeAttribute("valGuardada");

            request.getRequestDispatcher("/WEB-INF/views/valorarConsultaMovil.jsp")
                    .forward(request, response);
        } catch (UserNotFoundException_Exception e) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        } catch (ConsultaNotFoundException_Exception | ReservaDoesntExistsException_Exception e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND,
                    "No se encontró una reserva asistida para esa consulta.");
        }
    }

    private void guardarValoracion(HttpServletRequest request, HttpServletResponse response,
            UserControllerDelegator usuarios, String nickname, String nombre)
            throws IOException, UserNotFoundException_Exception,
                   ConsultaNotFoundException_Exception, ReservaDoesntExistsException_Exception {
        HttpSession session = request.getSession();
        String token = request.getParameter("token");
        if (token == null || !token.equals(session.getAttribute(TOKEN))) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        String puntaje = request.getParameter("puntaje");
        if (puntaje == null || !puntaje.matches("[1-5]")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Elegí un puntaje de 1 a 5.");
            return;
        }

        boolean guardada = usuarios.valorarConsulta(nickname, nombre, Integer.parseInt(puntaje));
        session.setAttribute("valGuardada", guardada);

        response.sendRedirect(request.getContextPath() + "/movil/valorar-consulta?consulta="
                + URLEncoder.encode(nombre, StandardCharsets.UTF_8));
    }
}