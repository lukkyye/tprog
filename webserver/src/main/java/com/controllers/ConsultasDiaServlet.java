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
        String especialidad = request.getParameter("especialidad");
        
        Set<DTConsulta> consultas = new HashSet<DTConsulta>();
        Set<DTConsulta> aceptadas = new HashSet<DTConsulta>();
        
       if (nombreDia!=null && especialidad==null) {
        	request.setAttribute("from", "del Dia");
            clinicController.selecListaConsultaDelDia(nombreDia).forEach(x->consultas.add(x));
        } else  if (nombreDia==null && especialidad!=null){
        	request.setAttribute("from", "por Especialidad");
        	clinicController.getConsultasPorEspecialidad(especialidad).forEach(
        				c->{
        					consultas.add(clinicController.seleccionarConsulta(c));
        				}
        			);
        }
        consultas.forEach(x->{
        	if (x.estado()==EstadoConsulta.Aceptada) {
        		aceptadas.add(x);
        	}
        });
        
        request.setAttribute("consultas", aceptadas);
        request.setAttribute("nombreDia", nombreDia);

        request.getRequestDispatcher("/WEB-INF/views/consultasDia.jsp").forward(request, response);
    }
}