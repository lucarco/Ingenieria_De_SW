package mesas.model;

/**
 * @author Lucas Arranz
 * 
 *         clase en la que se basa la tabla de Mesas en SQL
 */
public class Mesa {

	private String id; // id de la mesa
	private String juego; // id del juego. p.ej ruleta/poker
	private String jugadores; // dni de los jugadores. dps se hacé split(",")

	/**
	 * @param id
	 * @param juego
	 * @param jugadores
	 */
	public Mesa(String id, String juego, String jugadores) {
		this.id = id;
		this.juego = juego;
		this.jugadores = jugadores;
	}

	public String getId() {
		return this.id;
	}

	public String getJuego() {
		return this.juego;
	}

	public String getJugadores() {
		return this.jugadores;
	}

	@Override
	public String toString() {
		return "Mesa [id=" + id + ", juego=" + juego + ", jugadores=" + jugadores + "]";
	}

}
