package com.controllers;

import java.io.IOException;

import com.gateway.delegators.UserControllerDelegator;
import com.soap.ConsultaNotFoundException_Exception;
import com.soap.UserNotFoundException_Exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/movil/consultas-asistidas")
public class ConsultasAsistidasMovilServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nickname = pacienteLogueado(request, response);
        if (nickname == null) {
            return;
        }

        UserControllerDelegator usuarios = new UserControllerDelegator();
        try {
            request.setAttribute("consultas", usuarios.listarConsultasAsistidas(nickname));
            request.getRequestDispatcher("/WEB-INF/views/consultasAsistidasMovil.jsp")
                    .forward(request, response);
        } catch (UserNotFoundException_Exception e) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        } catch (ConsultaNotFoundException_Exception e) {
            throw new ServletException("No se pudieron cargar las consultas asistidas.", e);
        }
    }

    static String pacienteLogueado(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession(false);
        String nickname = session == null ? null : (String) session.getAttribute("nickname");

        if (nickname == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return null;
        }

        String tipoUsuario = (String) session.getAttribute("tipoUsuario");
        if (!"Paciente".equalsIgnoreCase(tipoUsuario)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }

        return nickname;
    }
}