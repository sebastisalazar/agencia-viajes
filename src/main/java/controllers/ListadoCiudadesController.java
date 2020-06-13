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
import modelo.DAOImp.PaisDAOImp;
import modelo.pojo.Ciudad;
import modelo.pojo.Pais;

/**
 * Servlet implementation class listadoPaisesController
 */
@WebServlet("/listado-ciudades")
public class ListadoCiudadesController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ListadoCiudadesController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// crud para operar contra bbdd
		CiudadDAOImp dao = CiudadDAOImp.getInstance();

		// lista para guardar todos los paises
		ArrayList<Ciudad> lista = new ArrayList<Ciudad>();

		// obtiene la session iniciada
		HttpSession session = request.getSession();

		// si el usuario viene de crear nueva ciudad se borran los datos
		// que se hayan escrito para que no vuelvan a aparecer en los input
		
		//datos del formulario update
		session.removeAttribute("nombreIntroducido");
		session.removeAttribute("paisSeleccionado");
		session.removeAttribute("continenteSeleccionado");
		
		//datos del formulario de crear
		session.removeAttribute("nombreIntroducidoC");
		session.removeAttribute("paisSeleccionadoC");
		session.removeAttribute("continenteSeleccionadoC");

		// se obtiene la lista
		try {
			lista = dao.getAll();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			// se pasa la lista guardada en la lista
			session.setAttribute("lista", lista);

			// mata la sesion en 1 hora
			session.setMaxInactiveInterval(60 * 60);

			// se redireciona
			response.sendRedirect("lista-ciudades.jsp");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
