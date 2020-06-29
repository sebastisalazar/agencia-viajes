package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.DAOImp.PaisDAOImp;
import modelo.pojo.Pais;

/**
 * Servlet implementation class PaisesContinenteController
 */
@WebServlet("/paises-continente")
public class PaisesContinenteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PaisesContinenteController() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int id=Integer.parseInt(request.getParameter("id"));
		String nombre= request.getParameter("nombre");
		
		PaisDAOImp daoPais= PaisDAOImp.getInstance();
		
		HttpSession session= request.getSession();
		
		Alerta alerta= new Alerta();
		
		try {
			ArrayList<Pais> paisesContinente= daoPais.getAllByContinente(id);
			//ArrayList<Ciudad> ciudadesPais=daoCiudad.getAllByContinente(id);
			session.setAttribute("busqueda",nombre);
			session.setAttribute("paisesContinente",paisesContinente);
			
			if(paisesContinente.size()==0) {
				alerta= new Alerta("warning", "No existen registros para este pais");
				session.setAttribute("alerta", alerta);
			}
		} catch (Exception e) {
			
			alerta= new Alerta("danger", e.getMessage());
			session.setAttribute("alerta", alerta);
		}finally {
			response.sendRedirect("paises-continente.jsp?id="+id+"&nombre="+nombre);
		}
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
