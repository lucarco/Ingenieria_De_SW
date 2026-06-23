package parking.dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import bbdd.BBDDparking;
import parking.model.Coche;
import parking.model.Reserva;


/**
 * Clase encargada de implementar las funciones procedentes de la interfaz IDaoParking
 * @author Marta Hernández y Alejandro Parra
 *
 */
public class DaoParking implements IDaoParking {
    
    private BBDDparking _bbdd;
    
    /**
     * Constructor vacío de la clase que llama a initGUI()
     * @author Marta Hernández y Alejandro Parra
     */
    public DaoParking() {
        init();
    }
    
    
    /**
     * Inicializa la variable _bbdd de tipo BBDDParking 
     * @author Marta Hernández y Alejandro Parra
     */
    private void init() {
        try {
            _bbdd = new BBDDparking();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Llama a ocuparPlaza de la variable propia de la clase(_bbdd)
     * @author Marta Hernández y Alejandro Parra
     * @return _bbdd.ocuparPlaza(c, dni);
     */
    @Override
    public boolean ocuparPlaza(Coche c, String dni) {
        return _bbdd.ocuparPlaza(c, dni);
    }

    /**
     * Llama a desOcuparPlaza de la variable propia de la clase(_bbdd)
     * @author Marta Hernández y Alejandro Parra
     * @return _bbdd.desOcuparPlaza(c, dni);
     */
    @Override
    public boolean desOcuparPlaza(String matricula) {
        return _bbdd.desOcuparPlaza(matricula);
    }

    
    /**
     * Llama a busquedaReservas de la variable propia de la clase(_bbdd)
     * @author Marta Hernández y Alejandro Parra
     * @return _bbdd.busquedaReservas(fecha, dni, time, marca, color);
     */
    @Override
    public ArrayList<Reserva> busquedaReservas(LocalDate fecha, String dni, LocalTime time, String marca, String color) {
        return _bbdd.busquedaReservas(fecha, dni, time, marca, color);
    }

    /**
     * 	Llama a existeCoche de la variable propia de la clase(_bbdd)
     * @author Marta Hernández y Alejandro Parra
     * @return _bbdd.existeCoche(matricula);
     */
    @Override
    public boolean existeCoche(String matricula) {
        return _bbdd.existeCoche(matricula);
    }
    
    /**
     * 	Llama a existeUsuario de la variable propia de la clase(_bbdd)
     * @author Marta Hernández y Alejandro Parra
     * @return _bbdd.existeUsuario(dni);
     */
    @Override
    public boolean existeUsuario(String dni) {
        return _bbdd.existeUsuario(dni);
    }
    
    /**
     * Llama a consultaReserva de la variable propia de la clase(_bbdd)
     * @author Marta Hernández y Alejandro Parra
     * @return _bbdd.consultaReserva(matricula);
     */
    @Override
    public Reserva consultaReserva(String matricula) {
    	return _bbdd.consultaReserva(matricula);
    }
}
