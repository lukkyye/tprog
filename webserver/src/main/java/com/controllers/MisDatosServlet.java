package com.controllers;

import java.io.IOException;

import com.gateway.delegators.UserControllerDelegator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/misdatos")
public class MisDatosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // Si no hay una sesión iniciada, volver al login.
        if (session == null || session.getAttribute("nickname") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String nickname = (String) session.getAttribute("nickname");
        String tipoUsuario = (String) session.getAttribute("tipoUsuario");

        UserControllerDelegator controlador = new UserControllerDelegator();

        // Datos comunes del usuario.
        request.setAttribute("usuario", controlador.listarDatosUsuario(nickname));

        // Datos específicos según el tipo de usuario.
        if ("Paciente".equals(tipoUsuario)) {
            request.setAttribute(
                "datosPaciente",
                controlador.listarDatosPaciente(nickname)
            );
        } else if ("Medico".equals(tipoUsuario)) {
            request.setAttribute(
                "datosMedico",
                controlador.listarDatosMedico(nickname)
            );
        }

        request.getRequestDispatcher("/WEB-INF/views/MisDatos.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}