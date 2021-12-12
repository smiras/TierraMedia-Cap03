package services;

import java.util.List;

import modelo.Atraccion;
import modelo.Usuario;
import persistence.AtraccionDAO;
import persistence.commons.DAOFactory;

public class UsuarioService {

	public List<Usuario> list() {
		return DAOFactory.getUsuarioDAO().findAll();
	}

	public Usuario create(String nombre, String pass, Double presupuesto, Double tiempodisponible, String tipo) {
		Usuario user = new Usuario(-1, nombre, pass, presupuesto, tiempodisponible, tipo);
		user.setPass(pass);

		if (user.isValid()) {
			DAOFactory.getUsuarioDAO().insert(user);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return user;
	}
}
