/**
 * Controlador que obtiene un listado de todos los registros de ciudades en la base de datos
 * 
 * @author sebastian
 * @version 1.0
 * 
 */
package controllers;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class listadoPaisesController
 */
@WebServlet("/listado-ciudades")
public class ListadoCiudadesController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(ListadoCiudadesController.class);

	public ListadoCiudadesController() {
		super();

	}

	/**
	 * Se encarga de obtener el listado completo de ciudades cuando sea llamado por URL (href).
	 * 
	 * Atributos
	 * 
	 * 			dao: Para poder operar contra la base de datos
	 * 			lista: Array que almacenará el listado completo
	 * 			session: Para poder guardar los atributos al hacer un sendRedirect
	 * 
	 * 
	 * 			
	 */
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
			LOG.info("Iniciando controlador listado de todas las ciudades registradas");
			lista = dao.getAll();

		} catch (Exception e) {
			LOG.error(e);
			e.printStackTrace();
		} finally {
			
			

			// se pasa la lista guardada en la lista
			session.setAttribute("lista", lista);

			// mata la sesion en 1 hora
			session.setMaxInactiveInterval(60 * 60);

			// se redireciona
			response.sendRedirect("views/ciudad/lista-ciudades.jsp");
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
