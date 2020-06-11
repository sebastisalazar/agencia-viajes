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

import modelo.Ciudad;
import modelo.Pais;
import modelo.PaisDAOImp;
import modelo.CiudadDAOImp;
import modelo.Continente;
import modelo.ContinenteDAOImp;

/**
 * Servlet implementation class RegistroCiudadController
 */
@WebServlet({ "/crear-ciudad", "/actualizar-ciudad" })
public class InsertUpdateCiudadController extends HttpServlet {

	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private static Validator validator = factory.getValidator();

	private static final long serialVersionUID = 1L;

	
	public InsertUpdateCiudadController() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Se obtiene la session creada
		HttpSession session = request.getSession();

		// se borra los atributos de la sesion si se viene de otra pagina mediante ancla
		session.removeAttribute("nombreIntroducido");

		session.removeAttribute("paisSeleccionado");

		session.removeAttribute("continenteSeleccionado");

		// variable para guardar el parametro id por url SI ESQUE SE ENVIA
		String ids;
	
		// objeto para alertas
		Alerta alerta = new Alerta();

		// para poder enviar la lista de paises y continents
		try {

			// para obtener todos los paises registrados
			PaisDAOImp daoPais = PaisDAOImp.getInstance();
			ArrayList<Pais> selectPaises = daoPais.getAll();

			// para obtener todos los continentes
			ContinenteDAOImp daoCon = ContinenteDAOImp.getInstance();
			ArrayList<Continente> selectContinentes = daoCon.getAll();

			// Se pasan los atributos a la vista
			session.setAttribute("selectPaises", selectPaises);
			session.setAttribute("selectContinentes", selectContinentes);

		} catch (Exception e) {
			e.printStackTrace();
		} // fin dao get all

		// si se entra por ancla o url y contiene un id como parametro
		if (request.getParameterValues("id") != null) {

			// Se recoge el id en version string y se guarda en variable
			ids = request.getParameter("id");
			// se transforma el id regido en string y se pasa a int 
			int id = Integer.parseInt(ids);
			

			// crud para operar contra bbdd
			CiudadDAOImp daoCiudad = CiudadDAOImp.getInstance();

			// select by id
			try {

				// obtiene los datos de la base de datos sedun el id recogido
				Ciudad ci = daoCiudad.getById(id);

				// se manda los campos escritos para que la vista los conserve y rellene los
				// campos
				session.setAttribute("ciudadEditar", ci);

				// se redirecciona al formulario para mostrar lo que se va a editar
				response.sendRedirect("actualizar-ciudad.jsp?id=" + id);

			} catch (Exception e) {

				// si no encuentra el id envia alerta
				alerta = new Alerta("danger", e.getMessage());
			}

		//si no existe un id como parametro en la url quiere decir que es un insert
		} else {
			// se redirecciona
			response.sendRedirect("crear-ciudad.jsp");
		}

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// se obtiene la session creada
		HttpSession session = request.getSession();

		// creacion de objeto alerta
		Alerta alerta = new Alerta();

		// Guardamos los datos recogidos del formulario
		String nombre = request.getParameter("nombreciudad");
		String pais = request.getParameter("paisciudad");
		String continente = request.getParameter("continenteciudad");

		// Array para mostrar mensajes para campos requeridos
		ArrayList<String> requeridos = new ArrayList<>();

		// creacion de objetos para asignar asignar los datos del formulario
		Ciudad ci = new Ciudad();
		Pais p = new Pais();
		Continente co = new Continente();

		// Se guardan los atributos en los objetos
		ci.setNombre(nombre);
		p.setId(Integer.parseInt(pais));
		co.setId(Integer.parseInt(continente));

		ci.setPais(p);
		ci.setContinente(co);

		// Recogemos las violaciones si es que las hay
		Set<ConstraintViolation<Continente>> violationsContinente = validator.validate(co);
		Set<ConstraintViolation<Pais>> violationsPais = validator.validate(p);
		Set<ConstraintViolation<Ciudad>> violationsCiudad = validator.validate(ci);

