package modelo.DAO;

import java.util.ArrayList;

import modelo.CrudAble;
import modelo.pojo.Pais;

public interface PaisDAO extends CrudAble<Pais>{
	
	ArrayList<Pais> getAllByNombre( String palabraBuscada ) throws Exception;
}
