package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import modelo.DAOImp.PaisDAOImp;

import modelo.pojo.Pais;

/**
 * Servlet implementation class EliminarCiudadController
 */
@WebServlet("/eliminar-pais")
public class EliminaPaisController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(EliminaPaisController.class);
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		int id= Integer.parseInt(request.getParameter("id"));
		Alerta alerta= new Alerta();
		Pais p= new Pais();
		PaisDAOImp daoPais= PaisDAOImp.getInstance();
		
		try {
			LOG.info("Iniciando controlaro /eliminar-pais ");
			LOG.info("Ejecutando accion de eliminar (delete)");
			p=daoPais.delete(id);
			
			alerta= new Alerta("success","El pais "+p.getNombre()+" con id "+p.getId()+" se ha borrado correctamente.");
			
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
