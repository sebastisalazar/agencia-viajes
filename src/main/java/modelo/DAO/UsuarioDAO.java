package modelo.DAO;

import java.util.ArrayList;

import modelo.CrudAble;
import modelo.pojo.Usuario;

public interface UsuarioDAO extends CrudAble<Usuario> {

	ArrayList<Usuario> getAllByNombre( String palabraBuscada ) throws Exception;

	Usuario getExiste(String email, String password) throws Exception;
}
