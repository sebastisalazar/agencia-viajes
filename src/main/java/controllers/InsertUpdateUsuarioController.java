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

import org.apache.log4j.Logger;

import modelo.DAOImp.PaisDAOImp;
import modelo.DAOImp.UsuarioDAOImp;
import modelo.pojo.Pais;
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
	private final static Logger LOG = Logger.getLogger(InsertUpdateUsuarioController.class);

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

			LOG.info("LLama al controlador crear-usuario ");
			
			// para obtener todos los paises registrados
			PaisDAOImp daoPais = PaisDAOImp.getInstance();
			ArrayList<Pais> selectPaises;
			try {
				LOG.debug("Obteniendo listado de paises");
				selectPaises = daoPais.getAll();
				// Se pasan los atributos a la vista
				session.setAttribute("selectPaises", selectPaises);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				// se redirecciona
				response.sendRedirect("views/usuario/crear-usuario.jsp");
			}

			// si no es que llama a update
		} else {
			
			LOG.debug("LLama al controlador actualizar-usuario ");
			// Se recoge el id en version string y se guarda en variable
			String ids = request.getParameter("id");
			// se transforma el id regido en string y se pasa a int
			int id = Integer.parseInt(ids);

			// crud para operar contra bbdd
			UsuarioDAOImp daoUsuario = UsuarioDAOImp.getInstance();

			// select by id
			try {

				// obtiene los datos de la base de datos sedun el id recogido
				LOG.debug("Comprueba si el usuario a editar existe en la BBDD ");
				Usuario u = daoUsuario.getById(id);

				// se manda los campos escritos para que la vista los conserve y rellene los
				// campos
				session.setAttribute("usuarioEditar",u);

				// se redirecciona al formulario para mostrar lo que se va a editar
				response.sendRedirect("views/usuario/actualizar-usuario.jsp?id=" + id);

			} catch (Exception e) {
				
				LOG.error(e);
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
		
		//obtenemos el usuario logeado
		Usuario u2= (Usuario) session.getAttribute("loginUsuario");

		// creacion de objeto alerta
		Alerta alerta = null;
		
		// Array para mostrar mensajes para campos requeridos
		ArrayList<String> requeridos = new ArrayList<>();

		// Guardamos los datos recogidos del formulario
		String email = request.getParameter("email");
		
		String password = request.getParameter("password");
		
		String direccion= request.getParameter("direccion");
		
		String dnie= request.getParameter("dnie");
		
		String nombre=request.getParameter("nombre");
		
		String ape1=request.getParameter("ape1");
		
		String ape2=request.getParameter("ape2");
		
		String pais=request.getParameter("pais");
		
		int paisid=0;
		
		
		
		String id_rol= request.getParameter("rol");

		// recoge el id por url cuando sea para actualizar
		String id = request.getParameter("id");
		
		
		//campo contraseña
		String password1=request.getParameter("password1");
		
		String password2=request.getParameter("password2");
		
		//si no hay sesion iniciada significara que tiene que tiene que elegir un pais
		if (u2==null) {
					paisid=Integer.parseInt(pais.substring(0, pais.indexOf(",")));
		}else {
			
			if (u2.getRol().getId()==1) {
				if (!("").equalsIgnoreCase(password1) && !("").equalsIgnoreCase(password2)) {
					 
					 if(password1.equalsIgnoreCase(password2)) {
						 
						 if(!password1.equalsIgnoreCase(password)) {
							 password=password1; 
						 }
							 
					 }else {
						 
						requeridos.add("Los campos de nueva contraseña no coinciden");
						 
					 }
					 
				 }//fin if
			}
			
			
		}
		
		 

		 
		 
		// se manda los campos escritos para que el usuario no pierda los datos
		
		
		session.setAttribute("nombreIntroducido", nombre);

		session.setAttribute("ape1Introducido", ape1);
		
		session.setAttribute("ape2Introducido", ape2);

		session.setAttribute("paisIntroducido", pais);
		
		session.setAttribute("dnieIntroducido", dnie);
		
		session.setAttribute("emailIntroducido", email);
		
		session.setAttribute("direccionIntroducido", direccion);

		session.setAttribute("passwordIntroducido", password);

		session.setAttribute("rolSeleccionado", id_rol);
		
		
		
		

		// crud para operar contra la bbdd
		UsuarioDAOImp daoUsuario = UsuarioDAOImp.getInstance();

		// creacion de objetos para asignar asignar los datos del formulario
		Usuario u= new Usuario();
		Rol rol= new Rol();

		// Se guardan los atributos en los objetos
		
		u.setEmail(email);
		u.setPassword(password);
		u.setRol(rol);
		u.setNombre(nombre);
		u.setApe1(ape1);
		u.setApe2(ape2);
		u.setDNI_NIE(dnie);
		u.setResidencia(direccion);
		
		//si no hay sesion iniciada significara que tiene que tiene que elegir un pais
		if (u2==null) {
			u.setNacionalidad(paisid);
			
			// Recogemos las violaciones si es que las hay
			Set<ConstraintViolation<Usuario>> violationsUsuario = validator.validate(u);

			// si existen violaciones para la ciudad se añaden a requeridos
			if (!violationsUsuario.isEmpty()) {
				// se recogen los mensajes y se añaden al listado de requeridos
				for (ConstraintViolation<Usuario> v : violationsUsuario) {
					requeridos.add("<p><b>"+v.getPropertyPath()+"</b>: " + v.getMessage() + "</p>");
				}
			}
		}
		

		

// INSERT***************************************************************************************************	
		try {

			if (llamaACrear) {
				
				LOG.info("LLama a crear usuario mediante formulario");

				// se manda los campos escritos para que el usuario no pierda los datos
				session.setAttribute("emailIntroducidoC", email);

				session.setAttribute("passwordIntroducidoC", password);

				session.setAttribute("rolSeleccionadoC", id_rol);
				
				session.setAttribute("nombreIntroducidoC", nombre);

				session.setAttribute("ape1IntroducidoC", ape1);
				
				session.setAttribute("ape2IntroducidoC", ape2);

				session.setAttribute("paisIntroducidoC", paisid);
				
				session.setAttribute("dnieIntroducidoC", dnie);
				
				session.setAttribute("direccionIntroducidoC", direccion);

				if (requeridos.size() != 0) {

					// se manda los mensajes para requeridos
					LOG.info("No se han rellenado todos los campos");
					session.setAttribute("requeridos", requeridos);

					response.sendRedirect("views/usuario/crear-usuario.jsp");
					// si existen errores pero la url no contiene ningun parametro será para crear
				} else {
			
					LOG.info("Registrando datos del usuario en la BBDD (insert)");
					daoUsuario.insert(u);
					
					
					session.removeAttribute("nombreIntroducidoC");

					session.removeAttribute("ape1IntroducidoC");
					
					session.removeAttribute("ape2IntroducidoC");

					session.removeAttribute("paisIntroducidoC");
					
					session.removeAttribute("dnieIntroducidoC");
					
					session.removeAttribute("emailIntroducidoC");

					session.removeAttribute("passwordIntroducidoC");

					session.removeAttribute("rolSeleccionadoC");
					
					session.removeAttribute("direccionIntroducidoC");

					// si la insert va bien manda el siguiente mensaje
					alerta = new Alerta("success", "El usuario se ha creado correctamente.");
					
					response.sendRedirect("views/login.jsp");

				}

				
// UPDATE ***************************************************************************************************				
			} else {
				
				LOG.info("LLama a actualizar usuario mediante formulario");
				
				if (requeridos.size() != 0) {
					
					LOG.info("No se han rellenado todos los campos");
					// se manda los mensajes para requeridos
					session.setAttribute("requeridos", requeridos);

					// si existen errores pero la url no contiene ningun parametro será para crear
				} else {

					// por seguridad comprobamos si no es null
					if (id == null) {
						LOG.debug("Se ha intentado actualizar con id de usuario no valido en la URL");
						response.sendRedirect("listado-ciudades");
					}

					// antes de ejecutar el update cogemos el id pasado por url y se lo asignamos al
					// objeto
					u.setId(Integer.parseInt(id));
					
					//comprobacion si el usuario logeado es admin o usuario normal
					
					if (u2.getRol().getId()==2) {
						u.setRol( new Rol(Integer.parseInt(id_rol)));
					}else {
						u.setRol(new Rol(1));
					}
					
					
					// ejecucion update
					LOG.info("Iniciando actualizacion (update) de usuario");
					daoUsuario.update(u);

					// si la insert va bien manda el siguiente mensaje
					alerta = new Alerta("success", "El usuario se ha actualizado correctamente.");

				} // fin requeridos INSERT

				if (u2.getRol().getId()==2) {
					response.sendRedirect("listado-usuarios");
				}else {
					response.sendRedirect("views/frontoffice/datos-usuario.jsp");
				}
				
			} // FIN try

			// Si no salta ninguna excepcion al insertar o actualizar, se borran los campos
			// que se hayan escrito para que no vuelvan a aparecer en los input
			session.removeAttribute("emailIntroducido");

			session.removeAttribute("passwordIntroducido");

			session.removeAttribute("rolSeleccionado");
			

		} catch (Exception e) {

			requeridos.add(e.getMessage());
			
			LOG.debug(e);

			// si existe excepcion y se llamo por servlet crear-ciudad se redirige de vuelta
			// alli
			if (llamaACrear) {
				
				LOG.info("Error debido a una excepcion en la pagina crear-usuario");
				response.sendRedirect("crear-usuario.jsp");
				
				// si no quiere decir que es updte/actualizar ciudad
			} else {
				LOG.info("Error debido a una excepcion en la pagina actualizar-usuario");
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
