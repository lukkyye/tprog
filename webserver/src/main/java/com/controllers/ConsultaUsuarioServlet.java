package com.controllers;

import java.io.IOException;

import com.gateway.delegators.UserControllerDelegator;
import com.soap.ConsultaNotFoundException_Exception;
import com.soap.DtMedico;
import com.soap.DtPaciente;
import com.soap.DtUsuario;
import com.soap.UserNotFoundException_Exception;

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

        DtUsuario usuario;

        try {
            usuario = controlador.listarDatosUsuario(nickname);
        } catch (UserNotFoundException_Exception e) {
            request.setAttribute("error", "No se encontró ese usuario.");
            request.setAttribute("usuarios", controlador.listarUsuarios());

            request.getRequestDispatcher(
                    "/WEB-INF/views/ConsultaUsuarios.jsp"
            ).forward(request, response);
            return;
        }

        request.setAttribute("usuario", usuario);

        try {
            DtPaciente paciente = controlador.listarDatosPaciente(nickname);
            request.setAttribute("datosPaciente", paciente);
            request.setAttribute("esMiPerfil", false);

            request.getRequestDispatcher(
                    "/WEB-INF/views/detailPerfilPaciente.jsp"
            ).forward(request, response);
            return;

        } catch (UserNotFoundException_Exception e) {
            DtMedico medico = null;
			try {
				medico = controlador.listarDatosMedico(nickname);
			} catch (UserNotFoundException_Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            request.setAttribute("datosMedico", medico);
            try {
				request.setAttribute(
				        "especialidades",
				        controlador.listarEspecialidadesMedico(nickname)
				);
			} catch (UserNotFoundException_Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            try {
				request.setAttribute(
				        "consultas",
				        controlador.listarConsultasAsociadas(nickname)
				);
			} catch (ConsultaNotFoundException_Exception | UserNotFoundException_Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            request.setAttribute("esMiPerfil", false);

            request.getRequestDispatcher(
                    "/WEB-INF/views/detailPerfilMedico.jsp"
            ).forward(request, response);
        }
    }
}