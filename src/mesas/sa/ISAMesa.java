package mesas.sa;

import java.util.ArrayList;

import mesas.model.Mesa;
import usuario.model.Cliente;

/**
 * @author Lucas Arranz
 * 
 *	Interfaz implementada en la clase SAMesaImp
 *	Interfaz que se llama desde la clase FachadaSAMesaImp 
 */
public interface ISAMesa {

	public boolean altaMesa(Mesa m);

	public boolean bajaMesa(String id);

	public ArrayList<Mesa> busquedaMesa(String juego);

	public boolean modificarMesa(Mesa m);

	public Mesa consultarMesa(String id);

	public boolean quitarJugador(String jugadorId, String mesaId);

	public boolean anadirJugador(String jugadorId, String mesaId);

	public boolean simularJuego(int p);

	public ArrayList<Cliente> obtenerJugadores(String mesaId);

}
