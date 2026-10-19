package com.controllers;

import java.io.IOException;

import com.gateway.delegators.UserControllerDelegator;
import com.models.datatypes.DTBeneficioAnual;
import com.models.excepciones.BeneficioNotFoundException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ConsultaBeneficioServlet")
public class ConsultaBeneficioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final UserControllerDelegator userController = new UserControllerDelegator();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession sesion = request.getSession(false);
        String nickname = sesion == null ? null : (String) sesion.getAttribute("nickname");
        if (nickname == null || !"Paciente".equalsIgnoreCase((String) sesion.getAttribute("tipoUsuario"))) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        int anio;
        try {
            anio = Integer.parseInt(request.getParameter("anio"));
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Falta el año del beneficio o no es válido.");
            return;
        }

        try {
            DTBeneficioAnual beneficio = userController.listarDatosBeneficioAnual(nickname, anio);
            request.setAttribute("beneficio", beneficio);
        } catch (BeneficioNotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
            return;
        }

        request.getRequestDispatcher("/WEB-INF/views/consultaBeneficio.jsp").forward(request, response);
    }
}
