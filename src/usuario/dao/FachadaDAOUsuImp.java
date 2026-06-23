package usuario.dao;

import java.util.ArrayList;

import usuario.model.Usuario;

/**
 * Clase fachcada DAO usuario (Implementación)
 * @author Adrian Valverde y Alejandro Remiro
 */
public class FachadaDAOUsuImp implements IFachadaDAOUsu {

	private IDAOUsu _iDAOUsu;

	/**
	 * Contructor de la clase en la que se inicializa su unico atributo (Interfaz fachada DAO usuario)
	 * @author Adrian Valverde y Alejandro Remiro 
	 */
	public FachadaDAOUsuImp() {
		this._iDAOUsu = new DAOUsuImp();
	}

	@Override
	public boolean altaUsuario(Usuario u) {
		return _iDAOUsu.altaUsuario(u);
	}

	@Override
	public boolean BajaUsuario(String dni) {
		return _iDAOUsu.BajaUsuario(dni);
	}

	@Override
	public ArrayList<Usuario> busquedaUsuario(String nombre,String apellidos,int edad,int nFichas) {
		return _iDAOUsu.busquedaUsuario(nombre, apellidos, edad, nFichas);
	}

	@Override
	public boolean modificarUsuario(Usuario u) {
		return _iDAOUsu.modificarUsuario(u);
	}

	@Override
	public Usuario consultarUsuario(String dni) {
		return _iDAOUsu.consultarUsuario(dni);
	}

	@Override
	public boolean comprarFichas(int numFichas, String dni) {
		return _iDAOUsu.comprarFichas(numFichas, dni);
	}

	@Override
	public boolean venderFichas(int numFichas, String dni) {
		return _iDAOUsu.venderFichas(numFichas, dni);
	}

	@Override
	public boolean existeUsuario(String dni) {
		return _iDAOUsu.existeUsuario(dni);
	}

	@Override
	public boolean saldoCorrecto(String dni, int nFichas) {
		return _iDAOUsu.saldoCorrecto(dni, nFichas);
	}

}
