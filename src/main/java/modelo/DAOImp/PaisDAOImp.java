package modelo.DAOImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.ConnectionManager;
import modelo.DAO.PaisDAO;
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

	

	private final String SQL_GETALL="SELECT id, nombre, bandera, continente FROM agencia_viajes.pais;";
	
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
				Pais p= new Pais();
				
				//asignacion de datos por campo al objeto
				p.setId(rs.getInt("id"));
				p.setNombre(rs.getString("nombre"));
				p.setBandera(rs.getString("bandera"));
				p.setContinente( new Continente(rs.getInt("continente")));
				
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
	
	
}
