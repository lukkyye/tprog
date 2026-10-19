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
import java.net.URLEncoder;
import com.models.datatypes.EstadoConsulta;

import com.models.datatypes.DTConsulta;

@WebServlet("/ConsultasDiaServlet")
public class ConsultasDiaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ClinicControllerDelegator clinicController =
            new ClinicControllerDelegator();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombreDia = request.getParameter("nombreDia");

        Set<DTConsulta> consultas;

        if (nombreDia == null || nombreDia.isBlank()) {
            consultas = new HashSet<>();
        } else {
            consultas = clinicController.selecListaConsultaDelDia(nombreDia);
        }

        Set<DTConsulta> consultasAceptadas = new HashSet<>();

        for (DTConsulta consulta : consultas) {
        	if (consulta.estado() == EstadoConsulta.Aceptada) {
                consultasAceptadas.add(consulta);
            }
        }

        request.setAttribute("consultas", consultasAceptadas);
        request.setAttribute("nombreDia", nombreDia);

        request.getRequestDispatcher("/WEB-INF/views/consultasDia.jsp").forward(request, response);
    }
}