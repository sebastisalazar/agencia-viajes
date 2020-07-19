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
 * Servlet implementation class CiudadesPaisController
 */
@WebServlet("/ciudades-pais")
public class CiudadesPaisController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id=Integer.parseInt(request.getParameter("id"));
		String nombre=request.getParameter("nombre");

		CiudadDAOImp dao= CiudadDAOImp.getInstance();
		
		Alerta alerta= new Alerta();
		
		HttpSession session= request.getSession();
		
		try {
			ArrayList<Ciudad> ciudades=dao.getAllByPais(id);
			session.setAttribute("ciudadesPais", ciudades);
			session.setAttribute("busquedaCiudades", nombre);
		} catch (Exception e) {
			alerta= new Alerta("danger", e.getMessage());
			session.setAttribute("alerta", alerta);
		}
		
		response.sendRedirect("views/ciudad/ciudades-pais.jsp?id="+id+"&nombre="+nombre);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
