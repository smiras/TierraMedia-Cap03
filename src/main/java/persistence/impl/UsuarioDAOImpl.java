package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.nullobjects.NullUser;
import modelo.Usuario;
import persistence.UsuarioDAO;
import persistence.commons.ConnectionProvider;
import persistence.commons.MissingDataException;

public class UsuarioDAOImpl implements UsuarioDAO {

	public int insert(Usuario user) {
		try {
			String sql = "INSERT INTO Usuarios (Id, Nombre, Pass, Presupuesto, Tiempo disponible, tipo) VALUES (?, ?, ?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, user.getId());
			statement.setString(2, user.getNombre());
			statement.setString(3, user.getPass());
			statement.setDouble(4, user.getPresupuesto());
			statement.setDouble(5, user.getTiempoDisponible());
			statement.setString(6, user.getTipo());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int update(Usuario user) {
		try {
			String sql = "UPDATE Usuarios SET Nombre = ?, Pass = ?, Presupuesto = ?, Tiempo disponible = ?, tipo = ? WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, user.getId());
			statement.setString(2, user.getNombre());
			statement.setString(3, user.getPass());
			statement.setDouble(4, user.getPresupuesto());
			statement.setDouble(5, user.getTiempoDisponible());
			statement.setString(6, user.getTipo());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int delete(Usuario user) {
		try {
			String sql = "DELETE FROM Usuarios WHERE Nombre = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, user.getNombre());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public Usuario findByUsername(String username) {
		try {
			String sql = "SELECT * FROM Usuarios WHERE Nombre = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet resultados = statement.executeQuery();

			Usuario user = NullUser.build();

			if (resultados.next()) {
				user = toUser(resultados);
			}

			return user;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MissingDataException(e);
		}
	}

	public Usuario find(Integer id) {
		try {
			String sql = "SELECT * FROM Usuarios WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultados = statement.executeQuery();

			Usuario user = NullUser.build();

			if (resultados.next()) {
				user = toUser(resultados);
			}

			return user;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM Usuarios";
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

	public LinkedList<Usuario> findAll() {
		try {
			String sql = "SELECT * FROM Usuarios";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			LinkedList<Usuario> usuarios = new LinkedList<Usuario>();
			while (resultados.next()) {
				usuarios.add(toUser(resultados));
			}

			return usuarios;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Usuario toUser(ResultSet resulset) throws SQLException {
		return new Usuario(resulset.getInt(1), resulset.getString(2), resulset.getString(3),
				resulset.getDouble(4), resulset.getDouble(5), resulset.getString(6));
	}

}
