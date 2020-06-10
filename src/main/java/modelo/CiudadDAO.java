package modelo;

import java.util.ArrayList;

public interface CiudadDAO extends CrudAble<Ciudad>{
	
	ArrayList<Ciudad> getAllByNombre( String palabraBuscada ) throws Exception;
}
