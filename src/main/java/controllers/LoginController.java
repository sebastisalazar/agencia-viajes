package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import modelo.DAOImp.UsuarioDAOImp;
import modelo.pojo.Continente;
import modelo.pojo.Usuario;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private static Validator validator = factory.getValidator();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String email=request.getParameter("email");
		
	
		String password=request.getParameter("password");
		
		String recordar=request.getParameter("recordar");
		
		HttpSession session= request.getSession();
		Alerta alerta= new Alerta();
		
		UsuarioDAOImp daoUsuario= UsuarioDAOImp.getInstance();
		Usuario u= new Usuario();
	
		
		ArrayList<String> requeridos= new ArrayList<String>();
		
		if (("").equalsIgnoreCase(email)) {
			requeridos.add("<p><b>Email</b> Introduce un email </p>");
		}
		
		if (("").equalsIgnoreCase(password)) {
			requeridos.add("<p><b>Password</b> Introduce un password</p>");
		}
		
		
		if (requeridos.size()!=0) {
			
			session.setAttribute("requeridos", requeridos);
			session.setAttribute("loginEmailErroneo", email);
			session.setAttribute("loginPasswordErroneo",password);
			response.sendRedirect("login.jsp");
			
		}else {
			
			
			try {
				daoUsuario.getExiste(email, password);
				alerta= new Alerta("success", "Has Iniciado sesión correctamente.");
				
				session.removeAttribute("loginEmailErroneo");
				session.removeAttribute("loginPasswordErroneo");
				
				session.setAttribute("loginEmail", email);
				session.setAttribute("loginPassword",password);
				
				response.sendRedirect("listado-ciudades");
			} catch (Exception e) {
				alerta= new Alerta("warning",e.getMessage());
				session.setAttribute("loginEmailErroneo",email);
				session.setAttribute("loginPasswordErroneo",password);
				response.sendRedirect("login.jsp");
			}finally {
				session.setAttribute("alerta",alerta);
			}
		}
		
		
	}

}
