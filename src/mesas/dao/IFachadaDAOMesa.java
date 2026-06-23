package mesas.dao;

import java.util.ArrayList;

import mesas.model.Mesa;
import usuario.model.Cliente;


/**
 * @author Lucas Arranz y Alejandro Ramos
 * 
 * Interfaz que implementa la clase FachadaDAOMesaImp
 * Interfaz que se llama desde la clase SAMesaImp
 *
 */
public interface IFachadaDAOMesa {

	public boolean altaMesa(Mesa m);

	public boolean bajaMesa(String id);

	public ArrayList<Mesa> busquedaMesa(String juego);

	public boolean modificarMesa(Mesa m);

	public Mesa consultarMesa(String id);

	public boolean existeMesa(String id);

	public boolean quitarJugador(String jugadorId, String mesaId);

	public boolean anadirJugador(String jugadorId, String mesaId);

	public ArrayList<Cliente> obtenerJugadores(String mesaId);

}
