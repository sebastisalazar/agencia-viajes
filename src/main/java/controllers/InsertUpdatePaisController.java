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
@WebServlet({ "/crear-pais", "/actualizar-pais" })
public class InsertUpdatePaisController extends HttpServlet {

	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private static Validator validator = factory.getValidator();

	private static final long serialVersionUID = 1L;

	public InsertUpdatePaisController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// para saber si entra por crear
		boolean llamaACrear = ("/crear-pais").equalsIgnoreCase(request.getServletPath());

		// Se obtiene la session creada
		HttpSession session = request.getSession();

		// objeto para alertas
		Alerta alerta;


		// si se llama a crear-ciudad se redirecciona
		if (llamaACrear) {

			// se redirecciona
			response.sendRedirect("crear-pais.jsp");

			// si no es que llama a update
		} else {
			// Se recoge el id en version string y se guarda en variable
			String ids = request.getParameter("id");
			// se transforma el id regido en string y se pasa a int
			int id = Integer.parseInt(ids);

			// crud para operar contra bbdd
			PaisDAOImp daoPais = PaisDAOImp.getInstance();

			// select by id
			try {

				// obtiene los datos de la base de datos segun el id recogido
				Pais p = daoPais.getById(id);

				// se manda los campos escritos para que la vista los conserve y rellene los
				// campos
				session.setAttribute("paisEditar", p);

				// se redirecciona al formulario para mostrar lo que se va a editar
				response.sendRedirect("actualizar-pais.jsp?id="+id);

			} catch (Exception e) {

				// si no encuentra el id envia alerta
				alerta = new Alerta("danger", e.getMessage());
				session.setAttribute("alerta", alerta);
				response.sendRedirect("crear-pais");
			}

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// para saber si entra por crear
		boolean llamaACrear = ("/crear-pais").equalsIgnoreCase(request.getServletPath());

		// se obtiene la session creada
		HttpSession session = request.getSession();

		// creacion de objeto alerta
		Alerta alerta = null;

		// Guardamos los datos recogidos del formulario
		String nombrepais = request.getParameter("nombrepais");
		
		String codigopais = request.getParameter("codigopais"); 
		
		String continentepais=request.getParameter("continentepais");

		// recoge el id por url cuando sea para actualizar
		String id = request.getParameter("id");

		// se manda los campos escritos para que el usuario no pierda los datos
		session.setAttribute("nombreIntroducido", nombrepais);

		session.setAttribute("codigoSeleccionado", codigopais);

		session.setAttribute("continenteSeleccionado", continentepais);

		// Array para mostrar mensajes para campos requeridos
		ArrayList<String> requeridos = new ArrayList<>();

		// crud para operar contra la bbdd
		PaisDAOImp daoPais = PaisDAOImp.getInstance();

		// creacion de objetos para asignar asignar los datos del formulario
		Pais p = new Pais();
		Continente co = new Continente();

		// Se guardan los atributos en los objetos
		p.setNombre(nombrepais);
		p.setNombrecorto(codigopais);
		co.setId(Integer.parseInt(continentepais));
		p.setContinente(co);

		// Recogemos las violaciones si es que las hay
		Set<ConstraintViolation<Pais>> violationsPais = validator.validate(p);

		// si existen violaciones para la ciudad se añaden a requeridos
		if (!violationsPais.isEmpty()) {
			// se recogen los mensajes y se añaden al listado de requeridos
			for (ConstraintViolation<Pais> v : violationsPais) {
				
				if ((String.valueOf(v.getPropertyPath())).equalsIgnoreCase("nombre")) {
					requeridos.add("<p><b>Nombre pais</b>: " + v.getMessage() + "</p>");
				}else {
					requeridos.add("<p><b>Codigo pais</b>: " + v.getMessage() + "</p>");
				}
				
			}
		}


		if (("0".equalsIgnoreCase(continentepais))) {
			requeridos.add("<p><b>Continente</b>: Selecciona un continente de la lista</p>");
		}
// INSERT***************************************************************************************************	
		try {

			if (llamaACrear) {

				// se manda los campos escritos para que el usuario no pierda los datos
				session.setAttribute("nombreIntroducidoC", nombrepais);

				session.setAttribute("codigopaisSeleccionadoC", codigopais);

				session.setAttribute("continenteSeleccionadoC", continentepais);

				if (requeridos.size() != 0) {

					// se manda los mensajes para requeridos
					session.setAttribute("requeridos", requeridos);

					response.sendRedirect("crear-pais.jsp");
					// si existen errores pero la url no contiene ningun parametro será para crear
				} else {

					daoPais.insert(p);
					
					session.removeAttribute("nombreIntroducidoC");

					session.removeAttribute("codigopaisSeleccionadoC");

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
					p.setId(Integer.parseInt(id));
					// ejecucion update

					daoPais.update(p);

					// si la insert va bien manda el siguiente mensaje
					alerta = new Alerta("success", "El registro se ha actualizado correctamente.");

				} // fin requeridos INSERT

				response.sendRedirect("actualizar-pais?id=" + id);
			} // FIN try

			// Si no salta ninguna excepcion al insertar o actualizar, se borran los campos
			// que se hayan escrito para que no vuelvan a aparecer en los input
			session.removeAttribute("nombreIntroducido");

			session.removeAttribute("codigoSeleccionado");

			session.removeAttribute("continenteSeleccionado");


		} catch (Exception e) {

			requeridos.add(e.getMessage());

			// si existe excepcion y se llamo por servlet crear-ciudad se redirige de vuelta
			// alli
			if (llamaACrear) {
				response.sendRedirect("crear-pais.jsp");

				// si no quiere decir que es updte/actualizar ciudad
			} else {
				response.sendRedirect("actualizar-pais?id=" + id);
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
