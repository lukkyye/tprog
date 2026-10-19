package com.controllers;

import java.io.IOException;

import com.gateway.delegators.UserControllerDelegator;
import com.models.datatypes.DTPaciente;
import com.models.datatypes.DTMedico;
import com.models.datatypes.DTUsuario;
import com.models.excepciones.UserNotFoundException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/consultaUsuario")
public class ConsultaUsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserControllerDelegator controlador = new UserControllerDelegator();
        String nickname = request.getParameter("nickname");

        if (nickname == null || nickname.isBlank()) {
            request.setAttribute("usuarios", controlador.listarUsuarios());

            request.getRequestDispatcher(
                    "/WEB-INF/views/ConsultaUsuarios.jsp"
            ).forward(request, response);
            return;
        }

        HttpSession sesion = request.getSession(false);
        String nicknameSesion = null;

        if (sesion != null) {
            nicknameSesion = (String) sesion.getAttribute("nickname");
        }

        if (nicknameSesion != null && nicknameSesion.equals(nickname)) {
            response.sendRedirect(request.getContextPath() + "/misdatos");
            return;
        }

        DTUsuario usuario;

        try {
            usuario = controlador.listarDatosUsuario(nickname);
        } catch (UserNotFoundException e) {
            request.setAttribute("error", "No se encontró ese usuario.");
            request.setAttribute("usuarios", controlador.listarUsuarios());

            request.getRequestDispatcher(
                    "/WEB-INF/views/ConsultaUsuarios.jsp"
            ).forward(request, response);
            return;
        }

        request.setAttribute("usuario", usuario);

        try {
            DTPaciente paciente = controlador.listarDatosPaciente(nickname);
            request.setAttribute("datosPaciente", paciente);
            request.setAttribute("esMiPerfil", false);

            request.getRequestDispatcher(
                    "/WEB-INF/views/detailPerfilPaciente.jsp"
            ).forward(request, response);
            return;

        } catch (UserNotFoundException e) {
            DTMedico medico = controlador.listarDatosMedico(nickname);
            request.setAttribute("datosMedico", medico);
            request.setAttribute(
                    "especialidades",
                    controlador.listarEspecialidadesMedico(nickname)
            );
            request.setAttribute(
                    "consultas",
                    controlador.listarConsultasAsociadas(nickname)
            );
            request.setAttribute("esMiPerfil", false);

            request.getRequestDispatcher(
                    "/WEB-INF/views/detailPerfilMedico.jsp"
            ).forward(request, response);
        }
    }
}