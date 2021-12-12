package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

import persistence.commons.ConnectionProvider;
import persistence.commons.MissingDataException;
import persistence.impl.AtraccionDAOImpl;

public class Promocion {
	private int id, esp;
	private String tipo, nombre, descripcion, imagen;
	private LinkedList<Atraccion> atraccionesIncluidas;
	protected double costo = 0;
	protected double duracion = 0;

	public Promocion() {

	}

	public Promocion(int id, String nombre, String tipo, int esp, String descripcion, String imagen) {
		this.id = id;
		this.nombre = nombre;
		this.esp = esp;
		this.tipo = tipo;
		this.descripcion = descripcion;
		this.atraccionesIncluidas = this.leerAtraccionesIncluidas(id);
		this.imagen = imagen;
		costo = this.verificaPositivo(calcularCosto(atraccionesIncluidas, tipo, esp));
		duracion = this.verificaPositivo(calcularDuracion(atraccionesIncluidas));

	}

	public Promocion(int id) {
		
	}

	public int getEsp() {
		return esp;
	}

	public void setEsp(int esp) {
		this.esp = esp;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public void setId(int id) {
		this.id = id;
	}

	public void setAtraccionesIncluidas(LinkedList<Atraccion> atraccionesIncluidas) {
		this.atraccionesIncluidas = atraccionesIncluidas;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public void setDuracion(double duracion) {
		this.duracion = duracion;
	}

	public boolean tieneCupo() {
		boolean tienecupo = true;
		for (Atraccion a : atraccionesIncluidas) {
			if (a.getCupo() == 0)
				tienecupo = false;
		}

		return tienecupo;
	}

	public int compareTo(Promocion pr) {
		return Double.valueOf(costo).compareTo(pr.getCosto());
	}

	public double getCosto() {
		return costo;
	}

	public double getDuracion() {
		return duracion;
	}

	public double verificaPositivo(double valor) {
		if (valor >= 0)
			return valor;
		else
			throw new RuntimeException("no se pueden cargar valores negativos");
	}

	public double calcularDuracion(LinkedList<Atraccion> atracciones) {
		double d = 0;

		for (Atraccion at : atracciones) {
			d += at.getDuracion();
		}
		return d;

	}

	public double calcularCosto(LinkedList<Atraccion> atracciones, String tipo, int esp) {
		double c = 0;
		if (tipo.equals("porcentual")) {
			for (Atraccion at : atracciones) {
				c += at.getCosto();
			}
			c -= c * esp / 100;
		} else if (tipo.equals("absoluta")) {
			c = esp;
		} else if (tipo.equals("axb")) {

			for (int i = 1; i < esp; i++) {

				c += atracciones.get(i).getCosto();
			}
		}

		return c;

	}

	public LinkedList<Atraccion> leerAtraccionesIncluidas(int id_promo) {

		try {
			String sql = "SELECT Atracciones.atraccion_id, Atracciones.Nombre, Atracciones.Costo, Atracciones.Duracion, Atracciones.Cupo, Atracciones.Descripcion, Atracciones.imagen FROM Atracciones JOIN atrac_promo on Atracciones.atraccion_id = atrac_promo.atraccion_id WHERE atrac_promo.promocion_id = "
					+ id_promo;
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();
			AtraccionDAOImpl atraccion = new AtraccionDAOImpl();
			LinkedList<Atraccion> atracciones = new LinkedList<Atraccion>();
			while (resultados.next()) {
				atracciones.add(atraccion.toAtraccion(resultados));
			}

			return atracciones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}

	}

	public LinkedList<Atraccion> getAtraccionesIncluidas() {
		return atraccionesIncluidas;
	}

	public LinkedList<Promocion> promosPosibles(LinkedList<Promocion> listaPromociones, Usuario usuario) {
		LinkedList<Promocion> promosPosibles = new LinkedList<Promocion>();

		for (Promocion p : listaPromociones) {

			if ((usuario.getPresupuesto() >= p.getCosto()) && (usuario.getTiempoDisponible() >= p.getDuracion())
					&& (p.tieneCupo())) {
				promosPosibles.add(p);
			}
		}

		Collections.sort(promosPosibles, (a, b) -> Double.compare(b.getCosto(), a.getCosto()));
		promosPosibles.removeAll(usuario.getPromosAceptadas());

		return promosPosibles;
	}

	public void ofertarPromos(LinkedList<Promocion> listaPromociones, Usuario usuario) {
		String respuesta = "n";
		Scanner sc = new Scanner(System.in);
		int i = 0;
		LinkedList<Promocion> listaPromosPosibles = promosPosibles(listaPromociones, usuario);

		while ((respuesta.equalsIgnoreCase("n")) && (listaPromosPosibles != null) && (i < listaPromosPosibles.size())) {

			System.out.println(listaPromosPosibles.get(i));
			do {
				System.out.println("�Acepta la sugerencia? Ingrese: s (Si) - n (No) - x (Salir)\n");
				respuesta = sc.nextLine();
			} while (!respuesta.equalsIgnoreCase("s") && !respuesta.equalsIgnoreCase("n")
					&& !respuesta.equalsIgnoreCase("x"));

			if (respuesta.contentEquals("s")) {
				usuario.aceptarPromo(listaPromosPosibles.get(i));
				listaPromosPosibles = promosPosibles(listaPromosPosibles, usuario);
				i = 0;
			} else if (respuesta.contentEquals("n")) {
				i++;
			} else if (respuesta.contentEquals("x")) {
				listaPromosPosibles = null;
			}
		}

	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Promocion\nNombre: " + nombre + "  Costo: " + costo + " Duracion: " + duracion
				+ "\nAtracciones incluidas: " + atraccionesIncluidas;
	}
}
