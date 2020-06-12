package modelo.DAOImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import modelo.ConnectionManager;
import modelo.DAO.ContinenteDAO;
import modelo.pojo.Continente;

public class ContinenteDAOImp implements ContinenteDAO {

	private static ContinenteDAOImp INSTANCE = null;

	private ContinenteDAOImp() {
		super();
	}

	public static synchronized ContinenteDAOImp getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new ContinenteDAOImp();
		}

		return INSTANCE;
	}
	
	private final String SQL_GETALL="SELECT id, nombre FROM agencia_viajes.continente;";

	@Override
	public ArrayList<Continente> getAll() throws Exception {
		
		//lista para guardar los paises
				ArrayList<Continente> continentes = new ArrayList<Continente>();
				
				//recursos y conexiones
				try(
					Connection con= ConnectionManager.getConnection();
					PreparedStatement pst= con.prepareStatement(SQL_GETALL);
					ResultSet rs=pst.executeQuery();
				){
					//mientras existan registros 
					while (rs.next()) {
						
						//por cada linea leida crea un  objeto vacio
						Continente c = new Continente();
						
						//asignacion de datos por campo al objeto
						c.setId(rs.getInt("id"));
						c.setNombre(rs.getString("nombre"));
						
						//se añade a la lista
						continentes.add(c);
						
					}
					
				}catch (Exception e) {
					
					//si existe exception
					throw new Exception(e.getMessage());
				}
				
		return continentes;
	}

	@Override
	public Continente getById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Continente delete(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Continente insert(Continente pojo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Continente update(Continente pojo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Continente> getAllByNombre(String palabraBuscada) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
