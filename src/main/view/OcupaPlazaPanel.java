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
 * Clase encargada de la interfaz que realiza la función de ocupar plaza
 * @author Alejandro Parra
 *
 */
public class OcupaPlazaPanel extends JPanel{
	JTextField matricula;
	JTextField dni;
	JTextField marca;
	JTextField color;
	ControladorParking control;
	
	/**
	 * Constructor de la clase, que inicializa la variable de tipo ControladorParking y llama a initGUI()
	 * @author Alejandro Parra
	 * @param c
	 */
	public OcupaPlazaPanel(ControladorParking c) {
		control = c;
		initGUI();
	}
	
	/**
	 * Se encarga de inicializar todos los componentes de la interfaz gráfica (tanto los botones como los textfields, creación de paneles...).
	 * @author Alejandro Parra
	 */
	private void initGUI() {
		matricula = new JTextField();
		dni = new JTextField();
		marca = new JTextField();
		color = new JTextField();
		
		this.setLayout(new BorderLayout());
		JToolBar toolbar =  crearToolBar();
		JButton botonAtras = new JButton();
		
		botonAtras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow mainWindow = (MainWindow) SwingUtilities.getWindowAncestor(OcupaPlazaPanel.this);
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

		JLabel labelDNI = new JLabel("DNI");

		labelDNI.setForeground(Color.WHITE);

		labelDNI.setFont(new Font("Arial", Font.BOLD, 40));

		central3.add(labelDNI);

		dni.setFont(new Font("Arial", Font.BOLD, 40));

		central3.add(dni);

		JLabel labelMatricula = new JLabel("MATRICULA");

		labelMatricula.setForeground(Color.WHITE);

		labelMatricula.setFont(new Font("Arial", Font.BOLD, 40));

		central3.add(labelMatricula);

		matricula.setFont(new Font("Arial", Font.BOLD, 40));

		central3.add(matricula);

		JLabel labelMarca = new JLabel("MARCA");

		labelMarca.setForeground(Color.WHITE);

		labelMarca.setFont(new Font("Arial", Font.BOLD, 40));

		central3.add(labelMarca);

		marca.setFont(new Font("Arial", Font.BOLD, 40));

		central3.add(marca);

		JLabel labelColor = new JLabel("COLOR");

		labelColor.setForeground(Color.WHITE);

		labelColor.setFont(new Font("Arial", Font.BOLD, 40));

		central3.add(labelColor);

		color.setFont(new Font("Arial", Font.BOLD, 40));

		central3.add(color);

		central2.add(central3, BorderLayout.PAGE_END);
		central.add(Box.createRigidArea(new Dimension(500, 200)), BorderLayout.EAST);
		central.add(Box.createRigidArea(new Dimension(500, 200)), BorderLayout.WEST);
		JPanel panelAuxB = new JPanel();
		panelAuxB.setLayout(new BorderLayout());
		panelAuxB.setBackground(Color.BLACK);
		panelAuxB.add(Box.createRigidArea(new Dimension(0, 60)), BorderLayout.PAGE_START);
		panelAuxB.add(crearBoton("Ocupar Plaza"), BorderLayout.PAGE_END);
		central.add(panelAuxB, BorderLayout.PAGE_END);
		central.add(central2, BorderLayout.CENTER);
		this.add(central, BorderLayout.CENTER);
	}

	/**
	 * Función encargada de crear la toolBar que es utilizada por todos como plantilla
	 * @author Alejandro Parra
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
	 * Se encarga de la obtención de los datos insertados dentro de la interfaz, y en caso de que ninguno de los datos sea null llama a 'ocuparPlaza(matricula.getText(), dni.getText(), marca.getText(), color.getText())'
	 * @author Alejandro Parra
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
				if (!dni.getText().isEmpty() &&!matricula.getText().isEmpty() && !marca.getText().isEmpty()
						&& !color.getText().isEmpty()) {
					if(control.ocuparPlaza(matricula.getText(), dni.getText(), marca.getText(), color.getText())) {
						ViewUtils.showSuccessMsg("El vehículo ha sido registrado en su plaza correspondiente");
					}else {
						ViewUtils.showErrorMsg("No existe dicho usuario");
					}
				}else {
					if (dni.getText().isEmpty()) {
						ViewUtils.showSuccessMsg("DNI vacío, introduzca los datos correctamente");
					}
					if (matricula.getText().isEmpty()) {
						ViewUtils.showSuccessMsg("Matrícula vacía, introduzca los datos correctamente");
					}
					if (marca.getText().isEmpty()) {
						ViewUtils.showSuccessMsg("Marca vacía, introduzca los datos correctamente");
					}
					if (color.getText().isEmpty()) {
						ViewUtils.showSuccessMsg("Color vacío, introduzca los datos correctamente");
					}
				};
				
			}
		});
			
		return b;
	}
}
