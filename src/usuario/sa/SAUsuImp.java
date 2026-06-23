package usuario.sa;

import java.util.ArrayList;

import usuario.dao.FachadaDAOUsuImp;
import usuario.dao.IFachadaDAOUsu;
import usuario.model.Usuario;

/**
 * @author Alejandro Remiro, Adrian Valverde
 */
public class SAUsuImp implements ISAUsu {

	private IFachadaDAOUsu _iFachadaDAOUsu;

	public SAUsuImp() {
		this._iFachadaDAOUsu = new FachadaDAOUsuImp();
	}

	/**
	 * @author Alejandro Remiro, Adrian Valverde
	 * Si no existe el usuario y el alta se realiza correctamente devuelve true
	 */
	@Override
	public boolean altaUsuario(Usuario u) {
		return  !_iFachadaDAOUsu.existeUsuario(u.getDni()) && _iFachadaDAOUsu.altaUsuario(u);
	}

	/**
	 * @author Alejandro Remiro, Adrian Valverde
	 * Si existe el usuario y el baja se realiza correctamente devuelve true
	 */
	@Override
	public boolean bajaUsuario(String dni) {
		return _iFachadaDAOUsu.existeUsuario(dni) && _iFachadaDAOUsu.BajaUsuario(dni);
	}

	/**
	 * @author Alejandro Remiro, Adrian Valverde
	 * Devuelve una lista de usuarios con los datos introducidos
	 */
	@Override
	public ArrayList<Usuario> busquedaUsuario(String nombre,String apellidos,int edad,int nFichas) {
		return _iFachadaDAOUsu.busquedaUsuario(nombre, apellidos, edad, nFichas);
	}

	/**
	 * @author Alejandro Remiro, Adrian Valverde
	 * Si existe el usuario permite su modificacion y devuelve si se ha podido hacer o no
	 */
	@Override
	public boolean modificarUsuario(Usuario u) {
		boolean usuarioMod = false;
		if(_iFachadaDAOUsu.existeUsuario(u.getDni())) {
			usuarioMod = _iFachadaDAOUsu.modificarUsuario(u);
		}
		return usuarioMod;
	}

	
	/**
	 * @author Alejandro Remiro, Adrian Valverde
	 * Devuelve el usuario con un id
	 */
	@Override
	public Usuario consultarUsuario(String dni) {
		if(_iFachadaDAOUsu.existeUsuario(dni)) {
			return _iFachadaDAOUsu.consultarUsuario(dni);
		}
		return null;
	}

	/**
	 * @author Alejandro Remiro, Adrian Valverde
	 * Devuelve si se ha podido o no comprar fichas
	 */
	@Override
	public boolean comprarFichas(int numFichas, String dni) {
		boolean compra = false;
		compra = numFichas >= 0 && _iFachadaDAOUsu.existeUsuario(dni) && _iFachadaDAOUsu.comprarFichas(numFichas, dni);
		return compra;
	}

	/**
	 * @author Alejandro Remiro, Adrian Valverde
	 * Devuelve si se ha podido o no vender fichas
	 */
	@Override
	public boolean venderFichas(int numFichas, String dni) {
		boolean vende = false;
		vende = numFichas >= 0 && _iFachadaDAOUsu.existeUsuario(dni) && _iFachadaDAOUsu.saldoCorrecto(dni, numFichas) && _iFachadaDAOUsu.venderFichas(numFichas, dni);
		return vende;
	}

}
