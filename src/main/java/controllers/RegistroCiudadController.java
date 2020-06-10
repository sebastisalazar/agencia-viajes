package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Alerta;
import modelo.Ciudad;
import modelo.Pais;
import modelo.PaisDAOImp;
import modelo.CiudadDAOImp;
import modelo.Continente;
import modelo.ContinenteDAOImp;

/**
 * Servlet implementation class RegistroCiudadController
 */
@WebServlet("/registro-ciudad")
public class RegistroCiudadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistroCiudadController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			//para obtener todos los paises registrados
			PaisDAOImp daoPais=PaisDAOImp.getInstance();
			ArrayList<Pais> selectPaises= daoPais.getAll();
			
			//para obtener todos los continentes
			ContinenteDAOImp daoCon=ContinenteDAOImp.getInstance();
			ArrayList<Continente> selectContinentes=daoCon.getAll();
			
			//Se obtiene la session creada
			HttpSession session= request.getSession();
			
			
			//Se pasan los atributos a la vista
			session.setAttribute("selectPaises", selectPaises);
			session.setAttribute("selectContinentes",selectContinentes);
			
			//se redirecciona
			response.sendRedirect("registro-ciudad.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			
		//Guardamos los datos recogidos del formulario
		String nombre;
		String pais;
		String continente;
		
		//Array para mostrar mensajes para campos requeridos
		
		if (("").equals(request.getParameter(""))) {
			
		}
		
		
		
		
		
	}

}
