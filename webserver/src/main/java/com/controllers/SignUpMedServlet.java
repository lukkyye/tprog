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
import java.util.List;
import java.util.stream.Collectors;
import com.gateway.delegators.ClinicControllerDelegator;
import com.gateway.delegators.UserControllerDelegator;
import com.soap.EmailAlreadyExistsException_Exception;
import com.soap.MedicoSinEspecialidadException_Exception;
import com.soap.NicknameAlreadyExistsException_Exception;
import com.soap.NumeroDeRegistroAlreadyExistsException_Exception;
import com.soap.SpecialityNotFoundException_Exception;
import com.soap.TipoMes;



@WebServlet("/signup/m")
@MultipartConfig
public class SignUpMedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public SignUpMedServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> specs = new ClinicControllerDelegator().listarEspecialidades().stream().collect(Collectors.toCollection(ArrayList::new));
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
		String state = null;
		List<String> specs = new ArrayList<String>();
		try {
			Arrays.asList(request.getParameterValues("specs")).forEach(s->specs.add(s));		
		} catch (java.lang.NullPointerException e) {
			state="Ingrese al menos una especialidad.";
			request.setAttribute("success", state);
			doGet(request, response);
			return;
		}
		float porcentajeAdicional=Float.parseFloat(request.getParameter("porcentajeAdicional"));
		String link=request.getParameter("sitioWeb");
		Part img = request.getPart("imagen");
		
		TipoMes mesLic= switch (request.getParameter("mesLicencia").toLowerCase()) {
		case "enero" -> TipoMes.ENERO;
		case "febrero" -> TipoMes.FEBRERO;
		case "marzo" -> TipoMes.MARZO;
		case "abril" -> TipoMes.ABRIL;
		case "mayo" -> TipoMes.MAYO;
		case "junio" -> TipoMes.JUNIO;
		case "julio" -> TipoMes.JULIO;
		case "agosto" -> TipoMes.AGOSTO;
		case "septiembre" -> TipoMes.SEPTIEMBRE;
		case "octubre" -> TipoMes.OCTUBRE;
		case "noviembre" -> TipoMes.NOVIEMBRE;
		case "diciembre" -> TipoMes.DICIEMBRE;
		default -> null;
		};
		UserControllerDelegator controladorUsuario = new UserControllerDelegator();
		if (nickname!=null && name!=null && lastname!=null && mail!=null && password!=null && 
				nroReg!=null && mesLic!=null) {
			if (confirm.equals(password)) {
				try {
					controladorUsuario.ingresarMedico(nickname, password, name, lastname, mail, nroReg, mesLic, (int) porcentajeAdicional, link, specs);		
					if (img!=null) {
						com.gateway.delegators.ImageHandlerDelegator ihd = new com.gateway.delegators.ImageHandlerDelegator();
						ihd.addImg(img, nickname);
					}
				} catch (NicknameAlreadyExistsException_Exception e){
					state = e.getMessage();
				} catch (EmailAlreadyExistsException_Exception e2) {
					state = e2.getMessage();
				} catch (MedicoSinEspecialidadException_Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NumeroDeRegistroAlreadyExistsException_Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SpecialityNotFoundException_Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("success", state==null ? "Se registró correctamente el nuevo usuario. Por favor inicie sesión." : state);
				request.getRequestDispatcher("/WEB-INF/views/SignUpMed.jsp").forward(request, response);
			} else {
				request.setAttribute("success", "Por favor, intentelo de nuevo.");
				request.getRequestDispatcher("/WEB-INF/views/SignUpMed.jsp").forward(request, response);
			}
		}
	}

}
