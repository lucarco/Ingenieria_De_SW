package bbdd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import usuario.model.Cliente;
import usuario.model.Trabajador;
import usuario.model.Usuario;

/**
 * Alejandro Remiro, Adrian Valverde
 */
public class BBDDUsuario extends BBDD{
	
	ResultSet rs;

	public BBDDUsuario() throws SQLException {
		super();
	}
	
	public boolean existeUsuario(String dni) {
		try {
			rs = prompt.executeQuery("SELECT *  FROM Usuario WHERE Usuario.dni = " + "'" + dni + "'");
			return rs.next();
		} catch (SQLException e) {
			return false;
		}
	}

	public boolean altaUsuario(Usuario u) {
		try {
			prompt.executeUpdate("INSERT INTO Usuario VALUES ('" + u.getDni() + "', '" + u.getNombre() + "', '"
					+ u.getApellidos() + "', " + u.getEdad() + "," + u.getFichas() + "," + u.getRol() + ")");
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public boolean bajaUsuario(String dni) {
		try {
			prompt.executeUpdate("DELETE FROM Usuario WHERE Usuario.dni = " + '"' + dni + '"');
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Usuario consultaUsuario(String dni) {
		try {
			rs = prompt.executeQuery("SELECT * FROM Usuario WHERE Usuario.dni = " + "'" + dni + "'");
			return creaUsuario(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Usuario> busquedaUsuario(String nombre, String apellidos, int edad, int nFichas) {
		String sqlp = "SELECT * FROM Usuario WHERE (";
		if (nombre != null)
			sqlp = sqlp + "Usuario.nombre LIKE " + "'%" + nombre + "%'" + " AND ";
		if (apellidos != null)
			sqlp = sqlp + "Usuario.apellidos LIKE " + "'%" + apellidos + "%'" + " AND ";
		if (edad != -1)
			sqlp = sqlp + "Usuario.edad >= " + edad + " AND ";
		if (nFichas != -1)
			sqlp = sqlp + "Usuario.nFichas >= " + nFichas + " AND ";
		sqlp = sqlp + "1" + ")";

		try {
			ArrayList<Usuario> l = new ArrayList<>();
			Usuario usu = null;
			String dni;
			int rol;
			rs = prompt.executeQuery(sqlp);
			while (rs.next()) {
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
				l.add(usu);
			}
			return l;
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

	public boolean modificarUsuario(Usuario u) {
		try {
			prompt.executeUpdate("UPDATE Usuario SET nombre = " + "'" + u.getNombre() + "', apellidos = '"
					+ u.getApellidos() + "', edad = " + u.getEdad() + ", nFichas = " + u.getFichas() + ", rol = "
					+ u.getRol() + " WHERE dni = '" + u.getDni() + "'");
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public boolean comprarFichas(int nFichas, String dni) {
		try {
			rs = prompt.executeQuery("SELECT * FROM Usuario WHERE Usuario.dni = " + "'" + dni + "'");
			String nombre;
			String apellidos;
			int edad;
			int rol;
			int nFichaOld = 0;
			if (rs.next()) {
				dni = rs.getString("dni");
				nombre = rs.getString("nombre");
				apellidos = rs.getString("apellidos");
				edad = rs.getInt("edad");
				nFichaOld = rs.getInt("nFichas");
				rol = rs.getInt("rol");
			}
			
			nFichaOld += nFichas;
			
			prompt.executeUpdate("UPDATE Usuario SET nFichas = " + nFichaOld +" WHERE Usuario.dni = " + "'" + dni + "'");
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	public boolean venderFichas(int nFichas, String dni) {
		try {
			rs = prompt.executeQuery("SELECT * FROM Usuario WHERE Usuario.dni = " + "'" + dni + "'");
			String nombre;
			String apellidos;
			int edad;
			int rol;
			int nFichaOld = 0;
			if (rs.next()) {
				dni = rs.getString("dni");
				nombre = rs.getString("nombre");
				apellidos = rs.getString("apellidos");
				edad = rs.getInt("edad");
				nFichaOld = rs.getInt("nFichas");
				rol = rs.getInt("rol");
			}
			
			nFichaOld -= nFichas;
			
			prompt.executeUpdate("UPDATE Usuario SET nFichas = " + nFichaOld +" WHERE Usuario.dni = " + "'" + dni + "'");
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	public boolean saldoCorrecto(String dni, int nFichas) {
		try {
			rs = prompt.executeQuery("SELECT * FROM Usuario WHERE Usuario.dni = " + "'" + dni + "'");
			String nombre;
			String apellidos;
			int edad;
			int rol;
			int nFichaOld = 0;
			if (rs.next()) {
				dni = rs.getString("dni");
				nombre = rs.getString("nombre");
				apellidos = rs.getString("apellidos");
				edad = rs.getInt("edad");
				nFichaOld = rs.getInt("nFichas");
				rol = rs.getInt("rol");
			}
			return nFichaOld - nFichas >= 0;
		} catch (SQLException e) {
			return false;
		}
	}

}
