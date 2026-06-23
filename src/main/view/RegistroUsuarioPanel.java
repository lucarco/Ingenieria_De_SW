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
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import usuario.control.ControllerUsu;

/**
 * @author Alejandro Remiro, Adrian Valverde, Alejandro Parra
 */
public class RegistroUsuarioPanel extends JPanel {

	JTextField dni;
	JTextField nombre;
	JTextField apellidos;
	JTextField edad;
	ControllerUsu control;

	public RegistroUsuarioPanel(ControllerUsu c) {
		control = c;
		initGUI();
	}

	private void initGUI() {

		dni = new JTextField();
		nombre = new JTextField();
		apellidos = new JTextField();
		edad = new JTextField();

		this.setLayout(new BorderLayout());
		crearToolBar();
		this.setBackground(Color.BLACK);
		JPanel central = new JPanel();
		central.setLayout(new BorderLayout());
		central.setBackground(Color.BLACK);
		JPanel central2 = new JPanel();
		central2.setBackground(Color.BLACK);
		central2.setLayout(new BorderLayout());
		central2.add(new JLabel(new ImageIcon("icons/usuario2.png")), BorderLayout.CENTER);

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

		JLabel labelNombre = new JLabel("NOMBRE");

		labelNombre.setForeground(Color.WHITE);

		labelNombre.setFont(new Font("Arial", Font.BOLD, 40));

		central3.add(labelNombre);

		nombre.setFont(new Font("Arial", Font.BOLD, 40));

		central3.add(nombre);

		JLabel labelApellidos = new JLabel("APELLIDOS");

		labelApellidos.setForeground(Color.WHITE);

		labelApellidos.setFont(new Font("Arial", Font.BOLD, 40));

		central3.add(labelApellidos);

		apellidos.setFont(new Font("Arial", Font.BOLD, 40));

		central3.add(apellidos);

		JLabel labelEdad = new JLabel("EDAD");

		labelEdad.setForeground(Color.WHITE);

		labelEdad.setFont(new Font("Arial", Font.BOLD, 40));

		central3.add(labelEdad);

		edad.setFont(new Font("Arial", Font.BOLD, 40));

		central3.add(edad);

		central2.add(central3, BorderLayout.PAGE_END);
		central.add(Box.createRigidArea(new Dimension(500, 200)), BorderLayout.EAST);
		central.add(Box.createRigidArea(new Dimension(500, 200)), BorderLayout.WEST);
		JPanel panelAuxB = new JPanel();
		panelAuxB.setLayout(new BorderLayout());
		panelAuxB.setBackground(Color.BLACK);
		panelAuxB.add(Box.createRigidArea(new Dimension(50, 100)), BorderLayout.PAGE_START);
		JButton boton1 = crearBoton("Iniciar Sesión");
		boton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow mainWindow = (MainWindow) SwingUtilities.getWindowAncestor(RegistroUsuarioPanel.this);
				mainWindow.showInicioSesionPanel();
				
			}
		});
		panelAuxB.add(boton1,BorderLayout.CENTER);
		JButton boton2 = crearBoton("Registrarse");
		boton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (!dni.getText().isEmpty() && !nombre.getText().isEmpty() && !apellidos.getText().isEmpty()
							&& !edad.getText().isEmpty() && Integer.parseInt(edad.getText()) >= 18) {
						char[] dNi = dni.getText().toCharArray();
						if(dNi.length != 9) {
							ViewUtils.showErrorMsg("DNI no válido (Introduzca un dni tipo XXXXXXXXA)");
						}else {
							try {
								int a = Integer.parseInt(Character.toString(dNi[0]));
								for(int i = 1; i < dNi.length-1;i++) {
									a = (a * 10) + Integer.parseInt(Character.toString(dNi[i]));
								}
								if(Abcdario.valueOf(Character.toString(dNi[dNi.length-1])) != null) {
									if(altaUsuario(dni.getText(), nombre.getText(), apellidos.getText(), Integer.parseInt(edad.getText()))) {
										MainWindow mainWindow = (MainWindow) SwingUtilities.getWindowAncestor(RegistroUsuarioPanel.this);
										mainWindow.showControlPanelUsuarios();
									}else {
										ViewUtils.showErrorMsg("El usuario ya existe");
									}
								}
							}catch(Exception ex) {
								ViewUtils.showErrorMsg("DNI no válido (Introduzca un dni tipo XXXXXXXXA)");
							}
						}
				
					}else ViewUtils.showErrorMsg("Faltan campos por rellenar");
					
				}catch(NumberFormatException ex) {
						ViewUtils.showErrorMsg("La edad no es un numero válido");
				}

			}
		});
		panelAuxB.add(boton2, BorderLayout.PAGE_END);
		central.add(panelAuxB, BorderLayout.PAGE_END);
		central.add(central2, BorderLayout.CENTER);
		this.add(central, BorderLayout.CENTER);
	}

	private void crearToolBar() {

		JToolBar toolbar = new JToolBar();
		toolbar.setLayout(new BorderLayout());

		JLabel titulo = new JLabel();
		titulo.setText("Casino ");
		titulo.setForeground(Color.WHITE);
		titulo.setFont(new Font("Arial", Font.BOLD, 60));
		titulo.setAlignmentX(CENTER_ALIGNMENT);

		toolbar.setBackground(new Color(0, 9, 128));
		toolbar.add(new JLabel(new ImageIcon("icons/logo.jpg")), BorderLayout.WEST);
		toolbar.setFloatable(false);

		toolbar.add(titulo, BorderLayout.EAST);

		toolbar.setBorderPainted(false);

		this.add(toolbar, BorderLayout.NORTH);

	}

	private JButton crearBoton(String texto) {
		JButton b = new JButton(texto);
		b.setBackground(new Color(59, 89, 182));
		b.setForeground(Color.WHITE);
		b.setFocusPainted(false);
		b.setFont(new Font("Tahoma", Font.BOLD, 60));
		return b;
	}
	
	private boolean altaUsuario(String dni, String nombre, String apellido, int edad) {
		boolean a = control.altaUsuario(dni, nombre, apellido, edad, 0, 0);
		if(a) Credentials.setDni(dni);
		return a;
	}

}
