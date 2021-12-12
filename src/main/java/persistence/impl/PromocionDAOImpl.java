package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import modelo.Atraccion;
import modelo.Promocion;
import persistence.PromocionDAO;
import persistence.commons.ConnectionProvider;
import persistence.commons.MissingDataException;

public class PromocionDAOImpl implements PromocionDAO {

	public LinkedList<Promocion> findAll() {
		try {
			String sql = "SELECT * FROM Promociones";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			LinkedList<Promocion> attractions = new LinkedList<Promocion>();
			while (resultados.next()) {
				attractions.add(toPromocion(resultados));
			}

			return attractions;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Promocion find(Integer id) {
		try {
			String sql = "SELECT * FROM Promociones WHERE promocion_id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			
			ResultSet resultados = statement.executeQuery();

			Promocion promocion = null;
			if (resultados.next()) {
				promocion = toPromocion(resultados);
			}

			return promocion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
		}
	
	private Promocion toPromocion(ResultSet result) throws SQLException {
		return new Promocion(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4), result.getString(5), result.getString(6));
		
	}

	@Override
	public int insert(Promocion promocion) {
		try {
			String sql = "INSERT INTO Promocion (promocion_id, nombre, tipo, dato_esp, descripcion, imagen) VALUES (?, ?, ?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			int i = 1;
			statement.setInt(i++, promocion.getId());
			statement.setString(i++, promocion.getNombre());
			statement.setString(i++, promocion.getTipo());
			statement.setInt(i++, promocion.getEsp());
			statement.setString(i++, promocion.getDescripcion());
			statement.setString(i++, promocion.getImagen());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(Promocion promocion) {
		try {
			String sql = "UPDATE Promociones SET nombre = ?, tipo = ?, dato_esp = ?, descripcion = ?, imagen= ? WHERE promocion_id = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			int i = 1;
			statement.setInt(i++, promocion.getId());
			statement.setString(i++, promocion.getNombre());
			statement.setString(i++, promocion.getTipo());
			statement.setInt(i++, promocion.getEsp());
			statement.setString(i++, promocion.getDescripcion());
			statement.setString(i++, promocion.getImagen());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int delete(Promocion promocion) {
		try {
			String sql = "DELETE FROM Promociones WHERE promocion_id = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, promocion.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM Promociones";
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
