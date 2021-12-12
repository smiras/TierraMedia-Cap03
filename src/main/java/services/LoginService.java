package services;

import model.nullobjects.NullUser;
import modelo.Usuario;
import persistence.UsuarioDAO;
import persistence.commons.DAOFactory;

public class LoginService {

	public Usuario login(String username, String password) {
		UsuarioDAO userDao = DAOFactory.getUsuarioDAO();
    	Usuario user = userDao.findByUsername(username);
    	
    	if (user.isNull() || !user.checkPassword(password)) {
    		user = NullUser.build();
    	}
    	return user;
	}
	
}
