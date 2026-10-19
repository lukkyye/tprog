package com.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.gateway.delegators.ClinicControllerDelegator;
import com.gateway.delegators.ImageHandlerDelegator;
import com.models.datatypes.DTDiaConsulta;
import com.models.excepciones.ConsultaNombreAlreadyExistsException;
import com.models.excepciones.FechaDiaSemanaInvalidaException;
import com.models.excepciones.MedicoSinEspecialidadException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@WebServlet("/AltaConsultaServlet")
@MultipartConfig
public class AltaConsultaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String VISTA = "/WEB-INF/views/altaConsulta.jsp";

    private final ClinicControllerDelegator clinicController = new ClinicControllerDelegator();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (nicknameMedico(request) == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        request.setAttribute("diaSel", request.getParameter("dia"));
        if ("1".equals(request.getParameter("ok"))) {
            request.setAttribute("exito", "Consulta dada de alta correctamente. Queda en estado Ingresada hasta que el administrador la acepte.");
        }
        cargarDias(request);
        request.getRequestDispatcher(VISTA).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String medico = nicknameMedico(request);
        if (medico == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        request.setCharacterEncoding("UTF-8");

        String dia = request.getParameter("dia");
        String nombre = request.getParameter("nombre");
        String fechaTexto = request.getParameter("fecha");
        String salaTexto = request.getParameter("sala");
        String cantTexto = request.getParameter("cantNum");

        request.setAttribute("diaSel", dia);
        request.setAttribute("nombreVal", nombre);
        request.setAttribute("fechaVal", fechaTexto);
        request.setAttribute("salaVal", salaTexto);
        request.setAttribute("cantVal", cantTexto);

        try {
            if (vacio(dia) || vacio(nombre) || vacio(fechaTexto) || vacio(salaTexto) || vacio(cantTexto)) {
                throw new IllegalArgumentException("Debes completar todos los campos.");
            }
            if (!existeDia(dia)) {
                throw new IllegalArgumentException("Selecciona un día de consulta válido.");
            }

            LocalDate fecha = LocalDate.parse(fechaTexto.trim());
            int sala = Integer.parseInt(salaTexto.trim());
            int cantNum = Integer.parseInt(cantTexto.trim());
            if (sala < 1 || cantNum < 1) {
                throw new IllegalArgumentException("La sala y la cantidad de números deben ser mayores a 0.");
            }

            clinicController.ingresarConsulta(dia, medico, nombre.trim(), fecha, sala, cantNum);

            guardarImagen(request, nombre.trim());

            response.sendRedirect(request.getContextPath() + "/AltaConsultaServlet?ok=1");
            return;

        } catch (NumberFormatException e) {
            request.setAttribute("mensaje",
                    "Sala y Cantidad de números deben ser valores numéricos.");
        } catch (DateTimeParseException e) {
            request.setAttribute("mensaje", "La fecha ingresada no es válida.");
        } catch (ConsultaNombreAlreadyExistsException | MedicoSinEspecialidadException | FechaDiaSemanaInvalidaException | IllegalArgumentException e) {
            request.setAttribute("mensaje", e.getMessage());
        }

        cargarDias(request);
        request.getRequestDispatcher(VISTA).forward(request, response);
    }

    private void guardarImagen(HttpServletRequest request, String nombreConsulta)
            throws IOException, ServletException {
        Part archivo = request.getPart("imagen");
        if (archivo != null && archivo.getSize() > 0) {
        	new ImageHandlerDelegator().add_img(archivo, "consulta:" + nombreConsulta);
        }
    }

    private boolean existeDia(String nombreDia) {
        for (DTDiaConsulta d : clinicController.listarDatosDiaConsulta()) {
            if (d.nomD().equals(nombreDia)) {
                return true;
            }
        }
        return false;
    }

    private void cargarDias(HttpServletRequest request) {
        List<DTDiaConsulta> dias = new ArrayList<>(clinicController.listarDatosDiaConsulta());
        dias.sort(Comparator.comparing(DTDiaConsulta::nomD));
        request.setAttribute("dias", dias);
    }

    private static String nicknameMedico(HttpServletRequest request) {
        HttpSession sesion = request.getSession(false);
        if (sesion == null || sesion.getAttribute("nickname") == null || !"Medico".equalsIgnoreCase((String) sesion.getAttribute("tipoUsuario"))) {
            return null;
        }
        return (String) sesion.getAttribute("nickname");
    }

    private static boolean vacio(String texto) {
        return texto == null || texto.isBlank();
    }
}
