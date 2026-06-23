package bbdd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import juegos.model.Juego;
import usuario.model.Cliente;
import usuario.model.Trabajador;
import usuario.model.Usuario;

/**
 * @author Alejandro Alba Mammeri y Daniel Menéndez Crespo
 *
 * Clase que implementa los métodos de la BBDD de juegos.
 * 
 */
public class BBDDJuegos extends BBDD{
	
	ResultSet rs;

	/**
     * Constructor de la clase BBDDJuegos.
     *
     * @author Alejandro Alba Mammeri y Daniel Menéndez Crespo
     * @throws SQLException Si ocurre un error al establecer la conexión con la base de datos.
     */
	public BBDDJuegos() throws SQLException {
		super();
	}
	
	/**
     * Verifica si existe un juego en la base de datos dado su ID.
     *
     * @author Alejandro Alba Mammeri y Daniel Menéndez Crespo
     * @param id El ID del juego a comprobar.
     * @return true si el juego existe, false en caso contrario.
     */
	public boolean existeJuego(String id) {
		try {
			rs = prompt.executeQuery("SELECT * FROM Juegos WHERE Juegos.id = " + "'" + id + "'");
			return rs.next();
		} catch (SQLException e) {
			return false;
		}
	}

	/**
     * Da de alta un juego en la base de datos.
     *
     * @author Alejandro Alba Mammeri y Daniel Menéndez Crespo
     * @param j El juego a dar de alta.
     * @return true si el alta se realiza correctamente, false si hay algún error.
     */
	public boolean altaJuego(Juego j) {
		try {
			prompt.executeUpdate("INSERT INTO Juegos VALUES ('" + j.getId() + "', " + j.getProb() + ")");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
     * Elimina un juego de la base de datos dado su ID.
     *
     * @author Alejandro Alba Mammeri y Daniel Menéndez Crespo
     * @param id El ID del juego a eliminar.
     * @return true si la eliminación se realiza correctamente, false si hay algún error.
     */
	public boolean eliminarJuego(String id) {
		try {
			prompt.executeUpdate("DELETE FROM Juegos WHERE Juegos.id = " + '"' + id + '"');
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
     * Actualiza la probabilidad de un juego en la base de datos.
     *
     * @author Alejandro Alba Mammeri y Daniel Menéndez Crespo
     * @param id El ID del juego cuya probabilidad se va a actualizar.
     * @param p La nueva probabilidad de ganar del juego.
     * @return true si la actualización se realiza correctamente, false si hay algún error.
     */
	public boolean setProbabilidad(String id, int p) {
		try {
			prompt.executeUpdate("UPDATE Juegos SET probabilidad = " + p + " WHERE id = '" + id + "'");
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	/**
     * Consulta un juego en la base de datos dado su ID.
     *
     * @author Alejandro Alba Mammeri y Daniel Menéndez Crespo
     * @param id El ID del juego a consultar.
     * @return El objeto Juego correspondiente al ID especificado, o null si no se encuentra el juego o hay algún error.
     */
	public Juego consultarJuego(String id) {
		try {
			rs = prompt.executeQuery("SELECT * FROM Juegos WHERE Juegos.id = '" + id + "'");
			return creaJuego(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
     * Modifica las fichas de un usuario de forma acorde a la apuesta y la simulación del juego.
     *
     * @author Alejandro Alba Mammeri y Daniel Menéndez Crespo
     * @param dni El DNI del usuario que realiza la apuesta.
     * @param fichas La cantidad de fichas que se suman o restan a la cuenta del usuario.
     * @return true si la apuesta se realiza correctamente, false si hay algún error.
     */
	public boolean apostar(String dni, int fichas) {
		try {
			
			int nuevasFichas = consultaUsuario(dni).getFichas() + fichas;
			
			prompt.executeUpdate("UPDATE Usuario SET nFichas = " + nuevasFichas + " WHERE Usuario.dni = " +  '"' + dni + '"');
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
     * Crea un objeto Juego a partir de un ResultSet.
     *
     * @author Alejandro Alba Mammeri y Daniel Menéndez Crespo
     * @param rs El ResultSet que contiene los datos del juego.
     * @return El objeto Juego creado a partir de los datos del ResultSet.
     * @throws SQLException Si ocurre un error al acceder a los datos en el ResultSet.
     */
	private Juego creaJuego(ResultSet rs) throws SQLException {
		Juego juego = null;
		int t;
		String id;
		
		if (rs.next()) {
			id = rs.getString("id");
			t = rs.getInt("probabilidad");
			if (t > 0) {
				juego = new Juego(id, t);
			}
		}
		return juego;
	}
	
	private Usuario consultaUsuario(String dni) {
		try {
			rs = prompt.executeQuery("SELECT * FROM Usuario WHERE Usuario.dni = " + "'" + dni + "'");
			return creaUsuario(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	

	private Usuario creaUsuario(ResultSet rs) throws SQLException {
		Usuario usu = null;
		String dni;
		String nombre;
		String apellidos;
		int edad;
		int nFichas;
		int rol;
		if (rs.next()) {
			dni = rs.getString("dni");
			nombre = rs.getString("nombre");
			apellidos = rs.getString("apellidos");
			edad = rs.getInt("edad");
			nFichas = rs.getInt("nFichas");
			rol = rs.getInt("rol");
			if (rol == 0) {
				usu = new Cliente(dni, nombre, apellidos, edad, nFichas);
			} else if (rol == 1) {
				usu = new Trabajador(dni, nombre, apellidos, edad, nFichas);
			}
		}
		return usu;
	}

	/**
     * Obtiene todos los juegos almacenados en la base de datos.
     *
     * @author Alejandro Alba Mammeri y Daniel Menéndez Crespo
     * @return Una lista de objetos Juego que representan todos los juegos almacenados en la base de datos, o null si hay algún error.
     */
	public ArrayList<Juego> getJuegos() {

		String query = "SELECT * FROM Juegos ";
		try {
			Juego j = null;
			ResultSet result = prompt.executeQuery(query);
			ArrayList<Juego> listaJuegos = new ArrayList<>();
			
			while (result.next()) {
				String id = result.getString("id");
				int prob = result.getInt("probabilidad");
				
				 j = new Juego(id, prob);
				 listaJuegos.add(j);
			}
			if (j == null) JOptionPane.showMessageDialog(null,"No existen juegos todavia");
			return listaJuegos;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
