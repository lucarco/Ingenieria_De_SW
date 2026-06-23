package main.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import parking.control.ControladorParking;

/**
 * Clase encargada de la interfaz que realiza la función de desocupar plaza
 * @author Marta Hernández 
 *
 */
public class DesOcupaPlazaPanel extends JPanel{
	JTextField matricula;
	ControladorParking control;
	
	/**
	 * Constructor de la clase, que inicializa la variable c, de tipo ControladorParking y llama a initGUI()
	 * @author Marta Hernández 
	 * @param c
	 */
	public DesOcupaPlazaPanel(ControladorParking c) {
		control = c;
		initGUI();
	}
	
	/**
	 * Inicializar todos los botones, textfields y paneles de la interfaz gráfica.
	 * @author Marta Hernández 
	 */
	private void initGUI() {
		matricula = new JTextField();
		
		this.setLayout(new BorderLayout());
		JToolBar toolbar =  crearToolBar();
		JButton botonAtras = new JButton();
		
		botonAtras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow mainWindow = (MainWindow) SwingUtilities.getWindowAncestor(DesOcupaPlazaPanel.this);
				mainWindow.showParkingPanel();
			}
			
		});
		
		botonAtras.setIcon(new ImageIcon("icons/atras.png"));
		botonAtras.setBackground(new Color(0, 9, 60));
		botonAtras.setBorder(null);
		botonAtras.setBorderPainted(false);
		botonAtras.setOpaque(false);
		
		this.setBackground(Color.BLACK);
		toolbar.add(botonAtras, BorderLayout.WEST);
		this.add(toolbar, BorderLayout.NORTH);
		JPanel central = new JPanel();
		central.setLayout(new BorderLayout());
		central.setBackground(Color.BLACK);
		JPanel central2 = new JPanel();
		central2.setBackground(Color.BLACK);
		central2.setLayout(new BorderLayout());
		central2.add(new JLabel(new ImageIcon("icons/cocheP.png")), BorderLayout.CENTER);

		JPanel central3 = new JPanel();

		central.setBackground(Color.BLACK);

		central3.setLayout(new BoxLayout(central3, BoxLayout.Y_AXIS));

		central3.setBackground(Color.BLACK);


		JLabel labelMatricula = new JLabel("MATRICULA");

		labelMatricula.setForeground(Color.WHITE);

		labelMatricula.setFont(new Font("Arial", Font.BOLD, 40));

		central3.add(labelMatricula);

		matricula.setFont(new Font("Arial", Font.BOLD, 40));

		central3.add(matricula);

		central2.add(central3, BorderLayout.PAGE_END);
		central.add(Box.createRigidArea(new Dimension(500, 200)), BorderLayout.EAST);
		central.add(Box.createRigidArea(new Dimension(500, 200)), BorderLayout.WEST);
		JPanel panelAuxB = new JPanel();
		panelAuxB.setLayout(new BorderLayout());
		panelAuxB.setBackground(Color.BLACK);
		panelAuxB.add(Box.createRigidArea(new Dimension(0, 60)), BorderLayout.PAGE_START);
		panelAuxB.add(crearBoton("Desocupar Plaza"), BorderLayout.PAGE_END);
		central.add(panelAuxB, BorderLayout.PAGE_END);
		central.add(central2, BorderLayout.CENTER);
		this.add(central, BorderLayout.CENTER);
	}

	/**
	 * Función encargada de crear la toolBar que es utilizada por todos como plantilla
	 * @author Marta Hernández
	 * @return toolbar
	 */
	private JToolBar crearToolBar() {

		JToolBar toolbar = new JToolBar();
		toolbar.setLayout(new BorderLayout());

		JLabel titulo = new JLabel();

		titulo.setText("Casino ");
		titulo.setForeground(Color.WHITE);
		titulo.setFont(new Font("Arial", Font.BOLD, 60));
		titulo.setAlignmentX(CENTER_ALIGNMENT);
		toolbar.setBackground(new Color(0, 9, 128));
		toolbar.setFloatable(false);
		toolbar.add(titulo, BorderLayout.EAST);

		toolbar.setBorderPainted(false);

		return toolbar;

	}

	/**
	 * Se encarga de la obtención de la matrícula insertada dentro de la interfaz, y en caso de que no sea null llama a la función del controlador "desOcuparPlaza(matricula.getText()"
	 * @author Marta Hernández
	 * @param texto
	 * @return b
	 */
	private JButton crearBoton(String texto) {
		JButton b = new JButton(texto);
		b.setBackground(new Color(59, 89, 182));
		b.setForeground(Color.WHITE);
		b.setFocusPainted(false);
		b.setFont(new Font("Tahoma", Font.BOLD, 60));
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!matricula.getText().isEmpty()) {
					if(control.desOcuparPlaza(matricula.getText())) {
						ViewUtils.showSuccessMsg("El vehículo ha sido borrado del sistema");
					}else {
						ViewUtils.showErrorMsg("Vehiculo no existente");
					}
				}else ViewUtils.showErrorMsg("Por favor, rellene los datos correctamente");
				
			}
		});
			
		return b;
	}
}
