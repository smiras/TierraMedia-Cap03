package persistence;

import modelo.Usuario;
import persistence.commons.GenericDAO;

public interface UsuarioDAO extends GenericDAO<Usuario> {

	public abstract Usuario findByUsername(String username);
	
}
