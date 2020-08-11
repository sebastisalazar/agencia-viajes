package controllers;


import java.io.IOException;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;


/**
 * Servlet implementation class LogoutController
 */
@WebServlet("/logout")
public class LogoutController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(LogoutController.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutController() {
        super();
      
    }

	/**
	 * @throws IOException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		//se obtiene la session que se haya creado
		HttpSession session = request.getSession();
		
		
		
		//se mata /invalida la sesion
		session.removeAttribute("loginUsuario");
		
		
		//se pasa el mensaje de DESLOGEO mediante una alerta
		session.setAttribute("alerta", new Alerta("success", "Has cerrado sesión correctamente."));
		
		LOG.debug("Session cerrada, datos borrados de session.");
		
		//Se redirecciona a login
		response.sendRedirect("views/login.jsp");
	}

	/**
	 * @throws IOException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		doGet(request, response);
	}

}
