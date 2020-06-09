package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Pais;
import modelo.PaisDAOImp;

/**
 * Servlet implementation class listadoPaisesController
 */
@WebServlet("/listado-paises")
public class ListadoPaisesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ListadoPaisesController() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		PaisDAOImp dao = PaisDAOImp.getInstance();
		ArrayList<Pais> lista= new ArrayList<Pais>();
		try {
			lista= dao.getAll();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			request.setAttribute("lista",lista);
			request.getRequestDispatcher("lista-paises.jsp").forward(request, response);
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
