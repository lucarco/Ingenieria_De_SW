package usuario.model;

/**
 * Clase "Cliente" que hereda de la clase abstracta "Usuario".
 * @author Adrian Valverde y Alejandro Remiro
 */
public class Cliente extends Usuario {

	/**
	 * Contructor de la clase que llama al constructor de "Usuario".
	 * @param dni
	 * @param nombre
	 * @param apellidos
	 * @param edad
	 * @param fichas
	 * @author Adrian Valverde y Alejandro Remiro
	 */
	public Cliente(String dni, String nombre, String apellidos, int edad, int fichas) {
		super(dni, nombre, apellidos, edad, fichas);
	}

	/**
	 * Devuelve el tipo de usuario.
	 * @author Adrian Valverde y Alejandro Remiro
	 */
	@Override
	public int getRol() {
		return 0;
	}

}
