package parking.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import parking.model.Coche;
import parking.model.Reserva;

/**
/**
 * Interfaz propia del Parking
 * @author Marta Hernández y Alejandro Parra
 *
 */

public interface IFachadaDaoParking {

	public boolean ocuparPlaza(Coche c, String dni);
	public boolean desOcuparPlaza(String matricula);
	public ArrayList<Reserva> busquedaReservas(LocalDate fecha, String dni, LocalTime time, String marca, String color);
	public boolean existeCoche(String matricula);
	Reserva consultaReserva(String matricula);
	public boolean existeUsuario(String dni);
}
