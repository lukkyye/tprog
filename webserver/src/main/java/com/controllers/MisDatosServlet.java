package com.controllers;

import java.io.IOException;

import com.gateway.delegators.ImageHandlerDelegator;
import com.gateway.delegators.UserControllerDelegator;
import com.soap.ConsultaNotFoundException_Exception;
import com.soap.UserNotFoundException_Exception;

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

        String fotoPerfil = new ImageHandlerDelegator().getImg64(nickname);
        request.setAttribute("fotoPerfil", fotoPerfil);

        UserControllerDelegator controlador = new UserControllerDelegator();

        try {
			request.setAttribute("usuario", controlador.listarDatosUsuario(nickname));
		} catch (UserNotFoundException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        if ("Paciente".equals(tipoUsuario)) {
            try {
				request.setAttribute(
				        "datosPaciente",
				        controlador.listarDatosPaciente(nickname)
				);
			} catch (UserNotFoundException_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            try {
				request.setAttribute(
				        "reservas",
				        controlador.listarResConsultas(nickname)
				);
			} catch (UserNotFoundException_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            request.setAttribute("esMiPerfil", true);

            request.getRequestDispatcher(
                    "/WEB-INF/views/detailPerfilPaciente.jsp"
            ).forward(request, response);
            return;
        }

        if ("Medico".equals(tipoUsuario)) {
            try {
				request.setAttribute(
				        "datosMedico",
				        controlador.listarDatosMedico(nickname)
				);
			} catch (UserNotFoundException_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            try {
				request.setAttribute(
				        "especialidades",
				        controlador.listarEspecialidadesMedico(nickname)
				);
			} catch (UserNotFoundException_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            try {
				request.setAttribute(
				        "consultas",
				        controlador.listarConsultasAsociadas(nickname)
				);
			} catch (ConsultaNotFoundException_Exception | UserNotFoundException_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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