		// si existen violaciones para la ciudad se añaden a requeridos
		if (!violationsCiudad.isEmpty()) {
			// se recogen los mensajes y se añaden al listado de requeridos
			for (ConstraintViolation<Ciudad> v : violationsCiudad) {
				requeridos.add("<p><b>Nombre ciudad</b>: " + v.getMessage() + "</p>");
			}
		}

		// si existen violaciones para el pais se añaden a requeridos
		if (!violationsPais.isEmpty()) {
			// se recogen los mensajes y se añaden al listado de requeridos
			for (ConstraintViolation<Pais> v : violationsPais) {
				requeridos.add("<p><b>Pais</b>: " + v.getMessage() + "</p>");
			}
		}

		// si existen violaciones para el continente se añaden a requeridos
		if (!violationsContinente.isEmpty()) {
			// se recogen los mensajes y se añaden al listado de requeridos
			for (ConstraintViolation<Continente> v : violationsContinente) {
				requeridos.add("<p><b>Continente</b>: " + v.getMessage() + "</p>");
			}
		}

		// si el array de requeridos contiene mensajes (violaciones) se mandan a la
		// vista
		if (requeridos.size() != 0) {

			// se manda los mensajes para requeridos
			session.setAttribute("requeridos", requeridos);

			// se manda los campos escritos para que el usuario no pierda los datos
			session.setAttribute("nombreIntroducido", nombre);

			session.setAttribute("paisSeleccionado", pais);

			session.setAttribute("continenteSeleccionado", continente);

			// si existen errores y la url contiene por parametro un id redireccionará
			// quiere decir que es para editar/actualizar
			if (request.getParameterValues("id") != null) {
				int id = Integer.parseInt(request.getParameter("id"));
				response.sendRedirect("actualizar-ciudad?id=" + id);

				// si existen errores pero la url no contiene ningun parametro será para crear
			} else {
				// se redirecciona al formulario
				response.sendRedirect("crear-ciudad.jsp");
			}

			// si no existen campos erroneos con violaciones
		} else {

			// crud para operar contra la bbdd
			CiudadDAOImp daoCiudad = CiudadDAOImp.getInstance();

			// ejecucion segun si la url contiene parametro o no
			try {

				// si no existen campos erroneos/con violaciones y la url no contiene un id sera
				// un insert
				if (request.getParameterValues("id") == null) {
					daoCiudad.insert(ci);
					// si la insert va bien manda el siguiente mensaje
					alerta = new Alerta("success", "El registro se ha guardado correctamente.");

					// si la url contiene un id por url será un update
				} else {
					// antes de ejecutar el update cogemos el id pasado por url y se lo asignamos al
					// objeto
					ci.setId(Integer.parseInt(request.getParameter("id")));
					// ejecucion update
					daoCiudad.update(ci);

					// si la insert va bien manda el siguiente mensaje
					alerta = new Alerta("success", "El registro se ha actualizado correctamente.");

				}

				// Si no salta ninguna excepcion al insertar o actualizar, se borran los campos
				// que se hayan escrito para que no vuelvan a aparecer en los input
				session.removeAttribute("nombreIntroducido");

				session.removeAttribute("paisSeleccionado");

				session.removeAttribute("continenteSeleccionado");

			} catch (Exception e) {

				// si existe alguna excepcion al insertar o al actualizar se recuperará los
				// datos escritos y se volvera a mandar a la vista para que se puedan corregir
				session.setAttribute("nombreIntroducido", nombre);

				session.setAttribute("paisSeleccionado", pais);

				session.setAttribute("continenteSeleccionado", continente);

				// si la insert/update va mal se manda el siguiente mensaje, e.getmessage lo
				// manda la
				// insert/update
				alerta = new Alerta("danger", e.getMessage());

				// por ultimo exista error o no
			} finally {

				// si la url NO contiene un parametro id se redirecciona a crear
				if (request.getParameterValues("id") == null) {
					response.sendRedirect("crear-ciudad");

					// si la url contiene un paramtero id se redirecciona a actualizar/update
					// indicando el id a editar
				} else {
					int id = Integer.parseInt(request.getParameter("id"));
					response.sendRedirect("actualizar-ciudad?id=" + id);
				}

				// se manda el mensaje alerta
				session.setAttribute("alerta", alerta);

			} // fin try insert
		} // fin if requeridos
	}// fin DOPOST

}// fin clase
