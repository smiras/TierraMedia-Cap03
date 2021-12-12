package modelo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import modelo.Atraccion;
import modelo.Promocion;
import utils.Crypt;

public class Usuario {

	private int id;
	private String nombre, tipo, pass;
	private double presupuesto, tiempodisponible, dineroGastado, tiempoNecesario;
	private LinkedList<Promocion> promosAceptadas = new LinkedList<Promocion>();
	
	
	public LinkedList<Atraccion> getAtraccionesAceptadas() {
		return atraccionesAceptadas;
	}

	public LinkedList<Promocion> getPromosAceptadas() {
		return promosAceptadas;
	}

	private LinkedList<Atraccion> atraccionesAceptadas = new LinkedList<Atraccion>();
	HashMap<String, String> errors;

	public Usuario(int id, String nombre, String pass, double presupuesto, double tiempodisponible, String tipo) {
		this.id = id;
		this.nombre = nombre;
		this.pass = pass;
		this.presupuesto = presupuesto;
		this.tiempodisponible = tiempodisponible;
		this.tipo = tipo;
	}

	public Usuario() {

	}

	public void addToItinerary(Atraccion atraccion) {
		this.presupuesto -= atraccion.getCosto();
		this.tiempodisponible -= atraccion.getDuracion();
		// TODO agregar a su lista
	}

	public boolean canAfford(Atraccion attraction) {
		return attraction.getCosto() <= this.presupuesto;
	}

	public boolean canAttend(Atraccion attraction) {
		return attraction.getDuracion() <= this.tiempodisponible;
	}

	public boolean checkPassword(String password) {
		// this.password en realidad es el hash del password
		return Crypt.match(password, this.pass);
	}

	public Boolean getAdmin() {
		if (tipo.equals("admin"))
			return true;
		else
			return false;
	}

	public Double getPresupuesto() {
		return presupuesto;
	}

	public Integer getId() {
		return id;
	}

	public String getPass() {
		return pass;
	}

	public Double getTiempoDisponible() {
		return tiempodisponible;
	}

	public String getNombre() {
		return nombre;
	}

	public Boolean isAdmin() {
		if (tipo.equals("admin"))
			return true;
		else
			return false;
	}

	public boolean isNull() {
		return false;
	}

	public void setAdmin(Boolean admin) {
		this.tipo = "admin";
	}

	public void setPresupuesto(double d) {
		this.presupuesto = d;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPass(String pass) {
		this.pass = Crypt.hash(pass);
	}

	public void setTiempoDisponible(Double tiempodisponible) {
		this.tiempodisponible = tiempodisponible;
	}

	public void setUsername(String nombre) {
		this.nombre = nombre;
	}

	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}

	public void validate() {
		errors = new HashMap<String, String>();

		if (presupuesto < 0) {
			errors.put("coins", "No debe ser negativo");
		}
		if (tiempodisponible < 0) {
			errors.put("time", "No debe ser negativo");
		}
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + nombre + ", password=" + pass + "]";
	}

	public String getTipo() {
		
		return tipo;
	}
	
	public void aceptarPromo(Promocion promocionAceptada) {

		this.setPresupuesto(presupuesto - promocionAceptada.getCosto());
		this.setTiempoDisponible(tiempodisponible - promocionAceptada.getDuracion());
		promocionAceptada.getAtraccionesIncluidas().forEach(at -> at.host(1));
		promosAceptadas.add(promocionAceptada);
		

	}

	public void aceptarAtraccion(Atraccion atraccion) {

		presupuesto -= atraccion.getCosto();
		tiempodisponible -= atraccion.getDuracion();
		atraccion.host(1);
		atraccionesAceptadas.add(atraccion);
	}

}
