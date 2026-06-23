package parking.sa;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import parking.model.Coche;
import parking.model.Reserva;

public class FSAParking implements IFachadaSAParking{

	private ISAParking _iSAParking;
	
	/**
     * Constructor vacío de la clase que incializa la variable _iSAParking de tipo DaoParking
     * @author Marta Hernández y Alejandro Parra
     */
	public FSAParking() {
		this._iSAParking = new saParking();
	}
	
	/**
	 * Llama a ocuparPlaza de la variable propia de la clase(_iSAParking)
     * @author Marta Hernández y Alejandro Parra
     * @return _iSAParking.ocuparPlaza(c, dni);
	 */
	@Override
	public boolean ocuparPlaza(Coche c, String dni) {
		return _iSAParking.ocuparPlaza(c, dni);
	}

	/**
	  * Llama a desOcuparPlaza de la variable propia de la clase(_iSAParking)
    * @author Marta Hernández y Alejandro Parra
    * @return _iSAParking.desOcuparPlaza(c, dni);
	 */
	@Override
	public boolean desOcuparPlaza(String matricula) {
		return _iSAParking.desOcuparPlaza(matricula);
	}

	/**
	 * Llama a busquedaReservas de la variable propia de la clase(_iSAParking)
     * @author Marta Hernández y Alejandro Parra
     * @return _iSAParking.busquedaReservas(fecha, dni, time, marca, color);
	 */
	@Override
	public ArrayList<Reserva> busquedaReservas(LocalDate fecha, String dni, LocalTime time, String marca, String color) {
		return _iSAParking.busquedaReservas(fecha, dni, time, marca, color);
	}
	/**
	 * Llama a consultaReserva de la variable propia de la clase(_iSAParking)
     * @author Marta Hernández y Alejandro Parra
     * @return _iSAParking.consultaReserva(matricula);
	 */
	@Override
	public Reserva consultaReserva(String matricula) {
		return _iSAParking.consultaReserva(matricula);
	}

}
