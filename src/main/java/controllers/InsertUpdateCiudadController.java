package controllers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import modelo.DAOImp.CiudadDAOImp;
import modelo.DAOImp.PaisDAOImp;
import modelo.pojo.Ciudad;
import modelo.pojo.Continente;
import modelo.pojo.Pais;

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

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// para saber si entra por crear
		boolean llamaACrear = ("/crear-ciudad").equalsIgnoreCase(request.getServletPath());

		// Se obtiene la session creada
		HttpSession session = request.getSession();

		// objeto para alertas
		Alerta alerta;

		// para poder enviar la lista de paises y continents
		try {

			// para obtener todos los paises registrados
			PaisDAOImp daoPais = PaisDAOImp.getInstance();
			ArrayList<Pais> selectPaises = daoPais.getAll();

			// Se pasan los atributos a la vista
			session.setAttribute("selectPaises", selectPaises);

		} catch (Exception e) {
			e.printStackTrace();
		} // fin dao get all

		// si se llama a crear-ciudad se redirecciona
		if (llamaACrear) {

			// se redirecciona
			response.sendRedirect("crear-ciudad.jsp");

			// si no es que llama a update
		} else {
			// Se recoge el id en version string y se guarda en variable
			String ids = request.getParameter("id");
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
				session.setAttribute("alerta", alerta);
				response.sendRedirect("crear-ciudad");
			}

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// para saber si entra por crear
		boolean llamaACrear = ("/crear-ciudad").equalsIgnoreCase(request.getServletPath());

		// se obtiene la session creada
		HttpSession session = request.getSession();

		// creacion de objeto alerta
		Alerta alerta = null;

		// Guardamos los datos recogidos del formulario
		String nombre = request.getParameter("nombreciudad");
		
		//el input que del que recoge contiene value="1,es"
		String pais = request.getParameter("paisciudad"); //primero cogemos todo el dato
		String paiscorto=pais.substring(pais.indexOf(",")); //de ese dato recogido solo nos quedamos con lo que hay detras de la coma
		pais=pais.substring(0,pais.indexOf(","));//nos quedamos solo con el número
		
		String continente = request.getParameter("continenteciudad");

		// recoge el id por url cuando sea para actualizar
		String id = request.getParameter("id");

		// se manda los campos escritos para que el usuario no pierda los datos
		session.setAttribute("nombreIntroducido", nombre);

		session.setAttribute("paisSeleccionado", pais);

		session.setAttribute("continenteSeleccionado", continente);

		// Array para mostrar mensajes para campos requeridos
		ArrayList<String> requeridos = new ArrayList<>();

		// crud para operar contra la bbdd
		CiudadDAOImp daoCiudad = CiudadDAOImp.getInstance();

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
		Set<ConstraintViolation<Ciudad>> violationsCiudad = validator.validate(ci);

		// si existen violaciones para la ciudad se añaden a requeridos
		if (!violationsCiudad.isEmpty()) {
			// se recogen los mensajes y se añaden al listado de requeridos
			for (ConstraintViolation<Ciudad> v : violationsCiudad) {
				requeridos.add("<p><b>Nombre ciudad</b>: " + v.getMessage() + "</p>");
			}
		}

		// validaciones combo box
		if (("0".equalsIgnoreCase(pais))) {
			requeridos.add("<p><b>Pais</b>: Selecciona un pais de la lista</p>");
		}

		if (("0".equalsIgnoreCase(continente))) {
			requeridos.add("<p><b>Continente</b>: Selecciona un continente de la lista</p>");
		}
// INSERT***************************************************************************************************	
		try {

			if (llamaACrear) {

				// se manda los campos escritos para que el usuario no pierda los datos
				session.setAttribute("nombreIntroducidoC", nombre);

				session.setAttribute("paisSeleccionadoC", pais);

				session.setAttribute("continenteSeleccionadoC", continente);

				if (requeridos.size() != 0) {

					// se manda los mensajes para requeridos
					session.setAttribute("requeridos", requeridos);

					response.sendRedirect("crear-ciudad.jsp");
					// si existen errores pero la url no contiene ningun parametro será para crear
				} else {

					daoCiudad.insert(ci);
					

					session.removeAttribute("nombreIntroducidoC");

					session.removeAttribute("paisSeleccionadoC");

					session.removeAttribute("continenteSeleccionadoC");

					// si la insert va bien manda el siguiente mensaje
					alerta = new Alerta("success", "El registro se ha guardado correctamente.");
					
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
					ci.setId(Integer.parseInt(id));
					// ejecucion update

					daoCiudad.update(ci);

					// si la insert va bien manda el siguiente mensaje
					alerta = new Alerta("success", "El registro se ha actualizado correctamente.");

				} // fin requeridos INSERT

				response.sendRedirect("actualizar-ciudad?id=" + id);
			} // FIN try

			// Si no salta ninguna excepcion al insertar o actualizar, se borran los campos
			// que se hayan escrito para que no vuelvan a aparecer en los input
			session.removeAttribute("nombreIntroducido");

			session.removeAttribute("paisSeleccionado");

			session.removeAttribute("continenteSeleccionado");
			



		} catch (Exception e) {

			requeridos.add(e.getMessage());

			// si existe excepcion y se llamo por servlet crear-ciudad se redirige de vuelta
			// alli
			if (llamaACrear) {
				response.sendRedirect("crear-ciudad.jsp");

				// si no quiere decir que es updte/actualizar ciudad
			} else {
				response.sendRedirect("actualizar-ciudad?id=" + id);
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
