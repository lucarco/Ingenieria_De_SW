package usuario.sa;

import java.util.ArrayList;

import usuario.model.Usuario;


/**
 *Interfaz SA usuario 
 *@author Adrian Valverde y Alejandro Remiro
 */
public interface ISAUsu {
	
	public boolean altaUsuario(Usuario u);

	public boolean bajaUsuario(String dni);

	public ArrayList<Usuario> busquedaUsuario(String nombre,String apellidos,int edad,int nFichas);

	public boolean modificarUsuario(Usuario u);

	public Usuario consultarUsuario(String dni);

	public boolean comprarFichas(int numFichas, String dni);

	public boolean venderFichas(int numFichas, String dni);
}
