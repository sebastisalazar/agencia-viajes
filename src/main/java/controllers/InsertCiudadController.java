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
@WebServlet("/registro-ciudad")
public class InsertCiudadController extends HttpServlet {

	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private static Validator validator = factory.getValidator();

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertCiudadController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			// para obtener todos los paises registrados
			PaisDAOImp daoPais = PaisDAOImp.getInstance();
			ArrayList<Pais> selectPaises = daoPais.getAll();

			// para obtener todos los continentes
			ContinenteDAOImp daoCon = ContinenteDAOImp.getInstance();
			ArrayList<Continente> selectContinentes = daoCon.getAll();

			// Se obtiene la session creada
			HttpSession session = request.getSession();

			// Se pasan los atributos a la vista
			session.setAttribute("selectPaises", selectPaises);
			session.setAttribute("selectContinentes", selectContinentes);

			// se redirecciona
			response.sendRedirect("registro-ciudad.jsp");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
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
		
		
		//Recogemos las violaciones si es que las hay
		Set<ConstraintViolation<Continente>> violationsContinente = validator.validate(co);
		Set<ConstraintViolation<Pais>> violationsPais = validator.validate(p);
		Set<ConstraintViolation<Ciudad>> violationsCiudad = validator.validate(ci);
		
		
		//si existen violaciones para el continente se añaden a requeridos
		if (!violationsContinente.isEmpty()) {
			//se recogen los mensajes y se añaden al listado de requeridos
			for (ConstraintViolation<Continente> v : violationsContinente) {					
				requeridos.add("<p><b>Continente</b>: "  + v.getMessage() + "</p>");					
			}
		}
		
		//si existen violaciones para el pais se añaden a requeridos
		if (!violationsPais.isEmpty()) {
			//se recogen los mensajes y se añaden al listado de requeridos
			for (ConstraintViolation<Pais> v : violationsPais) {					
				requeridos.add("<p><b>Pais</b>: "  + v.getMessage() + "</p>");					
			}
		}
		
		//si existen violaciones para la ciudad se añaden a requeridos
		if (!violationsCiudad.isEmpty()) {
			//se recogen los mensajes y se añaden al listado de requeridos
			for (ConstraintViolation<Ciudad> v : violationsCiudad) {					
				requeridos.add("<p><b>Nombre ciudad</b>: "  + v.getMessage() + "</p>");					
			}
		}
		
		
		// si el array de requeridos contiene mensajes (violaciones) se mandan a la vista
		if (requeridos.size()!=0) {

			// se manda los mensajes para requeridos
			session.setAttribute("requeridos", requeridos);

			// se manda los campos escritos para que el usuario no pierda los datos
			session.setAttribute("nombreIntroducido", nombre);

			session.setAttribute("paisSeleccionado", pais);

			session.setAttribute("continenteSeleccionado", continente);

			// se redirecciona al formulario
			response.sendRedirect("registro-ciudad.jsp");

			// si no existen campos con violaciones 
		} else {
			
			
			// crud para operar contra la bbdd
			CiudadDAOImp daoCiudad = CiudadDAOImp.getInstance();

			// ejecucion de insert
			try {

				ci = daoCiudad.insert(ci);

				// si la insert va bien manda el siguiente mensaje
				alerta = new Alerta("success", "El registro se ha completado correctamente.");

				// se borra los atributos de la sesion al recargar la pagina
				session.removeAttribute("nombreIntroducido");

				session.removeAttribute("paisSeleccionado");

				session.removeAttribute("continenteSeleccionado");

			} catch (Exception e) {

				// se manda los campos escritos
				session.setAttribute("nombreIntroducido", nombre);

				session.setAttribute("paisSeleccionado", pais);

				session.setAttribute("continenteSeleccionado", continente);

				// si la insert va mal se manda el siguiente mensaje, e.getmessage lo manda la
				// insert
				alerta = new Alerta("danger", e.getMessage());

			} finally {

				// se manda el mensaje alerta
				session.setAttribute("alerta", alerta);

				// se redirecciona al mismo formulario
				response.sendRedirect("registro-ciudad.jsp");

			}
		}
	}

}
