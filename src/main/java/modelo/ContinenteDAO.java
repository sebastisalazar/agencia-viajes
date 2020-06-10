package modelo;

import java.util.ArrayList;

public interface ContinenteDAO extends CrudAble<Continente> {
	ArrayList<Continente> getAllByNombre( String palabraBuscada ) throws Exception;
}
