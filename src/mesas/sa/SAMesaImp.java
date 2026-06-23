package mesas.sa;

import java.util.ArrayList;
import java.util.Random;

import mesas.dao.FachadaDAOMesaImp;
import mesas.dao.IFachadaDAOMesa;
import mesas.model.Mesa;
import usuario.model.Cliente;

/**
 * @author Lucas Arranz
 * 
 *	Implementa la interfaz ISAMesa
 *	Todas sus funciones utilizan la interfaz IFachadaDAOMesa
 */
public class SAMesaImp implements ISAMesa {

	private IFachadaDAOMesa _iFachadaDAOMesa;

	/**
	 * constructor
	 */
	public SAMesaImp() {
		this._iFachadaDAOMesa = new FachadaDAOMesaImp();
	}

	/**
	 *	Comprueba que la mesa no existe en la BBDD
	 *	En tal caso la anyade
	 */
	@Override
	public boolean altaMesa(Mesa m) {
		boolean MesaAlta = false;
		if (!_iFachadaDAOMesa.existeMesa(m.getId())) {
			MesaAlta = _iFachadaDAOMesa.altaMesa(m);
		}
		return MesaAlta;
	}

	/**
	 *	Comprueba que la mesa existe en la BBDD
	 *	En tal caso la borra
	 */
	@Override
	public boolean bajaMesa(String id) {
		boolean MesaBaja = false;
		if (_iFachadaDAOMesa.existeMesa(id)) {
			MesaBaja = _iFachadaDAOMesa.bajaMesa(id);
		}
		return MesaBaja;
	}

	@Override
	public ArrayList<Mesa> busquedaMesa(String juego) {
		return _iFachadaDAOMesa.busquedaMesa(juego);
	}

	/**
	 *	Comprueba que la mesa existe en la BBDD
	 *	En tal aso la modifica
	 */
	@Override
	public boolean modificarMesa(Mesa m) {
		boolean MesaMod = false;
		if (_iFachadaDAOMesa.existeMesa(m.getId())) {
			MesaMod = _iFachadaDAOMesa.modificarMesa(m);
		}
		return MesaMod;
	}

	@Override
	public Mesa consultarMesa(String id) {
		if (_iFachadaDAOMesa.existeMesa(id)) {
			return _iFachadaDAOMesa.consultarMesa(id);
		}
		return null;
	}

	@Override
	public boolean quitarJugador(String jugadorId, String mesaId) {
		return _iFachadaDAOMesa.quitarJugador(jugadorId, mesaId);
	}

	@Override
	public boolean anadirJugador(String jugadorId, String mesaId) {
		return _iFachadaDAOMesa.anadirJugador(jugadorId, mesaId);
	}

	/**
	 *	Simula si el evento sucedió o no creando un instancia Random()
	 * y viendo si es menor que el parámetro p recibido
	 */
	@Override
	public boolean simularJuego(int p) {

		boolean ok = false;
		Random random = new Random();
		int randomNumber = random.nextInt(100);
        if(randomNumber <= p) {
        	ok = true;
        }
		
		
		return ok;
	}

	@Override
	public ArrayList<Cliente> obtenerJugadores(String mesaId) {
		return _iFachadaDAOMesa.obtenerJugadores(mesaId);
	}

}
