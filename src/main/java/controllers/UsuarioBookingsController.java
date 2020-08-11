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

import modelo.DAOImp.BookingDAOImp;
import modelo.pojo.Booking;
import modelo.pojo.Usuario;

/**
 * Servlet implementation class UsuarioBookingsController
 */
@WebServlet({"/views/frontoffice/inicio","/views/frontoffice/proximos-vuelos","/views/frontoffice/vuelos-cogidos","/views/frontoffice/vuelos-cancelados","/views/frontoffice/mis-datos"})
public class UsuarioBookingsController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(UsuarioBookingsController.class);   
    
    public UsuarioBookingsController() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BookingDAOImp daoBooking= BookingDAOImp.getInstance();
		ArrayList<Booking> listabooking=new ArrayList<Booking>(); 
		
		HttpSession session= request.getSession();
		Usuario usu=(Usuario) session.getAttribute("loginUsuario");
		
		Alerta alerta= new Alerta();
		
		String url=request.getServletPath();
		
	
		if (("/views/frontoffice/inicio").equalsIgnoreCase(url)) {
			
			LOG.info("Iniciando controlador /views/frontoffice/inicio");
			try {
				
				
				int proximos=daoBooking.getAllFlightsToTake(usu).size();
				int tomados=daoBooking.getAllFlightsTaken(usu).size();
				int cancelados=daoBooking.getAllCancelledFlights(usu).size();
				
				
				session.setAttribute("proximos",proximos);
				session.setAttribute("tomados", tomados);
				session.setAttribute("cancelados", cancelados);
				
				LOG.info("Obteniendo listado de vuelos por usuario");
				listabooking=daoBooking.getAllByUser(usu);
				
				if (listabooking.size()==0) {
					alerta= new Alerta("warning", "No tienes vuelos previos registrados ");
					session.setAttribute("alerta", alerta);
				}
				
				session.setAttribute("listabooking", listabooking);
			
			} catch (Exception e) {
				
				LOG.error(e);
				alerta= new Alerta("danger", "Lo sentimos, "+e.getMessage());
				session.setAttribute("alerta", alerta);
				
			}finally{
				
				response.sendRedirect("views/frontoffice/index.jsp");
			}
			
		}else if (("/views/frontoffice/proximos-vuelos").equalsIgnoreCase(url)) {
			
			LOG.info("Iniciando controlador /views/frontoffice/inicio");
			try {
				
				LOG.info("Obteniendo listado de proximos vuelos por usuario");
				listabooking=daoBooking.getAllFlightsToTake(usu);
				
				if (listabooking.size()==0) {
					alerta= new Alerta("warning", "No tienes vuelos proximos registrados ");
					session.setAttribute("alerta", alerta);
				}
				
				session.setAttribute("listaProximos", listabooking);
				
			} catch (Exception e) {
				
				LOG.error(e);
				alerta= new Alerta("warning", "Lo sentimos, "+e.getMessage());
				session.setAttribute("alerta", alerta);
				
			}finally{
				
				response.sendRedirect("proximos-vuelos.jsp");
			}
			
		}else if (("/views/frontoffice/vuelos-cogidos").equalsIgnoreCase(url)) {
			
			
			LOG.info("Iniciando controlador /views/frontoffice/vuelos-cogidos");
			try {
				
				LOG.info("Obteniendo listado de vuelos cogidos por usuario");
				listabooking=daoBooking.getAllFlightsTaken(usu);
				
				if (listabooking.size()==0) {
					alerta= new Alerta("warning", "No tienes vuelos previos registrados ");
					session.setAttribute("alerta", alerta);
				}
				
				session.setAttribute("listaCogidos", listabooking);
				
			} catch (Exception e) {
				LOG.error(e);
				alerta= new Alerta("warning", "Lo sentimos, "+e.getMessage());
				session.setAttribute("alerta", alerta);
				
			}finally{
				
				response.sendRedirect("vuelos-cogidos.jsp");
			}
			
		}else if (("/views/frontoffice/vuelos-cancelados").equalsIgnoreCase(url)) {
			
			LOG.info("Iniciando controlador /views/frontoffice/vuelos-cancelados ");
			try {
				
				LOG.info("Obteniendo listado de vuelos cancelados por usuario");
				listabooking=daoBooking.getAllCancelledFlights(usu);
				
				if (listabooking.size()==0) {
					alerta= new Alerta("warning", "No tienes vuelos previos cancelados ");
					session.setAttribute("alerta", alerta);
				}
				
				session.setAttribute("listaCancelados", listabooking);
				
			} catch (Exception e) {
				
				LOG.error(e);
				alerta= new Alerta("warning", "Lo sentimos, "+e.getMessage());
				session.setAttribute("alerta", alerta);
				
			}finally{
				
				response.sendRedirect("vuelos-cancelados.jsp");
			}
			
		}
		
			
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
