package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CiudadDAOImp implements CiudadDAO {

	private static CiudadDAOImp INSTANCE = null;

	private CiudadDAOImp() {
		super();
	}

	public static synchronized CiudadDAOImp getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new CiudadDAOImp();
		}

		return INSTANCE;
	}

	private final String SQL_GETALL = "SELECT ciudad.id as ciudad_id,ciudad.nombre as ciudad_nombre," + 
			"ciudad.pais as ciudad_pais," + 
			"ciudad.continente as ciudad_continente," + 
			"pais.nombre as pais_nombre," + 
			"pais.bandera as pais_bandera," + 
			"continente.nombre as continente_nombre " + 
			"FROM agencia_viajes.ciudad " + 
			"INNER JOIN agencia_viajes.pais ON ciudad.pais = pais.id " + 
			"INNER JOIN agencia_viajes.continente ON ciudad.continente = continente.id;";

	private final String SQL_INSERT = "INSERT INTO agencia_viajes.ciudad (nombre, pais, continente) VALUES(?, ?, ?);";
	private final String SQL_GETBYID="SELECT id, nombre, pais, continente FROM agencia_viajes.ciudad WHERE id=?;";
	private final String SQL_UPDATE="UPDATE agencia_viajes.ciudad SET nombre=?, pais=?, continente=? WHERE id=?;";

	@Override
	public ArrayList<Ciudad> getAll() throws Exception {

		// lista para guardar todos los paises
		ArrayList<Ciudad> listaCiudades = new ArrayList<Ciudad>();

		// conecta con la base de datos
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GETALL);
				ResultSet rs = pst.executeQuery();

		) {
			// lee linea por linea
			while (rs.next()) {

				// Se encia a la funcion mapper para que asigne los datos segun el objeto y se
				// agrega a la lista
				listaCiudades.add(mapperALL(rs));

			}

		} catch (Exception e) {

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
			
			try(ResultSet rs= pst.executeQuery()){
				
				if (rs.next()) {
					ci=mapperCiudad(rs);
				}else {
					throw new Exception("Usuario no encontrado id =" + id);
				}
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return ci;
	}

	@Override
	public Ciudad delete(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
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
				throw new Exception("\nLo sentimos, " + (pojo.getNombre()).toUpperCase() + "\n"
						+ " ya está registrado en el pais seleccionado.");
			}

		} catch (Exception e) {
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
			
			int insertado=pst.executeUpdate();
			
			if (insertado==2) {
				throw new Exception("Lo sentimos pero la ciudad "+pojo.getNombre()+" con id "+pojo.getId()+"no esta registrado");
				
			}
			
		}catch (Exception duplicatedQException) {
			throw new Exception("Lo sentimos pero ya existe la ciudad "+pojo.getNombre().toUpperCase()+" para el pais seleccionado");
		
		}
		
		return pojo;
	}

	@Override
	public ArrayList<Ciudad> getAllByNombre(String palabraBuscada) throws Exception {
		// TODO Auto-generated method stub
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

		co.setNombre(rs.getString("continente_nombre"));
		co.setId(rs.getInt("ciudad_continente"));
		ci.setId(rs.getInt("ciudad_id"));
		ci.setNombre(rs.getString("ciudad_nombre"));
		p.setId(rs.getInt("ciudad_pais"));
		p.setNombre(rs.getString("pais_nombre"));
		p.setBandera(rs.getString("pais_bandera"));
		p.setContinente(co);

		ci.setPais(p);
		ci.setContinente(co);

		return ci;

	}
	
	private Ciudad mapperCiudad(ResultSet rs) throws SQLException {

		// creacion de objtetos para recoger los atributos de la tabla
		Ciudad ci = new Ciudad();
		Pais p = new Pais();
		Continente co = new Continente();

		// asercion de objetos segun el dato
		co.setId(rs.getInt("continente"));
		ci.setId(rs.getInt("id"));
		ci.setNombre(rs.getString("nombre"));
		p.setId(rs.getInt("pais"));
		p.setContinente(co);

		ci.setPais(p);
		ci.setContinente(co);

		return ci;

	}
	
	
}
