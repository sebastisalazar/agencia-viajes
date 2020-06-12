package modelo.DAO;

import java.util.ArrayList;

import modelo.CrudAble;
import modelo.pojo.Continente;

public interface ContinenteDAO extends CrudAble<Continente> {
	ArrayList<Continente> getAllByNombre( String palabraBuscada ) throws Exception;
}
