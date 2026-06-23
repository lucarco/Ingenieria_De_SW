package bbdd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import usuario.model.Cliente;
import bbdd.BBDD;
import parking.model.Coche;
import parking.model.Reserva;

/**
 * Clase de la base de datos de Parking encargada de conectar la base de datos con el sistema.
 * @author Marta Hernández y Alejandro Parra
 *
 */
public class BBDDparking extends BBDD {

	
	/**
	 * Constructor propio de la clase
	 * @author Marta Hernández y Alejandro Parra
	 * @throws SQLException
	 */
	public BBDDparking() throws SQLException {
		super();
	}

	/**
	 * Inserta los datos del coche dentro de la tabla de Parking de la base de datos.
	 * @author Marta Hernández y Alejandro Parra
	 * @param c
	 * @param dni
	 * @return true 
	 */
	public boolean ocuparPlaza(Coche c, String dni) {
		try {

			prompt.executeUpdate("INSERT INTO Parking VALUES ( " + "'" + c.getMatricula() + "','" + dni + "','"
					+ LocalDate.now() + "','" + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "','"
					+ c.getMarca() + "','" + c.getColor() + "')");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Borra los datos del coche dentro de la tabla de Parking de la base de datos.
	 * @author Marta Hernández y Alejandro Parra
	 * @param matricula
	 * @return
	 */
	public boolean desOcuparPlaza(String matricula) {
		try {

			prompt.executeUpdate("DELETE FROM Parking WHERE Parking.matricula = " + "'" + matricula + "'");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}


	/**
	 * Inserta una reserva dentro de la lista de reservas , dependiendo de los parámetros introducidos.
	 * @author Marta Hernández y Alejandro Parra
	 * @param fecha
	 * @param dni
	 * @param time
	 * @param marca
	 * @param color
	 * @return reservas
	 */
	public ArrayList<Reserva> busquedaReservas(LocalDate fecha, String dni, LocalTime time, String marca, String color) {

		String sqlp = "SELECT * FROM Parking WHERE (";
		if (fecha != null)
			sqlp = sqlp + "Parking.fecha = " + '"' + fecha + '"' + " AND ";
		if (!dni.equals("null"))
			sqlp = sqlp + "Parking.dni = " + '"' + dni + '"' + " AND ";
		if (time != null)
			sqlp = sqlp + "Parking.hora = " + "'"+time.format(DateTimeFormatter.ofPattern("HH:mm:ss"))+ "'" + " AND ";
		if (!marca.equals("null"))
			sqlp = sqlp + "Parking.marca = " + '"' + marca + '"' + " AND ";
		if (!color.equals("null"))
			sqlp = sqlp + "Parking.color = " + '"' + color + '"' + " AND ";
		sqlp = sqlp + "1" + ")";

		
		System.out.println(sqlp);
		try {
			Reserva r = null;
			ResultSet result = prompt.executeQuery(sqlp);
			ArrayList<Reserva> reservas = new ArrayList<>();
			while (result.next()) {
				String matricula = result.getString("matricula");
				String dni2 = result.getString("dni");
				LocalDate fechaReserva = LocalDate.parse(result.getString("fecha"));
				LocalTime hora = LocalTime.parse(result.getString("hora"));
				String marca2 = result.getString("marca");
				String color2 = result.getString("color");
				 r = new Reserva(new Coche(matricula, marca2, color2), fechaReserva, dni2, hora);
				
				
				reservas.add(r);
			}
			if (r == null) JOptionPane.showMessageDialog(null,"No se encuentran reservas con los parámetros establecidos");
			return reservas;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	/**
	 * Devuelve true en el caso de que exista la matrícula introducida, false en caso contrario
	 * @author Marta Hernández y Alejandro Parra
	 * @param matricula
	 * @return
	 */
	public boolean existeCoche(String matricula) {
		try {
			ResultSet result = prompt.executeQuery("SELECT * FROM Parking WHERE matricula = '" + matricula + "'");
			return result.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Devuelve true en el caso de que exista el usuario con el dni introducido, false en caso contrario
	 * @author Marta Hernández y Alejandro Parra
	 * @param dni
	 * @return
	 */
	public boolean existeUsuario(String dni) {
		try {
			ResultSet result = prompt.executeQuery("SELECT *  FROM Usuario WHERE Usuario.dni = " + "'" + dni + "'");
			return result.next();
		} catch (SQLException e) {
			return false;
		}
	}
	
	/**
	 * Permite obtener una reserva en el caso de que la matrícula pertenezca al sistema, devuelve null en caso contrario.
	 * @author Marta Hernández y Alejandro Parra
	 * @param matricula
	 * @return
	 */
	public Reserva consultaReserva(String matricula) {
		try {
			ResultSet result = prompt.executeQuery("SELECT * FROM Parking WHERE Parking.matricula = " + "'" + matricula + "'");
			String matricula2 = null;
			String dni = null;
			LocalDate fechaReserva = null;
			LocalTime hora = null;
			String marca = null;
			String color = null;
			if (result.next()) {
				matricula2 = result.getString("matricula");
				dni = result.getString("dni");
				fechaReserva = LocalDate.parse(result.getString("fecha"));
				hora = LocalTime.parse(result.getString("hora"));
				marca = result.getString("marca");
				color = result.getString("color");
			}
			return new Reserva(new Coche(matricula2, marca, color), fechaReserva, dni, hora);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
