package juegos.sa;

import java.util.ArrayList;

import juegos.model.Juego;

/**
 * Interfaz que define metodos para interactuar con juegos en un sistema de apuestas.
 * Implementaciones de esta interfaz proporcionaron funcionalidades especificas para acceder y manipular datos de juegos.
 * 
 * @author Alejandro Alba Mammeri y Daniel Menendez Crespo
 */
public interface ISAJuegos {

	/**
	 * Da de alta un juego en el sistema.
	 * 
	 * @param j El juego a dar de alta.
	 * @return true si el juego se dio de alta correctamente, false de lo contrario.
	 */
	public boolean darAltaJuego(Juego j);

	/**
	 * Da de baja un juego del sistema.
	 * 
	 * @param id El identificador del juego a dar de baja.
	 * @return true si el juego se dio de baja correctamente, false de lo contrario.
	 */
	public boolean darBajaJuego(String id);

	/**
	 * Consulta un juego en el sistema dado su identificador.
	 * 
	 * @param id El identificador del juego a consultar.
	 * @return El objeto {@link Juego} correspondiente al juego consultado, o null si no se encuentra.
	 */
	public Juego consultarJuego(String id);

	/**
	 * Verifica si un juego existe en el sistema dado su identificador.
	 * 
	 * @param id El identificador del juego a verificar.
	 * @return true si el juego existe en el sistema, false de lo contrario.
	 */
	public boolean existeJuego(String id);

	/**
	 * Realiza una apuesta en un juego espec�fico.
	 * 
	 * @param id El identificador del usuario que realiza la apuesta.
	 * @param fichas La cantidad de fichas a apostar.
	 * @return true si la apuesta se realiz� correctamente, false de lo contrario.
	 */
	public boolean apostar(String id, int fichas);
	
	/**
	 * Establece la probabilidad de ganancia para un juego espec�fico.
	 * 
	 * @param id El identificador del juego al que se establecer� la probabilidad.
	 * @param p La probabilidad de ganancia a establecer.
	 * @return true si la probabilidad se estableci� correctamente, false de lo contrario.
	 */
	public boolean setProbabilidad(String id, int p);

	/**
	 * Obtiene la lista de juegos disponibles en el sistema.
	 * 
	 * @return ArrayList de objetos {@link Juego} que representan los juegos disponibles.
	 */
	public ArrayList<Juego> getJuegos();
	
}
