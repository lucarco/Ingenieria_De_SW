package usuario.dao;

import java.util.ArrayList;

import usuario.model.Usuario;

/**
 * Interfaz fachcada DAO usuario
 * @author Adrian Valverde y Alejandro Remiro
 */
public interface IFachadaDAOUsu {
	
	public boolean altaUsuario(Usuario u);

	public boolean BajaUsuario(String dni);

	public ArrayList<Usuario> busquedaUsuario(String nombre,String apellidos,int edad,int nFichas);

	public boolean modificarUsuario(Usuario u);

	public Usuario consultarUsuario(String dni);

	public boolean comprarFichas(int numFichas, String dni);

	public boolean venderFichas(int numFichas, String dni);
	
	public boolean existeUsuario(String dni);
	
	public boolean saldoCorrecto(String dni, int nFichas);
	
}
