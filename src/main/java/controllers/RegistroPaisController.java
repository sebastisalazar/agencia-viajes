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
import modelo.Pais;
import modelo.PaisDAOImp;

/**
 * Servlet implementation class RegistroPaisController
 */
@WebServlet("/registro-pais")
public class RegistroPaisController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistroPaisController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session= request.getSession();
		ArrayList<String> requeridos=new ArrayList<String>();
		String nombrePais=request.getParameter("paisnuevo");
		
		//Validacion del input nombre, minimo 3 caracteres 
		if (nombrePais.length()<3) {
			
			//añade el mensaje al array que va a pasar
			requeridos.add("El nombre es demasiado corto, debe contener mínimo 3 caracteres");
			
			//pasa el array a la vista 
			session.setAttribute("requeridos", requeridos);
			
			//redirecciona a la vista
			response.sendRedirect("registro-pais.jsp");
		
		//validacion del input nombre, maximo 100 caracteres
		}else if (nombrePais.length()>100) {
			
			//añade el mensaje al array que va a pasar
			requeridos.add("El nombre es demasiado largo, debe contener máximo 100 caracteres");
			
			//pasa el array a la vista 
			session.setAttribute("requeridos", requeridos);
			
			//redirecciona a la vista
			response.sendRedirect("registro-pais.jsp");
			
		//si el nombre no es mayor de 3 caracteres y menor de 100 entra por aquí
		}else {
			
			PaisDAOImp dao= PaisDAOImp.getInstance();
			Pais p= new Pais();
			Alerta alerta=null;		
			p.setName(nombrePais);
			
			try {
				
				//inserta el objeto pais recogiendo sus atributos y volcandolos a la base de datos
				p=dao.insert(p);
				
				//si se inserta correctamente se avisa al usuario
				alerta= new Alerta("success","El registro se ha insertado correctamente");
			} catch (Exception e) {
				
				//crea y atrapa el mensaje que devuelve el modelo cuando dé una excepcion
				alerta= new Alerta("danger",e.getMessage());
			}finally {
				
				//se pasa el mensaje del insert a la vista
				session.setAttribute("alerta",alerta);
				
				
				//se redirecciona a la vista
				response.sendRedirect("registro-pais.jsp");
				
			}
			
			
		}
		
		
		
		
	}

}
