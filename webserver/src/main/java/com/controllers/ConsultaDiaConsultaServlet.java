
package com.controllers;

import java.io.IOException;
import java.util.Set;

import com.gateway.connectors.ClinicControllerConnector;
import com.soap.DtConsulta;
import com.soap.DtDiaConsulta;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ConsultaDiaConsultaServlet")
public class ConsultaDiaConsultaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ConsultaDiaConsultaServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String nombreDia = request.getParameter("nombreDia");

        if (nombreDia == null || nombreDia.isBlank()) {
            response.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "Falta el nombre del dia de consulta."
            );
            return;
        }

        ClinicControllerConnector controlador =
            new ClinicControllerConnector();

        Set<DtDiaConsulta> dias =
            controlador.listarDatosDiaConsulta();

        DtDiaConsulta diaSeleccionado = null;

        for (DtDiaConsulta dia : dias) {
            if (dia.getNomD().equals(nombreDia)) {
                diaSeleccionado = dia;
                break;
            }
        }

        if (diaSeleccionado == null) {
            response.sendError(
                HttpServletResponse.SC_NOT_FOUND,
                "No se encontro el dia de consulta."
            );
            return;
        }

        Set<DtConsulta> consultas =
            controlador.selecListaConsultaDelDia(nombreDia);

        request.setAttribute("diaConsulta", diaSeleccionado);
        request.setAttribute("consultas", consultas);

        request.getRequestDispatcher(
            "/WEB-INF/views/consultaDiaConsulta.jsp"
        ).forward(request, response);
    }
}