package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import modelo.DAOImp.CiudadDAOImp;
import modelo.pojo.Ciudad;

/**
 * Servlet implementation class EliminarCiudadController
 */
@WebServlet("/eliminar-ciudad")
public class EliminarCiudadController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(EliminarCiudadController.class);
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		int id= Integer.parseInt(request.getParameter("id"));
		Alerta alerta= new Alerta();
		Ciudad ci= new Ciudad();
		CiudadDAOImp daoCiudad= CiudadDAOImp.getInstance();
		
		try {
			LOG.info("Iniciando controlador /eliminar-ciudad ");
			LOG.info("Ejecutando accion de eliminar (delete)");
			ci=daoCiudad.delete(id);
			
			alerta= new Alerta("success","La ciudad "+ci.getNombre()+" con id "+ci.getId()+" se ha borrado correctamente.");
			
		} catch (Exception e) {
			LOG.error(e);
			alerta= new Alerta("danger", e.getMessage());
			e.printStackTrace();
		}finally {
			session.setAttribute("alerta",alerta);
			response.sendRedirect("listado-ciudades");
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
