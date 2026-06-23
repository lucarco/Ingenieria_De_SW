package parking.sa;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import parking.model.Coche;
import parking.model.Reserva;

/**
 * Interfaz propia del Parking
 * @author Marta Hernández y Alejandro Parra
 */
public interface IFachadaSAParking {

	public boolean ocuparPlaza(Coche c, String dni);
	public boolean desOcuparPlaza(String matricula);
	//public ArrayList<Coche> busquedaCoches(tFecha fecha );
	public ArrayList<Reserva> busquedaReservas(LocalDate fecha, String dni, LocalTime time, String marca, String color);
	public Reserva consultaReserva(String matricula);

}
