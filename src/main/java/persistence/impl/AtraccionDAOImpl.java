package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import modelo.Atraccion;
import persistence.AtraccionDAO;
import persistence.commons.ConnectionProvider;
import persistence.commons.MissingDataException;

public class AtraccionDAOImpl implements AtraccionDAO {

	public LinkedList<Atraccion> findAll() {
		try {
			String sql = "SELECT * FROM Atracciones";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			LinkedList<Atraccion> attractions = new LinkedList<Atraccion>();
			while (resultados.next()) {
				attractions.add(toAtraccion(resultados));
			}

			return attractions;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Atraccion find(Integer id) {
		try {
			String sql = "SELECT * FROM Atracciones WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			
			ResultSet resultados = statement.executeQuery();

			Atraccion atraccion = null;
			if (resultados.next()) {
				atraccion = toAtraccion(resultados);
			}

			return atraccion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	public Atraccion toAtraccion(ResultSet result) throws SQLException {
		return new Atraccion(result.getInt(1), result.getString(2), result.getDouble(3), result.getDouble(4), result.getInt(5), result.getString(6), result.getString(7));
		
	}

	@Override
	public int insert(Atraccion atraccion) {
		try {
			String sql = "INSERT INTO Atraccion (atraccion_id, Nombre, Costo, Duracion, Cupo, Descripcion, Imagen) VALUES (?, ?, ?, ?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			int i = 1;
			statement.setInt(i++, atraccion.getId());
			statement.setString(i++, atraccion.getNombre());
			statement.setDouble(i++, atraccion.getCosto());
			statement.setDouble(i++, atraccion.getDuracion());
			statement.setDouble(i++, atraccion.getCupo());
			statement.setString(i++, atraccion.getDescripcion());
			statement.setString(i++, atraccion.getImagen());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(Atraccion atraccion) {
		try {
			String sql = "UPDATE ATTRACTIONS SET Nombre = ?, Costo = ?, Duracion = ?, Cupo = ?, Descripcion = ?, Imagen= ? WHERE atraccion_id = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			int i = 1;
			statement.setInt(i++, atraccion.getId());
			statement.setString(i++, atraccion.getNombre());
			statement.setDouble(i++, atraccion.getCosto());
			statement.setDouble(i++, atraccion.getDuracion());
			statement.setDouble(i++, atraccion.getCupo());
			statement.setString(i++, atraccion.getDescripcion());
			statement.setString(i++, atraccion.getImagen());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int delete(Atraccion atraccion) {
		try {
			String sql = "DELETE FROM Atracciones WHERE atraccion_id = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, atraccion.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM Atracciones";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			resultados.next();
			int total = resultados.getInt("TOTAL");

			return total;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}



}
