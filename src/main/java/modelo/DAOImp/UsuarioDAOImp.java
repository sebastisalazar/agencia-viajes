package modelo.DAOImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import listener.InicioAppListenner;
import modelo.ConnectionManager;
import modelo.DAO.UsuarioDAO;

import modelo.pojo.Rol;
import modelo.pojo.Usuario;

public class UsuarioDAOImp implements UsuarioDAO {

	private static UsuarioDAOImp INSTANCE = null;
	private final static Logger LOG = Logger.getLogger(InicioAppListenner.class);

	private UsuarioDAOImp() {
		super();
	}

	public static synchronized UsuarioDAOImp getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new UsuarioDAOImp();
		}

		return INSTANCE;
	}

	private final String SQL_GETALL="SELECT " + 
									"usuarios.id as usuario_id," + 
									"usuarios.nombre as usuario_nombre," + 
									"usuarios.password as usuario_password," + 
									"usuarios.imagen as usuario_fotoperfil," + 
									"usuarios.id_rol as usuario_idrol," + 
									"rol.nombre as usuario_tipo " + 
									"FROM usuarios,rol " + 
									"WHERE usuarios.id_rol=rol.id ";
	
	private final String SQL_GETBYID="SELECT id, nombre, password, imagen, id_rol FROM agencia_viajes.usuarios WHERE id=?;";
	
	private final String SQL_EXISTE = "SELECT u.id, u.nombre as email, u.password, u.imagen, u.id_rol," + 
			"d.nombre,d.ape1,d.ape2,d.DNI_NIE, d.nacionalidad, d.residencia," + 
			"t.numero,t.caducidad,t.num_seguridad, t.titular " + 
			"FROM usuarios u, datos_personales d, tarjetas_credito t " + 
			"WHERE u.id_datos = d.id " + 
			"AND d.id_tarjeta =t.id " + 
			"AND u.nombre LIKE ? " + 
			"AND u.password LIKE ? ";
	
	private final String SQL_INSERT="INSERT INTO agencia_viajes.usuarios (nombre, password) "+
									"VALUES(?,?);"; 
	
	private final String SQL_UPDATE="UPDATE agencia_viajes.usuarios "+
									"SET nombre=?, "+
									 	"password=?,"+
									 	"id_rol=? "+
									 	"WHERE id=?;";
	@Override
	public ArrayList<Usuario> getAll() throws Exception {
		// lista para guardar todos los paises
		ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();

		// conecta con la base de datos
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GETALL);
				ResultSet rs = pst.executeQuery();

		) {
			LOG.debug(pst);
			// lee linea por linea
			while (rs.next()) {
						
				Usuario u = new Usuario();
				u.setId(rs.getInt("usuario_id"));
				u.setEmail(rs.getString("usuario_nombre"));
				u.setPassword(rs.getString("usuario_password"));
				u.setImagen(rs.getString("usuario_fotoperfil"));
				
				Rol r= new Rol(rs.getInt("usuario_idrol"), rs.getString("usuario_tipo"));
				u.setRol(r);
				
				listaUsuarios.add(u);

			}

		} catch (Exception e) {

			LOG.error(e);
			e.printStackTrace();

		}

		return listaUsuarios;
	}

	@Override
	public Usuario getById(int id) throws Exception {
		
		Usuario u= null;
		
		try(
				Connection con= ConnectionManager.getConnection();
				PreparedStatement pst=con.prepareStatement(SQL_GETBYID);
			
			){
				pst.setInt(1, id);
				LOG.debug(pst);
				try(ResultSet rs= pst.executeQuery()){
					
					while (rs.next()) {
						u= new Usuario();
						u.setId(rs.getInt("id"));
						u.setEmail(rs.getString("nombre"));
						u.setPassword(rs.getString("password"));
						u.setImagen(rs.getString("imagen"));
						u.setRol(new Rol(rs.getInt("id_rol")));
						
					}
					
					if (u==null) {
						throw new Exception("Error, id de usuario no encontrado");
					}
				}
				
			}catch (Exception e) {
				LOG.error(e);
				throw new Exception(e.getMessage());
			}
			
			return u;
	}

	@Override
	public Usuario delete(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario insert(Usuario pojo) throws Exception {
		
		try (Connection con = ConnectionManager.getConnection();

				// RETURN_GENERATED_KEYS es para recuperar la ID que asignara la abse de datos
				// al nuevo registro
				PreparedStatement pst = con.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

		) {
			// se modifica la insert diciendo que el interrogante lo sustituya con el nombre
			// del objeto
			pst.setString(1, pojo.getEmail());
			pst.setString(2, pojo.getPassword());

			LOG.debug(pst);
			
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
				
				LOG.error(DBSQLException);
				throw new Exception("\nLo sentimos, " + (pojo.getEmail()).toUpperCase() + "\n"
						+ " ya está registrado como usuario.");
			}

		} catch (Exception e) {
			
			LOG.error(e);
			// este lanzariía el mensaje del catch interno (Erro, ya existe...)
			throw new Exception(e.getMessage());
		}

		return pojo;
	}

	@Override
	public Usuario update(Usuario pojo) throws Exception {
		

		try(
				Connection con= ConnectionManager.getConnection();
				PreparedStatement pst=con.prepareStatement(SQL_UPDATE);
			){
				pst.setString(1, pojo.getEmail());
				pst.setString(2, pojo.getPassword());
				pst.setInt(3, pojo.getRol().getId());
				pst.setInt(4, pojo.getId());
				
				LOG.debug(pst);
			
				int insertado=pst.executeUpdate();
				if (insertado==2) {
					throw new Exception("Lo sentimos pero el usuario "+pojo.getEmail()+" con id "+pojo.getId()+" no esta registrado.");
						
				}
				
			}catch (Exception e) {
				
				LOG.error(e);
				throw new Exception("Lo sentimos pero el usuario "+pojo.getEmail().toUpperCase()+" ya está registrado.");
			
			}
			
			return pojo;
	}

	@Override
	public ArrayList<Usuario> getAllByNombre(String palabraBuscada) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario getExiste(String email, String password) throws Exception {

		Usuario u = null;

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_EXISTE);

		) {
			pst.setString(1, email);
			pst.setString(2, password);

			LOG.debug(pst);
			try (ResultSet rs = pst.executeQuery()) {

				while (rs.next()) {
					
					u = new Usuario();
					u.setId(rs.getInt("id"));
					u.setEmail(rs.getString("email"));
					u.setPassword(rs.getString("password"));
					u.setImagen(rs.getString("imagen"));
					
					Rol rol= new Rol(Integer.parseInt(rs.getString("id_rol")));
					u.setRol(rol);
					
					u.setNombre(rs.getString("nombre"));
					u.setApe1(rs.getString("ape1"));
					u.setApe2(rs.getString("ape2"));
					u.setDNI_NIE(rs.getString("DNI_NIE"));
					u.setNacionalidad(rs.getString("nacionalidad"));
					u.setResidencia(rs.getString("residencia"));
					u.setNumTarjeta(rs.getInt("numero"));
					u.setCaducidadTarjeta(rs.getDate("caducidad").toString());
					u.setNumseguridadTarjeta(rs.getInt("num_seguridad"));
					u.setTitular(rs.getString("titular"));
				}

				// si no encuentra registro lo busca por email
				LOG.info("Usuario y contraseña introducidos no existe en la base de datos");
				if (u == null) {
					
					String sql_buscarEmail = "SELECT id, nombre, password, imagen FROM agencia_viajes.usuarios WHERE nombre='"
							+ email + "';";
					try (
							Connection con2 = ConnectionManager.getConnection();
							PreparedStatement pst2 = con2.prepareStatement(sql_buscarEmail);
							ResultSet rs2 = pst2.executeQuery();
							
					) {
						LOG.info("Buscando si existe el usuario");
						LOG.debug(pst2);
						// si la busqueda por email devuelve registro significa que se ha introducido
						// una contraseña erronea
						if (rs2.next()) {
							throw new Exception("\nEmail registrado, contraseña erronea.");

							// si la busqueda por email NO devuelve registro significa que no existe en la
							// bbdd
						} else {
							LOG.info("El usuario NO existe en la base de datos");
							
							throw new Exception("Lo sentimos el email " + email + " no está registrado");
						} // fin if busqueda email

					}//fin try catch

				}//fin if u==null

			}//fin try catch

		} catch (Exception e) {
			LOG.error(e);
			throw new Exception(e.getMessage());
		}

		return u;
	}

}
