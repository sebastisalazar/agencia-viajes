package modelo.DAO;

import java.util.ArrayList;

import modelo.CrudAble;
import modelo.pojo.Ciudad;

public interface CiudadDAO extends CrudAble<Ciudad>{
	
	ArrayList<Ciudad> getAllByNombre( String palabraBuscada ) throws Exception;
	ArrayList<Ciudad> getAllByPais( int id ) throws Exception;
	ArrayList<Ciudad> getLast( int numRegistro ) throws Exception;
}
