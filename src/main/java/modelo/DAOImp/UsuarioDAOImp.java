package modelo.DAOImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import modelo.ConnectionManager;
import modelo.DAO.UsuarioDAO;
import modelo.pojo.Usuario;

public class UsuarioDAOImp implements UsuarioDAO {

	private static UsuarioDAOImp INSTANCE = null;

	private UsuarioDAOImp() {
		super();
	}

	public static synchronized UsuarioDAOImp getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new UsuarioDAOImp();
		}

		return INSTANCE;
	}

	private final String SQL_EXISTE = "SELECT id, nombre, password, imagen FROM agencia_viajes.usuarios WHERE nombre=? AND password=?;";

	@Override
	public ArrayList<Usuario> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario getById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario delete(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario insert(Usuario pojo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario update(Usuario pojo) throws Exception {
		// TODO Auto-generated method stub
		return null;
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

			try (ResultSet rs = pst.executeQuery()) {

				while (rs.next()) {
					u = new Usuario();
					u.setEmail(rs.getString("nombre"));
					u.setPassword(rs.getString("password"));

				}

				// si no encuentra registro lo busca por email
				if (u == null) {
					String sql_buscarEmail = "SELECT id, nombre, password, imagen FROM agencia_viajes.usuarios WHERE nombre='"
							+ email + "';";
					try (
							Connection con2 = ConnectionManager.getConnection();
							PreparedStatement pst2 = con2.prepareStatement(sql_buscarEmail);
							ResultSet rs2 = pst2.executeQuery();
					) {
						// si la busqueda por email devuelve registro significa que se ha introducido
						// una contraseña erronea
						if (rs2.next()) {
							throw new Exception("\nEmail registrado, contraseña erronea.");

							// si la busqueda por email NO devuelve registro significa que no existe en la
							// bbdd
						} else {
							throw new Exception("Lo sentimos el email " + email + " no está registrado");
						} // fin if busqueda email

					}//fin try catch

				}//fin if u==null

			}//fin try catch

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return u;
	}

}
