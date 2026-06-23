package juegos.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import bbdd.BBDDJuegos;
import juegos.model.Juego;

/**
 * @author Alejandro Alba Mammeri y Daniel Menéndez Crespo
 *
 * Implementación de la interfaz de DAOJuegos. Se llaman a las funciones de acceso a datos.
 * 
 */
public class DAOJuegosImp implements IDAOJuegos {
	
	private BBDDJuegos _bbdd;
	
	public DAOJuegosImp() {
		init();
	}
	
	private void init() {
		try {
			_bbdd = new BBDDJuegos();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean darAltaJuego(Juego j) {
		return _bbdd.altaJuego(j);
	}

	@Override
	public boolean darBajaJuego(String id) {
		return _bbdd.eliminarJuego(id);
	}

	@Override
	public Juego consultarJuego(String id) {
		return _bbdd.consultarJuego(id);
	}

	@Override
	public boolean existeJuego(String id) {
		return _bbdd.existeJuego(id);
	}

	@Override
	public boolean apostar(String id, int fichas) {
		return _bbdd.apostar(id, fichas);
	}

	@Override
	public boolean setProbabilidad(String id, int p) {
		return _bbdd.setProbabilidad(id, p);
	}
	
	public ArrayList<Juego> getJuegos(){
		return _bbdd.getJuegos();
	}
	
}
