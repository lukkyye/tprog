package com.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import com.gateway.delegators.ClinicControllerDelegator;
import com.gateway.delegators.UserControllerDelegator;
import com.models.datatypes.TipoMes;
import com.models.excepciones.EmailAlreadyExistsException;
import com.models.excepciones.IdAlreadyExistsException;
import com.models.excepciones.NicknameAlreadyExistsException;


@WebServlet("/signup/m")
@MultipartConfig
public class SignUpMedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public SignUpMedServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//(new UserControllerDelegator()).altaEspecialidad("Cardiología", 2);
		//(new UserControllerDelegator()).altaEspecialidad("Pediatría", 2);
		ArrayList<String> specs = new ClinicControllerDelegator().listarEspecialidades().stream().collect(Collectors.toCollection(ArrayList::new));
		request.setAttribute("specs", specs);
		request.getRequestDispatcher("/WEB-INF/views/SignUpMed.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nickname=request.getParameter("nickname");
		String name=request.getParameter("nombre");
		String lastname=request.getParameter("apellido");
		String mail=request.getParameter("correo");
		String password = request.getParameter("contrasena");
		String confirm = request.getParameter("confirmacion");
		String nroReg=request.getParameter("numeroRegistro");
		ArrayList<String> specs = new ArrayList<String>(Arrays.asList(request.getParameterValues("specs")));
		float porcentajeAdicional=Float.parseFloat(request.getParameter("porcentajeAdicional"));
		String link=request.getParameter("sitioWeb");
		Part img = request.getPart("imagen");
		TipoMes mesLic=TipoMes.fromString(request.getParameter("mesLicencia"));
		
		UserControllerDelegator cu = new UserControllerDelegator();
		if (nickname!=null && name!=null && lastname!=null && mail!=null && password!=null && 
				nroReg!=null && specs!=null && mesLic!=null) {
			if (confirm.equals(password)) {
				String state = null;
				try {
					cu.ingresarMedico(nickname, password, name, lastname, mail, nroReg, mesLic, (int) porcentajeAdicional, link, specs);		
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
				request.setAttribute("success", state==null ? "Se registró correctamente el nuevo usuario. Por favor inicie sesión." : state);
				request.getRequestDispatcher("/WEB-INF/views/SignUpPac.jsp").forward(request, response);
			} else {
				request.setAttribute("success", "Por favor, intentelo de nuevo.");
				request.getRequestDispatcher("/WEB-INF/views/SignUpMed.jsp").forward(request, response);
			}
		}
	}

}
