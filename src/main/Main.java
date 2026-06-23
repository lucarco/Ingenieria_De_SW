package main;

import javax.swing.SwingUtilities;

import juegos.control.ControllerJuegos;
import main.view.*;
import mesas.control.ControllerMesas;
import parking.control.ControladorParking;
import usuario.control.ControllerUsu;

public class Main {
	/**
	 * @param args
	 * @author LudoSoftware
	 */
	public static void main(String[] args) {
		ControllerJuegos j = new ControllerJuegos();
		ControllerUsu u = new ControllerUsu();
		ControladorParking p = new ControladorParking();
		ControllerMesas m = new ControllerMesas();
		
		SwingUtilities.invokeLater(() -> new MainWindow(j,u,p,m));
	}
}
