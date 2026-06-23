package parking.control;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import parking.model.Coche;
import parking.sa.FSAParking;
import parking.sa.IFachadaSAParking;
import parking.model.Reserva;

/**
 * Clase Controlador, llama a las funciones de la FachadaSAParking
 * @author Marta Hernández y Alejandro Parra
 */
public class ControladorParking {
	
private IFachadaSAParking s = new FSAParking();
	
	
	/**
	 * Crea un tipo coche con los atributos que le llegan y llama a ocuparPlaza de FachadaSAParking
	 * @author Marta Hernández y Alejandro Parra
	 * @param matricula
	 * @param dni
	 * @param marca
	 * @param color
	 * @return this.s.ocuparPlaza(c, dni) (siendo s una variable de tipo FSAParking)
	 */
	public boolean ocuparPlaza(String matricula, String dni, String marca, String color) {
		Coche c = new Coche(matricula, marca, color);
		return this.s.ocuparPlaza(c, dni);
	}

	/**
	 * Llama a desOcuparPlaza de FachadaSAParking
	 * @author Marta Hernández y Alejandro Parra
	 * @param matricula
	 * @return this.s.desOcuparPlaza(matricula) (siendo s una variable de tipo FSAParking)
	 */
	public boolean desOcuparPlaza(String matricula) {
		return this.s.desOcuparPlaza(matricula);
	}

	/**
	 * Llama a busquedaReservas de FachadaSAParking
	 * @author Marta Hernández y Alejandro Parra
	 * @param fecha
	 * @param dni
	 * @param time
	 * @param marca
	 * @param color
	 * @return this.s.busquedaReservas(fecha, dni, time, marca, color) (siendo s una variable de tipo FSAParking)
	 */
	public ArrayList<Reserva> busquedaReservas(LocalDate fecha, String dni, LocalTime time, String marca, String color) {
		return this.s.busquedaReservas(fecha, dni, time, marca, color);
	}
	
	/**
	 * Llama a consultaReserva de FachadaSAParking
	 * @author Marta Hernández y Alejandro Parra
	 * @param matricula
	 * @return this.s.consultaReserva(matricula) (siendo s una variable de tipo FSAParking)
	 */
	public Reserva consultaReserva(String matricula) {
		return this.s.consultaReserva(matricula);
	}

}
	