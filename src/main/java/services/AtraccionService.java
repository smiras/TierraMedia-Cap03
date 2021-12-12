package services;

import java.util.LinkedList;
import java.util.List;

import modelo.Atraccion;
import persistence.AtraccionDAO;
import persistence.commons.DAOFactory;

public class AtraccionService {

	public LinkedList<Atraccion> list() {
		return DAOFactory.getAtraccionDAO().findAll();
	}

	public Atraccion create (int id, String nombre, double costo, double duracion, int cupo, String descripcion, String imagen) {

		Atraccion atraccion = new Atraccion(id, nombre, costo, duracion, cupo, descripcion, imagen);

		if (atraccion.isValid()) {
			AtraccionDAO attractionDAO = DAOFactory.getAtraccionDAO();
			attractionDAO.insert(atraccion);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return atraccion;
	}

	public Atraccion update(Integer id, String nombre, Double costo, Double duracion, int cupo, String descripcion, String imagen) {

		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		Atraccion atraccion = atraccionDAO.find(id);

		atraccion.setId(id);
		atraccion.setNombre(nombre);
		atraccion.setCosto(costo);
		atraccion.setDuracion(duracion);
		atraccion.setCupo(cupo);
		atraccion.setDescripcion(descripcion);
		atraccion.setImagen(imagen);

		if (atraccion.isValid()) {
			atraccionDAO.update(atraccion);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return atraccion;
	}

	public void delete(int id) {
		Atraccion atraccion = new Atraccion(id);

		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		atraccionDAO.delete(atraccion);
	}

	public Atraccion find(Integer id) {
		return DAOFactory.getAtraccionDAO().find(id);
	}

}
