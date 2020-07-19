package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.DAOImp.CiudadDAOImp;
import modelo.pojo.Ciudad;

/**
 * Servlet implementation class InicioController
 */
@WebServlet("/inicio")
public class InicioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CiudadDAOImp dao=CiudadDAOImp.getInstance();
		ArrayList<Ciudad> ciudades= new ArrayList<Ciudad>();
		HttpSession session= request.getSession();
		
		
		HttpServletRequest req= (HttpServletRequest) request;
		
		//necesitamos la url de como comienza nuestra App, apra construir una ryta ABSOLUTA y que no sea relativa
		String urlInicioApp = req.getContextPath();
		
		try {
			ciudades= dao.getLast(9);
			session.setAttribute("ciudadesMasVisitadas",ciudades);
		} catch (Exception e) {
			Alerta alerta= new Alerta("danger","Opps.. something went wrong");
			session.setAttribute("alerta",alerta);
		}finally {
			
			response.sendRedirect(urlInicioApp+"/index.jsp");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
