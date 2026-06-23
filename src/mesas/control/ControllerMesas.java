package mesas.control;

import java.util.ArrayList;

import mesas.model.Mesa;
import mesas.sa.FachadaSAMesaImp;
import mesas.sa.IFachadaSAMesa;
import usuario.model.Cliente;

/**
 * @author Lucas Arranz y Alejandro Ramos
 *
 *	Controller de todo el módulo de mesas
 */


public class ControllerMesas {
	
	// inicializacion de la interfaz de la fachada
	IFachadaSAMesa s = new FachadaSAMesaImp();


	public boolean altaMesa(String id, String juego, String jugadores) {
		return s.altaMesa(new Mesa(id, juego, jugadores));
	}

	public boolean bajaMesa(String id) {
		return s.bajaMesa(id);
	}

	public Mesa consultaMesa(String id) {
		return s.consultarMesa(id);
	}

	public ArrayList<Mesa> busquedaMesa(String juego) {
		return s.busquedaMesa(juego);
	}

	public boolean modificarMesa(String id, String juego, String jugadores) {
		return s.modificarMesa(new Mesa(id, juego, jugadores));
	}

	public boolean quitarJugador(String jugadorId, String mesaId) {
		return s.quitarJugador(jugadorId, mesaId);
	}

	public boolean anadirJugador(String jugadorId, String mesaId) {
		return s.anadirJugador(jugadorId, mesaId);
	}

	public boolean simularJuego(int p) {
		return s.simularJuego(p);
	}
	
	public ArrayList<Cliente> obtenerJugadores(String mesaId) {
		return s.obtenerJugadores(mesaId);
	}
	
	

}
