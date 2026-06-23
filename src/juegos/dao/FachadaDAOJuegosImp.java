package juegos.dao;

import java.util.ArrayList;

import juegos.model.Juego;

/**
 * @author USUARIO
 *
 * Esta clase implementa la interfaz IFachadaDAOJuegos y actúa como una fachada para el acceso a los juegos.
 * 
 */
public class FachadaDAOJuegosImp implements IFachadaDAOJuegos{
	
	private IDAOJuegos _iDAOJuegos;

	public FachadaDAOJuegosImp() {
		this._iDAOJuegos = new DAOJuegosImp();
	}

	@Override
	public boolean darAltaJuego(Juego j) {
		return _iDAOJuegos.darAltaJuego(j);
	}

	@Override
	public boolean darBajaJuego(String id) {
		return _iDAOJuegos.darBajaJuego(id);
	}

	@Override
	public Juego consultarJuego(String id) {
		return _iDAOJuegos.consultarJuego(id);
	}

	public boolean existeJuego(String id) {
		return _iDAOJuegos.existeJuego(id);
	}

	public boolean apostar(String id, int fichas) {
		return _iDAOJuegos.apostar(id, fichas);
	}

	public boolean setProbabilidad(String id, int p) {
		return _iDAOJuegos.setProbabilidad(id, p);
	}

	public ArrayList<Juego> getJuegos(){
		return _iDAOJuegos.getJuegos();
	}
}
