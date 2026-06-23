package main.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import usuario.control.ControllerUsu;

import javax.swing.JLabel;

/**
 * @author Alejandro Remiro, Adrian Valverde, Alejandro Parra
 */
public class InicioSesionPanel extends JPanel {
	
	ControllerUsu c;
	JTextField tUsuario;
	JLabel dni;

	public InicioSesionPanel(ControllerUsu c) {
		this.c = c;
		initGUI();
	}

	private void initGUI() {
		this.setLayout(new BorderLayout());
		crearToolBar();
		this.setBackground(Color.BLACK);
		JPanel central = new JPanel();
		central.setLayout(new BorderLayout());
		central.setBackground(Color.BLACK);
		tUsuario = new JTextField();
		tUsuario.setFont(new Font("Arial", Font.BOLD, 60));
		dni = new JLabel("DNI");
		dni.setForeground(Color.WHITE);
		dni.setFont(new Font("Arial", Font.BOLD, 40));
		JPanel central2 = new JPanel();
		central2.setBackground(Color.BLACK);
		central2.setLayout(new BorderLayout());
		central2.add(new JLabel(new ImageIcon("icons/usuario2.png")), BorderLayout.NORTH);
		central2.add(tUsuario, BorderLayout.PAGE_END);
		central2.add(dni, BorderLayout.CENTER);
		central.add(Box.createRigidArea(new Dimension(500, 200)), BorderLayout.EAST);
		central.add(Box.createRigidArea(new Dimension(500, 200)), BorderLayout.WEST);
		//central.add(Box.createRigidArea(new Dimension(500, 200)), BorderLayout.NORTH);
		JPanel panelAuxB = new JPanel();
		panelAuxB.setLayout(new BorderLayout());
		panelAuxB.setBackground(Color.BLACK);
		panelAuxB.add(Box.createRigidArea(new Dimension(200, 400)), BorderLayout.PAGE_START);
		JButton boton1 = crearBoton("Iniciar Sesión");
		boton1.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int sol = botonInicioSesionPresionado(tUsuario.getText());
					if(sol != -1){
						Credentials.setDni(tUsuario.getText());
						if(sol == 1) {
							MainWindow mainWindow = (MainWindow) SwingUtilities.getWindowAncestor(InicioSesionPanel.this);
							mainWindow.showControlPanel();
						}else {
							MainWindow mainWindow = (MainWindow) SwingUtilities.getWindowAncestor(InicioSesionPanel.this);
							mainWindow.showControlPanelUsuarios();
						}
					}else {
						ViewUtils.showErrorMsg("Clave inexistente, Regístrese");
					}

				}

			});
		panelAuxB.add(boton1, BorderLayout.CENTER);
		
		JButton boton2 = crearBoton("Registrarse");
		
		boton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow mainWindow = (MainWindow) SwingUtilities.getWindowAncestor(InicioSesionPanel.this);
				mainWindow.showRegistroPanel();
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
	
	public void vaciarString() {
		Credentials.setDni("");
		this.tUsuario.setText("");
	}
	
	private int botonInicioSesionPresionado(String dni) {
		if(c.consultaUsuario(dni) == null) return -1;
		else return c.consultaUsuario(dni).getRol();
	}

}
