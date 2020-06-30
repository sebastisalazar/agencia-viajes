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
	
	
	private final String UPDATE_BANDERA="UPDATE agencia_viajes.pais SET bandera=? WHERE id=?;";
	private final String SQL_GETPAISESBYCONTINENTE="SELECT " + 
													"pais.id as pais_id," + 
													"pais.continente as continente_id," + 
													"pais.nombre_corto as pais_nombrecorto,"+
													"pais.bandera as pais_bandera,"+
													"ciudad.nombre as ciudad_nombre," + 
													"pais.nombre as pais_nombre " + 
													"FROM ciudad " + 
													"RIGHT JOIN pais ON pais.id = ciudad.pais " + 
													"Where pais.continente=?";
	
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pais delete(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pais insert(Pais pojo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pais update(Pais pojo) throws Exception {
		// TODO Auto-generated method stub
		return null;
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
					
					String ciudad=rs.getString("ciudad_nombre");
					
					//si el pais ya existe en el hashmap quiere decir que es porque tiene mas de una ciudad
					if (paises.containsKey(String.valueOf(p.getId()))) {
						
						p=paises.get(String.valueOf(p.getId()));
						p.setNumciudades(1);
						
					//si no se evalua 
					}else {
						
						//si no existe en el hasmap se intrudce como nuevo pero se mira si la columna ciudad_nombre no es null
						if (ciudad!=null) {
							p.setNumciudades(1);//si no es null se suma uno
						}
						
						//si no se introduce solo el pais
						paises.put(String.valueOf(p.getId()),p);
					}
					
				}
			}
			
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		
		return paises;
		
		
	}
	
	
	
}
