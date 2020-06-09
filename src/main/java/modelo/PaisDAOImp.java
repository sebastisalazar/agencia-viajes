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
	private final String SQL_INSERT="INSERT INTO pais (nombre) VALUES(?);";
	@Override
	public ArrayList<Pais> getAll() throws Exception {
		
		//lista para guardar todos los paises
		ArrayList<Pais> listaPaises= new ArrayList<Pais>();
		
		
		try (
				Connection con= ConnectionManager.getConnection();
				PreparedStatement pst=con.prepareStatement(SQL_GETALL);
				ResultSet rs=pst.executeQuery();
		
		){
				//lee linea por linea 
				while (rs.next()) {
					//por cada linea crea un objeto recogiendo los datos de la base de datos y asignandolos al objeto
					Pais p= new Pais(rs.getString("nombre"), rs.getInt("id"));
					
					//una vez rellenado el objeto se ñade a esa lista
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
		
		try(
				Connection con= ConnectionManager.getConnection();
				
				//RETURN_GENERATED_KEYS es para recuperar la ID que asignara la abse de datos al nuevo registro
				PreparedStatement pst=con.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);	
			
			){
				//se modifica la insert diciendo que el interrogante lo sustituya con el nombre del objeto
				pst.setString(1, pojo.getNombre());
				
				try{
					//insert
					int filaInsertada= pst.executeUpdate();
					
					//si ha insertado correctamente leemos el id asignado
					if (filaInsertada==1) {
						
						//se lee el id asignado en la base de datos
						try(ResultSet rs=pst.getGeneratedKeys();){
							if (rs.next()) {
								//se recoge en un int
								int generatedKey= rs.getInt(1);
								
								//se asigna el int al objeto
								pojo.setId(generatedKey);
							}
							
						}
					}
					
					//si captura una excepcion de typo sql la lanza
				}catch (Exception DBSQLException) {
					throw new Exception("\nLo sentimos, el pais "+(pojo.getNombre()).toUpperCase()+"\n"+" ya está registrado.");
				}
					
			} catch (Exception e) {
				
				//este lanzariía el mensaje del catch interno (Erro, ya existe...)
				throw new Exception(e.getMessage());
			}
			
			return pojo;
		
		
		
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
