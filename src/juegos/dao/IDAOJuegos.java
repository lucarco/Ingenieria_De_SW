package juegos.dao;

import java.util.ArrayList;

import juegos.model.Juego;


/**
 * @author Alejandro Alba Mammeri y Daniel Menéndez Crespo
 *
 * Esta interfaz define las operaciones que deben ser implementadas por cualquier clase que desee
 * actuar como un DAO para la gestión de juegos.
 * 
 */
public interface IDAOJuegos {
	
	/**
     * Registra un nuevo juego en el sistema.
     *
     * @param j El juego a registrar.
     * @return true si el juego se registró con éxito, false en caso contrario.
     */
	public boolean darAltaJuego(Juego j);
	
	/**
     * Da de baja un juego del sistema.
     *
     * @param id El identificador del juego a eliminar.
     * @return true si el juego se eliminó con éxito, false en caso contrario.
     */
	public boolean darBajaJuego(String id);
	
	/**
     * Consulta un juego en el sistema por su identificador y lo devuelve.
     *
     * @param id El identificador del juego a consultar.
     * @return El juego consultado, o null si no se encontró ningún juego con ese identificador.
     */
	public Juego consultarJuego(String id);
	
	/**
     * Verifica si un juego existe en el sistema dado su identificador.
     *
     * @param id El identificador del juego a verificar.
     * @return true si el juego existe en el sistema, false en caso contrario.
     */
	public boolean existeJuego(String id);
	
	 /**
     * Permite a un jugador realizar una apuesta en un juego; modificando sus fichas.
     *
     * @param id      El identificador del juego en el que se desea apostar.
     * @param fichas  La cantidad de fichas que ha ganado/perdido.
     * @return true si la apuesta se realizó con éxito, false en caso contrario.
     */
	public boolean apostar(String id, int fichas);
	
	 /**
     * Establece la probabilidad de ganar de un juego.
     *
     * @param id El identificador del juego cuya probabilidad se desea establecer.
     * @param p  La nueva probabilidad de ganar del juego.
     * @return true si la probabilidad se estableció con éxito, false en caso contrario.
     */
	public boolean setProbabilidad(String id, int p);

	/**
     * Obtiene la lista de todos los juegos registrados en el sistema.
     *
     * @return La lista de juegos registrados.
     */
	public ArrayList<Juego> getJuegos();

}
