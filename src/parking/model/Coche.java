package parking.model;

/**
 * Clase coche, tiene los atributos de un coche y funciones para obtenerlos
 * @author Marta Hernández y Alejandro Parra
 */
public class Coche {

	private String matricula;
	private String marca;
	private String color;
	
	/**
	 * Constructor que iguala los atributos a las variables que llegan por parámetro
	 * @author Marta Hernández y Alejandro Parra
	 * @param matricula
	 * @param marca
	 * @param color
	 */
	public Coche(String matricula, String marca, String color) {
		this.matricula = matricula;
		this.marca = marca;
		this.color = color;
	}
	
	/**
	 * Devuelve la matrícula del objeto
	 * @author Marta Hernández y Alejandro Parra
	 * @return this.matricula
	 */
	public String getMatricula() {
		return this.matricula;
	}

	/**
	 * Devuelve la marca del objeto
	 * @author Marta Hernández y Alejandro Parra
	 * @return marca
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * Devuelve el color del objeto
	 * @author Marta Hernández y Alejandro Parra
	 * @return color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Devuelve un string escribiendo los atributos del coche bien estructurado
	 * @author Marta Hernández y Alejandro Parra
	 */
	@Override
	public String toString() {
		return "Coche [matricula=" + matricula + ", marca=" + marca + ", color=" + color + "]";
	}
	
}
