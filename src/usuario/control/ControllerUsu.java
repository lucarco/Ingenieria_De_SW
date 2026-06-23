package usuario.control;

import java.util.ArrayList;

import usuario.model.Cliente;
import usuario.model.Trabajador;
import usuario.model.Usuario;
import usuario.sa.FachadaSAUsuImp;
import usuario.sa.IFachadaSAUsu;

/**
 * Clase controlador de "Usuario".
 * @author Adrian Valverde y Alejandro Remiro
 */
public class ControllerUsu {
	
	IFachadaSAUsu s = new FachadaSAUsuImp();
	
	/**
	 * Funcion que llama a la fachadaSAUsu para dar de alta a un usuario y devuelve un booleano de exito o fracaso.
	 * @param dni
	 * @param nombre
	 * @param apellido
	 * @param edad
	 * @param nFichas
	 * @param rol
	 * @return
	 * @author Adrian Valverde y Alejandro Remiro
	 */
	public boolean altaUsuario(String dni, String nombre, String apellido, int edad, int nFichas, int rol) {
		Usuario u = null;
		if(rol == 0) u = new Cliente(dni, nombre, apellido, edad, nFichas);
		else if(rol == 1) u = new Trabajador(dni, nombre, apellido, edad, nFichas);
		return s.altaUsuario(u);
	}
	
	/**
	 * Funcion que llama a la fachadaSAUsu para dar de baja a un usuario y devuelve un booleano de exito o fracaso.
	 * @param dni
	 * @return
	 * @author Adrian Valverde y Alejandro Remiro
	 */
	public boolean bajaUsuario(String dni) {
		return s.bajaUsuario(dni);
	}
	
	/**
	 * Funcion que llama a la fachadaSAUsu para consultar a un usuario y devuelve un booleano de exito o fracaso.
	 * @param dni
	 * @return
	 * @author Adrian Valverde y Alejandro Remiro
	 */
	public Usuario consultaUsuario(String dni) {
		return s.consultarUsuario(dni);
	}
	
	/**
	 * Funcion que llama a la fachadaSAUsu para buscar usuarios con las distintas caracteristicas y devuelve una lista con los usuarios encontrados.
	 * @param nombre
	 * @param apellidos
	 * @param edad
	 * @param nFichas
	 * @return
	 * @author Adrian Valverde y Alejandro Remiro
	 */
	public ArrayList<Usuario> busquedaUsuario(String nombre, String apellidos, int edad, int nFichas){
		return s.busquedaUsuario(nombre, apellidos, edad, nFichas);
	}
	
	/**
	 * Funcion que llama a la fachadaSAUsu para modificar a un usuario y devuelve un booleano de exito o fracaso.
	 * @param dni
	 * @param nombre
	 * @param apellido
	 * @param edad
	 * @param nFichas
	 * @param rol
	 * @return
	 * @author Adrian Valverde y Alejandro Remiro
	 */
	public boolean modificarUsuario(String dni, String nombre, String apellido, int edad, int nFichas, int rol) {
		Usuario u = null;
		if(rol == 0) u = new Cliente(dni, nombre, apellido, edad, nFichas);
		else if(rol == 1) u = new Trabajador(dni, nombre, apellido, edad, nFichas);
		return s.modificarUsuario(u);
	}
	
	/**
	 * Funcion que llama a la fachadaSAUsu para comprar fichas a un usuario y devuelve un booleano de exito o fracaso.
	 * @param dni
	 * @return
	 * @author Adrian Valverde y Alejandro Remiro
	 */
	public boolean comprarFichas(String dni, int nFichas) {
		return s.comprarFichas(nFichas, dni);
	}
	
	/**
	 * Funcion que llama a la fachadaSAUsu para vender a un usuario y devuelve un booleano de exito o fracaso.
	 * @param dni
	 * @param nFichas
	 * @return
	 * @author Adrian Valverde y Alejandro Remiro
	 */
	public boolean venderFichas(String dni, int nFichas) {
		return s.venderFichas(nFichas, dni);
	}
	
	
}
