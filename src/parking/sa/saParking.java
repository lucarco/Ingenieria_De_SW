package parking.sa;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import parking.dao.FDAOParking;
import parking.dao.IFachadaDaoParking;
import parking.model.Coche;
import parking.model.Reserva;

/**
 * Clase que implementa la interfaz de ISAParking
 * @author Marta Hernández y Alejandro Parra
 */
public class saParking implements ISAParking{

	private IFachadaDaoParking _iFachadaDaoParking;

	/**
     * Constructor vacío de la clase que incializa la variable _iFachadaDaoParking de tipo DaoParking
     * @author Marta Hernández y Alejandro Parra
     */

	public saParking() {
		this._iFachadaDaoParking = new FDAOParking();
	}
	
	/**
	 * Comprueba si existe un coche y un usuario, y en caso de que no exista el coche, llama a ocuparPlaza de la variable propia de la clase(_iFachadaDaoParking)
     * @author Marta Hernández y Alejandro Parra
     * @return _iFachadaDaoParking.ocuparPlaza(c, dni);
	 */
	@Override
	public boolean ocuparPlaza(Coche c, String dni) {
		boolean noExisteCocheyUsu = !_iFachadaDaoParking.existeCoche(c.getMatricula()) && _iFachadaDaoParking.existeUsuario(dni);
		if(noExisteCocheyUsu) {
		 _iFachadaDaoParking.ocuparPlaza(c, dni);
		}
		 return noExisteCocheyUsu ; // te devuelve el contrario
	}

	/**
	  * Comprueba si existe un coche , y en caso de que  exista el coche,llama a desOcuparPlaza de la variable propia de la clase(_iFachadaDaoParking)
   * @author Marta Hernández y Alejandro Parra
   * @return _iFachadaDaoParking.desOcuparPlaza(c, dni);
	 */
	@Override
	public boolean desOcuparPlaza(String matricula) {

		boolean existeCoche = _iFachadaDaoParking.existeCoche(matricula);
		if(existeCoche) {
		 _iFachadaDaoParking.desOcuparPlaza(matricula);
		}
		 return existeCoche;
	}
	/**
	 * Llama a busquedaReservas de la variable propia de la clase(_iFachadaDaoParking)
     * @author Marta Hernández y Alejandro Parra
     * @return _iFachadaDaoParking.busquedaReservas(fecha, dni, time, marca, color);
	 */
	@Override
	public ArrayList<Reserva> busquedaReservas(LocalDate fecha, String dni, LocalTime time, String marca, String color) {
		return _iFachadaDaoParking.busquedaReservas(fecha, dni, time, marca, color);
	}
	/**
	 *  Comprueba si existe un coche , y en caso de que exista el coche,llama a consultaReserva de la variable propia de la clase(_iFachadaDaoParking)
     * @author Marta Hernández y Alejandro Parra
     * @return _iFachadaDaoParking.consultaReserva(matricula);
	 */
	@Override
	public Reserva consultaReserva(String matricula) {
		boolean existeCoche = _iFachadaDaoParking.existeCoche(matricula);
		if(existeCoche) {
			return _iFachadaDaoParking.consultaReserva(matricula);
		}
		return null;
	}

}
