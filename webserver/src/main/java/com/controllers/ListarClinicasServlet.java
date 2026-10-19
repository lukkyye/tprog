package com.controllers;

import java.io.IOException;
import java.util.Set;

import com.gateway.delegators.ClinicControllerDelegator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ListarClinicasServlet")
public class ListarClinicasServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ClinicControllerDelegator clinicController =
            new ClinicControllerDelegator();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        Set<String> clinicas = clinicController.listarClinicas();

        request.setAttribute("clinicas", clinicas);

        HttpSession session = request.getSession(false);
        String tipoUsuario = null;

        if (session != null) {
            tipoUsuario = (String) session.getAttribute("tipoUsuario");
        }

        if (tipoUsuario == null || tipoUsuario.isBlank()) {
            tipoUsuario = "VISITANTE";
        }

        request.setAttribute("tipoUsuario", tipoUsuario);

        request.getRequestDispatcher(
                "/WEB-INF/views/listaClinicas.jsp"
        ).forward(request, response);
    }
}