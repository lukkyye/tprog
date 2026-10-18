package com.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.gateway.delegators.UserControllerDelegator;
import com.models.excepciones.EmailAlreadyExistsException;
import com.models.excepciones.IdAlreadyExistsException;
import com.models.excepciones.NicknameAlreadyExistsException;

/**
 * Servlet implementation class SignupPacServlet
 */
@WebServlet("/signup/p")
@MultipartConfig
public class SignupPacServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupPacServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/views/SignUpPac.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nickname=request.getParameter("nickname");
		String name=request.getParameter("nombre");
		String mail=request.getParameter("correo");
		String password = request.getParameter("contrasena");
		String confirm = request.getParameter("confirmacion");
		String lastname=request.getParameter("apellido");
		LocalDate born=LocalDate.parse(request.getParameter("fechaNacimiento"));
		int id=Integer.parseInt(request.getParameter("documento"));
		ArrayList<String> ant = new ArrayList<String>(List.of(
					request.getParameter("antecedentes").split("\n")
				));
		Part img= request.getPart("imagen");
		
		UserControllerDelegator cu = new UserControllerDelegator();
		if (nickname!=null && lastname!=null && confirm != null && password != null && born!=null) {
			if (confirm.equals(password)) {
				String state = null;
				try {
					cu.ingresarPaciente(nickname, password, name, lastname, mail, born, LocalDate.now(), id, ant);		
					if (img!=null) {
						com.gateway.delegators.ImageHandlerDelegator ihd = new com.gateway.delegators.ImageHandlerDelegator();
						ihd.add_img(img, nickname);
					}
				} catch (NicknameAlreadyExistsException e){
					state = e.getMessage();
				} catch (EmailAlreadyExistsException e2) {
					state = e2.getMessage();
				} catch (IdAlreadyExistsException e3) {
					state = e3.getMessage();
				}
				if (state == null) {
				    request.setAttribute("success", "Se registró correctamente. Ahora podés iniciar sesión.");
				} else {
				    request.setAttribute("error", state);
				}

				request.getRequestDispatcher("/WEB-INF/views/SignUpPac.jsp").forward(request, response);
			} else {
				request.setAttribute("success", "Por favor, intentelo de nuevo.");
				request.getRequestDispatcher("/WEB-INF/views/SignUpPac.jsp").forward(request, response);
			}
		}
		
	}

}
