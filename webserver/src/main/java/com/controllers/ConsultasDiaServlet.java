package com.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.gateway.delegators.ClinicControllerDelegator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.models.datatypes.DTConsulta;

@WebServlet("/ConsultasDiaServlet")
public class ConsultasDiaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private ClinicControllerDelegator clinicController =
            new ClinicControllerDelegator();


    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String nickname = (String) request.getSession().getAttribute("nickname");

        String nombreD = request.getParameter("nombreDia");

        request.setAttribute(
            "consultas",
            nombreD == null
                ? clinicController.listarConsultas()
                : clinicController.selecListaConsultaDelDia(nombreD)
        );

        request.getRequestDispatcher("/WEB-INF/views/altaReserva.jsp")
               .forward(request, response);
    }
}