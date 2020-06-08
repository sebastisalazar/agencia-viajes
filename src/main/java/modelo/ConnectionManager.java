package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	
	
	private final static String URL = "jdbc:mysql://localhost/agencia_viajes";
	private final static String USUARIO = "root-agencia";
	private final static String PASS = "sebastian";
	
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		
		Connection con = null;
		
		// comprobar que tengamos el .jar de MySQL
		Class.forName("com.mysql.jdbc.Driver");
		
		//establecer conexion
		con = DriverManager.getConnection(URL, USUARIO, PASS);
		
		return con;
	};
	

}
