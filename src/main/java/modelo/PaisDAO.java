package modelo;

import java.util.ArrayList;

public interface PaisDAO extends CrudAble<Pais>{
	
	ArrayList<Pais> getAllByNombre( String palabraBuscada ) throws Exception;
}
