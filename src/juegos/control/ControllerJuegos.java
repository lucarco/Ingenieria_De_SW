package juegos.control;

import java.util.ArrayList;

import juegos.model.Juego;
import juegos.sa.FachadaSAJuegosImp;
import juegos.sa.IFachadaSAJuegos;

/**
 * Clase que actua como controlador de los juegos en el sistema. 
 * Llama a las funciones de la interfaz de la fachada de servicio de aplicacion para interactuar con los juegos.
 * 
 * Esta clase proporciona metodos para realizar operaciones relacionadas con los juegos, como dar de alta o baja un juego, 
 * consultar informacion sobre un juego, verificar la existencia de un juego, realizar apuestas en un juego y establecer
 * la probabilidad de ganancia para un juego.
 * 
 * @author Alejandro Alba Mammeri
 * @author Daniel Menendez Crespo
 */
public class ControllerJuegos {
	
	/** Instancia de la interfaz de la fachada de servicio de aplicacion de juegos */
	IFachadaSAJuegos s = new FachadaSAJuegosImp();

	/**
	 * Da de alta un juego en el sistema con el identificador y la probabilidad especificados.
	 * 
	 * @param id El identificador del juego a dar de alta.
	 * @param p La probabilidad de ganancia del juego a dar de alta.
	 * @return true si el juego se dio de alta correctamente, false de lo contrario.
	 */
	public boolean darAltaJuego(String id, int p) {
		Juego j = new Juego(id, p);
		return s.darAltaJuego(j);
	}

	/**
	 * Da de baja un juego del sistema dado su identificador.
	 * 
	 * @param id El identificador del juego a dar de baja.
	 * @return true si el juego se dio de baja correctamente, false de lo contrario.
	 */
	public boolean darBajaJuego(String id) {
		return s.darBajaJuego(id);
	}

	/**
	 * Consulta la informaci�n de un juego en el sistema dado su identificador.
	 * 
	 * @param id El identificador del juego a consultar.
	 * @return El objeto {@link Juego} correspondiente al juego consultado, o null si no se encuentra.
	 */
	public Juego consultarJuego(String id) {
		return s.consultarJuego(id);
	}

	/**
	 * Verifica si un juego existe en el sistema dado su identificador.
	 * 
	 * @param id El identificador del juego a verificar.
	 * @return true si el juego existe en el sistema, false de lo contrario.
	 */
	public boolean existeJuego(String id) {
		return s.existeJuego(id);
	}

	/**
	 * Realiza una apuesta en un juego espec�fico.
	 * 
	 * @param id El identificador del usuario que realiza la apuesta.
	 * @param fichas La cantidad de fichas a apostar.
	 * @return true si la apuesta se realiz� correctamente, false de lo contrario.
	 */
	public boolean apostar(String id, int fichas) {
		return s.apostar(id, fichas);
	}

	/**
	 * Establece la probabilidad de ganancia para un juego espec�fico.
	 * 
	 * @param id El identificador del juego al que se establecer� la probabilidad.
	 * @param p La probabilidad de ganancia a establecer.
	 * @return true si la probabilidad se estableci� correctamente, false de lo contrario.
	 */
	public boolean setProbabilidad(String id, int p) {
		return s.setProbabilidad(id, p);
	}
	
	/**
	 * Obtiene la lista de juegos disponibles en el sistema.
	 * 
	 * @return ArrayList de objetos {@link Juego} que representan los juegos disponibles.
	 */
	public ArrayList<Juego> getJuegos(){
		return s.getJuegos();
	}
}
