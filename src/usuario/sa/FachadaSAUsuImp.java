package usuario.sa;

import java.util.ArrayList;

import usuario.model.Usuario;

/**
 * @author Alejandro Remiro, Adrian Valverde
 */
public class FachadaSAUsuImp implements IFachadaSAUsu{

	private ISAUsu _iSAUsu;
	
	public FachadaSAUsuImp() {
		this._iSAUsu = new SAUsuImp();
	}
	
	@Override
	public boolean altaUsuario(Usuario u) {
		return _iSAUsu.altaUsuario(u);
	}

	@Override
	public boolean bajaUsuario(String dni) {
		return _iSAUsu.bajaUsuario(dni);
	}

	@Override
	public ArrayList<Usuario> busquedaUsuario(String nombre,String apellidos,int edad,int nFichas) {
		return _iSAUsu.busquedaUsuario(nombre, apellidos, edad, nFichas);
	}

	@Override
	public boolean modificarUsuario(Usuario u) {
		return _iSAUsu.modificarUsuario(u);
	}

	@Override
	public Usuario consultarUsuario(String dni) {
		return _iSAUsu.consultarUsuario(dni);
	}

	@Override
	public boolean comprarFichas(int numFichas, String dni) {
		return _iSAUsu.comprarFichas(numFichas, dni);
	}

	@Override
	public boolean venderFichas(int numFichas, String dni) {
		return _iSAUsu.venderFichas(numFichas, dni);
	}

}
