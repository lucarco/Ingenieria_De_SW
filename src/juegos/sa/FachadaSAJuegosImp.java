package juegos.sa;

import java.util.ArrayList;

/**
 * @author Alejandro Alba Mammeri y Daniel Menéndez Crespo
 *
 * Implementación de la interfaz de FachadaSAJuegos. Se llaman a las funciones de servicio de aplicacion.
 * 
 */

import juegos.model.Juego;

public class FachadaSAJuegosImp implements IFachadaSAJuegos {
	
	private ISAJuegos _iSAJuegos;
	
	public FachadaSAJuegosImp() {
		_iSAJuegos = new SAJuegosImp();
	}

	@Override
	public boolean darAltaJuego(Juego j) {
		return _iSAJuegos.darAltaJuego(j);
	}

	@Override
	public boolean darBajaJuego(String id) {
		return _iSAJuegos.darBajaJuego(id);
	}

	@Override
	public Juego consultarJuego(String id) {
		return _iSAJuegos.consultarJuego(id);
	}

	@Override
	public boolean existeJuego(String id) {
		return _iSAJuegos.existeJuego(id);
	}

	@Override
	public boolean apostar(String id, int fichas) {
		return _iSAJuegos.apostar(id, fichas);
	}

	@Override
	public boolean setProbabilidad(String id, int p) {
		return _iSAJuegos.setProbabilidad(id, p);
	}

	@Override
	public ArrayList<Juego> getJuegos() {
		return _iSAJuegos.getJuegos();
	}

}
