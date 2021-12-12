package services;

import java.util.HashMap;
import java.util.Map;

import modelo.Atraccion;
import modelo.Usuario;
import persistence.AtraccionDAO;
import persistence.UsuarioDAO;
import persistence.commons.DAOFactory;

public class ComprarAtraccionService {

	AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
	UsuarioDAO userDAO = DAOFactory.getUsuarioDAO();

	public Map<String, String> buy(Integer userId, Integer atraccionId) {
		Map<String, String> errors = new HashMap<String, String>();

		Usuario user = userDAO.find(userId);
		Atraccion atraccion = atraccionDAO.find(atraccionId);

		if (!atraccion.canHost(1)) {
			errors.put("atraccion", "No hay cupo disponible");
		}
		if (!user.canAfford(atraccion)) {
			errors.put("user", "No tienes dinero suficiente");
		}
		if (!user.canAttend(atraccion)) {
			errors.put("user", "No tienes tiempo suficiente");
		}

		if (errors.isEmpty()) {
			user.addToItinerary(atraccion);
			atraccion.host(1);

			atraccionDAO.update(atraccion);
			userDAO.update(user);
		}

		return errors;

	}

}
