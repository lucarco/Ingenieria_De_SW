package parking.model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Clase reserva, tiene como atributos un tipo coche, la fecha, hora y el dni del usuario y funciones para obtenerlos
 * @author Marta Hernández y Alejandro Parra
 */
public class Reserva {
	
	private Coche coche;
	private LocalDate fecha;
	private LocalTime hora;
	private String dni;// me acabo de hacer un import de lo de los maricas de adrian y mark tengo miedo
	
	/**
	 * Constructor que iguala los atributos a las variables que llegan por parámetro
	 * @author Marta Hernández y Alejandro Parra
	 * @param coche
	 * @param fecha
	 * @param dni
	 * @param hora
	 */
	public Reserva(Coche coche,LocalDate fecha, String dni, LocalTime hora) {
		this.coche = coche;
		this.fecha = fecha;
		this.hora = hora;
		this.dni = dni;
	}

	/**
	 * Devuelve el tipo coche de la reserva
	 * @author Marta Hernández y Alejandro Parra
	 * @return coche
	 */
	public Coche getCoche() {
		return coche;
	}

	/**
	 * Devuelve la fecha de la reserva
	 * @author Marta Hernández y Alejandro Parra
	 * @return fecha
	 */
	public LocalDate getFecha() {
		return fecha;
	}

	/**
	 * Devuelve la hora de la reserva
	 * @author Marta Hernández y Alejandro Parra
	 * @return hora
	 */
	public LocalTime getHora() {
		return hora;
	}

	/**
	 * Devuelve el dni de la reserva
	 * @author Marta Hernández y Alejandro Parra
	 * @return dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * Devuelve un string escribiendo los atributos de la reserva bien estructurado
	 * @author Marta Hernández y Alejandro Parra
	 */
	@Override
	public String toString() {
		return "Reserva [coche=" + coche + ", fecha=" + fecha + ", hora=" + hora + ", dni=" + dni + "]";
	}
	
}
