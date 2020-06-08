package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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

	private final String SQL_GETALL="SELECT id, nombre FROM agencia_viajes.pais;";
	@Override
	public ArrayList<Pais> getAll() throws Exception {
		
		ArrayList<Pais> listaPaises= new ArrayList<Pais>();
		
		
		try (
				Connection con= ConnectionManager.getConnection();
				PreparedStatement pst=con.prepareStatement(SQL_GETALL);
				ResultSet rs=pst.executeQuery();
		
		){
				while (rs.next()) {
					Pais p= new Pais(rs.getString("nombre"), rs.getInt("id"));
					listaPaises.add(p);
				}
				
		}catch (Exception e) {

			e.printStackTrace();

		}
		
		return listaPaises;
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
