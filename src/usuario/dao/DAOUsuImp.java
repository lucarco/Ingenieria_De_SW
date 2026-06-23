package usuario.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import bbdd.BBDD;
import bbdd.BBDDUsuario;
import usuario.model.Usuario;

/**
 * Clase ImplementacionDAO
 * @author Alejandro Remiro, Adrián Valverde
 */
public class DAOUsuImp implements IDAOUsu {
	private BBDDUsuario _bbdd;

	public DAOUsuImp() {
		init();
	}
	
	private void init() {
		try {
			_bbdd = new BBDDUsuario();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean altaUsuario(Usuario u) {
		return _bbdd.altaUsuario(u);
		
	}

	@Override
	public boolean BajaUsuario(String dni) {
		return _bbdd.bajaUsuario(dni);
	}

	@Override
	public ArrayList<Usuario> busquedaUsuario(String nombre,String apellidos,int edad,int nFichas) {
		return _bbdd.busquedaUsuario(nombre, apellidos, edad, nFichas);
	}

	@Override
	public boolean modificarUsuario(Usuario u) {
		return _bbdd.modificarUsuario(u);
	}

	@Override
	public Usuario consultarUsuario(String dni) {
		return _bbdd.consultaUsuario(dni);
	}

	@Override
	public boolean comprarFichas(int numFichas, String dni) {
		return _bbdd.comprarFichas(numFichas, dni);
	}

	@Override
	public boolean venderFichas(int numFichas, String dni) {
		return _bbdd.venderFichas(numFichas, dni);
	}

	@Override
	public boolean existeUsuario(String dni) {
		return _bbdd.existeUsuario(dni);
	}

	@Override
	public boolean saldoCorrecto(String dni, int nFichas) {
		return _bbdd.saldoCorrecto(dni, nFichas);
	}

}
