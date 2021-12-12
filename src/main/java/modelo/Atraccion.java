package modelo;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Atraccion {
	int id, cupo;
	String nombre, descripcion, imagen;
	String[] atraccion = new String[1];
	double costo, duracion;

	private Map<String, String> errors;
	
	public Atraccion() {

	}
	
	public Atraccion(int id, String nombre, double costo, double duracion, int cupo, String descripcion, String imagen) {
		this.id = id;
		this.nombre = nombre;
		this.costo = costo;
		this.duracion = duracion;
		this.cupo = cupo;
		this.atraccion[0] = this.nombre;
		this.descripcion = descripcion;
		this.imagen = imagen;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setCupo(int cupo) {
		this.cupo = cupo;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public void setDuracion(double duracion) {
		this.duracion = duracion;
	}

	public String getImagen() {
		return imagen;
	}

	public Atraccion(int id) {
		this.id = id;
	}
	
	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}
	
	public void validate() {
		errors = new HashMap<String, String>();

		if (costo <= 0) {
			errors.put("cost", "Debe ser positivo");
		}
		if (duracion <= 0) {
			errors.put("duration", "Debe ser positivo");
		}
		if (duracion > 60) {
			errors.put("duration", "Excede el tiempo máximo");
		}
		if (cupo <= 0) {
			errors.put("capacity", "Debe ser positivo");
		}
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}

	public int getId() {
		return id;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String name) {
		this.nombre = name;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(Integer costo) {
		this.costo = costo;
	}

	public Double getDuracion() {
		return duracion;
	}

	public void setDuracion(Double duracion) {
		this.duracion = duracion;
	}

	public Integer getCupo() {
		return cupo;
	}

	public void setCupo(Integer cupo) {
		this.cupo = cupo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	@Override
	public String toString() {
		return "Attraction [id=" + id + ", name=" + nombre + ", cost=" + costo + ", duration=" + duracion + ", capacity="
				+ cupo + "]";
	}

	public boolean canHost(int i) {
		return cupo >= i;
	}

	public void host(int i) {
		this.cupo -= i;
	}

	public LinkedList<Atraccion> atraccionesPosibles(LinkedList<Atraccion> listaAtracciones, Usuario usuario) {
		LinkedList<Atraccion> atraccionesPosibles = new LinkedList<Atraccion>();
		LinkedList<Atraccion> Aceptadas = new LinkedList<Atraccion>();

		for (Atraccion at : listaAtracciones) {
			if ((usuario.getPresupuesto() >= at.getCosto()) && (usuario.getTiempoDisponible() >= at.getDuracion())
					&& (at.tieneCupo())) {
				atraccionesPosibles.add(at);
			}
		}

		Collections.sort(atraccionesPosibles, (a, b) -> Double.compare(b.getCosto(), a.getCosto()));
		Aceptadas.addAll(usuario.getAtraccionesAceptadas());
		atraccionesPosibles.removeAll(Aceptadas);
		return atraccionesPosibles;
	}
	
	public boolean tieneCupo() {
		return (cupo > 0);
	}
}
