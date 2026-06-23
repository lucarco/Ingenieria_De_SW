package usuario.model;


/**
 * Clase abstracta "Usuario"
 * @author Adrian Valverde y Alejandro Remiro
 */
public abstract class Usuario {
	private String _dni;
	private String _nombre;
	private String _apellidos;
	private int _edad;
	private int _fichas;

	/**
	 * Este es el constructor de la clase abstracta usuario que almacena los atributos de cada usuario. 
	 * @param dni
	 * @param nombre
	 * @param apellidos
	 * @param edad
	 * @param fichas
	 * @author Adrian Valverde y Alejandro Remiro
	 */
	Usuario(String dni, String nombre, String apellidos, int edad, int fichas) {
		_dni = dni;
		_nombre = nombre;
		_apellidos = apellidos;
		_edad = edad;
		_fichas = fichas;
	}

	/**
	 * Devuelve su dni.
	 * @return
	 * @author Adrian Valverde y Alejandro Remiro
	 */
	public String getDni() {
		return this._dni;
	}

	/**
	 * Devuelve su nombre.
	 * @return
	 * @author Adrian Valverde y Alejandro Remiro
	 */
	public String getNombre() {
		return this._nombre;
	}
	
	/**
	 * Devuelve su apellidos.
	 * @return
	 * @author Adrian Valverde y Alejandro Remiro
	 */
	public String getApellidos() {
		return this._apellidos;
	}
	
	/**
	 * Devuelve su edad.
	 * @return
	 * @author Adrian Valverde y Alejandro Remiro
	 */
	public int getEdad() {
		return this._edad;
	}
	
	/**
	 * Devuelve su numero de fichas.
	 * @return
	 * @author Adrian Valverde y Alejandro Remiro
	 */
	public int getFichas() {
		return this._fichas;
	}
	
	
	/**
	 * Modifica su dni con el que le llega por cabercera.
	 * @param dni
	 * @author Adrian Valverde y Alejandro Remiro
	 */
	public void setDni(String dni) {
		this._dni = dni;
	}


	/**
	 * Modifica su nombre con el que le llega por cabecera.
	 * @param nombre
	 * @author Adrian Valverde y Alejandro Remiro
	 */
	public void setNombre(String nombre) {
			this._nombre = nombre;
	}
	
	/**
	 * Modifica sus apellidos con los que le llegan por cabecera.
	 * @param apellidos
	 * @author Adrian Valverde y Alejandro Remiro
	 */
	public void setApellidos(String apellidos) {
		this._apellidos = apellidos;
	}
	
	/**
	 * Modifica su edad con la que le llega por cabecera.
	 * @param edad
	 * @author Adrian Valverde y Alejandro Remiro
	 */
	public void setEdad(int edad) {
		this._edad = edad;
	}
	
	/**
	 * Modifica su numero de fichas con las que le llegan por cabecera.
	 * @param fichas
	 * @author Adrian Valverde y Alejandro Remiro
	 */
	public void setFichas(int fichas) {
		this._fichas = fichas;
	}
	
	/**
	 * Funcion abstracta que se usa para determinar que tipo de usuario eres.
	 * @return
	 * @author Adrian Valverde y Alejandro Remiro
	 */
	public abstract int getRol();
	
}
