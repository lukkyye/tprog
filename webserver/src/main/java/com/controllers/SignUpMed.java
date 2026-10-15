package com.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import jakarta.servlet.annotation.MultipartConfig;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.models.logica.controladores.ControladorUsuario;
import com.models.logica.datatypes.TipoMes;
import com.models.logica.excepciones.EmailAlreadyExistsException;
import com.models.logica.excepciones.NicknameAlreadyExistsException;
import com.models.logica.excepciones.NumeroDeRegistroAlreadyExistsException;
import com.models.logica.manejadores.ManejadorEspecialidad;

/**
 * Servlet implementation class SignUpMed
 */
@WebServlet("/signup/med")
@MultipartConfig
public class SignUpMed extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpMed() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/auth/signUpMedico.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nickname = request.getParameter("nickname");
		String mail= request.getParameter("correo");
		String name = request.getParameter("nombre");
		String surname = request.getParameter("apellido");
		String nroreg = request.getParameter("numeroRegistro");
		String meslic= request.getParameter("mesLicencia");
		ArrayList<String> specs= new ArrayList<String>(Arrays.asList(request.getParameterValues("especialidades")));
		String percent= request.getParameter("porcentajeAdicional");
		String website= request.getParameter("sitioWeb");
		Part imagen = request.getPart("imagen");
		String password = request.getParameter("contrasena");
		String confpassword = request.getParameter("confirmacion");
		
		if (nickname==null || mail==null || name==null || surname==null || nroreg==null || TipoMes.fromString(meslic)==null || specs==null
				|| percent==null) {
			//raise exception
		}
		try {
			ControladorUsuario cu = new ControladorUsuario();
			ManejadorEspecialidad.get_instance().altaEspecialidad("Cardiología", 2);
			ManejadorEspecialidad.get_instance().altaEspecialidad("Pediatría", 4);
			cu.ingresarMedico(nickname, password, name, surname, mail, nroreg, TipoMes.fromString(meslic), Integer.parseInt(percent), website, specs);
			response.sendRedirect("/");
		} catch (NicknameAlreadyExistsException e) {
			System.out.println("e");
		} catch (EmailAlreadyExistsException e1) {
			System.out.println("e1");
		} catch (NumeroDeRegistroAlreadyExistsException e2) {
			System.out.println("e2");
		}
	}

}
