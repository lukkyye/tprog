package com.controllers;

import java.io.IOException;

import com.gateway.delegators.ImageHandlerDelegator;
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

        if (session == null || session.getAttribute("nickname") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String nickname = (String) session.getAttribute("nickname");
        String tipoUsuario = (String) session.getAttribute("tipoUsuario");

        String fotoPerfil = new ImageHandlerDelegator().get_img64(nickname);
        request.setAttribute("fotoPerfil", fotoPerfil);

        UserControllerDelegator controlador = new UserControllerDelegator();

        request.setAttribute("usuario", controlador.listarDatosUsuario(nickname));

        if ("Paciente".equals(tipoUsuario)) {
            request.setAttribute(
                    "datosPaciente",
                    controlador.listarDatosPaciente(nickname)
            );
            request.setAttribute(
                    "reservas",
                    controlador.listarResConsultas(nickname)
            );
            
            request.setAttribute("esMiPerfil", true);

            request.getRequestDispatcher(
                    "/WEB-INF/views/detailPerfilPaciente.jsp"
            ).forward(request, response);
            return;
        }

        if ("Medico".equals(tipoUsuario)) {
            request.setAttribute(
                    "datosMedico",
                    controlador.listarDatosMedico(nickname)
            );
            request.setAttribute(
                    "especialidades",
                    controlador.listarEspecialidadesMedico(nickname)
            );
            request.setAttribute(
                    "consultas",
                    controlador.listarConsultasAsociadas(nickname)
            );
            request.setAttribute("esMiPerfil", true);

            request.getRequestDispatcher(
                    "/WEB-INF/views/detailPerfilMedico.jsp"
            ).forward(request, response);
            return;
        }

        response.sendRedirect(request.getContextPath() + "/login");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}