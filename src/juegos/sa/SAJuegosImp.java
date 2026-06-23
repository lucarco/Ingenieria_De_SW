package juegos.sa;

import java.util.ArrayList;

import juegos.dao.FachadaDAOJuegosImp;
import juegos.dao.IFachadaDAOJuegos;
import juegos.model.Juego;

/**
 * @author Alejandro Alba Mammeri y Daniel Menéndez Crespo
 *
 * Implementación de la interfaz de SAJuegos. Se llaman a las funciones de acceso a datos.
 * 
 */

public class SAJuegosImp implements ISAJuegos {
	
	private IFachadaDAOJuegos _iFachadaDAOJuegos;
	
	public SAJuegosImp() {
		_iFachadaDAOJuegos = new FachadaDAOJuegosImp();
	}

	@Override
	public boolean darAltaJuego(Juego j) {
		return !_iFachadaDAOJuegos.existeJuego(j.getId()) &&_iFachadaDAOJuegos.darAltaJuego(j);
	}

	@Override
	public boolean darBajaJuego(String id) {
		return _iFachadaDAOJuegos.existeJuego(id) && _iFachadaDAOJuegos.darBajaJuego(id);
	}

	@Override
	public Juego consultarJuego(String id) {
		if (_iFachadaDAOJuegos.existeJuego(id)) {
			return _iFachadaDAOJuegos.consultarJuego(id);
		}
		return null;
	}

	@Override
	public boolean existeJuego(String id) {
		return _iFachadaDAOJuegos.existeJuego(id);
	}

	@Override
	public boolean apostar(String id, int fichas) {
		return _iFachadaDAOJuegos.apostar(id, fichas);
	}

	@Override
	public boolean setProbabilidad(String id, int p) {
		return _iFachadaDAOJuegos.existeJuego(id) && _iFachadaDAOJuegos.setProbabilidad(id, p);
	}

	@Override
	public ArrayList<Juego> getJuegos() {
		return _iFachadaDAOJuegos.getJuegos();
	}

	
	
}
