package modelo.DAOImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import modelo.ConnectionManager;
import modelo.DAO.PaisDAO;
import modelo.pojo.Ciudad;
import modelo.pojo.Continente;
import modelo.pojo.Pais;

public class PaisDAOImp implements PaisDAO {
	
	
	private static PaisDAOImp INSTANCE = null;

	private PaisDAOImp() {
		super();
	}

	public static synchronized PaisDAOImp getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new PaisDAOImp();
		}

		return INSTANCE;
	}


	private final String SQL_GETALL="SELECT " +
			"pais.id as pais_id," + 
			"pais.nombre  as pais_nombre," + 
			"pais.bandera as pais_bandera," + 
			"pais.continente as pais_continente," + 
			"continente.nombre as continente_nombre," + 
			"continente.id as continente_id," + 
			"pais.nombre_corto as pais_nombrecorto " + 
			"FROM agencia_viajes.pais "+
			"INNER JOIN agencia_viajes.continente "+
			"ON agencia_viajes.pais.continente = agencia_viajes.continente.id;";
	
	
	private final String SQL_GETPAISESBYCONTINENTE="SELECT " + 
			"pais.id as pais_id," + 
			"pais.continente as continente_id," + 
			"pais.nombre_corto as pais_nombrecorto," + 
			"pais.bandera as pais_bandera," + 
			"COUNT(ciudad.nombre)  as ciudades," + 
			"pais.nombre as pais_nombre " + 
			"FROM ciudad " + 
			"RIGHT JOIN pais ON pais.id = ciudad.pais " + 
			"WHERE pais.continente=? " + 
			"GROUP BY pais.nombre ";
	
	private final String SQL_GETBYID="SELECT " + 
			"pais.id as pais_id," + 
			"pais.continente as continente_id," + 
			"pais.nombre_corto as pais_nombrecorto," + 
			"pais.bandera as pais_bandera," + 
			"COUNT(ciudad.nombre)  as ciudades," + 
			"pais.nombre as pais_nombre " + 
			"FROM ciudad " + 
			"RIGHT JOIN pais ON pais.id = ciudad.pais " + 
			"WHERE pais.id=? " + 
			"GROUP BY pais.nombre ";
	

	private final String UPDATE_BANDERA="UPDATE agencia_viajes.pais SET bandera=? WHERE id=?;";
	private final String SQL_UPDATE="UPDATE agencia_viajes.pais SET nombre=?, continente=?, nombre_corto=? WHERE id=?;";
	private final String SQL_INSERT="INSERT INTO agencia_viajes.pais (nombre, continente, nombre_corto) VALUES(?, ?, ?);";
	private final String SQL_DELETE="DELETE FROM agencia_viajes.pais WHERE id=?";
	
	@Override
	public ArrayList<Pais> getAll() throws Exception {
		
		//lista para guardar los paises
		ArrayList<Pais> paises = new ArrayList<Pais>();
		
		//recursos y conexiones
		try(
			Connection con= ConnectionManager.getConnection();
			PreparedStatement pst= con.prepareStatement(SQL_GETALL);
			ResultSet rs=pst.executeQuery();
		){
			//mientras existan registros 
			while (rs.next()) {
				
				//por cada linea leida crea un  objeto vacio
				Pais p= mapper(rs);
				
				//se añade a la lista
				paises.add(p);
			}
			
		}catch (Exception e) {
			
			//si existe exception
			throw new Exception(e.getMessage());
		}
		
		return paises;
	}

	@Override
	public Pais getById(int id) throws Exception {
		
		Pais p= null;
		
		try(
				Connection con= ConnectionManager.getConnection();
				PreparedStatement pst=con.prepareStatement(SQL_GETBYID);
			
			){
				pst.setInt(1, id);
				try(ResultSet rs= pst.executeQuery()){
					
					while (rs.next()) {
						
						p= new Pais();
						p.setId(rs.getInt("pais_id"));
						p.getContinente().setId(rs.getInt("continente_id"));
						p.setNombre(rs.getString("pais_nombre"));
						p.setBandera(rs.getString("pais_bandera"));
						p.setNombrecorto(rs.getString("pais_nombrecorto"));
						p.setNumciudades(Integer.parseInt(rs.getString("ciudades")));
						
					}
					
					if (p==null) {
						throw new Exception("Error, id de pais no encontrado");
					}
				}
				
			}catch (Exception e) {
				throw new Exception(e.getMessage());
			}
			
			return p;
	}

	@Override
	public Pais delete(int id) throws Exception {
		Pais p= new Pais();
		
			try(
				Connection con = ConnectionManager.getConnection();
				PreparedStatement pst=con.prepareStatement(SQL_DELETE);
			){
				p=getById(id);
				pst.setInt(1,p.getId());
				int borrado=pst.executeUpdate();
				
				if (borrado==2) {
					throw new Exception("Lo sentimos, El pais "+p.getNombre()+" con id "+p.getId()+" no está registrado");
				}
				
			}catch (Exception e) {
				throw new Exception(e.getMessage());
			}
		
		return p;
	}

	@Override
	public Pais insert(Pais pojo) throws Exception {
		
		//(nombre, continente, nombre_corto)
		
		try (Connection con = ConnectionManager.getConnection();

				// RETURN_GENERATED_KEYS es para recuperar la ID que asignara la abse de datos
				// al nuevo registro
				PreparedStatement pst = con.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

		) {
			// se modifica la insert diciendo que el interrogante lo sustituya con el nombre
			// del objeto
			pst.setString(1, pojo.getNombre());
			pst.setInt(2, pojo.getContinente().getId());
			pst.setString(3, pojo.getNombrecorto());

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
						+ " ya está registrado en el continente seleccionado.");
			}

		} catch (Exception e) {
			// este lanzariía el mensaje del catch interno (Erro, ya existe...)
			throw new Exception(e.getMessage());
		}

		return pojo;
	}

	@Override
	public Pais update(Pais pojo) throws Exception {
		

		try(
				Connection con= ConnectionManager.getConnection();
				PreparedStatement pst=con.prepareStatement(SQL_UPDATE);
			){
				pst.setString(1, pojo.getNombre());
				pst.setInt(2, pojo.getContinente().getId());
				pst.setString(3, pojo.getNombrecorto());
				pst.setInt(4, pojo.getId());
				
			
				int insertado=pst.executeUpdate();
				if (insertado==2) {
					throw new Exception("Lo sentimos pero el pais "+pojo.getNombre()+" con id "+pojo.getId()+"no esta registrado");
						
				}
				
			}catch (Exception e) {
				throw new Exception("Lo sentimos pero ya existe el pais "+pojo.getNombre().toUpperCase()+" para el continente seleccionado");
			
			}
			
			return pojo;
	
	}

	@Override
	public ArrayList<Pais> getAllByNombre(String palabraBuscada) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Pais mapper(ResultSet rs) throws SQLException {
		
		Pais p= new Pais();
		Continente co= new Continente();
		
		//asignacion de datos por campo al objeto
		p.setId(rs.getInt("pais_id"));
		p.setNombre(rs.getString("pais_nombre"));
		p.setBandera(rs.getString("pais_bandera"));
		co.setId(rs.getInt("pais_continente"));
		co.setNombre(rs.getString("continente_nombre"));
		p.setContinente(co);
		p.setNombrecorto(rs.getString("pais_nombrecorto"));
		
		
		return p;
	}

	@Override
	public Ciudad setBandera(Ciudad ci) throws SQLException, Exception {
		
		Connection con = ConnectionManager.getConnection();
		PreparedStatement pst= con.prepareStatement(UPDATE_BANDERA);
		
		pst.setString(1,ci.getPais().getBandera());
		pst.setInt(2, ci.getPais().getId());
		
		int updated=pst.executeUpdate();
		
		if (updated==2) {
			throw  new Exception("Error, no se ha podido subir el archivo");
		}
		return ci;
		
		
		
	}

	@Override
	public HashMap<String,Pais> getAllByContinente(int id) throws Exception {
		
		HashMap<String,Pais> paises= new HashMap<String,Pais>();
		
		try(
			Connection con= ConnectionManager.getConnection();
			PreparedStatement pst=con.prepareStatement(SQL_GETPAISESBYCONTINENTE);
		
		){
			pst.setInt(1, id);
			try(ResultSet rs= pst.executeQuery()){
				
				while (rs.next()) {
					
					Pais p= new Pais();
					

		
					p.setId(rs.getInt("pais_id"));
					p.getContinente().setId(rs.getInt("continente_id"));
					p.setNombre(rs.getString("pais_nombre"));
					p.setBandera(rs.getString("pais_bandera"));
					p.setNombrecorto(rs.getString("pais_nombrecorto"));
					p.setNumciudades(Integer.parseInt(rs.getString("ciudades")));
					
					//si no se introduce solo el pais
					paises.put(String.valueOf(p.getId()),p);
					
				}
			}
			
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		
		return paises;
		
		
	}
	
	
	
}
