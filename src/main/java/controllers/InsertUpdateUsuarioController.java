package controllers;


import java.io.IOException;

import java.util.ArrayList;

import java.util.Set;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


import modelo.DAOImp.UsuarioDAOImp;
import modelo.pojo.Rol;
import modelo.pojo.Usuario;

/**
 * Servlet implementation class RegistroCiudadController
 */
@WebServlet({ "/crear-usuario", "/actualizar-usuario" })
public class InsertUpdateUsuarioController extends HttpServlet {

	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private static Validator validator = factory.getValidator();

	private static final long serialVersionUID = 1L;

	public InsertUpdateUsuarioController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// para saber si entra por crear
		boolean llamaACrear = ("/crear-usuario").equalsIgnoreCase(request.getServletPath());

		// Se obtiene la session creada
		HttpSession session = request.getSession();

		// objeto para alertas
		Alerta alerta;


		// si se llama a crear-ciudad se redirecciona
		if (llamaACrear) {

			// se redirecciona
			response.sendRedirect("views/usuario/crear-usuario.jsp");

			// si no es que llama a update
		} else {
			// Se recoge el id en version string y se guarda en variable
			String ids = request.getParameter("id");
			// se transforma el id regido en string y se pasa a int
			int id = Integer.parseInt(ids);

			// crud para operar contra bbdd
			UsuarioDAOImp daoUsuario = UsuarioDAOImp.getInstance();

			// select by id
			try {

				// obtiene los datos de la base de datos sedun el id recogido
				Usuario u = daoUsuario.getById(id);

				// se manda los campos escritos para que la vista los conserve y rellene los
				// campos
				session.setAttribute("usuarioEditar",u);

				// se redirecciona al formulario para mostrar lo que se va a editar
				response.sendRedirect("views/usuario/actualizar-usuario.jsp?id=" + id);

			} catch (Exception e) {

				// si no encuentra el id envia alerta
				alerta = new Alerta("danger", e.getMessage());
				session.setAttribute("alerta", alerta);
				response.sendRedirect("crear-usuario");
			}

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// para saber si entra por crear
		boolean llamaACrear = ("/crear-usuario").equalsIgnoreCase(request.getServletPath());

		// se obtiene la session creada
		HttpSession session = request.getSession();

		// creacion de objeto alerta
		Alerta alerta = null;

		// Guardamos los datos recogidos del formulario
		String email = request.getParameter("email");
		
		String password = request.getParameter("password");
		
		String id_rol= request.getParameter("rol");

		// recoge el id por url cuando sea para actualizar
		String id = request.getParameter("id");

		// se manda los campos escritos para que el usuario no pierda los datos
		session.setAttribute("emailIntroducido", email);

		session.setAttribute("passwordIntroducido", password);

		session.setAttribute("rolSeleccionado", id_rol);

		// Array para mostrar mensajes para campos requeridos
		ArrayList<String> requeridos = new ArrayList<>();

		// crud para operar contra la bbdd
		UsuarioDAOImp daoUsuario = UsuarioDAOImp.getInstance();

		// creacion de objetos para asignar asignar los datos del formulario
		Usuario u= new Usuario();
		Rol rol= new Rol();

		// Se guardan los atributos en los objetos
		
		u.setEmail(email);
		u.setPassword(password);
		u.setRol(rol);

		// Recogemos las violaciones si es que las hay
		Set<ConstraintViolation<Usuario>> violationsUsuario = validator.validate(u);

		// si existen violaciones para la ciudad se añaden a requeridos
		if (!violationsUsuario.isEmpty()) {
			// se recogen los mensajes y se añaden al listado de requeridos
			for (ConstraintViolation<Usuario> v : violationsUsuario) {
				requeridos.add("<p><b>"+v.getPropertyPath()+"</b>: " + v.getMessage() + "</p>");
			}
		}

// INSERT***************************************************************************************************	
		try {

			if (llamaACrear) {

				// se manda los campos escritos para que el usuario no pierda los datos
				session.setAttribute("emailIntroducidoC", email);

				session.setAttribute("passwordIntroducidoC", password);

				session.setAttribute("rolSeleccionadoC", id_rol);

				if (requeridos.size() != 0) {

					// se manda los mensajes para requeridos
					session.setAttribute("requeridos", requeridos);

					response.sendRedirect("crear-usuario.jsp");
					// si existen errores pero la url no contiene ningun parametro será para crear
				} else {
			
					daoUsuario.insert(u);
					
					session.removeAttribute("emailIntroducidoC");

					session.removeAttribute("passwordIntroducidoC");

					session.removeAttribute("rolSeleccionadoC");

					// si la insert va bien manda el siguiente mensaje
					alerta = new Alerta("success", "El usuario se ha creado correctamente.");
					
					response.sendRedirect("listado-ciudades");

				}

				
// UPDATE ***************************************************************************************************				
			} else {

				if (requeridos.size() != 0) {

					// se manda los mensajes para requeridos
					session.setAttribute("requeridos", requeridos);

					// si existen errores pero la url no contiene ningun parametro será para crear
				} else {

					// por seguridad comprobamos si no es null
					if (id == null) {
						response.sendRedirect("listado-ciudades");
					}

					// antes de ejecutar el update cogemos el id pasado por url y se lo asignamos al
					// objeto
					u.setId(Integer.parseInt(id));
					u.setRol( new Rol(Integer.parseInt(id_rol)));
					
					// ejecucion update
					daoUsuario.update(u);

					// si la insert va bien manda el siguiente mensaje
					alerta = new Alerta("success", "El usuario se ha actualizado correctamente.");

				} // fin requeridos INSERT

				response.sendRedirect("actualizar-usuario?id=" + id);
			} // FIN try

			// Si no salta ninguna excepcion al insertar o actualizar, se borran los campos
			// que se hayan escrito para que no vuelvan a aparecer en los input
			session.removeAttribute("emailIntroducido");

			session.removeAttribute("passwordIntroducido");

			session.removeAttribute("rolSeleccionado");
			

		} catch (Exception e) {

			requeridos.add(e.getMessage());

			// si existe excepcion y se llamo por servlet crear-ciudad se redirige de vuelta
			// alli
			if (llamaACrear) {
				response.sendRedirect("crear-usuario.jsp");

				// si no quiere decir que es updte/actualizar ciudad
			} else {
				response.sendRedirect("actualizar-usuario?id=" + id);
			}
			// por ultimo exista error o no
		} finally {
			// se manda el mensaje alerta
			if (alerta != null) {
				session.setAttribute("alerta", alerta);
			}

			if (requeridos.size() != 0) {
				session.setAttribute("requeridos", requeridos);
			}
		} // fin try

	}// fin DOPOST

}// fin clase
