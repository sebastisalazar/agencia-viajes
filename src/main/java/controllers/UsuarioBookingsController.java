package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.DAOImp.BookingDAOImp;
import modelo.pojo.Booking;
import modelo.pojo.Usuario;

/**
 * Servlet implementation class UsuarioBookingsController
 */
@WebServlet({"/views/frontoffice/inicio","/views/frontoffice/proximos-vuelos","/views/frontoffice/vuelos-cogidos"})
public class UsuarioBookingsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
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
			
			try {
				
				listabooking=daoBooking.getAllByUser(usu);
				session.setAttribute("listabooking", listabooking);
				
			} catch (Exception e) {
				
				alerta= new Alerta("danger", "Lo sentimos, "+e.getMessage());
				session.setAttribute("alerta", alerta);
				
			}finally{
				
				response.sendRedirect("views/frontoffice/index.jsp");
			}
			
		}else if (("/views/frontoffice/proximos-vuelos").equalsIgnoreCase(url)) {
			
			try {
				
				listabooking=daoBooking.getAllFlightsToTake(usu);
				session.setAttribute("listaProximos", listabooking);
				
			} catch (Exception e) {
				
				alerta= new Alerta("danger", "Lo sentimos, "+e.getMessage());
				session.setAttribute("alerta", alerta);
				
			}finally{
				
				response.sendRedirect("proximos-vuelos.jsp");
			}
			
		}else if (("/views/frontoffice/vuelos-cogidos").equalsIgnoreCase(url)) {
			
			try {
				
				listabooking=daoBooking.getAllFlightsTaken(usu);
				session.setAttribute("listaCogidos", listabooking);
				
			} catch (Exception e) {
				
				alerta= new Alerta("warning", "Lo sentimos, "+e.getMessage()+" a la fecha actual");
				session.setAttribute("alerta", alerta);
				
			}finally{
				
				response.sendRedirect("vuelos-cogidos.jsp");
			}
		}
		
			
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
