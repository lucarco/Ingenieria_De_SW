package mesas.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import bbdd.BBDDMesas;
import mesas.model.Mesa;
import usuario.model.Cliente;

/**
 * @author Lucas Arranz y Alejandro Ramos
 * 
 *	clase que accede directamente a la clase BBDD
 *	( la cual accede finalemente a la BBDD en SQL)
 */
public class DAOMesasImp implements IDAOMesa {
	private BBDDMesas _bbdd;

	/**
	 * constructor
	 */
	public DAOMesasImp() {
		init();
	}

	/**
	 * funcion del contructor que accede a la BBDD
	 */
	private void init() {
		try {
			_bbdd = new BBDDMesas();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean altaMesa(Mesa m) {
		return _bbdd.altaMesa(m);

	}

	@Override
	public boolean bajaMesa(String id) {
		return _bbdd.bajaMesa(id);
	}

	@Override
	public ArrayList<Mesa> busquedaMesa(String juego) {
		return _bbdd.busquedaMesa(juego);
	}

	@Override
	public boolean modificarMesa(Mesa m) {
		return _bbdd.modificarMesa(m);
	}

	@Override
	public Mesa consultarMesa(String id) {
		return _bbdd.consultaMesa(id);
	}

	@Override
	public boolean existeMesa(String id) {
		return _bbdd.existeMesa(id);
	}

	@Override
	public boolean anadirJugador(String jugadorId, String mesaId) {
		return _bbdd.anadirJugador(jugadorId, mesaId);
	}

	@Override
	public boolean quitarJugador(String jugadorId, String mesaId) {
		return _bbdd.quitarJugador(jugadorId, mesaId);
	}

	@Override
	public ArrayList<Cliente> obtenerJugadores(String mesaId) {
		return _bbdd.obtenerJugadores(mesaId);
	}

}
