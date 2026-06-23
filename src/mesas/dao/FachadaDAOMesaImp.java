package mesas.dao;

import java.util.ArrayList;

import mesas.model.Mesa;
import usuario.model.Cliente;

/**
 * @author Lucas Arranz y Alejandro Ramos
 * 
 * Todas sus funciones devuelven lo pedido desde la interfaz IDAOMesa
 *
 */
public class FachadaDAOMesaImp implements IFachadaDAOMesa {

	
	private IDAOMesa _iDAOMesa;


	/**
	 * constructor
	 */
	public FachadaDAOMesaImp() {
		this._iDAOMesa = new DAOMesasImp();
	}


	@Override
	public boolean altaMesa(Mesa m) {
		return _iDAOMesa.altaMesa(m);
	}

	@Override
	public boolean bajaMesa(String id) {
		return _iDAOMesa.bajaMesa(id);
	}

	@Override
	public ArrayList<Mesa> busquedaMesa(String juego) {
		return _iDAOMesa.busquedaMesa(juego);
	}

	@Override
	public boolean modificarMesa(Mesa m) {
		return _iDAOMesa.modificarMesa(m);
	}

	@Override
	public Mesa consultarMesa(String id) {
		return _iDAOMesa.consultarMesa(id);
	}

	@Override
	public boolean existeMesa(String id) {
		return _iDAOMesa.existeMesa(id);
	}

	@Override
	public boolean quitarJugador(String jugadorId, String mesaId) {
		return _iDAOMesa.quitarJugador(jugadorId, mesaId);
	}

	@Override
	public boolean anadirJugador(String jugadorId, String mesaId) {
		return _iDAOMesa.anadirJugador(jugadorId, mesaId);
	}

	@Override
	public ArrayList<Cliente> obtenerJugadores(String mesaId) {
		return _iDAOMesa.obtenerJugadores(mesaId);
	}
	
}
