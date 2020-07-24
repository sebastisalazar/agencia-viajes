package modelo.DAOImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.ConnectionManager;
import modelo.DAO.BookingDAO;
import modelo.pojo.Aerolinea;
import modelo.pojo.Booking;
import modelo.pojo.Ciudad;
import modelo.pojo.Usuario;

public class BookingDAOImp implements BookingDAO {

	private static BookingDAOImp INSTANCE = null;

	private BookingDAOImp(){
		super();
	}

	public static synchronized BookingDAOImp  getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new BookingDAOImp ();
		}

		return INSTANCE;
	}
	
	private final String SQL_GETALL_BYMONTHBOOKING="SELECT booking.id as id_booking," + 
			"id_ciudad,ciudad.nombre as nombre_ciudad," + 
			"id_usuario, usuarios.nombre as email_usuario," + 
			"id_aerolinea, aerolinea.nombre  as nombre_aerolinea," + 
			"fecha_booking, fecha_partida," + 
			"fecha_vuelta " + 
			"FROM booking, aerolinea, ciudad,usuarios " + 
			"WHERE booking.id_aerolinea =aerolinea.id AND " + 
			"booking.id_ciudad = ciudad.id AND " + 
			"booking.id_usuario =usuarios.id AND " + 
			"MONTH(fecha_booking)=? AND " + 
			"id_usuario = ?;";
	
	private final String SQL_GETALL_BYFLIGHTSTOTAKE="SELECT booking.id as id_booking,"
			+ "id_ciudad,"
			+ "ciudad.nombre as nombre_ciudad,"
			+ "id_usuario,"
			+ "usuarios.nombre as email_usuario,id_aerolinea,"
			+ "aerolinea.nombre  as nombre_aerolinea,fecha_booking,"
			+ "fecha_partida,fecha_vuelta, cancelado "
			+ "FROM booking, aerolinea, ciudad,usuarios " + 
			" WHERE booking.id_aerolinea =aerolinea.id " + 
			" AND booking.id_ciudad = ciudad.id " + 
			" AND booking.id_usuario =usuarios.id " + 
			" AND id_usuario =? " + 
			" AND MONTH(fecha_partida) >=MONTH (NOW())"
			+ "AND cancelado=0;";
	private final String SQL_GETALL_BYFLIGHTSTAKEN="SELECT booking.id as id_booking,"
			+ "id_ciudad,"
			+ "ciudad.nombre as nombre_ciudad,"
			+ "id_usuario,"
			+ "usuarios.nombre as email_usuario,id_aerolinea,"
			+ "aerolinea.nombre  as nombre_aerolinea,fecha_booking,"
			+ "fecha_partida,fecha_vuelta "
			+ "FROM booking, aerolinea, ciudad,usuarios " + 
			" WHERE booking.id_aerolinea =aerolinea.id " + 
			" AND booking.id_ciudad = ciudad.id " + 
			" AND booking.id_usuario =usuarios.id " + 
			" AND id_usuario = ? " + 
			" AND MONTH(fecha_partida) <=MONTH (NOW());";
	
	private final String SQL_GETALL="SELECT booking.id as id_booking," + 
			"id_ciudad,ciudad.nombre as nombre_ciudad," + 
			"id_usuario, usuarios.nombre as email_usuario," + 
			"id_aerolinea, aerolinea.nombre  as nombre_aerolinea," + 
			"fecha_booking, fecha_partida," + 
			"fecha_vuelta " + 
			"FROM booking, aerolinea, ciudad,usuarios " + 
			"WHERE booking.id_aerolinea =aerolinea.id AND " + 
			"booking.id_ciudad = ciudad.id AND " + 
			"booking.id_usuario =usuarios.id AND "+ 
			"id_usuario = ?;";
	
	private final String SQL_GETALL_BYCANCELLEDFLIGHTS="SELECT booking.id as id_booking,"
			+ "id_ciudad,"
			+ "ciudad.nombre as nombre_ciudad,"
			+ "id_usuario,"
			+ "usuarios.nombre as email_usuario,id_aerolinea,"
			+ "aerolinea.nombre  as nombre_aerolinea,fecha_booking,"
			+ "fecha_partida,fecha_vuelta, cancelado "
			+ "FROM booking, aerolinea, ciudad,usuarios " + 
			" WHERE booking.id_aerolinea =aerolinea.id " + 
			" AND booking.id_ciudad = ciudad.id " + 
			" AND booking.id_usuario =usuarios.id " + 
			" AND id_usuario =? " + 
			" AND MONTH(fecha_partida) >=MONTH (NOW())"
			+ "AND cancelado=1;";
	
	
	

	@Override
	public Booking getById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Booking delete(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Booking insert(Booking pojo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Booking update(Booking pojo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Booking> getAllByBookingMonth(String mes, Usuario usu) throws Exception {
		
		ArrayList<Booking> listaBookings=null;
		
		try (
			Connection con= ConnectionManager.getConnection();
			PreparedStatement pst=con.prepareStatement(SQL_GETALL_BYMONTHBOOKING)
			
		){
			pst.setString(1,mes);
			pst.setInt(2, usu.getId());
			
			try(ResultSet rs= pst.executeQuery()){
				
				while (rs.next()) {
					listaBookings= new ArrayList<Booking>();
					listaBookings.add(mapper(rs));
					
				}
				
				if (listaBookings==null) {
					throw new Exception("No se han encontrado bookings para este mes");
				}
				
				
			}
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		return listaBookings;
	}

	

	@Override
	public ArrayList<Booking> getAllByUser(Usuario usu) throws Exception {
		
		ArrayList<Booking> listaBookings= new ArrayList<Booking>();
		
		try (
			Connection con= ConnectionManager.getConnection();
			PreparedStatement pst=con.prepareStatement(SQL_GETALL);
			
		){
			pst.setInt(1,usu.getId());
			
			try(ResultSet rs= pst.executeQuery()){
				
				while (rs.next()) {
					listaBookings.add(mapper(rs));
				}
	
			}
			
		} catch (Exception e) {
			throw new Exception("Error, "+e.getMessage());
		}
		
		return listaBookings;
	}

	@Override
	public ArrayList<Booking> getAllByCiudad(String ciudad) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Booking> getAllByFechas(String f1, String f2) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Booking> getAllByBookingYear(String year, Usuario usu) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Booking> getAllByBookingPartida(String partida) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Booking mapper(ResultSet rs) throws SQLException {
		
		Usuario u= new Usuario();
		Ciudad ci= new Ciudad();
		Aerolinea ae= new Aerolinea();
		Booking bo= new Booking();
		
		ci.setId(rs.getInt("id_ciudad"));
		ci.setNombre(rs.getString("nombre_ciudad"));
		
		u.setId(rs.getInt("id_usuario"));
		u.setEmail(rs.getString("email_usuario"));
		
		ae.setId(rs.getInt("id_aerolinea"));
		ae.setNombre(rs.getString("nombre_aerolinea"));
		
		bo.setId(rs.getInt("id_booking"));
		bo.setFecha_booking(rs.getString("fecha_booking"));
		bo.setFecha_partida(rs.getString("fecha_partida"));
		bo.setFecha_vuelta((rs.getString("fecha_vuelta")==null)?"sin vuelta":rs.getString("fecha_vuelta"));
		
		bo.setCi(ci);
		bo.setU(u);
		bo.setAe(ae);
		
		return bo;
		
	}

	//METODO HEREDADO DEL CRUDABLE
	@Override
	public ArrayList<Booking> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Booking> getAllFlightsToTake(Usuario usu) throws Exception {
		
		ArrayList<Booking> listaBookings= new ArrayList<Booking>();
		
		try (
			Connection con= ConnectionManager.getConnection();
			PreparedStatement pst=con.prepareStatement(SQL_GETALL_BYFLIGHTSTOTAKE);
			
		){
			pst.setInt(1,usu.getId());
			
			try(ResultSet rs= pst.executeQuery()){
				
				while (rs.next()) {
					listaBookings.add(mapper(rs));
				}
				
				
			}
			
		} catch (Exception e) {
			throw new Exception("Error, "+e.getMessage());
		}
		
		return listaBookings;
	}

	@Override
	public ArrayList<Booking> getAllFlightsTaken(Usuario usu) throws Exception {
		
		ArrayList<Booking> listaBookings= new ArrayList<Booking>();
		
		try (
			Connection con= ConnectionManager.getConnection();
			PreparedStatement pst=con.prepareStatement(SQL_GETALL_BYFLIGHTSTAKEN);
			
		){
			pst.setInt(1,usu.getId());
			
			try(ResultSet rs= pst.executeQuery()){
				
				while (rs.next()) {
					listaBookings.add(mapper(rs));
				}
				
				
			}
			
		} catch (Exception e) {
			throw new Exception("Error, "+e.getMessage());
		}
		
		return listaBookings;
	}

	@Override
	public ArrayList<Booking> getAllCancelledFlights(Usuario usu) throws Exception {
		
		ArrayList<Booking> listaBookings= new ArrayList<Booking>();
		
		try (
			Connection con= ConnectionManager.getConnection();
			PreparedStatement pst=con.prepareStatement(SQL_GETALL_BYCANCELLEDFLIGHTS);
			
		){
			pst.setInt(1,usu.getId());
			
			try(ResultSet rs= pst.executeQuery()){
				
				while (rs.next()) {
					listaBookings.add(mapper(rs));
				}
				
				
			}
			
		} catch (Exception e) {
			throw new Exception("Error, "+e.getMessage());
		}
		
		return listaBookings;
	}



	

}
