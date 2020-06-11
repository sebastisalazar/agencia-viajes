package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Pais;
import modelo.Ciudad;
import modelo.CiudadDAOImp;
import modelo.PaisDAOImp;

/**
 * Servlet implementation class listadoPaisesController
 */
@WebServlet("/listado-ciudades")
public class ListadoCiudadesController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
    public ListadoCiudadesController() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//crud para operar contra bbdd
		CiudadDAOImp dao = CiudadDAOImp.getInstance();
		
		//lista para guardar todos los paises
		ArrayList<Ciudad> lista= new ArrayList<Ciudad>();
		
		//obtiene la session iniciada
		HttpSession session=request.getSession();
		
		//se obtiene la lista
		try {
			lista= dao.getAll();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			//se pasa la lista guardada en la lista
			session.setAttribute("lista",lista);
			
			//se redireciona
			response.sendRedirect("lista-ciudades.jsp");
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