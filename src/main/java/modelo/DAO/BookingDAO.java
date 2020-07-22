package modelo.DAO;

import java.util.ArrayList;
import modelo.CrudAble;
import modelo.pojo.Booking;
import modelo.pojo.Usuario;

public interface BookingDAO extends CrudAble<Booking> {

	
	ArrayList<Booking> getAllByBookingMonth( String mes, Usuario usu) throws Exception;
	ArrayList<Booking> getAllFlightsToTake(Usuario usu) throws Exception;
	ArrayList<Booking> getAllFlightsTaken(Usuario usu) throws Exception;
	ArrayList<Booking> getAllCancelledFlights(Usuario usu) throws Exception;
	ArrayList<Booking> getAllByBookingYear( String year, Usuario usu ) throws Exception;
	ArrayList<Booking> getAllByUser( Usuario usu ) throws Exception;
	ArrayList<Booking> getAllByCiudad( String ciudad ) throws Exception;
	ArrayList<Booking> getAllByBookingPartida( String partida ) throws Exception;
	ArrayList<Booking> getAllByFechas( String f1, String f2 ) throws Exception;
	
}
