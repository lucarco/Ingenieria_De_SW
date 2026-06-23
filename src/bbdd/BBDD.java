package bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Alejandro Remiro, Adrian Valverde
 */
public abstract class BBDD {

	Connection conn = null;
	
	public Statement prompt;
	
	private Connection conexion ;

	public BBDD() throws SQLException {
		String url = "jdbc:mysql://error500.ddns.net:3306/db";
		String usuario = "CASINO";
		String contrasena = "casinoLS";

		conexion = DriverManager.getConnection(url, usuario, contrasena);

		prompt = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

	}

	

}
