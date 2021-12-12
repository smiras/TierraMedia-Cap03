package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Producto {
	
	private int id;
	private String nombre;
	private Double costo;
	private Double duracion;
	private String descripcion;
	private String imagen;
	
	public Producto(int id, String nombre, Double costo, Double duracion, String descripcion, String imagen) {
		this.id = id;
		this.nombre = nombre;
		this.costo = costo;
		this.duracion = duracion;
		this.descripcion = descripcion;
		this.imagen = imagen;
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Double getCosto() {
		return costo;
	}


	public void setCosto(Double costo) {
		this.costo = costo;
	}


	public Double getDuracion() {
		return duracion;
	}


	public void setDuracion(Double duracion) {
		this.duracion = duracion;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getImagen() {
		return imagen;
	}


	public void setImagen(String imagen) {
		this.imagen = imagen;
	}


	

}
