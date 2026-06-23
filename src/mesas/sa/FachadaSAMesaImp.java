package mesas.sa;

import java.util.ArrayList;

import mesas.model.Mesa;
import usuario.model.Cliente;

/**
 * @author Lucas Arranz
 * 
 *         Fachada que implementa la interfaz IFachadaSAMesa
 * 
 *         Devuelve en todas sus funciones la funcion con el mismo nombre de la
 *         interfaz ISAMesa
 */
public class FachadaSAMesaImp implements IFachadaSAMesa {

	private ISAMesa _iSAMesa;

	/**
	 * constructor
	 */
	public FachadaSAMesaImp() {
		this._iSAMesa = new SAMesaImp();
	}

	@Override
	public boolean altaMesa(Mesa m) {
		return _iSAMesa.altaMesa(m);
	}

	@Override
	public boolean bajaMesa(String id) {
		return _iSAMesa.bajaMesa(id);
	}

	@Override
	public ArrayList<Mesa> busquedaMesa(String juego) {
		return _iSAMesa.busquedaMesa(juego);
	}

	@Override
	public boolean modificarMesa(Mesa m) {
		return _iSAMesa.modificarMesa(m);
	}

	@Override
	public Mesa consultarMesa(String id) {
		return _iSAMesa.consultarMesa(id);
	}

	@Override
	public boolean quitarJugador(String jugadorId, String mesaId) {
		return _iSAMesa.quitarJugador(jugadorId, mesaId);
	}

	@Override
	public boolean anadirJugador(String jugadorId, String mesaId) {
		return _iSAMesa.anadirJugador(jugadorId, mesaId);
	}

	@Override
	public boolean simularJuego(int p) {
		return _iSAMesa.simularJuego(p);
	}

	@Override
	public ArrayList<Cliente> obtenerJugadores(String mesaId) {
		return _iSAMesa.obtenerJugadores(mesaId);
	}

}
