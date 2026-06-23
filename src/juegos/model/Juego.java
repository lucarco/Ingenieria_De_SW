package juegos.model;

/**
 * @author Alejandro Alba Mammeri y Daniel Menéndez Crespo
 *
 * Clase para representar los juegos del casino con id y probabilidad de ganar.
 * 
 */
public class Juego {

	private String id;
	private int p;
	
	/**
     * Constructor de la clase Juego.
     *
     * @author Alejandro Alba Mammeri y Daniel Menéndez Crespo
     * @param id El identificador del juego.
     * @param p  La probabilidad de ganar asociada al juego.
     */
	public Juego(String id, int p) {
		this.id = id;
		this.p = p;
	}
	
	/**
     * Obtiene el ID del juego.
     *
     * @author Alejandro Alba Mammeri y Daniel Menéndez Crespo
     * @return El identificador del juego.
     */
	public String getId() {
		return id;
	}
	
	/**
     * Establece el ID del juego.
     *
     * @author Alejandro Alba Mammeri y Daniel Menéndez Crespo
     * @param id El identificador del juego.
     */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
     * Obtiene la probabilidad de ganar asociada al juego.
     *
     * @author Alejandro Alba Mammeri y Daniel Menéndez Crespo
     * @return La probabilidad de ganar asociada al juego.
     */
	public int getProb() {
		return p;
	}
	
	 /**
     * Configura la probabilidad de ganar asociada al juego.
     *
     * @author Alejandro Alba Mammeri y Daniel Menéndez Crespo
     * @param p La probabilidad de ganar asociada al juego.
     */
	public void setProb(int p) {
		this.p = p;
	}
	
}
