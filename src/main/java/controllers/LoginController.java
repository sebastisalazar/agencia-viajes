package controllers;

import java.io.IOException;
import java.util.ArrayList;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;


import modelo.DAOImp.UsuarioDAOImp;

import modelo.pojo.Rol;
import modelo.pojo.Usuario;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(LoginController.class);
       
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session= request.getSession();
		
		if (session.getAttribute("loginUsuario")!=null) {
			
			Usuario u= (Usuario) session.getAttribute("loginUsuario");
			if (u.getRol().getId()==1) {
				
				response.sendRedirect("views/frontoffice/index.jsp");
			}else {
				response.sendRedirect("views/backoffice/index.jsp");
			}
			
		}else {
			
			response.sendRedirect("login.jsp");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String email=request.getParameter("email");
		
		String password=request.getParameter("password");
		
		//String recordar=request.getParameter("recordar");
		
		HttpSession session= request.getSession();
		Alerta alerta= new Alerta();
		
		UsuarioDAOImp daoUsuario= UsuarioDAOImp.getInstance();
		Usuario u= new Usuario();
	
		
		ArrayList<String> requeridos= new ArrayList<String>();
		
		
		//verificacion de campos vacios
		if (("").equalsIgnoreCase(email)) {
			requeridos.add("<p><b>Email</b> Introduce un email </p>");
		}
		
		if (("").equalsIgnoreCase(password)) {
			requeridos.add("<p><b>Password</b> Introduce un password</p>");
		}
		
		//si hay algun campo vacio se recargará la página indicando que campo/s es/son erroneos
		if (requeridos.size()!=0) {
			
			LOG.info("Error, existen campos vacios o con errores");
			session.setAttribute("requeridos", requeridos);
			session.setAttribute("loginEmailErroneo", email);
			session.setAttribute("loginPasswordErroneo",password);
			response.sendRedirect("views/login.jsp");
		
		//si estan ambos campos rellenador
		}else {
			
			
			try {
				LOG.info("Iniciando controlador para comprobar login");
				u=daoUsuario.getExiste(email, password);
				alerta= new Alerta("success", "Has Iniciado sesión correctamente.");
				
				session.removeAttribute("loginEmailErroneo");
				session.removeAttribute("loginPasswordErroneo");
				session.setAttribute("loginUsuario",u);
				session.setAttribute("alerta",alerta);
				
				if (u.getRol().getId()==Rol.ADMINISTRADOR) {
					LOG.info("Tipo usuario: Admin");
					response.sendRedirect("views/backoffice/index.jsp");
				}else {
					LOG.info("Tipo usuario: Normal");
					request.getRequestDispatcher("views/frontoffice/inicio").forward(request, response);
					
				}
				
			} catch (Exception e) {
				LOG.error(e);
				requeridos.add(e.getMessage());
				session.setAttribute("requeridos", requeridos);;
				session.setAttribute("loginEmailErroneo",email);
				session.setAttribute("loginPasswordErroneo",password);
				response.sendRedirect("views/login.jsp");
			}
		}
		
		
	}

}
