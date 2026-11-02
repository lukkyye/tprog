package com.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


import com.gateway.delegators.ClinicControllerDelegator;
import com.soap.ConsultaNotFoundException_Exception;
import com.soap.DtConsulta;
import com.soap.EstadoConsulta;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
        
        Set<DtConsulta> consultas = new HashSet<DtConsulta>();
        Set<DtConsulta> aceptadas = new HashSet<DtConsulta>();
        
       if (nombreDia!=null && especialidad==null) {
        	request.setAttribute("from", "del Dia");
            clinicController.selecListaConsultaDelDia(nombreDia).forEach(x->consultas.add(x));
        } else  if (nombreDia==null && especialidad!=null){
        	request.setAttribute("from", "por Especialidad");
        	clinicController.getConsultasPorEspecialidad(especialidad).forEach(
        				c->{
        					try {
								consultas.add(clinicController.seleccionarConsulta(c));
							} catch (ConsultaNotFoundException_Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
        				}
        			);
        }
        consultas.forEach(x->{
        	if (x.getEstado()==EstadoConsulta.ACEPTADA) {
        		aceptadas.add(x);
        	}
        });
        
        request.setAttribute("consultas", aceptadas);
        request.setAttribute("nombreDia", nombreDia);

        request.getRequestDispatcher("/WEB-INF/views/consultasDia.jsp").forward(request, response);
    }
}