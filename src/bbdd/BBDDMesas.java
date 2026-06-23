package bbdd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import mesas.model.Mesa;
import usuario.model.Cliente;

/**
 * @author Lucas Arranz, Alejandro Ramos,  Alejandro Remiro
 */
public class BBDDMesas extends BBDD {

	ResultSet rs;

	public BBDDMesas() throws SQLException {
		super();
	}

	public boolean existeMesa(String id) {
		try {
			rs = prompt.executeQuery("SELECT *  FROM Mesas WHERE Mesas.id = " + "'" + id + "'");
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Si que funciona
	public boolean altaMesa(Mesa m) {

		try {
			prompt.executeUpdate("INSERT INTO Mesas VALUES ('" + m.getId() + "', '" + m.getJuego() + "', '"
					+ m.getJugadores() + "')");
			return true;
		} catch (SQLException e) {
			System.out.println("Fallo en BBDDMesas altaMesa");
			e.printStackTrace();
			return false;
		}
	}

	// TODO no funciona
	public boolean bajaMesa(String id) {
		try {
			prompt.executeUpdate("DELETE FROM Mesas WHERE Mesas.id = '" + id + "'");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// TODO me devuelve null siempre
	public Mesa consultaMesa(String id) {
		try {
			rs = prompt.executeQuery("SELECT * FROM Mesas WHERE Mesas.id = '" + id + "'");
			return creaMesa(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private Mesa creaMesa(ResultSet rs) throws SQLException {
		// Obtener los datos de la fila actual del ResultSet

		if (!rs.next())
			return null;

		String id = rs.getString("id");
		String juego = rs.getString("juego");
		String jugadores = rs.getString("jugadores"); // Suponiendo que los jugadores están separados por
														// comas en la base de datos

		// Crear y devolver un objeto Mesa
		return new Mesa(id, juego, jugadores);
	}

	public ArrayList<Cliente> obtenerJugadores(String mesaId) {

		ArrayList<Cliente> jugadores = new ArrayList<>();

		try {

			String aux;
			String[] jugadorIds = null;
			// Consultar la lista de jugadores de la mesa
			String sqlQuery = "SELECT * FROM Mesas WHERE id = '" + mesaId + "'";
			ResultSet resultSet = prompt.executeQuery(sqlQuery);

			// Si se encuentra la mesa
			if (resultSet.next()) {
				// System.out.println(resultSet.getString("id"));
				// System.out.println(resultSet.getString("juego"));f
				aux = resultSet.getString("jugadores");
				if (aux != null) {
					jugadorIds = aux.trim().split(",");

					// Recorrer la lista de IDs de jugadores y obtener los datos de usuario
					// correspondientes
					for (String jugadorId : jugadorIds) {
						// Consultar la información del usuario en base al ID
						String consultaUsuario = "SELECT * FROM Usuario WHERE dni = '" + jugadorId + "'";
						ResultSet resultSetUsuario = prompt.executeQuery(consultaUsuario);

						// Si se encuentra el usuario
						if (resultSetUsuario.next()) {
							// Obtener los datos del usuario y crear un objeto Usuario
							String nombre = resultSetUsuario.getString("nombre");
							String apellidos = resultSetUsuario.getString("apellidos");
							int edad = resultSetUsuario.getInt("edad");
							int fichas = resultSetUsuario.getInt("nFichas");

							Cliente jugador = new Cliente(jugadorId, nombre, apellidos, edad, fichas);
							jugadores.add(jugador);
						}
					}
				}
			}
		} catch (SQLException e) {
			//e.printStackTrace();
			// Manejar la excepción según sea necesario (por ejemplo, lanzarla nuevamente o
			// devolver una lista vacía)
		}catch(NullPointerException e) {
			
		}
		return jugadores;
	}

	public boolean modificarMesa(Mesa m) {
		try {
			prompt.executeUpdate("UPDATE Mesas SET id = " + "'" + m.getId() + "', juego = '" + m.getJuego()
					+ "', jugadores = " + m.getJugadores() + " WHERE id = '" + m.getId() + "'");
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	// Devuelve las mesas con dicho juego
	public ArrayList<Mesa> busquedaMesa(String juego) {
		// ArrayList para almacenar las mesas encontradas
		ArrayList<Mesa> mesas = new ArrayList<>();

		// Sentencia SQL para buscar mesas por juego
		String sqlQuery = "SELECT * FROM Mesas WHERE (";
		if (juego != null) {
			sqlQuery = sqlQuery + "Mesas.juego LIKE " + "'%" + juego + "%'" + " AND ";
		}
		sqlQuery = sqlQuery + '1' + ")";

		try {
			// Ejecutar la consulta
			ResultSet resultSet = prompt.executeQuery(sqlQuery);

			// Iterar a través de los resultados y crear objetos Mesa
			while (resultSet.next()) {
				String id = resultSet.getString("id");
				String juegos = resultSet.getString("juego");
				String jugadores = resultSet.getString("jugadores"); // Suponiendo que los jugadores están
																		// almacenados como una cadena
																		// separada por comas

				// Crear objeto Mesa y agregarlo a la lista
				Mesa mesa = new Mesa(id, juegos, jugadores);
//				mesa.print();
				mesas.add(mesa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return mesas;
	}

	public boolean existeUsuario(String dni) {
		try {
			rs = prompt.executeQuery("SELECT *  FROM Usuario WHERE Usuario.dni = " + "'" + dni + "'");
			return rs.next();
		} catch (SQLException e) {
			return false;
		}
	}

	public boolean anadirJugador(String jugadorId, String mesaId) {
		if (!existeUsuario(jugadorId) || !existeMesa(mesaId)) {
			return false;
		}
		// Sentencia SQL para actualizar la mesa y agregar el jugador
		String sqlQuery = "UPDATE Mesas\r\n" + "SET jugadores = CASE\r\n"
				+ "                WHEN jugadores IS NULL THEN '," + jugadorId + "'\r\n"
				+ "                ELSE CONCAT(jugadores, '," + jugadorId + "')\r\n" + "             END\r\n"
				+ "WHERE id = '" + mesaId + "'";
		try {
			// Ejecutar la actualización
			int filasActualizadas = prompt.executeUpdate(sqlQuery);

			// Comprobar si se ha actualizado al menos una fila
			if (filasActualizadas > 0) {
				return true; // Jugador añadido exitosamente
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false; // No se pudo añadir el jugador
	}

	public boolean quitarJugador(String jugadorId, String mesaId) {
		if (!existeUsuario(jugadorId) || !existeMesa(mesaId)) {
			return false;
		}
		// Sentencia SQL para actualizar la mesa y quitar el jugador
		String sqlQuery = "UPDATE Mesas SET jugadores = REPLACE(jugadores, '," + jugadorId + "', '') WHERE id = '"
				+ mesaId + "'";

		try {
			// Ejecutar la actualización
			int filasActualizadas = prompt.executeUpdate(sqlQuery);

			// Comprobar si se ha actualizado al menos una fila
			if (filasActualizadas > 0) {
				return true; // Jugador quitado exitosamente
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false; // No se pudo quitar el jugador
	}

}
