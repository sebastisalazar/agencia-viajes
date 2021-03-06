package modelo.DAOImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import listener.InicioAppListenner;
import modelo.ConnectionManager;
import modelo.DAO.CiudadDAO;
import modelo.pojo.Ciudad;
import modelo.pojo.Continente;
import modelo.pojo.Pais;

public class CiudadDAOImp implements CiudadDAO {

	private static CiudadDAOImp INSTANCE = null;
	private final static Logger LOG = Logger.getLogger(InicioAppListenner.class);

	private CiudadDAOImp() {
		super();
	}

	public static synchronized CiudadDAOImp getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new CiudadDAOImp();
		}

		return INSTANCE;
	}

	private final String SQL_INSERT = "INSERT INTO agencia_viajes.ciudad (nombre, pais, continente) VALUES(?, ?, ?);";
	private final String SQL_UPDATE="UPDATE agencia_viajes.ciudad SET nombre=?, pais=?, continente=? WHERE id=?;";
	private final String SQL_DELETE="DELETE FROM agencia_viajes.ciudad WHERE id=?;";
	private final String SQL_GETLAST="SELECT ciudad.id as ciudad_id,ciudad.nombre as ciudad_nombre," + 
											"ciudad.pais as ciudad_pais," + 
											"ciudad.continente as ciudad_continente," +
											"ciudad.portada as ciudad_portada,"+
											"pais.nombre as pais_nombre," + 
											"pais.nombre_corto as pais_nombrecorto," + 
											"pais.bandera as pais_bandera," +
											"continente.nombre as continente_nombre " + 
											"FROM agencia_viajes.ciudad " + 
											"INNER JOIN agencia_viajes.pais ON ciudad.pais = pais.id " + 
											"INNER JOIN agencia_viajes.continente ON ciudad.continente = continente.id "+
											"ORDER BY ciudad.id DESC LIMIT ? ;";
	
	
	
	private final String SQL_GETBYID="SELECT ciudad.id as ciudad_id,ciudad.nombre as ciudad_nombre," + 
			"ciudad.pais as ciudad_pais," + 
			"ciudad.continente as ciudad_continente," + 
			"ciudad.portada as ciudad_portada,"+
			"pais.nombre as pais_nombre," +
			"pais.nombre_corto as pais_nombrecorto," + 
			"pais.bandera as pais_bandera," +
			"continente.nombre as continente_nombre " + 
			"FROM agencia_viajes.ciudad " + 
			"INNER JOIN agencia_viajes.pais ON ciudad.pais = pais.id " + 
			"INNER JOIN agencia_viajes.continente ON ciudad.continente = continente.id "+
			"WHERE ciudad.id=?;";
	private final String SQL_GETALL = "SELECT ciudad.id as ciudad_id,ciudad.nombre as ciudad_nombre," + 
			"ciudad.pais as ciudad_pais," + 
			"ciudad.continente as ciudad_continente," + 
			"ciudad.portada as ciudad_portada,"+
			"pais.nombre as pais_nombre," +
			"pais.nombre_corto as pais_nombrecorto," + 
			"pais.bandera as pais_bandera," +
			"continente.nombre as continente_nombre " + 
			"FROM agencia_viajes.ciudad " + 
			"INNER JOIN agencia_viajes.pais ON ciudad.pais = pais.id " + 
			"INNER JOIN agencia_viajes.continente ON ciudad.continente = continente.id;";
	
	private final String SQL_SELECTBYPAIS= "SELECT ciudad.id as ciudad_id,ciudad.nombre as ciudad_nombre," + 
			"ciudad.pais as ciudad_pais," + 
			"ciudad.continente as ciudad_continente," + 
			"ciudad.portada as ciudad_portada,"+
			"pais.nombre as pais_nombre," + 
			"pais.id as pais_id," +
			"pais.nombre_corto as pais_nombrecorto," + 
			"pais.bandera as pais_bandera," +
			"continente.nombre as continente_nombre " + 
			"FROM agencia_viajes.ciudad " + 
			"INNER JOIN agencia_viajes.pais ON ciudad.pais = pais.id " + 
			"INNER JOIN agencia_viajes.continente ON ciudad.continente = continente.id "+
			"WHERE ciudad.pais=?;";
			
			

	

	@Override
	public ArrayList<Ciudad> getAll() throws Exception {

		// lista para guardar todos los paises
		ArrayList<Ciudad> listaCiudades = new ArrayList<Ciudad>();

		// conecta con la base de datos
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GETALL);
				ResultSet rs = pst.executeQuery();

		) {
			LOG.debug(pst);
			// lee linea por linea
			while (rs.next()) {

				// Se encia a la funcion mapper para que asigne los datos segun el objeto y se
				// agrega a la lista
				listaCiudades.add(mapperALL(rs));

			}
			
			

		} catch (Exception e) {
			LOG.error(e);
			e.printStackTrace();

		}

		return listaCiudades;
	}

	@Override
	public Ciudad getById(int id) throws Exception {

		
		Ciudad ci= new Ciudad();
		// conecta con la base de datos
		try (
			Connection con = ConnectionManager.getConnection();
			PreparedStatement pst = con.prepareStatement(SQL_GETBYID);
		) {
			
			pst.setInt(1,id);
			LOG.debug(pst);
			try(ResultSet rs= pst.executeQuery()){
				
				if (rs.next()) {
					ci=mapperALL(rs);
				}else {
					throw new Exception("Usuario no encontrado id =" + id);
				}
			}

		} catch (Exception e) {
			LOG.error(e);
			e.printStackTrace();

		}

		return ci;
	}

	@Override
	public Ciudad delete(int id) throws Exception {
		
		Ciudad ci= new Ciudad();
		
		try(
			Connection con = ConnectionManager.getConnection();
			PreparedStatement pst=con.prepareStatement(SQL_DELETE);
		){
			ci=getById(id);
			pst.setInt(1, id);
			LOG.debug(pst);
			int borrado=pst.executeUpdate();
			
			if (borrado==2) {
				throw new Exception("Lo sentimos, la ciudad "+ci.getNombre()+" con id "+ci.getId()+" no está registrada");
			}
		}catch (Exception e) {
			LOG.error(e);
			throw new Exception(e.getMessage());
		}
		
		
		return ci;
		
	}

	@Override
	public Ciudad insert(Ciudad pojo) throws Exception {

		try (Connection con = ConnectionManager.getConnection();

				// RETURN_GENERATED_KEYS es para recuperar la ID que asignara la abse de datos
				// al nuevo registro
				PreparedStatement pst = con.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

		) {
			// se modifica la insert diciendo que el interrogante lo sustituya con el nombre
			// del objeto
			pst.setString(1, pojo.getNombre());
			pst.setInt(2, pojo.getPais().getId());
			pst.setInt(3, pojo.getContinente().getId());

			try {
				// insert
				LOG.debug(pst);
				int filaInsertada = pst.executeUpdate();

				// si ha insertado correctamente leemos el id asignado
				if (filaInsertada == 1) {

					// se lee el id asignado en la base de datos
					try (ResultSet rs = pst.getGeneratedKeys();) {
						if (rs.next()) {
							// se recoge en un int
							int generatedKey = rs.getInt(1);

							// se asigna el int al objeto
							pojo.setId(generatedKey);
						}

					}
				}

				// si captura una excepcion de typo sql la lanza
			} catch (Exception DBSQLException) {
				LOG.error(DBSQLException);
				throw new Exception("\nLo sentimos, " + (pojo.getNombre()).toUpperCase() + "\n"
						+ " ya está registrado en el pais seleccionado.");
			}

		} catch (Exception e) {
			LOG.error(e);
			// este lanzariía el mensaje del catch interno (Erro, ya existe...)
			throw new Exception(e.getMessage());
		}

		return pojo;

	}

	@Override
	public Ciudad update(Ciudad pojo) throws Exception {
		
		
		try(
			Connection con= ConnectionManager.getConnection();
			PreparedStatement pst=con.prepareStatement(SQL_UPDATE);
		){
			pst.setString(1, pojo.getNombre());
			pst.setInt(2, pojo.getPais().getId());
			pst.setInt(3, pojo.getContinente().getId());
			pst.setInt(4, pojo.getId());
			
		
			LOG.debug(pst);
				int insertado=pst.executeUpdate();
				if (insertado==2) {
					throw new Exception("Lo sentimos pero la ciudad "+pojo.getNombre()+" con id "+pojo.getId()+"no esta registrado");
					
				}
			
		}catch (Exception e) {
			LOG.error(e);
			throw new Exception("Lo sentimos pero ya existe la ciudad "+pojo.getNombre().toUpperCase()+" para el pais seleccionado");
		
		}
		
		return pojo;
	}

	@Override
	public ArrayList<Ciudad> getAllByNombre(String palabraBuscada) throws Exception {
	
		return null;
	}

	/**
	 * 
	 * @param rs Recibe una linea de registro para leer
	 * @return Objteto tipo ciudad con sus atributos rellenados
	 * @throws SQLException
	 */
	private Ciudad mapperALL(ResultSet rs) throws SQLException {

		// creacion de objtetos para recoger los atributos de la tabla
		Ciudad ci = new Ciudad();
		Pais p = new Pais();
		Continente co = new Continente();
		 
		// asercion de objetos segun el dato

		ci.setId(rs.getInt("ciudad_id"));
		ci.setNombre(rs.getString("ciudad_nombre"));
		p.setId(rs.getInt("ciudad_pais"));
		co.setId(rs.getInt("ciudad_continente"));
		ci.setPortada(rs.getString("ciudad_portada"));
		p.setNombre(rs.getString("pais_nombre"));
		p.setNombrecorto(rs.getString("pais_nombrecorto"));
		p.setBandera(rs.getString("pais_bandera"));
		co.setNombre(rs.getString("continente_nombre"));
		
		
		p.setContinente(co);

		ci.setPais(p);
		ci.setContinente(co);

		return ci;

	}
	

	@Override
	public ArrayList<Ciudad> getLast(int numRegistro) throws Exception {
		// lista para guardar todos los paises
				ArrayList<Ciudad> listaCiudades = new ArrayList<Ciudad>();

				// conecta con la base de datos
				try (Connection con = ConnectionManager.getConnection();
						PreparedStatement pst = con.prepareStatement(SQL_GETLAST);
						

				) {
					pst.setInt(1, numRegistro);
					LOG.debug(pst);
					try(ResultSet rs = pst.executeQuery();){
						
						// lee linea por linea
						while (rs.next()) {

							// Se encia a la funcion mapper para que asigne los datos segun el objeto y se
							// agrega a la lista
							listaCiudades.add(mapperALL(rs));

						}
					}
					

				} catch (Exception e) {
					LOG.error(e);
					e.printStackTrace();

				}

				return listaCiudades;
	}

	@Override
	public ArrayList<Ciudad> getAllByPais(int id) throws Exception {
		
		ArrayList<Ciudad> ciudades = new ArrayList<Ciudad>();
		
		try(
			Connection con= ConnectionManager.getConnection();
			PreparedStatement pst= con.prepareStatement(SQL_SELECTBYPAIS);	
		) {
			
			pst.setInt(1, id);
			LOG.debug(pst);
			try(ResultSet rs= pst.executeQuery()){
				
				while (rs.next()) {
					Ciudad ci= mapperALL(rs);
					ciudades.add(ci);
				}
				
			}
			
		} catch (Exception e) {
			LOG.error(e);
			throw new Exception(e.getMessage());
		}
		
		return ciudades;
	}
	
	
}
