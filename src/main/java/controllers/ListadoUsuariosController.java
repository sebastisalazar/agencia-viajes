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


import modelo.DAOImp.UsuarioDAOImp;

import modelo.pojo.Usuario;

/**
 * Servlet implementation class listadoPaisesController
 */
@WebServlet("/listado-usuarios")
public class ListadoUsuariosController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(ListadoUsuariosController.class);

	public ListadoUsuariosController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// crud para operar contra bbdd
		UsuarioDAOImp dao = UsuarioDAOImp.getInstance();

		// lista para guardar todos los paises
		ArrayList<Usuario> lista = new ArrayList<Usuario>();

		// obtiene la session iniciada
		HttpSession session = request.getSession();

		// si el usuario viene de crear nueva ciudad se borran los datos
		// que se hayan escrito para que no vuelvan a aparecer en los input
		
		//datos del formulario update
		session.removeAttribute("emailIntroducido");
		session.removeAttribute("passwordIntroducido");
		session.removeAttribute("rolSeleccionado");
		
		//datos del formulario de crear
		session.removeAttribute("emailIntroducidoC");
		session.removeAttribute("passwordIntroducidoC");
		session.removeAttribute("rolSeleccionadoC");

		// se obtiene la lista
		try {
			
			LOG.info("Iniciando controlador para obtener todos los usuarios de la base de datos");
			lista = dao.getAll();

		} catch (Exception e) {
			LOG.error(e);
			e.printStackTrace();
		} finally {

			// se pasa la lista guardada en la lista
			session.setAttribute("listaUsuarios", lista);

			// mata la sesion en 1 hora
			session.setMaxInactiveInterval(60 * 60);

			// se redireciona
			response.sendRedirect("views/usuario/lista-usuarios.jsp");
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
