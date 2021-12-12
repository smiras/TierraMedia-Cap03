package services;

import java.util.LinkedList;

import modelo.Atraccion;
import modelo.Promocion;
import persistence.AtraccionDAO;
import persistence.PromocionDAO;
import persistence.commons.DAOFactory;

public class PromocionService {
	public LinkedList<Promocion> list() {
		return DAOFactory.getPromocionDAO().findAll();
	}

	public Promocion create(int id, String nombre, String tipo, int esp, String descripcion, String imagen) {

		Promocion promocion = new Promocion(id, nombre, tipo, esp, descripcion, imagen);

		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		promocionDAO.insert(promocion);

		return promocion;
	}

	
	public Promocion update(int id, String nombre, String tipo, int esp, String descripcion, String imagen) {

		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		Promocion promocion = promocionDAO.find(id);

		promocion.setId(id);
		promocion.setNombre(nombre);
		promocion.setTipo(tipo);
		promocion.setEsp(esp);
		promocion.setDescripcion(descripcion);
		promocion.setImagen(imagen);

			promocionDAO.update(promocion);

		return promocion;
	}

	public void delete(int id) {
		Promocion promocion = new Promocion(id);

		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		promocionDAO.delete(promocion);
		
		
	}

	public Promocion find(Integer id) {
		return DAOFactory.getPromocionDAO().find(id);
	}

}
