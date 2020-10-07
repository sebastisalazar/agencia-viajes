/**
 * Hereda métodos de la interfaz CrudAble y añade los siguientes metodos
 * 
 * getAllByBookingMonth
 * getAllFlightsToTake
 * getAllFlightsTaken
 * getAllCancelledFlights
 * getAllByBookingYear
 * getAllByUser
 * getAllByCiudad
 * getAllByBookingPartida
 * getAllByFechas
 * 
 * @author Sebastian
 * @version 1.0 
 * * */

package modelo.DAO;

import java.util.ArrayList;
import modelo.CrudAble;
import modelo.pojo.Booking;
import modelo.pojo.Usuario;


public interface BookingDAO extends CrudAble<Booking> {

	/**
	 * Obtiene los vuelos hecho por mes por un usuario
	 * @param mes Mes en el que se buscará
	 * @param usu Usuario al que ha realizados los vuelos
	 * @return {@code ArrayList<Booking>} Lista de vuelos
	 * @throws Exception Si existe algun error al ejecutar la query o no encuentra bookings
	 */
	ArrayList<Booking> getAllByBookingMonth( String mes, Usuario usu) throws Exception;
	
	/**
	 * Obtiene los proximos vuelos por usuario
	 * @param usu Usuario que tomará los vuelos
	 * @return {@code ArrayList<Booking>}  Lista de bookings
	 * @throws Exception Si existe algun error al ejecutar la query
	 */
	ArrayList<Booking> getAllFlightsToTake(Usuario usu) throws Exception;
	
	/**
	 * Obtiene vuelos con fecha pasadas por usuario
	 * @param usu Usuario que ha realizado los vuelos
	 * @return {@code ArrayList<Booking>} Lista de bookings
	 * @throws Exception Si existe algun error al ejecutar la query
	 */
	ArrayList<Booking> getAllFlightsTaken(Usuario usu) throws Exception;
	
	/**
	 * Obtiene vuelos cancelados por un usuario
	 * @param usu Usuario que ha cancelado los vuelos
	 * @return {@code ArrayList<Booking>} Lista de bookings
	 * @throws Exception Si existe algun error al ejecutar la query
	 */
	ArrayList<Booking> getAllCancelledFlights(Usuario usu) throws Exception;
	
	/**
	 * Obtiene vuelos realizados en un año concreto por un usuario
	 * @param year Año en el que se buscara
	 * @param usu  Usuario que ha relaizado los vuelos
	 * @return {@code ArrayList<Booking>} Lista de bookings
	 * @throws Exception Si existe algun error al ejecutar la query
	 */
	ArrayList<Booking> getAllByBookingYear( String year, Usuario usu ) throws Exception;
	
	/**
	 * Obtiene todos los vuelos(tomados,cancelados y por tomar) de un usuario
	 * @param usu Usuario que ha realizado los vuelos
	 * @return {@code ArrayList<Booking>} Lista de bookings
	 * @throws Exception Si existe algun error al ejecutar la query
	 */
	ArrayList<Booking> getAllByUser( Usuario usu ) throws Exception;
	
	/**
	 * Obtiene todos los vuelos realizados por distintos usuarios en una ciudad
	 * @param ciudad Ciudad en la que se buscará
	 * @return {@code ArrayList<Booking>} Lista de bookings
	 * @throws Exception Si existe algun error al ejecutar la query
	 */
	ArrayList<Booking> getAllByCiudad( String ciudad ) throws Exception;
	
	/**
	 * Obtiene todos los vuelos que han tomado o van a ser tomados en una fecha concreta
	 * @param partida Fecha exacta de todos los vuelos que parten o han partido
	 * @return {@code ArrayList<Booking>} Lista de bookings
	 * @throws Exception Si existe algun error al ejecutar la query* @return 
	 */
	ArrayList<Booking> getAllByBookingPartida( String partida ) throws Exception;
	
	/**
	 * Obtiene todos los vuelos realizados entre un periodo de fechas
	 * @param f1 fecha exacta desde cuando empezara a buscarse vuelos
	 * @param f2 fecha exacta de hasta cuando buscará vuelos
	 * @return {@code ArrayList<Booking>} Lista de bookings
	 * @throws Exception Si existe algun error al ejecutar la query* @return 
	 */
	ArrayList<Booking> getAllByFechas( String f1, String f2 ) throws Exception;
	
}
