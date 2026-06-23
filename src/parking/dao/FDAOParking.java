package parking.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import parking.model.Coche;
import parking.model.Reserva;

/**
 * Clase encargada de implementar las funciones procedentes de la interfaz IFachadaDaoParking
 * @author Marta Hernández y Alejandro Parra
 */
public class FDAOParking implements IFachadaDaoParking{

	private IDaoParking _iDaoParking;
	
	/**
     * Constructor vacío de la clase que incializa la variabl_ iDaoParking de tipo DaoParking
     * @author Marta Hernández y Alejandro Parra
     */
	public FDAOParking() {
		this._iDaoParking = new DaoParking();
	}
	
	
	
	/**
	 * Llama a ocuparPlaza de la variable propia de la clase(_iDaoParking)
     * @author Marta Hernández y Alejandro Parra
     * @return _iDaoParking.ocuparPlaza(c, dni);
	 */
	@Override
	public boolean ocuparPlaza(Coche c, String dni) {
		return this._iDaoParking.ocuparPlaza(c, dni);
	}

	/**
	  * Llama a desOcuparPlaza de la variable propia de la clase(_iDaoParking)
     * @author Marta Hernández y Alejandro Parra
     * @return _iDaoParking.desOcuparPlaza(c, dni);
	 */
	@Override
	public boolean desOcuparPlaza(String matricula) {
		return  this._iDaoParking.desOcuparPlaza(matricula);
	}

	/**
	 * Llama a busquedaReservas de la variable propia de la clase(_iDaoParking)
     * @author Marta Hernández y Alejandro Parra
     * @return _iDaoParking.busquedaReservas(fecha, dni, time, marca, color);
	 */
	@Override
	public ArrayList<Reserva> busquedaReservas(LocalDate fecha, String dni, LocalTime time, String marca, String color) {
		return this._iDaoParking.busquedaReservas(fecha, dni, time, marca, color);
	}

	/**
	 * 	Llama a existeCoche de la variable propia de la clase(_iDaoParking)
     * @author Marta Hernández y Alejandro Parra
     * @return _iDaoParking.existeCoche(matricula);
	 */
	@Override
	public boolean existeCoche(String matricula) {
		return this._iDaoParking.existeCoche(matricula);
	}
	
	/**
	* 	Llama a existeUsuario de la variable propia de la clase(_iDaoParking)
     * @author Marta Hernández y Alejandro Parra
     * @return _iDaoParking.existeUsuario(dni);
	 */
	@Override
	public boolean existeUsuario(String dni) {
		return _iDaoParking.existeUsuario(dni);
	}	

	/**
	 * Llama a consultaReserva de la variable propia de la clase(_iDaoParking)
     * @author Marta Hernández y Alejandro Parra
     * @return _iDaoParking.consultaReserva(matricula);
	 */
	@Override
	public Reserva consultaReserva(String matricula) {
		return this._iDaoParking.consultaReserva(matricula);
	}
	
}
