package modelo.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import modelo.CrudAble;
import modelo.pojo.Ciudad;
import modelo.pojo.Pais;

public interface PaisDAO extends CrudAble<Pais>{
	
	ArrayList<Pais> getAllByNombre( String palabraBuscada ) throws Exception;
	
	Ciudad setBandera(Ciudad ci) throws SQLException, Exception;
}
